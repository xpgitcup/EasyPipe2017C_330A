/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-08-15 11:03:29
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `ambient_temperature`
-- ----------------------------
DROP TABLE IF EXISTS `ambient_temperature`;
CREATE TABLE `ambient_temperature` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `start_id` bigint(20) NOT NULL,
  `end_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `pipe_network_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g0yvbbir8j9vrrvqjox408vt6` (`name`),
  KEY `FKnsy5bae90n0bcsdxu0fm95ot1` (`start_id`),
  KEY `FK43dvrntlysiidmfc1cf68vd1l` (`end_id`),
  KEY `FKpq6vcr8sw5nopkhedefne93xg` (`pipe_network_id`),
  CONSTRAINT `FK43dvrntlysiidmfc1cf68vd1l` FOREIGN KEY (`end_id`) REFERENCES `hydraulic_vertex` (`id`),
  CONSTRAINT `FKnsy5bae90n0bcsdxu0fm95ot1` FOREIGN KEY (`start_id`) REFERENCES `hydraulic_vertex` (`id`),
  CONSTRAINT `FKpq6vcr8sw5nopkhedefne93xg` FOREIGN KEY (`pipe_network_id`) REFERENCES `pipe_network` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ambient_temperature
-- ----------------------------
INSERT INTO `ambient_temperature` VALUES ('1', '0', '1', '14', '克乌线-环境温度', '1');
