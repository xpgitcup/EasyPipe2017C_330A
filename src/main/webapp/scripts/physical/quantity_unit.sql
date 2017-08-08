/*
Navicat MySQL Data Transfer

Source Server         : 10.1.16.50
Source Server Version : 50627
Source Host           : 10.1.16.50:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2017-07-04 16:16:54
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `quantity_unit`
-- ----------------------------
DROP TABLE IF EXISTS `quantity_unit`;
CREATE TABLE `quantity_unit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `dimension` varchar(255) DEFAULT NULL,
  `english_name` varchar(255) DEFAULT NULL,
  `factora` double NOT NULL,
  `factorb` double NOT NULL,
  `symbol` varchar(255) NOT NULL,
  `unit_name` varchar(255) NOT NULL,
  `unit_system_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ci4q57gmqf45pue6iloo00mb4` (`unit_name`),
  KEY `FK2bhdf7ah0gxrcb0foc6to5af6` (`unit_system_id`),
  CONSTRAINT `FK2bhdf7ah0gxrcb0foc6to5af6` FOREIGN KEY (`unit_system_id`) REFERENCES `unit_system` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quantity_unit
-- ----------------------------
INSERT INTO `quantity_unit` VALUES ('1', '0', '{\"L\":1,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', null, '1', '0', 'm', '米', '1');
INSERT INTO `quantity_unit` VALUES ('2', '0', '{\"L\":0,\"M\":1,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', null, '1', '0', 'kg', '千克', '1');
INSERT INTO `quantity_unit` VALUES ('3', '0', '{\"L\":0,\"M\":0,\"T\":1,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', null, '1', '0', 's', '秒', '1');
INSERT INTO `quantity_unit` VALUES ('4', '0', '{\"L\":0,\"M\":0,\"T\":0,\"I\":1,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', null, '1', '0', 'A', '安培', '1');
INSERT INTO `quantity_unit` VALUES ('5', '0', '{\"L\":0,\"M\":0,\"T\":0,\"I\":0,\"K\":1,\"n(v)\":0,\"I(Iv)\":0}', null, '1', '0', 'K', '开尔文', '1');
INSERT INTO `quantity_unit` VALUES ('6', '0', '{\"L\":0,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":1,\"I(Iv)\":0}', null, '1', '0', 'mol', '摩尔', '1');
INSERT INTO `quantity_unit` VALUES ('7', '0', '{\"L\":0,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":1}', null, '1', '0', 'cd', '坎德拉', '1');
INSERT INTO `quantity_unit` VALUES ('8', '0', '{\"L\":1,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', null, '0.001', '0', 'mm', '毫米', '1');
INSERT INTO `quantity_unit` VALUES ('9', '0', '{\"L\":1,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', null, '1000', '0', 'km', '千米', '1');
INSERT INTO `quantity_unit` VALUES ('10', '0', '{\"L\":1,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', null, '0.01', '0', 'cm', '厘米', '1');
INSERT INTO `quantity_unit` VALUES ('11', '0', '{\"L\":1,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', null, '0.1', '0', 'dm', '分米', '1');
INSERT INTO `quantity_unit` VALUES ('12', '0', '{\"L\":0,\"M\":1,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', null, '0.001', '0', 'g', '克', '1');
INSERT INTO `quantity_unit` VALUES ('13', '0', '{\"L\":0,\"M\":1,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', null, '1000', '0', 't', '吨', '1');
INSERT INTO `quantity_unit` VALUES ('14', '0', '{\"L\":0,\"M\":0,\"T\":1,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', null, '60', '0', 'm', '分钟', '1');
INSERT INTO `quantity_unit` VALUES ('15', '0', '{\"L\":0,\"M\":0,\"T\":1,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', null, '3600', '0', 'h', '小时', '1');
