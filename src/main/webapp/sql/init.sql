insert into t_permission (C_ID, C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
values (1, '系统管理', '@system', 0, '', 1, 1);

	insert into t_permission (C_ID, C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
		values (2, '机构管理', '/department/showTree.html', 1, '', 1, 2);
		insert into t_permission (C_ID, C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
			values (3, '查询', '/department/showTree.html', 2, '', 1, 3);
		insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
			values (4, '新增编辑', '/department/showDepartment.html', 2, '', 2, 3);
		insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
			values (5, '删除', '/department/delDepartment.html', 2, '', 3, 3);
		insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
			values (6, '更改状态', '/department/changeStatus.html', 2, '', 4, 3);

	insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
		values (7, '用户管理', '/employee/main.html', 1, '', 2, 2);
		insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
			values (8, '查询', '/employee/findPage.html', 7, '', 1, 3);
		insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
			values (9, '查看', '/employee/showEmployee.html', 7, '', 2, 3);
		insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
			values (10, '新增编辑', '/employee/toSaveOrAddEmployee.html', 7, '', 3, 3);
		insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
			values (11, '激活注销', '/employee/changeStatus.html', 7, '', 4, 3);
	
	insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
		values (12, '角色管理', '/role/main.html', 1, '', 3, 2);
		insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
			values (13, '新增编辑角色', '/role/toAddRole.html', 12, '', 2, 3);
		insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
			values (14, '停启用角色', '/role/openOrCloseRole.html', 12, '', 3, 3);
		insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
			values (15, '角色赋权', '/role/toShowPermission.html', 12, '', 4, 3);
		
		
		
insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
values (16, '基础管理', '@basicmanage', 1, '', 2, 1);
	
	insert into t_permission (C_ID,  C_NAME, C_URI, C_PID, C_REMARK, C_ORDER, C_LEVEL)
	values (17, '数据字典', '/dictionary/list.html', 16, '', 4, 2);

-- 添加管理员
insert into t_employee (c_id, c_login_name, c_password, c_user_name,  c_system_user) values
(1, 'admin', '81dc9bdb52d04dc20036dbd8313ed055', '系统管理员', '1');



-- 添加管理员角色
insert into t_role (c_id, c_name, c_status) values
(1, '系统管理员', 1);

-- 添加人员和角色的对应关系
insert into t_role_emp (c_id, c_employee_id, c_role_id) values
(1, 1, 1);


-- 附权限
insert into t_role_permission(c_id, c_role_id, c_permission_id) VALUES (null, 1, 1), (null, 1, 2),(null, 1, 3), (null, 1, 4),(null, 1, 5), (null, 1, 6),(null, 1, 7), (null, 1, 8),(null, 1, 9), (null, 1, 10),(null, 1, 11), (null, 1, 12),(null, 1, 13), (null, 1, 14),(null, 1, 15), (null, 1, 16),(null, 1, 17)

