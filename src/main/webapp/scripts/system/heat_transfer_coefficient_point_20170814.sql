/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-08-14 21:35:31
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of heat_transfer_coefficient_point
-- ----------------------------
INSERT INTO `heat_transfer_coefficient_point` VALUES ('1', '0', '272.216', '1', '0');
INSERT INTO `heat_transfer_coefficient_point` VALUES ('2', '0', '273.376', '1', '0.267');
INSERT INTO `heat_transfer_coefficient_point` VALUES ('3', '0', '272.985', '1', '0.811');
INSERT INTO `heat_transfer_coefficient_point` VALUES ('4', '0', '273.81', '1', '1.479');
INSERT INTO `heat_transfer_coefficient_point` VALUES ('5', '0', '272.26', '1', '2.191');
INSERT INTO `heat_transfer_coefficient_point` VALUES ('6', '0', '388.225', '1', '167.504');
INSERT INTO `heat_transfer_coefficient_point` VALUES ('7', '0', '753.858', '1', '293.325');
INSERT INTO `heat_transfer_coefficient_point` VALUES ('8', '0', '758.895', '1', '293.492');
INSERT INTO `heat_transfer_coefficient_point` VALUES ('9', '0', '760.217', '1', '293.694');
INSERT INTO `heat_transfer_coefficient_point` VALUES ('10', '0', '763.098', '1', '293.913');
INSERT INTO `heat_transfer_coefficient_point` VALUES ('11', '0', '761.77', '1', '296.9');
