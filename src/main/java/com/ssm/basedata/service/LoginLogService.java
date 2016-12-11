package com.ssm.basedata.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.basedata.entity.LoginLog;

@Service
@Transactional(readOnly = true)
public class LoginLogService {

	/**
	 * 保存日志
	 * @param log
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void saveOrUpdate(LoginLog log) {
		// TODO Auto-generated method stub

	}

}
