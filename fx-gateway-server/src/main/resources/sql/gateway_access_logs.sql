/*
Navicat MySQL Data Transfer

Source Server         : dms-dev
Source Server Version : 50616
Source Host           : 172.16.1.37:3306
Source Database       : dms_base

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2022-01-12 17:52:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gateway_access_logs
-- ----------------------------
DROP TABLE IF EXISTS `gateway_access_logs`;
CREATE TABLE `gateway_access_logs` (
  `access_id` bigint(20) NOT NULL COMMENT '访问ID',
  `path` varchar(255) DEFAULT NULL COMMENT '访问路径',
  `params` text COMMENT '请求数据',
  `headers` text COMMENT '请求头',
  `ip` varchar(500) DEFAULT NULL COMMENT '请求IP',
  `http_status` varchar(100) DEFAULT NULL COMMENT '响应状态',
  `method` varchar(50) DEFAULT NULL,
  `request_time` datetime DEFAULT NULL COMMENT '访问时间',
  `response_time` datetime DEFAULT NULL,
  `use_time` bigint(20) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL COMMENT '区域',
  `authentication` mediumtext COMMENT '认证信息',
  `service_id` varchar(100) DEFAULT NULL COMMENT '服务名',
  `error` varchar(255) DEFAULT NULL COMMENT '错误信息',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `operation_type` varchar(20) DEFAULT NULL COMMENT '操作类型 添加、删除、更新、查询',
  `api_name` varchar(100) DEFAULT NULL COMMENT '请求名称',
  `client_ip` varchar(100) DEFAULT NULL COMMENT '客户端IP',
  `market_id` bigint(20) DEFAULT '1307965417568268290' COMMENT '市场名称',
  `area_code` varchar(50) DEFAULT 'GuangDong' COMMENT '区域编码',
  PRIMARY KEY (`access_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开放网关-访问日志';
