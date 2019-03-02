/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2019-03-02 20:51:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ay_mood
-- ----------------------------
DROP TABLE IF EXISTS `ay_mood`;
CREATE TABLE `ay_mood` (
  `id` varchar(32) NOT NULL,
  `content` varchar(256) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `praise_num` int(11) DEFAULT NULL,
  `publish_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mood_user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ay_mood
-- ----------------------------
INSERT INTO `ay_mood` VALUES ('1', '这是我的第一条微信说说！！！', '1', '0', '2019-02-28 14:48:55');
INSERT INTO `ay_mood` VALUES ('2', '这是我的第一条微信说说！！！', '2', '0', '2019-02-28 14:48:54');

-- ----------------------------
-- Table structure for ay_role
-- ----------------------------
DROP TABLE IF EXISTS `ay_role`;
CREATE TABLE `ay_role` (
  `id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='【角色表】';

-- ----------------------------
-- Records of ay_role
-- ----------------------------
INSERT INTO `ay_role` VALUES ('1', 'ADMIN');
INSERT INTO `ay_role` VALUES ('2', 'USER');

-- ----------------------------
-- Table structure for ay_user
-- ----------------------------
DROP TABLE IF EXISTS `ay_user`;
CREATE TABLE `ay_user` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='【用户表】';

-- ----------------------------
-- Records of ay_user
-- ----------------------------
INSERT INTO `ay_user` VALUES ('1', 'helei', '111111');
INSERT INTO `ay_user` VALUES ('10', '贺涛', '123456');
INSERT INTO `ay_user` VALUES ('11', 'helei', 'werwr');
INSERT INTO `ay_user` VALUES ('12', 'g', 'retea');
INSERT INTO `ay_user` VALUES ('13', 'f', 'gdfagfadgas');
INSERT INTO `ay_user` VALUES ('14', 'e', 'dgaga');
INSERT INTO `ay_user` VALUES ('2', 'd', '1234567');
INSERT INTO `ay_user` VALUES ('3', 'c', '12345678');
INSERT INTO `ay_user` VALUES ('5', '贺雷', '111111');
INSERT INTO `ay_user` VALUES ('6', 'a', '888888');
INSERT INTO `ay_user` VALUES ('9', 'helie', '123');

-- ----------------------------
-- Table structure for ay_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `ay_user_role_rel`;
CREATE TABLE `ay_user_role_rel` (
  `user_id` varchar(50) DEFAULT NULL,
  `role_id` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='【用户角色关联表】';

-- ----------------------------
-- Records of ay_user_role_rel
-- ----------------------------
INSERT INTO `ay_user_role_rel` VALUES ('1', '1');
INSERT INTO `ay_user_role_rel` VALUES ('2', '2');
