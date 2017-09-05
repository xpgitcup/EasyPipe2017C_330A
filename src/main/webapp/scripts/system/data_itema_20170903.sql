/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-09-03 16:52:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `data_itema`
-- ----------------------------
DROP TABLE IF EXISTS `data_itema`;
CREATE TABLE `data_itema` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `up_data_item_id` bigint(20) DEFAULT NULL,
  `data_keya_id` bigint(20) NOT NULL,
  `data_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlrpcx0g4lk3n9wlo7v4forioc` (`up_data_item_id`),
  KEY `FK68j42wij37vyqumwua871rl0t` (`data_keya_id`),
  CONSTRAINT `FK68j42wij37vyqumwua871rl0t` FOREIGN KEY (`data_keya_id`) REFERENCES `data_keya` (`id`),
  CONSTRAINT `FKlrpcx0g4lk3n9wlo7v4forioc` FOREIGN KEY (`up_data_item_id`) REFERENCES `data_itema` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_itema
-- ----------------------------
INSERT INTO `data_itema` VALUES ('1', '0', null, '16', null);
INSERT INTO `data_itema` VALUES ('2', '0', '1', '17', '[源]');
INSERT INTO `data_itema` VALUES ('3', '0', '1', '17', '[汇]');
INSERT INTO `data_itema` VALUES ('4', '0', '1', '17', '[离心泵]');
INSERT INTO `data_itema` VALUES ('5', '0', '1', '17', '[截断阀]');
INSERT INTO `data_itema` VALUES ('6', '0', '1', '17', '[调节阀]');
INSERT INTO `data_itema` VALUES ('7', '0', '1', '17', '[泄压阀]');
INSERT INTO `data_itema` VALUES ('8', '0', '1', '17', '[大罐]');
INSERT INTO `data_itema` VALUES ('9', '0', '1', '18', '[]');
INSERT INTO `data_itema` VALUES ('10', '0', '1', '18', '[]');
INSERT INTO `data_itema` VALUES ('11', '0', '1', '18', '[]');
INSERT INTO `data_itema` VALUES ('12', '0', '1', '18', '[]');
INSERT INTO `data_itema` VALUES ('13', '0', '1', '18', '[]');
INSERT INTO `data_itema` VALUES ('14', '0', '1', '18', '[]');
INSERT INTO `data_itema` VALUES ('15', '0', '1', '18', '[]');
