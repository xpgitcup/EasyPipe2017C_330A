/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-08-18 15:55:36
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `data_keya`
-- ----------------------------
DROP TABLE IF EXISTS `data_keya`;
CREATE TABLE `data_keya` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `data_unit` varchar(255) DEFAULT NULL,
  `up_data_key_id` bigint(20) DEFAULT NULL,
  `append_parameter` varchar(255) DEFAULT NULL,
  `inherit_count` int(11) NOT NULL,
  `data_tag` varchar(255) NOT NULL,
  `basic_data_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKokoa47yvmx079u6sq1lu4dq9y` (`up_data_key_id`),
  CONSTRAINT `FKokoa47yvmx079u6sq1lu4dq9y` FOREIGN KEY (`up_data_key_id`) REFERENCES `data_keya` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_keya
-- ----------------------------
INSERT INTO `data_keya` VALUES ('1', '0', null, null, null, '0', '站点/节点位置', 'dataModel');
INSERT INTO `data_keya` VALUES ('2', '0', null, '1', null, '0', '管道标识', 'normalData');
INSERT INTO `data_keya` VALUES ('3', '0', null, '1', '节点, 里程（km）', '0', '节点位置二维数组', 'vector2D');
INSERT INTO `data_keya` VALUES ('9', '0', null, null, null, '0', '管段信息', 'dataModel');
INSERT INTO `data_keya` VALUES ('10', '0', null, '9', null, '0', '起点', 'normalData');
INSERT INTO `data_keya` VALUES ('11', '0', null, '9', null, '0', '终点', 'normalData');
INSERT INTO `data_keya` VALUES ('12', '0', null, '9', null, '1', '里程高程', 'inheritModel');
INSERT INTO `data_keya` VALUES ('13', '0', null, '12', '里程（km）, 高程（m）', '0', '里程高程二维数组', 'vector2D');
INSERT INTO `data_keya` VALUES ('14', '0', null, '9', null, '1', '地温数据', 'inheritModel');
INSERT INTO `data_keya` VALUES ('15', '0', null, '14', '里程（km）,地温（℃）', '0', '沿线地温二维数组', 'vector2D');
INSERT INTO `data_keya` VALUES ('16', '0', null, '9', null, '1', '总传热系数', 'inheritModel');
INSERT INTO `data_keya` VALUES ('17', '0', null, '16', '里程（km）, K（?）', '0', '沿线总传热系数二维数组', 'vector2D');
