/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-08-15 11:04:01
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `heat_transfer_coefficient_point`
-- ----------------------------
DROP TABLE IF EXISTS `heat_transfer_coefficient_point`;
CREATE TABLE `heat_transfer_coefficient_point` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `heat_transfer_coefficient` double NOT NULL,
  `overall_heat_transfer_coefficient_id` bigint(20) NOT NULL,
  `mileage` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKknqkioljdtimijaa9812g67b6` (`overall_heat_transfer_coefficient_id`),
  CONSTRAINT `FKknqkioljdtimijaa9812g67b6` FOREIGN KEY (`overall_heat_transfer_coefficient_id`) REFERENCES `overall_heat_transfer_coefficient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of heat_transfer_coefficient_point
-- ----------------------------
INSERT INTO `heat_transfer_coefficient_point` VALUES ('1', '0', '2.1', '1', '0');
INSERT INTO `heat_transfer_coefficient_point` VALUES ('2', '0', '2.1', '1', '296.9');
