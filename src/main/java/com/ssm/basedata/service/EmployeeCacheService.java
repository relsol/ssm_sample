package com.ssm.basedata.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.basedata.constants.PermissionCons;
import com.ssm.basedata.entity.Employee;
import com.ssm.basedata.entity.Permission;
import com.ssm.core.frame.utils.JsonUtils;

@Service
@Transactional(readOnly = true)
public class EmployeeCacheService {

	private Logger logger = LoggerFactory.getLogger(EmployeeCacheService.class.getName());

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private DepartmentEmpService departmentEmpService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	/*
	 * @Resource private DictionaryService dictionaryService;
	 * 
	 * @Resource private DictionaryItemService dictionaryItemService;
	 * 
	 * @Resource private PersonnelService personnelService;
	 */
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public Employee initRedisData(String userName) {
		if (StringUtils.isBlank(userName)) {
			throw new IllegalArgumentException("缺少必要参数:userName.");
		}
		Employee currentUser = this.employeeService.findByLoginName(userName);

		try {
			String key = "Employee:{" + currentUser.getLoginName() + "}";
			HashOperations<String, String, Object> hashOperations = this.redisTemplate.opsForHash();

			// 向REDIS中写入操作权限信息
			redisTemplate.delete(key);
			if (!hashOperations.hasKey(key, "permissionInfo")) {
				List<Permission> permissionList = this.permissionService.findByEmployeeId(currentUser.getId());
				List<String> permissionStr = new ArrayList<String>();
				for (Permission per : permissionList) {
					permissionStr.add(per.getUri());
				}
				hashOperations.put(key, "permissionInfo", JsonUtils.objToString(permissionStr));
				hashOperations.put(key, "permissionList", JsonUtils.objToString(permissionList));
			}

			/*
			 * // 用户的照片 try { Personnel personnel =
			 * personnelService.findByEmployeeId(currentUser.getId());
			 * if(!hashOperations.hasKey(key, "employeePhoto") &&
			 * personnel!=null){
			 * if(StringUtils.isNotBlank(personnel.getPhotoUrl())){
			 * hashOperations.put(key, "employeePhoto",
			 * GlobalConfigure.HTTP_FILE_SERVER_PATH + personnel.getPhotoUrl());
			 * } } } catch (Exception e) { logger.error(e.getMessage(), e); }
			 * 
			 * // 向Redis中写入字典信息 ListOperations<String, Object> listOperations =
			 * this.redisTemplate.opsForList(); for (Map.Entry<String, String>
			 * entry : DictionaryCons.DICTIONARYCONSMAP.entrySet()) {
			 * this.redisTemplate.delete(entry.getKey()); List<DictionaryItem>
			 * dictionaryItems =
			 * this.dictionaryItemService.findChildByCode(entry.getKey()); if
			 * (null != dictionaryItems && !dictionaryItems.isEmpty())
			 * listOperations.leftPush(entry.getKey(), dictionaryItems); }
			 */
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return currentUser;
	}

	@SuppressWarnings("unchecked")
	public List<Permission> getRedisMenuUrl(String userName) {

		if (StringUtils.isBlank(userName)) {
			throw new IllegalArgumentException("缺少必要参数:userName.");
		}
		Employee currentUser = this.employeeService.findByLoginName(userName);

		String key = "Employee:{" + currentUser.getLoginName() + "}";
		String problemKey = "problem_permission_key";
		HashOperations<String, String, Object> hashOperations = this.redisTemplate.opsForHash();
		// 获取菜单集合
		if (hashOperations.hasKey(key, problemKey)) {
			List<Permission> perList = (List<Permission>) hashOperations.get(key, problemKey);
			return perList;
		} else {
			List<Permission> perList = this.permissionService.findByEmpAndLevel(currentUser.getId(),
					PermissionCons.MENU);
			hashOperations.put(key, problemKey, perList);
			return perList;
		}
	}
}
