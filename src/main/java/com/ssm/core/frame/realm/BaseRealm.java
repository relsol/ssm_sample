package com.ssm.core.frame.realm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ssm.basedata.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.ssm.basedata.constants.BaseCons;
import com.ssm.basedata.entity.Department;
import com.ssm.basedata.entity.Employee;
import com.ssm.basedata.entity.LoginLog;
import com.ssm.basedata.entity.Permission;
import com.ssm.core.frame.utils.JsonUtils;
import com.ssm.core.frame.utils.ObjectUtil;
import com.ssm.core.frame.utils.SessionUtil;

public class BaseRealm extends AuthorizingRealm {

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private EmployeeCacheService employeeCacheService;
	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private EmployeeService employeeService;

	private static final Logger logger = LoggerFactory.getLogger(BaseRealm.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.
	 * apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken _authcToken = (UsernamePasswordToken) authcToken;
		String codeNumb = _authcToken.getUsername();
		if (StringUtils.isEmpty(codeNumb)) {
			throw new AccountException("Null usernames are not allowed by this realm.");
		}
		Employee currentUser = this.employeeService.findByLoginName(codeNumb);
		if (null == currentUser) {
			throw new UnknownAccountException();
		}
		String password = currentUser.getPassword();
		return new SimpleAuthenticationInfo(codeNumb, password, getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache
	 * .shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Employee currentUser = SessionUtil.getCurrentUser();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		StringBuilder builder = new StringBuilder();
		builder.append("_").append(currentUser.getId()).append(":role:").append(currentUser.getLoginName()).append("_");

		HashOperations<String, String, Object> hashOperations = this.redisTemplate.opsForHash();

		String key = "Employee:{" + currentUser.getLoginName() + "}";

		String str = (String) hashOperations.get(key, "permissionInfo");
		List<String> perStrs = null;
		try {
			perStrs = JsonUtils.readJson(str, List.class, String.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		if (ObjectUtil.isBlank(perStrs)) {
			List<Permission> permissionList = this.permissionService.findByEmployeeId(currentUser.getId());
			perStrs = new ArrayList<String>();
			for (Permission permission : permissionList) {
				perStrs.add(permission.getUri());
			}
			if (logger.isDebugEnabled()) {
				logger.debug("用户姓名:" + currentUser.getLoginName() + ", 用户角色:" + perStrs);
			}
			// 向REDIS中写入角色信息
			hashOperations.put(key, "permissionInfo", JsonUtils.objToString(perStrs));
		}
		info.addStringPermissions(perStrs);
		return info;
	}

}
