/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-08-14 21:37:27
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of temperature_point
-- ----------------------------
INSERT INTO `temperature_point` VALUES ('1', '0', '1', '272.216', '0');
INSERT INTO `temperature_point` VALUES ('2', '0', '1', '273.376', '0.267');
INSERT INTO `temperature_point` VALUES ('3', '0', '1', '272.985', '0.811');
INSERT INTO `temperature_point` VALUES ('4', '0', '1', '273.81', '1.479');
INSERT INTO `temperature_point` VALUES ('5', '0', '1', '272.26', '2.191');
INSERT INTO `temperature_point` VALUES ('6', '0', '1', '388.225', '167.504');
INSERT INTO `temperature_point` VALUES ('7', '0', '1', '753.858', '293.325');
INSERT INTO `temperature_point` VALUES ('8', '0', '1', '758.895', '293.492');
INSERT INTO `temperature_point` VALUES ('9', '0', '1', '760.217', '293.694');
INSERT INTO `temperature_point` VALUES ('10', '0', '1', '763.098', '293.913');
INSERT INTO `temperature_point` VALUES ('11', '0', '1', '761.77', '296.9');
