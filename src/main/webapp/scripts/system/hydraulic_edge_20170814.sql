/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-08-14 21:35:54
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `hydraulic_edge`
-- ----------------------------
DROP TABLE IF EXISTS `hydraulic_edge`;
CREATE TABLE `hydraulic_edge` (
  `start_id` bigint(20) NOT NULL,
  `end_id` bigint(20) NOT NULL,
  PRIMARY KEY (`start_id`,`end_id`),
  KEY `FKjson08gqc5wia51hld14lu8aa` (`end_id`),
  CONSTRAINT `FKjson08gqc5wia51hld14lu8aa` FOREIGN KEY (`end_id`) REFERENCES `hydraulic_vertex` (`id`),
  CONSTRAINT `FKkkyajr5np8a95lep7hcas6230` FOREIGN KEY (`start_id`) REFERENCES `hydraulic_vertex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hydraulic_edge
-- ----------------------------
INSERT INTO `hydraulic_edge` VALUES ('1', '2');
INSERT INTO `hydraulic_edge` VALUES ('2', '3');
INSERT INTO `hydraulic_edge` VALUES ('3', '4');
INSERT INTO `hydraulic_edge` VALUES ('4', '5');
INSERT INTO `hydraulic_edge` VALUES ('5', '6');
INSERT INTO `hydraulic_edge` VALUES ('6', '7');
INSERT INTO `hydraulic_edge` VALUES ('7', '8');
INSERT INTO `hydraulic_edge` VALUES ('8', '9');
INSERT INTO `hydraulic_edge` VALUES ('9', '10');
INSERT INTO `hydraulic_edge` VALUES ('10', '11');
INSERT INTO `hydraulic_edge` VALUES ('11', '12');
INSERT INTO `hydraulic_edge` VALUES ('12', '13');
INSERT INTO `hydraulic_edge` VALUES ('13', '14');
