/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : ctf_manager

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 08/08/2022 02:29:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_base_class
-- ----------------------------
DROP TABLE IF EXISTS `gen_base_class`;
CREATE TABLE `gen_base_class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `package_name` varchar(200) DEFAULT NULL COMMENT '基类包名',
  `code` varchar(200) DEFAULT NULL COMMENT '基类编码',
  `fields` varchar(500) DEFAULT NULL COMMENT '基类字段，多个用英文逗号分隔',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='基类管理';

-- ----------------------------
-- Records of gen_base_class
-- ----------------------------
BEGIN;
INSERT INTO `gen_base_class` (`id`, `package_name`, `code`, `fields`, `remark`, `create_time`) VALUES (3, 'net.maku.framework.common.entity', 'BaseEntity', 'id,creator,create_time,updater,update_time,version,deleted', '使用该基类，则需要表里有这些字段', '2022-08-05 01:48:11');
COMMIT;

-- ----------------------------
-- Table structure for gen_datasource
-- ----------------------------
DROP TABLE IF EXISTS `gen_datasource`;
CREATE TABLE `gen_datasource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `db_type` varchar(200) DEFAULT NULL COMMENT '数据库类型 MySQL、Oracle、PostgreSQL',
  `conn_name` varchar(200) NOT NULL COMMENT '连接名',
  `conn_url` varchar(500) DEFAULT NULL COMMENT 'URL',
  `username` varchar(200) DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据源管理';

-- ----------------------------
-- Records of gen_datasource
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for gen_field_type
-- ----------------------------
DROP TABLE IF EXISTS `gen_field_type`;
CREATE TABLE `gen_field_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `column_type` varchar(200) DEFAULT NULL COMMENT '字段类型',
  `attr_type` varchar(200) DEFAULT NULL COMMENT '属性类型',
  `package_name` varchar(200) DEFAULT NULL COMMENT '属性包名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `column_type` (`column_type`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COMMENT='字段类型管理';

-- ----------------------------
-- Records of gen_field_type
-- ----------------------------
BEGIN;
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (1, 'datetime', 'Date', 'java.util.Date', '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (2, 'date', 'Date', 'java.util.Date', '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (3, 'tinyint', 'Integer', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (4, 'smallint', 'Integer', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (5, 'mediumint', 'Integer', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (6, 'int', 'Integer', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (7, 'integer', 'Integer', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (8, 'bigint', 'Long', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (9, 'float', 'Float', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (10, 'double', 'Double', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (11, 'decimal', 'BigDecimal', 'java.math.BigDecimal', '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (12, 'bit', 'Boolean', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (13, 'char', 'String', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (14, 'varchar', 'String', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (15, 'tinytext', 'String', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (16, 'text', 'String', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (17, 'mediumtext', 'String', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (18, 'longtext', 'String', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (19, 'timestamp', 'Date', 'java.util.Date', '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (20, 'NUMBER', 'Integer', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (21, 'BINARY_INTEGER', 'Integer', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (22, 'BINARY_FLOAT', 'Float', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (23, 'BINARY_DOUBLE', 'Double', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (24, 'VARCHAR2', 'String', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (25, 'NVARCHAR', 'String', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (26, 'NVARCHAR2', 'String', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (27, 'CLOB', 'String', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (28, 'int8', 'Long', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (29, 'int4', 'Integer', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (30, 'int2', 'Integer', NULL, '2022-08-05 01:48:11');
INSERT INTO `gen_field_type` (`id`, `column_type`, `attr_type`, `package_name`, `create_time`) VALUES (31, 'numeric', 'BigDecimal', 'java.math.BigDecimal', '2022-08-05 01:48:11');
COMMIT;

-- ----------------------------
-- Table structure for gen_table_field
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_field`;
CREATE TABLE `gen_table_field` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `table_id` bigint(20) DEFAULT NULL COMMENT '表ID',
  `table_name` varchar(200) DEFAULT NULL COMMENT '表名',
  `column_name` varchar(200) DEFAULT NULL COMMENT '列名',
  `column_type` varchar(200) DEFAULT NULL COMMENT '类型',
  `column_comment` varchar(200) DEFAULT NULL COMMENT '列说明',
  `attr_name` varchar(200) DEFAULT NULL COMMENT '属性名',
  `attr_type` varchar(200) DEFAULT NULL COMMENT '属性类型',
  `package_name` varchar(200) DEFAULT NULL COMMENT '属性包名',
  `is_pk` tinyint(4) DEFAULT NULL COMMENT '是否主键 0：否  1：是',
  `is_required` tinyint(4) DEFAULT NULL COMMENT '是否必填 0：否  1：是',
  `is_form` tinyint(4) DEFAULT NULL COMMENT '是否表单字段 0：否  1：是',
  `is_list` tinyint(4) DEFAULT NULL COMMENT '是否列表字段 0：否  1：是',
  `is_query` tinyint(4) DEFAULT NULL COMMENT '是否查询字段 0：否  1：是',
  `query_type` varchar(200) DEFAULT NULL COMMENT '查询方式',
  `form_type` varchar(200) DEFAULT NULL COMMENT '表单类型',
  `dict_name` varchar(200) DEFAULT NULL COMMENT '字典名称',
  `validator_type` varchar(200) DEFAULT NULL COMMENT '效验方式',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `table_name` (`table_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='代码生成表列';

-- ----------------------------
-- Records of gen_table_field
-- ----------------------------
BEGIN;
INSERT INTO `gen_table_field` (`id`, `table_id`, `table_name`, `column_name`, `column_type`, `column_comment`, `attr_name`, `attr_type`, `package_name`, `is_pk`, `is_required`, `is_form`, `is_list`, `is_query`, `query_type`, `form_type`, `dict_name`, `validator_type`, `sort`, `create_time`) VALUES (1, 1, 'sys_dept', 'id', 'bigint', '主键', 'id', 'Long', NULL, 1, 0, 1, 1, 0, '=', 'text', NULL, NULL, 0, NULL);
INSERT INTO `gen_table_field` (`id`, `table_id`, `table_name`, `column_name`, `column_type`, `column_comment`, `attr_name`, `attr_type`, `package_name`, `is_pk`, `is_required`, `is_form`, `is_list`, `is_query`, `query_type`, `form_type`, `dict_name`, `validator_type`, `sort`, `create_time`) VALUES (2, 1, 'sys_dept', 'name', 'varchar', '部门名称', 'name', 'String', NULL, 0, 0, 1, 1, 0, '=', 'text', NULL, NULL, 1, NULL);
INSERT INTO `gen_table_field` (`id`, `table_id`, `table_name`, `column_name`, `column_type`, `column_comment`, `attr_name`, `attr_type`, `package_name`, `is_pk`, `is_required`, `is_form`, `is_list`, `is_query`, `query_type`, `form_type`, `dict_name`, `validator_type`, `sort`, `create_time`) VALUES (3, 1, 'sys_dept', 'parent_id', 'bigint', '父节点id', 'parentId', 'Long', NULL, 0, 0, 1, 1, 0, '=', 'text', NULL, NULL, 2, NULL);
INSERT INTO `gen_table_field` (`id`, `table_id`, `table_name`, `column_name`, `column_type`, `column_comment`, `attr_name`, `attr_type`, `package_name`, `is_pk`, `is_required`, `is_form`, `is_list`, `is_query`, `query_type`, `form_type`, `dict_name`, `validator_type`, `sort`, `create_time`) VALUES (4, 1, 'sys_dept', 'tree_path', 'varchar', '父节点id路径', 'treePath', 'String', NULL, 0, 0, 1, 1, 0, '=', 'text', NULL, NULL, 3, NULL);
INSERT INTO `gen_table_field` (`id`, `table_id`, `table_name`, `column_name`, `column_type`, `column_comment`, `attr_name`, `attr_type`, `package_name`, `is_pk`, `is_required`, `is_form`, `is_list`, `is_query`, `query_type`, `form_type`, `dict_name`, `validator_type`, `sort`, `create_time`) VALUES (5, 1, 'sys_dept', 'sort', 'int', '显示顺序', 'sort', 'Integer', NULL, 0, 0, 1, 1, 0, '=', 'text', NULL, NULL, 4, NULL);
INSERT INTO `gen_table_field` (`id`, `table_id`, `table_name`, `column_name`, `column_type`, `column_comment`, `attr_name`, `attr_type`, `package_name`, `is_pk`, `is_required`, `is_form`, `is_list`, `is_query`, `query_type`, `form_type`, `dict_name`, `validator_type`, `sort`, `create_time`) VALUES (6, 1, 'sys_dept', 'status', 'tinyint', '状态(1:正常;0:禁用)', 'status', 'Integer', NULL, 0, 0, 1, 1, 0, '=', 'text', NULL, NULL, 5, NULL);
INSERT INTO `gen_table_field` (`id`, `table_id`, `table_name`, `column_name`, `column_type`, `column_comment`, `attr_name`, `attr_type`, `package_name`, `is_pk`, `is_required`, `is_form`, `is_list`, `is_query`, `query_type`, `form_type`, `dict_name`, `validator_type`, `sort`, `create_time`) VALUES (7, 1, 'sys_dept', 'deleted', 'tinyint', '逻辑删除标识(1:已删除;0:未删除)', 'deleted', 'Integer', NULL, 0, 0, 1, 1, 0, '=', 'text', NULL, NULL, 6, NULL);
INSERT INTO `gen_table_field` (`id`, `table_id`, `table_name`, `column_name`, `column_type`, `column_comment`, `attr_name`, `attr_type`, `package_name`, `is_pk`, `is_required`, `is_form`, `is_list`, `is_query`, `query_type`, `form_type`, `dict_name`, `validator_type`, `sort`, `create_time`) VALUES (8, 1, 'sys_dept', 'create_time', 'datetime', '创建时间', 'createTime', 'Date', 'java.util.Date', 0, 0, 1, 1, 0, '=', 'text', NULL, NULL, 7, NULL);
INSERT INTO `gen_table_field` (`id`, `table_id`, `table_name`, `column_name`, `column_type`, `column_comment`, `attr_name`, `attr_type`, `package_name`, `is_pk`, `is_required`, `is_form`, `is_list`, `is_query`, `query_type`, `form_type`, `dict_name`, `validator_type`, `sort`, `create_time`) VALUES (9, 1, 'sys_dept', 'update_time', 'datetime', '更新时间', 'updateTime', 'Date', 'java.util.Date', 0, 0, 1, 1, 0, '=', 'text', NULL, NULL, 8, NULL);
COMMIT;

-- ----------------------------
-- Table structure for gen_table_info
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_info`;
CREATE TABLE `gen_table_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `table_name` varchar(200) DEFAULT NULL COMMENT '表名',
  `class_name` varchar(200) DEFAULT NULL COMMENT '类名',
  `table_comment` varchar(200) DEFAULT NULL COMMENT '功能名',
  `author` varchar(200) DEFAULT NULL COMMENT '作者',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `package_name` varchar(200) DEFAULT NULL COMMENT '项目包名',
  `version` varchar(200) DEFAULT NULL COMMENT '项目版本号',
  `backend_path` varchar(500) DEFAULT NULL COMMENT '后端生成路径',
  `frontend_path` varchar(500) DEFAULT NULL COMMENT '前端生成路径',
  `module_name` varchar(200) DEFAULT NULL COMMENT '模块名',
  `sub_module_name` varchar(200) DEFAULT NULL COMMENT '子模块名',
  `datasource_id` bigint(20) DEFAULT NULL COMMENT '数据源ID',
  `baseclass_id` bigint(20) DEFAULT NULL COMMENT '基类ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='代码生成表';

-- ----------------------------
-- Records of gen_table_info
-- ----------------------------
BEGIN;
INSERT INTO `gen_table_info` (`id`, `table_name`, `class_name`, `table_comment`, `author`, `email`, `package_name`, `version`, `backend_path`, `frontend_path`, `module_name`, `sub_module_name`, `datasource_id`, `baseclass_id`, `create_time`) VALUES (1, 'sys_dept', 'SysDept', '部门表', 'H.m', 'hackmgod@foxmail.com', 'com.ctf', '1.0.0', 'D:\\generator\\fast-boot\\fast-server', 'D:\\generator\\fast-admin', NULL, NULL, 0, NULL, NULL);
COMMIT;



-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) DEFAULT '' COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父节点id',
  `tree_path` varchar(255) DEFAULT '' COMMENT '父节点id路径',
  `sort` int(11) DEFAULT '0' COMMENT '显示顺序',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态(1:正常;0:禁用)',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除标识(1:已删除;0:未删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` (`id`, `name`, `parent_id`, `tree_path`, `sort`, `status`, `deleted`, `create_time`, `update_time`, `tenant_id`) VALUES (1, '技术', 0, '0', 1, 1, 0, NULL, NULL, NULL);
INSERT INTO `sys_dept` (`id`, `name`, `parent_id`, `tree_path`, `sort`, `status`, `deleted`, `create_time`, `update_time`, `tenant_id`) VALUES (2, '研发部门', 1, '0,1', 1, 1, 0, NULL, '2022-04-19 12:46:37', NULL);
INSERT INTO `sys_dept` (`id`, `name`, `parent_id`, `tree_path`, `sort`, `status`, `deleted`, `create_time`, `update_time`, `tenant_id`) VALUES (3, '测试部门', 1, '0,1', 2, 1, 0, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_code` varchar(64) DEFAULT NULL COMMENT '字典类型编码',
  `name` varchar(50) DEFAULT '' COMMENT '字典项名称',
  `value` varchar(50) DEFAULT '' COMMENT '字典项值',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `status` tinyint(4) DEFAULT '0' COMMENT '状态(1:正常;0:禁用)',
  `defaulted` tinyint(4) DEFAULT '0' COMMENT '是否默认(1:是;0:否)',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (1, 'gender', '男', '1', 1, 1, 0, NULL, '2019-05-05 13:07:52', '2022-06-12 23:20:39', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (2, 'gender', '女', '2', 2, 1, 0, NULL, '2019-04-19 11:33:00', '2019-07-02 14:23:05', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (3, 'gender', '未知', '0', 1, 1, 0, NULL, '2020-10-17 08:09:31', '2020-10-17 08:09:31', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (6, 'grant_type', '密码模式', 'password', 1, 1, 0, NULL, '2020-10-17 09:11:52', '2021-01-31 09:48:18', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (7, 'grant_type', '授权码模式', 'authorization_code', 1, 1, 0, NULL, '2020-10-17 09:12:15', '2020-12-14 10:11:00', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (8, 'grant_type', '客户端模式', 'client_credentials', 1, 1, 0, NULL, '2020-10-17 09:12:36', '2020-12-14 10:11:00', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (9, 'grant_type', '刷新模式', 'refresh_token', 1, 1, 0, NULL, '2020-10-17 09:12:57', '2021-01-08 17:33:12', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (10, 'grant_type', '简化模式', 'implicit', 1, 1, 0, NULL, '2020-10-17 09:13:23', '2020-12-14 10:11:00', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (11, 'micro_service', '系统服务', 'ctf-admin', 1, 1, 0, NULL, '2021-06-17 00:14:12', '2021-06-17 00:14:12', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (12, 'micro_service', '会员服务', 'ctf-ums', 2, 1, 0, NULL, '2021-06-17 00:15:06', '2021-06-17 00:15:06', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (13, 'micro_service', '商品服务', 'ctf-pms', 3, 1, 0, NULL, '2021-06-17 00:15:26', '2021-06-17 00:16:18', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (14, 'micro_service', '订单服务', 'ctf-oms', 4, 1, 0, NULL, '2021-06-17 00:15:40', '2021-06-17 00:16:10', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (15, 'micro_service', '营销服务', 'ctf-sms', 5, 1, 0, NULL, '2021-06-17 00:16:01', '2021-06-17 00:16:01', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (16, 'request_method', '不限', '*', 1, 1, 0, NULL, '2021-06-17 00:18:34', '2021-06-17 00:18:34', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (17, 'request_method', 'GET', 'GET', 2, 1, 0, NULL, '2021-06-17 00:18:55', '2021-06-17 00:18:55', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (18, 'request_method', 'POST', 'POST', 3, 1, 0, NULL, '2021-06-17 00:19:06', '2021-06-17 00:19:06', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (19, 'request_method', 'PUT', 'PUT', 4, 1, 0, NULL, '2021-06-17 00:19:17', '2021-06-17 00:19:17', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (20, 'request_method', 'DELETE', 'DELETE', 5, 1, 0, NULL, '2021-06-17 00:19:30', '2021-06-17 00:19:30', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (21, 'request_method', 'PATCH', 'PATCH', 6, 1, 0, NULL, '2021-06-17 00:19:42', '2021-06-17 00:19:42', NULL);
INSERT INTO `sys_dict_item` (`id`, `type_code`, `name`, `value`, `sort`, `status`, `defaulted`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (22, 'grant_type', '验证码', 'captcha', 1, 1, 0, '', '2022-05-28 11:44:28', '2022-05-28 11:44:28', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `name` varchar(50) DEFAULT '' COMMENT '类型名称',
  `code` varchar(50) DEFAULT '' COMMENT '类型编码',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态(0:正常;1:禁用)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `type_code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_type` (`id`, `name`, `code`, `status`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (1, '性别', 'gender', 1, NULL, '2019-12-06 19:03:32', '2022-06-12 16:21:28', NULL);
INSERT INTO `sys_dict_type` (`id`, `name`, `code`, `status`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (2, '授权方式', 'grant_type', 1, NULL, '2020-10-17 08:09:50', '2021-01-31 09:48:24', NULL);
INSERT INTO `sys_dict_type` (`id`, `name`, `code`, `status`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (3, '微服务列表', 'micro_service', 1, NULL, '2021-06-17 00:13:43', '2021-06-17 00:17:22', NULL);
INSERT INTO `sys_dict_type` (`id`, `name`, `code`, `status`, `remark`, `create_time`, `update_time`, `tenant_id`) VALUES (4, '请求方式', 'request_method', 1, NULL, '2021-06-17 00:18:07', '2021-06-17 00:18:07', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(64) DEFAULT '' COMMENT '菜单名称',
  `path` varchar(128) DEFAULT '' COMMENT '路由路径(浏览器地址栏路径)',
  `component` varchar(128) DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
  `icon` varchar(64) DEFAULT '' COMMENT '菜单图标',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `visible` tinyint(1) DEFAULT '1' COMMENT '状态(0:禁用;1:开启)',
  `redirect` varchar(128) DEFAULT '' COMMENT '跳转路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `type` tinyint(4) DEFAULT NULL COMMENT '菜单类型(1:菜单;2:目录;3:外链)',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (1, 0, '系统管理', '/system', 'Layout', 'system', 1, 1, '/system/user', '2021-08-28 09:12:21', '2021-08-28 09:12:21', 2, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (2, 1, '用户管理', 'user', 'system/user/index', 'user', 1, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (3, 1, '角色管理', 'role', 'system/role/index', 'role', 2, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (4, 1, '菜单管理', 'cmenu', 'system/menu/index', 'menu', 3, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (5, 1, '部门管理', 'dept', 'system/dept/index', 'tree', 4, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (6, 1, '字典管理', 'dict', 'system/dict/index', 'dict', 5, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (7, 1, '客户端管理', 'client', 'system/client/index', 'client', 6, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (9, 0, '营销管理', '/sms', 'Layout', 'number', 5, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 2, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (10, 9, '广告列表', 'advert', 'sms/advert/index', 'advert', 1, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (11, 0, '商品管理', '/pms', 'Layout', 'goods', 2, 1, '/pms/goods', '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (12, 11, '商品列表', 'goods', 'pms/goods/index', 'goods-list', 1, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (13, 0, '订单管理', '/oms', 'Layout', 'shopping', 3, 1, '/oms/order', '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (14, 13, '订单列表', 'order', 'oms/order/index', 'order', 1, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (15, 0, '会员管理', '/ums', 'Layout', 'user', 4, 1, '/ums/member', '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (16, 15, '会员列表', 'member', 'ums/member/index', 'peoples', 1, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (17, 11, '品牌管理', 'brand', 'pms/brand/index', 'brand', 5, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (18, 11, '商品分类', 'category', 'pms/category/index', 'menu', 3, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (19, 11, '商品上架', 'goods-detail', 'pms/goods/detail', 'publish', 2, 1, NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (20, 0, '多级菜单', '/multi-level-menu', 'Layout', 'nested', 7, 1, '/nested/level1/level2', '2022-02-16 23:11:00', '2022-02-16 23:11:00', 2, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (21, 20, '菜单一级', 'nested_level1_index', 'nested/level1/index', '', 1, 1, '/nested/level1/level2', '2022-02-16 23:13:38', '2022-02-16 23:13:38', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (22, 21, '菜单二级', 'nested_level1_level2_index', 'nested/level1/level2/index', '', 1, 1, '/nested/level1/level2/level3', '2022-02-16 23:14:23', '2022-02-16 23:14:23', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (23, 22, '菜单三级-1', 'nested_level1_level2_level3_index1', 'nested/level1/level2/level3/index1', '', 1, 1, '', '2022-02-16 23:14:51', '2022-02-16 23:14:51', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (24, 22, '菜单三级-2', 'nested_level1_level2_level3_index2', 'nested/level1/level2/level3/index2', '', 2, 1, '', '2022-02-16 23:15:08', '2022-02-16 23:15:08', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (26, 0, '外部链接', '/external-link', 'Layout', 'link', 9, 1, 'noredirect', '2022-02-17 22:51:20', '2022-02-17 22:51:20', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (30, 26, 'document', 'www.baidu.com', '', 'link', 1, 1, '', '2022-02-18 00:01:40', '2022-02-18 00:01:40', 3, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (32, 0, '实验室', '/lab', 'Layout', 'lab', 8, 1, 'noredirect', '2022-04-19 09:35:38', '2022-04-19 09:35:38', 2, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (33, 32, 'Seata', 'seata', 'lab/seata/index', 'security', 1, 1, '', '2022-04-19 09:35:38', '2022-04-19 09:35:38', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (34, 32, 'RabbitMQ', 'rabbitmq', 'lab/rabbit/index', 'rabbitmq', 2, 1, '', '2022-04-19 09:38:25', '2022-04-19 09:38:25', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (37, 9, '优惠券列表', 'coupon', 'sms/coupon/index', 'input', 2, 1, '', '2022-05-29 00:24:07', '2022-05-29 00:24:07', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (39, 32, 'Sentinel', 'sentinel', 'lab/sentinel/index', 'security', 2, 1, '', '2022-07-23 09:52:41', '2022-07-23 09:52:41', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (40, 0, '开发工具', '/gen', 'Layout', 'guide', 10, 1, 'noredirect', '2022-04-19 09:35:38', '2022-04-19 09:35:38', 2, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (41, 40, '基类管理', 'baseClass', 'gen/base-class/index', 'nested', 1, 1, '', '2022-04-19 09:35:38', '2022-04-19 09:35:38', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (42, 40, '数据源管理', 'dataSource', 'gen/datasource/index', 'nested', 2, 1, '', '2022-04-19 09:35:38', '2022-04-19 09:35:38', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (43, 40, '字段类型映射', 'fieldType', 'gen/field-type/index', 'nested', 3, 1, '', '2022-04-19 09:35:38', '2022-04-19 09:35:38', 1, NULL);
INSERT INTO `sys_menu` (`id`, `parent_id`, `name`, `path`, `component`, `icon`, `sort`, `visible`, `redirect`, `create_time`, `update_time`, `type`, `tenant_id`) VALUES (44, 40, '代码生成', 'generator', 'gen/generator/index', 'nested', 4, 1, '', '2022-04-19 09:35:38', '2022-04-19 09:35:38', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_oauth_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client`;
CREATE TABLE `sys_oauth_client` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_oauth_client
-- ----------------------------
BEGIN;
INSERT INTO `sys_oauth_client` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('client', '', '123456', 'all', 'password,refresh_token', NULL, NULL, 3600, 7200, NULL, 'true');
INSERT INTO `sys_oauth_client` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('ctf-admin', '', '123456', 'all', 'password,client_credentials,refresh_token,authorization_code', NULL, '', 3600, 7200, NULL, 'true');
INSERT INTO `sys_oauth_client` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('ctf-admin-web', '', '123456', 'all', 'password,refresh_token,captcha', NULL, '', 3600, 7200, NULL, 'true');
INSERT INTO `sys_oauth_client` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('ctf-app', '', '123456', 'all', 'sms_code,refresh_token', NULL, NULL, 3600, 7200, NULL, 'true');
INSERT INTO `sys_oauth_client` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('ctf-mall-app', '', '123456', 'all', 'authorization_code,password,refresh_token,implicit,wechat,refresh_token', NULL, NULL, 3600, 7200, NULL, 'true');
INSERT INTO `sys_oauth_client` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('ctf-weapp', '', '123456', 'all', 'wechat,refresh_token', NULL, NULL, 3600, 7200, NULL, 'true');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) DEFAULT NULL COMMENT '权限名称',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单模块ID\r\n',
  `url_perm` varchar(128) DEFAULT NULL COMMENT 'URL权限标识',
  `btn_perm` varchar(64) DEFAULT NULL COMMENT '按钮权限标识',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id` (`id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` (`id`, `name`, `menu_id`, `url_perm`, `btn_perm`, `create_time`, `update_time`, `tenant_id`) VALUES (1, '查看用户', 2, 'GET:/ctf-admin/api/v1/users/*', 'sys:user:view', '2021-02-02 14:16:07', '2021-06-16 22:25:24', NULL);
INSERT INTO `sys_permission` (`id`, `name`, `menu_id`, `url_perm`, `btn_perm`, `create_time`, `update_time`, `tenant_id`) VALUES (2, '编辑用户', 2, 'PUT:/ctf-admin/users/*', 'sys:user:edit', '2021-06-16 16:19:44', '2021-06-16 23:36:53', NULL);
INSERT INTO `sys_permission` (`id`, `name`, `menu_id`, `url_perm`, `btn_perm`, `create_time`, `update_time`, `tenant_id`) VALUES (3, '新增用户', 2, 'POST:/ctf-admin/api/v1/users', 'sys:user:add', '2021-06-16 23:36:37', '2021-06-16 23:37:03', NULL);
INSERT INTO `sys_permission` (`id`, `name`, `menu_id`, `url_perm`, `btn_perm`, `create_time`, `update_time`, `tenant_id`) VALUES (4, '删除用户', 2, 'DELETE:/ctf-admin/api/v1/users/*', 'sys:user:delete', '2021-06-16 23:43:54', '2021-06-16 23:43:54', NULL);
INSERT INTO `sys_permission` (`id`, `name`, `menu_id`, `url_perm`, `btn_perm`, `create_time`, `update_time`, `tenant_id`) VALUES (5, '路由列表', 4, 'GET:/ctf-admin/api/v1/menus/route', 'sys:route:query', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '角色名称',
  `code` varchar(32) DEFAULT NULL COMMENT '角色编码',
  `sort` int(11) DEFAULT NULL COMMENT '显示顺序',
  `status` tinyint(1) DEFAULT '1' COMMENT '角色状态(1-正常；0-停用)',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标识(0-未删除；1-已删除)',
  `create_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `name`, `code`, `sort`, `status`, `deleted`, `create_time`, `update_time`, `tenant_id`) VALUES (1, '超级管理员', 'ROOT', 1, 1, 0, '2021-05-21 14:56:51', '2018-12-23 16:00:00', NULL);
INSERT INTO `sys_role` (`id`, `name`, `code`, `sort`, `status`, `deleted`, `create_time`, `update_time`, `tenant_id`) VALUES (2, '系统管理员', 'ADMIN', 2, 1, 0, '2021-03-25 12:39:54', '2022-06-12 23:19:11', NULL);
INSERT INTO `sys_role` (`id`, `name`, `code`, `sort`, `status`, `deleted`, `create_time`, `update_time`, `tenant_id`) VALUES (3, '访问游客', 'GUEST', 3, 1, 0, '2021-05-26 15:49:05', '2019-05-05 16:00:00', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 1);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 2);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 3);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 4);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 5);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 6);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 7);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 11);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 12);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 19);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 18);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 17);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 13);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 14);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 15);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 16);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 9);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 10);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 37);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 20);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 21);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 22);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 23);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 24);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 32);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 33);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 34);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 39);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 26);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 30);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 40);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 41);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 42);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 43);
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES (2, 44);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) DEFAULT NULL COMMENT '资源id',
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `permission_id` (`permission_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (2, 1);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (2, 2);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (2, 3);
INSERT INTO `sys_role_permission` (`role_id`, `permission_id`) VALUES (2, 4);
COMMIT;

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant` (
  `id` bigint(64) NOT NULL COMMENT 'id',
  `tenant_code` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户编号',
  `tenant_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '租户名',
  `app_id` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'appid加解密用',
  `app_key` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'appkdy加解密用',
  `tenant_pics` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '组合图片，多个图片用，隔开',
  `open_sso` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '开启SSO,ctf平台登录',
  `open_fa` varchar(1) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '只有在开启SSO情况下，才能开启To FA',
  `auth_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '授权地址',
  `auth_token` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '授权token',
  `auth_key` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'EncodingAESKey',
  `retrieve_pwd_url` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '找回密码URL',
  `job_pwd` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '工号+密码',
  `mobile_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机+验证码',
  `mobile_pwd` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机+密码',
  `created_by` bigint(64) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(64) DEFAULT NULL COMMENT '更新人',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(1) COLLATE utf8mb4_bin DEFAULT '1' COMMENT '状态(1启用 0不启用)',
  `remarks` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '删除状态;0-未删除 1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='租户详情信息表';

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `gender` tinyint(1) DEFAULT '1' COMMENT '性别((1:男;2:女))',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `avatar` varchar(255) DEFAULT '' COMMENT '用户头像',
  `mobile` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `status` tinyint(1) DEFAULT '1' COMMENT '用户状态((1:正常;0:禁用))',
  `email` varchar(128) DEFAULT NULL COMMENT '用户邮箱',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除标识(0:未删除;1:已删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `tenant_id` bigint(20) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `login_name` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `nickname`, `gender`, `password`, `dept_id`, `avatar`, `mobile`, `status`, `email`, `deleted`, `create_time`, `update_time`, `tenant_id`) VALUES (1, 'root', '技术', 0, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', NULL, 'https://s2.loli.net/2022/04/07/gw1L2Z5sPtS8GIl.gif', '17621590365', 1, 'hackmgod@foxmail.com', 0, NULL, NULL, NULL);
INSERT INTO `sys_user` (`id`, `username`, `nickname`, `gender`, `password`, `dept_id`, `avatar`, `mobile`, `status`, `email`, `deleted`, `create_time`, `update_time`, `tenant_id`) VALUES (2, 'admin', '系统管理员', 1, '$2a$10$8/8PxGHA.30EeWg8x4/4BuWuCUJubFbGJXyUYRs7RaJEdVvEMRbWe', 2, 'https://s2.loli.net/2022/04/07/gw1L2Z5sPtS8GIl.gif', '17621210366', 1, '', 0, '2019-10-10 13:41:22', '2022-06-12 15:32:43', NULL);
INSERT INTO `sys_user` (`id`, `username`, `nickname`, `gender`, `password`, `dept_id`, `avatar`, `mobile`, `status`, `email`, `deleted`, `create_time`, `update_time`, `tenant_id`) VALUES (3, 'test', '测试小用户', 1, '$2a$10$MPJkNw.hKT/fZOgwYP8q9eu/rFJJDsNov697AmdkHNJkpjIpVSw2q', 3, 'https://s2.loli.net/2022/04/07/gw1L2Z5sPtS8GIl.gif', '17621210366', 1, 'hackmgod@foxmail.com', 0, '2021-06-05 01:31:29', '2021-06-05 01:31:29', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (3, 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
