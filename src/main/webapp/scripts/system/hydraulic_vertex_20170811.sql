/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-08-11 14:22:20
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `hydraulic_vertex`
-- ----------------------------
DROP TABLE IF EXISTS `hydraulic_vertex`;
CREATE TABLE `hydraulic_vertex` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `elevation` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `x_location_geo` double DEFAULT NULL,
  `y_location` double DEFAULT NULL,
  `x_location` double DEFAULT NULL,
  `y_location_geo` double DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `mileage` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `pipe_network_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5d1vkf8tbpdcmvuvuqwrrcwab` (`pipe_network_id`),
  CONSTRAINT `FK5d1vkf8tbpdcmvuvuqwrrcwab` FOREIGN KEY (`pipe_network_id`) REFERENCES `pipe_network` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hydraulic_vertex
-- ----------------------------
INSERT INTO `hydraulic_vertex` VALUES ('1', '0', null, null, null, null, null, null, '金龙镇首站', '0', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('2', '0', null, null, null, null, null, null, '@1#手动截断阀', '23', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('3', '0', null, null, null, null, null, null, '@2#RTU(702泵站)', '47', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('4', '0', null, null, null, null, null, null, '@3#手动截断阀', '73', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('5', '0', null, null, null, null, null, null, '703清管站', '100.39', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('6', '0', null, null, null, null, null, null, '@4#手动截断阀', '129.45', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('7', '0', null, null, null, null, null, null, '@5#RTU', '157.7', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('8', '0', null, null, null, null, null, null, '@6#RTU', '175.25', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('9', '0', null, null, null, null, null, null, '@7#RTU(705泵站)', '207.25', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('10', '0', null, null, null, null, null, null, '@8#单向阀', '231.25', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('11', '0', null, null, null, null, null, null, '@9#单向阀', '255.25', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('12', '0', null, null, null, null, null, null, '@10#RTU', '274.345', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('13', '0', null, null, null, null, null, null, '@11#RTU', '285.037', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('14', '0', null, null, null, null, null, null, '王家沟末站', '296.9', null, '1');
