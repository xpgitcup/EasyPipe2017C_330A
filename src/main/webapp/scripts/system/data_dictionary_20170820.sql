/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-08-20 09:02:11
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `data_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `data_dictionary`;
CREATE TABLE `data_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l5g8lh0qa5u6chm6abv12myyu` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_dictionary
-- ----------------------------
INSERT INTO `data_dictionary` VALUES ('1', '0', '液体管道模拟');
INSERT INTO `data_dictionary` VALUES ('2', '0', '气体管道模拟');
INSERT INTO `data_dictionary` VALUES ('3', '0', '多相流管道莫尼');
INSERT INTO `data_dictionary` VALUES ('4', '0', '液体管网模拟');
INSERT INTO `data_dictionary` VALUES ('5', '0', '气体管网模拟');
INSERT INTO `data_dictionary` VALUES ('6', '0', '多相流管网模拟');
