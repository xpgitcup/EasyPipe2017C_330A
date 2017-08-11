/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-08-11 14:22:44
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `mileage_and_elevation`
-- ----------------------------
DROP TABLE IF EXISTS `mileage_and_elevation`;
CREATE TABLE `mileage_and_elevation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `start_id` bigint(20) NOT NULL,
  `end_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `pipe_network_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_blx0wbwkdnj6d8u5c694e0spb` (`name`),
  KEY `FK4j5u17n0qq97nbfqfvote2aax` (`start_id`),
  KEY `FKneok0vb0c7l1me5a6juspgobu` (`end_id`),
  KEY `FKhto9gv2ssh78ye2nl5pi6obm5` (`pipe_network_id`),
  CONSTRAINT `FK4j5u17n0qq97nbfqfvote2aax` FOREIGN KEY (`start_id`) REFERENCES `hydraulic_vertex` (`id`),
  CONSTRAINT `FKhto9gv2ssh78ye2nl5pi6obm5` FOREIGN KEY (`pipe_network_id`) REFERENCES `pipe_network` (`id`),
  CONSTRAINT `FKneok0vb0c7l1me5a6juspgobu` FOREIGN KEY (`end_id`) REFERENCES `hydraulic_vertex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mileage_and_elevation
-- ----------------------------
INSERT INTO `mileage_and_elevation` VALUES ('1', '0', '1', '14', '克乌线-高程-里程', '1');
