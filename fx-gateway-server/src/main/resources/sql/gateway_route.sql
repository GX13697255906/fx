/*
Navicat MySQL Data Transfer

Source Server         : dms-dev
Source Server Version : 50616
Source Host           : 172.16.1.37:3306
Source Database       : dms_base

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2022-01-12 17:52:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `gateway_route`;
CREATE TABLE `gateway_route` (
  `route_id` bigint(20) NOT NULL COMMENT '路由ID',
  `route_name` varchar(255) NOT NULL COMMENT '路由名称',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `service_id` varchar(255) DEFAULT NULL COMMENT '服务ID',
  `url` varchar(255) DEFAULT NULL COMMENT '完整地址',
  `strip_prefix` tinyint(3) NOT NULL DEFAULT '1' COMMENT '忽略前缀',
  `retryable` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0-不重试 1-重试',
  `status` tinyint(3) NOT NULL DEFAULT '1' COMMENT '状态:0-无效 1-有效',
  `is_persist` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否为保留数据:0-否 1-是',
  `route_desc` varchar(255) DEFAULT NULL COMMENT '路由说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `market_id` bigint(20) NOT NULL DEFAULT '1307965417568268290' COMMENT '市场ID',
  PRIMARY KEY (`route_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开放网关-路由';

-- ----------------------------
-- Records of gateway_route
-- ----------------------------
INSERT INTO `gateway_route` VALUES ('10737418247485965', 'fx-es-server', '/es/**', 'fx-es-server', 'lb://fx-es-server', '1', '0', '1', '0', '搜索引擎模块', '2022-01-12 16:35:54', '2022-01-12 16:35:59', '1307965417568268290');
INSERT INTO `gateway_route` VALUES ('10737418247485966', 'fx-uaa-server', '/uaa/**', 'fx-uaa-server', 'lb://fx-uaa-server', '1', '0', '1', '0', '服务登录授权模块', '2022-01-12 16:35:54', '2022-01-12 16:35:59', '1307965417568268290');