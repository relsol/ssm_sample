/*==============================================================*/
/* Table: T_LOGIN_LOG                                           */
/*==============================================================*/
CREATE TABLE T_LOGIN_LOG
(
   C_ID                 INTEGER NOT NULL AUTO_INCREMENT COMMENT '主键',
   C_EMPLOYEE_ID        INTEGER COMMENT '用户ID',
   C_EMPLOYEE_NAME      VARCHAR(100) COMMENT '用户姓名',
   C_LOGIN_TIME         TIMESTAMP COMMENT '登陆时间',
   C_LOGIN_IP           VARCHAR(60) COMMENT '登陆IP',
   C_SYS_CODE           VARCHAR(60) COMMENT '系统编码',
   PRIMARY KEY (C_ID)
) AUTO_INCREMENT=1;

ALTER TABLE T_LOGIN_LOG COMMENT '登陆日志表';

/*==============================================================*/
/* Table: T_OPERATE_LOG                                         */
/*==============================================================*/
CREATE TABLE T_OPERATE_LOG
(
   C_ID                 INTEGER NOT NULL AUTO_INCREMENT COMMENT '主键',
   C_OPT_CONTENT        VARCHAR(1000) COMMENT '操作内容',
   C_OPT_TIME           TIMESTAMP COMMENT '操作时间',
   C_OPERATER_NAME      VARCHAR(100) COMMENT '操作人姓名',
   C_OPERATER           INTEGER COMMENT '操作人ID',
   C_OPERATE_TYPE       VARCHAR(100) COMMENT '操作类型',
   C_IP_ADDRESS         VARCHAR(60) COMMENT '操作人IP',
   PRIMARY KEY (C_ID)
) AUTO_INCREMENT=1;

ALTER TABLE T_OPERATE_LOG COMMENT '系统操作日志';

/*==============================================================*/
/* Table: T_DEPARTMENT                                          */
/*==============================================================*/
CREATE TABLE T_DEPARTMENT
(
   C_ID                 INTEGER NOT NULL AUTO_INCREMENT COMMENT '主键',
   C_CODE               VARCHAR(60) COMMENT '机构编号',
   C_NAME               VARCHAR(100) COMMENT '机构名称',
   C_TYPE               VARCHAR(60) COMMENT '机构类型',
   C_DUTY_PHONE         VARCHAR(100) COMMENT '值班电话',
   C_ADDRESS            VARCHAR(1000) COMMENT '地址',
   C_STATUS             INT COMMENT '状态',
   C_REMARK             VARCHAR(1000) COMMENT '备注',
   C_PID                INTEGER COMMENT '父ID',
   C_ORDER              INT COMMENT '排序号',
   C_CREATER_ID         INTEGER COMMENT '创建人ID',
   C_CREATER_TIME       TIMESTAMP COMMENT '创建时间',
   C_CREATER_IP         VARCHAR(60) COMMENT '创建IP',
   C_UPDATER_ID         INTEGER COMMENT '修改人ID',
   C_UPDATER_TIME       TIMESTAMP COMMENT '修改时间',
   C_UPDATER_IP         VARCHAR(60) COMMENT '修改ip',
   PRIMARY KEY (C_ID)
) AUTO_INCREMENT=1;

ALTER TABLE T_DEPARTMENT COMMENT '机构表';
/*==============================================================*/
/* Table: T_DEPARTMENT_EMP                                      */
/*==============================================================*/
CREATE TABLE T_DEPARTMENT_EMP
(
   C_ID                 INTEGER NOT NULL AUTO_INCREMENT COMMENT '主键',
   C_DEPARTMENT_ID      INTEGER COMMENT '机构ID',
   C_EMPLOYEE_ID        INTEGER COMMENT '用户ID',
   C_TITLE              VARCHAR(100) COMMENT 'title',
   C_UPDATER_ID         INTEGER COMMENT '修改人ID',
   C_UPDATER_TIME       TIMESTAMP COMMENT '修改时间',
   C_UPDATER_IP         VARCHAR(60) COMMENT '修改ip',
   PRIMARY KEY (C_ID)
) AUTO_INCREMENT=1;

ALTER TABLE T_DEPARTMENT_EMP COMMENT '机构用户中间表';

/*==============================================================*/
/* Table: T_EMPLOYEE                                            */
/*==============================================================*/
CREATE TABLE T_EMPLOYEE
(
   C_ID                 INTEGER NOT NULL AUTO_INCREMENT COMMENT '主键',
   C_LOGIN_NAME         VARCHAR(100) COMMENT '登录帐号',
   C_PASSWORD           VARCHAR(100) COMMENT '登录密码',
   C_USER_NAME          VARCHAR(100) COMMENT '姓名',
   C_SEX                INT COMMENT '性别，1-男，0-女',
   C_PHONE              VARCHAR(60) COMMENT '电话',
   C_MOBILE             VARCHAR(60) COMMENT '手机',
   C_ADDRESS            VARCHAR(100) COMMENT '住址',
   C_CANCEL             INT COMMENT '注销  0：未注销，1：已注销',
   C_SYSTEM_USER        VARCHAR(60) COMMENT '是否是系统用户',
   C_SAFE_LEVEL_ID      INTEGER COMMENT '安全级别ID',
   C_SAFE_LEVEL         VARCHAR(100) COMMENT '安全级别',
   C_IP_ADDRESS         VARCHAR(60) COMMENT 'IP地址',
   C_JPUSH_ID           INTEGER COMMENT '激光推送ID',
   C_CREATER_ID         INTEGER COMMENT '创建人ID',
   C_CREATER_TIME       TIMESTAMP COMMENT '创建时间',
   C_UPDATER_ID         INTEGER COMMENT '修改人ID',
   C_UPDATER_TIME       TIMESTAMP COMMENT '修改时间',
   C_UPDATER_IP         VARCHAR(60) COMMENT '修改ip',
   PRIMARY KEY (C_ID)
) AUTO_INCREMENT=1;

ALTER TABLE T_EMPLOYEE COMMENT '用户表';

/*==============================================================*/
/* Table: T_ROLE_EMP                                            */
/*==============================================================*/
CREATE TABLE T_ROLE_EMP
(
   C_ID                 INTEGER NOT NULL AUTO_INCREMENT COMMENT '主键',
   C_ROLE_ID            INTEGER COMMENT '角色ID',
   C_EMPLOYEE_ID        INTEGER COMMENT '用户ID',
   PRIMARY KEY (C_ID)
) AUTO_INCREMENT=1;

ALTER TABLE T_ROLE_EMP COMMENT '角色用户中间表';

/*==============================================================*/
/* Table: T_ROLE                                                */
/*==============================================================*/
CREATE TABLE T_ROLE
(
   C_ID                 INTEGER NOT NULL AUTO_INCREMENT COMMENT '主键',
   C_NAME               VARCHAR(100) COMMENT '角色名称',
   C_CODE               VARCHAR(60) COMMENT '角色编号',
   C_STATUS             INT COMMENT '状态 1-启用，0-停用',
   C_REMARK             VARCHAR(1000) COMMENT '备注',
   C_PROJECT_ID         INTEGER COMMENT '项目ID',
   C_CREATER_ID         INTEGER COMMENT '创建人ID',
   C_CREATER_TIME       TIMESTAMP COMMENT '创建时间',
   C_CREATER_IP         VARCHAR(60) COMMENT '创建IP',
   C_UPDATER_ID         INTEGER COMMENT '修改人ID',
   C_UPDATER_TIME       TIMESTAMP COMMENT '修改时间',
   C_UPDATER_IP         VARCHAR(60) COMMENT '修改ip',
   PRIMARY KEY (C_ID)
) AUTO_INCREMENT=1;

ALTER TABLE T_ROLE COMMENT '角色表';

/*==============================================================*/
/* Table: T_ROLE_PERMISSION                                     */
/*==============================================================*/
CREATE TABLE T_ROLE_PERMISSION
(
   C_ID                 INTEGER NOT NULL AUTO_INCREMENT COMMENT '主键',
   C_PERMISSION_ID      INTEGER COMMENT '资源ID',
   C_ROLE_ID            INTEGER COMMENT '角色ID',
   PRIMARY KEY (C_ID)
) AUTO_INCREMENT=1;

ALTER TABLE T_ROLE_PERMISSION COMMENT '角色资源中间表';

/*==============================================================*/
/* Table: T_PERMISSION                                          */
/*==============================================================*/
CREATE TABLE T_PERMISSION
(
   C_ID                 INTEGER NOT NULL AUTO_INCREMENT COMMENT '主键',
   C_NAME               VARCHAR(100) COMMENT '资源名称',
   C_URI                VARCHAR(100) COMMENT 'uri',
   C_PID                INTEGER COMMENT '上级资源ID',
   C_REMARK             VARCHAR(300) COMMENT '备注',
   C_ORDER              INT COMMENT '排序',
   C_LEVEL              INT COMMENT '级别1:导航  2:菜单  3:button',
   C_PROJECT_ID         INTEGER COMMENT '项目ID',
   PRIMARY KEY (C_ID)
) AUTO_INCREMENT=1;

ALTER TABLE T_PERMISSION COMMENT '资源表';

/*==============================================================*/
/* Table: T_DICTIONARY                                          */
/*==============================================================*/
CREATE TABLE T_DICTIONARY
(
   C_ID                 INTEGER NOT NULL AUTO_INCREMENT COMMENT '主键',
   C_CODE               VARCHAR(100) COMMENT '编码',
   C_NAME               VARCHAR(100) COMMENT '名称',
   C_REMARK             VARCHAR(300) COMMENT '备注',
   C_PID                INTEGER COMMENT '父ID',
   PRIMARY KEY (C_ID)
) AUTO_INCREMENT=1;

ALTER TABLE T_DICTIONARY COMMENT '数据字典';


/*==============================================================*/
/* Table: T_DICTIONARY_ITEM                                     */
/*==============================================================*/
CREATE TABLE T_DICTIONARY_ITEM
(
   C_ID                 INTEGER NOT NULL AUTO_INCREMENT COMMENT '主键',
   C_DICTIONARY_ID      INTEGER COMMENT '字典ID',
   C_NAME               VARCHAR(100) COMMENT '属性名称',
   C_STATUS             INT COMMENT '状态 1-启用，0-停用',
   C_ORDER              INT COMMENT '排序号',
   C_REMARK             VARCHAR(300) COMMENT '备注',
   C_CREATER_ID         INTEGER COMMENT '创建人ID',
   C_CREATER_TIME       TIMESTAMP COMMENT '创建时间',
   C_CREATER_IP         VARCHAR(60) COMMENT '创建IP',
   C_UPDATER_ID         INTEGER COMMENT '修改人ID',
   C_UPDATER_TIME       TIMESTAMP COMMENT '修改时间',
   C_UPDATER_IP         VARCHAR(60) COMMENT '修改ip',
   PRIMARY KEY (C_ID)
) AUTO_INCREMENT=1;

