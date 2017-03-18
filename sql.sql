-----------------------------------------------------
-- Export file for user HR@ORCL                    --
-- Created by Administrator on 2017/3/18, 22:56:17 --
-----------------------------------------------------

set define off
spool sql.log

prompt
prompt Creating table CF_AUTHORITY
prompt ===========================
prompt
create table HR.CF_AUTHORITY
(
  authority_id   VARCHAR2(40) default sys_guid() not null,
  authority_name VARCHAR2(100),
  operation_uri  VARCHAR2(200),
  descriptions   VARCHAR2(300)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HR.CF_AUTHORITY.authority_id
  is 'Ȩ��ID';
comment on column HR.CF_AUTHORITY.authority_name
  is 'Ȩ������';
comment on column HR.CF_AUTHORITY.operation_uri
  is 'Ȩ�޶�Ӧ����ԴURI��';
comment on column HR.CF_AUTHORITY.descriptions
  is '����';
alter table HR.CF_AUTHORITY
  add constraint AUTHORITY_PRIMARY_KEY primary key (AUTHORITY_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CF_MENU
prompt ======================
prompt
create table HR.CF_MENU
(
  menu_no     VARCHAR2(40) not null,
  menu_name   VARCHAR2(50) not null,
  menu_uri    VARCHAR2(512),
  menu_level  NUMBER(1) not null,
  is_leaf     NUMBER(1) not null,
  create_time DATE not null,
  parent_no   VARCHAR2(40),
  order_no    NUMBER,
  menu_type   VARCHAR2(20),
  iconcls     VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HR.CF_MENU.menu_no
  is '�˵���ţ�����';
comment on column HR.CF_MENU.menu_name
  is '�˵�����';
comment on column HR.CF_MENU.menu_uri
  is '�˵���Ӧ����Դ';
comment on column HR.CF_MENU.menu_level
  is '�˵�����Ϊ1��2��3��������';
comment on column HR.CF_MENU.is_leaf
  is '�Ƿ���ĩ���˵�';
comment on column HR.CF_MENU.create_time
  is '����ʱ��';
comment on column HR.CF_MENU.parent_no
  is '���˵����,root ��ʾ�˵�ģ��';
comment on column HR.CF_MENU.order_no
  is '������';
comment on column HR.CF_MENU.menu_type
  is '�˵����ͣ�URI=ϵͳ�е�jspҳ�棻COMPONENT=�Զ����Extjs���';
comment on column HR.CF_MENU.iconcls
  is 'css��ʽ';
alter table HR.CF_MENU
  add constraint PK_CF_MENU primary key (MENU_NO)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table HR.CF_MENU
  add constraint UP_CF_MENU unique (MENU_NAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CF_PRIVILEGE
prompt ===========================
prompt
create table HR.CF_PRIVILEGE
(
  privilege_id           VARCHAR2(40) default sys_guid() not null,
  privilege_master       VARCHAR2(100) not null,
  privilege_value        VARCHAR2(100) not null,
  privilege_access       VARCHAR2(100) not null,
  privilege_access_value VARCHAR2(40) not null,
  privilege_operation    VARCHAR2(40) not null,
  last_modify_time       DATE not null,
  user_id                VARCHAR2(40) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HR.CF_PRIVILEGE.privilege_master
  is '�˵�Ȩ�޷�������û���/��ɫ��';
comment on column HR.CF_PRIVILEGE.privilege_value
  is '�˵�Ȩ�޷�������û�ID/��ɫID';
comment on column HR.CF_PRIVILEGE.privilege_access
  is '�˵�������';
comment on column HR.CF_PRIVILEGE.privilege_access_value
  is '�˵���ID';
comment on column HR.CF_PRIVILEGE.privilege_operation
  is 'Ȩ��˵����һ��ָ���û��ֹ��P_ENABLE=��Ȩ�ޣ�P_DISABLE=��Ȩ��';
alter table HR.CF_PRIVILEGE
  add constraint PK_CF_PRIVILEGE primary key (PRIVILEGE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CF_ROLE
prompt ======================
prompt
create table HR.CF_ROLE
(
  role_id     VARCHAR2(40) default sys_guid() not null,
  role_name   VARCHAR2(100) not null,
  role_code   VARCHAR2(100) not null,
  description VARCHAR2(250)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HR.CF_ROLE.role_id
  is '��ɫID';
comment on column HR.CF_ROLE.role_name
  is '��ɫ����';
comment on column HR.CF_ROLE.role_code
  is '��ɫ����';
comment on column HR.CF_ROLE.description
  is '����';
alter table HR.CF_ROLE
  add constraint CF_ROLE_PRIMARY_KEY primary key (ROLE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table HR.CF_ROLE
  add constraint CF_ROLE_CODE_UQ unique (ROLE_CODE)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table HR.CF_ROLE
  add constraint CF_ROLE_NAME_UQ unique (ROLE_NAME)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CF_ROLE_AUTHORITY
prompt ================================
prompt
create table HR.CF_ROLE_AUTHORITY
(
  role_id      VARCHAR2(40),
  authority_id VARCHAR2(40)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table HR.CF_ROLE_AUTHORITY
  add constraint ROLE_AUTHORITY_UQ unique (ROLE_ID, AUTHORITY_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CF_USER
prompt ======================
prompt
create table HR.CF_USER
(
  user_id         VARCHAR2(40) default sys_guid() not null,
  login_name      VARCHAR2(50) not null,
  login_password  VARCHAR2(64),
  real_name       VARCHAR2(40),
  selfdeclare     VARCHAR2(512),
  city            VARCHAR2(30),
  address         VARCHAR2(256),
  age             NUMBER(2),
  sex             NUMBER(1),
  birthday        DATE,
  telphone        VARCHAR2(20),
  email           VARCHAR2(40),
  create_time     DATE default sysdate not null,
  login_count     NUMBER not null,
  last_login_time DATE not null,
  is_lock         NUMBER(1) default 0,
  is_expired      NUMBER(1) default 0,
  expired_time    NUMBER default 0
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HR.CF_USER.user_id
  is '�û�ID';
comment on column HR.CF_USER.login_name
  is '��¼��';
comment on column HR.CF_USER.login_password
  is '��¼����';
comment on column HR.CF_USER.real_name
  is '��ʵ��';
comment on column HR.CF_USER.selfdeclare
  is '��������';
comment on column HR.CF_USER.city
  is '���ڳ���';
comment on column HR.CF_USER.address
  is 'סַ';
comment on column HR.CF_USER.age
  is '����';
comment on column HR.CF_USER.sex
  is '�Ա� 0=�� 1=Ů';
comment on column HR.CF_USER.birthday
  is '����';
comment on column HR.CF_USER.telphone
  is '�绰����';
comment on column HR.CF_USER.email
  is '��������';
comment on column HR.CF_USER.create_time
  is '����ʱ��';
comment on column HR.CF_USER.login_count
  is '��¼����';
comment on column HR.CF_USER.last_login_time
  is '���һ�ε�¼��ʱ��';
comment on column HR.CF_USER.is_lock
  is '�Ƿ�����';
comment on column HR.CF_USER.is_expired
  is '�Ƿ����';
comment on column HR.CF_USER.expired_time
  is '�˻��������������Ϊ0����ʾ��������';
alter table HR.CF_USER
  add constraint PK_CF_USER primary key (USER_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table CF_USER_ROLE
prompt ===========================
prompt
create table HR.CF_USER_ROLE
(
  user_id VARCHAR2(40) not null,
  role_id VARCHAR2(40) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column HR.CF_USER_ROLE.user_id
  is '�û�ID';
comment on column HR.CF_USER_ROLE.role_id
  is '��ɫID';
alter table HR.CF_USER_ROLE
  add constraint USER_ROLE_UQ unique (USER_ID, ROLE_ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating sequence USER_LOGIN_COUNT_SEQUENCE
prompt ===========================================
prompt
create sequence HR.USER_LOGIN_COUNT_SEQUENCE
minvalue 0
maxvalue 9999999999999999999999
start with 0
increment by 1
nocache;


spool off
