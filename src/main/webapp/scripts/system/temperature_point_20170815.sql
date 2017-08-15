/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-08-15 11:03:44
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `temperature_point`
-- ----------------------------
DROP TABLE IF EXISTS `temperature_point`;
CREATE TABLE `temperature_point` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `ambient_temperature_id` bigint(20) NOT NULL,
  `temperature` double NOT NULL,
  `mileage` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb2ajmoyyix62au14wqsg1nc95` (`ambient_temperature_id`),
  CONSTRAINT `FKb2ajmoyyix62au14wqsg1nc95` FOREIGN KEY (`ambient_temperature_id`) REFERENCES `ambient_temperature` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of temperature_point
-- ----------------------------
INSERT INTO `temperature_point` VALUES ('1', '0', '1', '15', '0');
INSERT INTO `temperature_point` VALUES ('2', '0', '1', '15', '296.9');
