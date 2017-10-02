/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-10-01 20:02:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for data_keya
-- ----------------------------
DROP TABLE IF EXISTS `data_keya`;
CREATE TABLE `data_keya` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `up_data_key_id` bigint(20) DEFAULT NULL,
  `data_key_type` varchar(255) NOT NULL,
  `column_number` int(11) NOT NULL,
  `data_unit` varchar(255) DEFAULT NULL,
  `column_seperator` varchar(255) NOT NULL,
  `append_parameter` varchar(255) DEFAULT NULL,
  `data_tag` varchar(255) NOT NULL,
  `dictionary_id` bigint(20) NOT NULL,
  `order_number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKokoa47yvmx079u6sq1lu4dq9y` (`up_data_key_id`),
  KEY `FK9l4diqnlwnipgyqxai15oq3k6` (`dictionary_id`),
  CONSTRAINT `FK9l4diqnlwnipgyqxai15oq3k6` FOREIGN KEY (`dictionary_id`) REFERENCES `data_dictionary` (`id`),
  CONSTRAINT `FKokoa47yvmx079u6sq1lu4dq9y` FOREIGN KEY (`up_data_key_id`) REFERENCES `data_keya` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_keya
-- ----------------------------
INSERT INTO `data_keya` VALUES ('1', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '管道', '1', '0');
INSERT INTO `data_keya` VALUES ('2', '0', '1', 'dataKeyNormal', '1', 'm', ',', null, '外径', '1', '0');
INSERT INTO `data_keya` VALUES ('3', '0', '1', 'dataKeyNormal', '1', 'm', ',', null, '壁厚', '1', '0');
INSERT INTO `data_keya` VALUES ('4', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '测试项目', '1', '0');
INSERT INTO `data_keya` VALUES ('5', '0', '4', 'dataKeyFile', '1', '无量纲', ',', null, '一个文件', '1', '0');
INSERT INTO `data_keya` VALUES ('6', '0', '4', 'dataKeyEnum', '1', '无量纲', ',', '第一个,第二个', '枚举类型', '1', '0');
INSERT INTO `data_keya` VALUES ('7', '0', '4', 'dataKeyDateTime', '1', '无量纲', ',', null, '时间日期', '1', '0');
INSERT INTO `data_keya` VALUES ('8', '0', '4', 'dataKeyRef', '1', '无量纲', ',', null, '测试引用', '1', '0');
INSERT INTO `data_keya` VALUES ('9', '1', '4', 'dataKeyFile', '1', '无量纲', ',', null, '另一个文件', '1', '0');
INSERT INTO `data_keya` VALUES ('10', '0', null, 'dataKeyNormal', '1', '无量纲', ',', null, '测试项目二', '1', '0');
INSERT INTO `data_keya` VALUES ('11', '0', '10', 'dataKeyNormal', '1', '无量纲', ',', null, '普通数据', '1', '0');
INSERT INTO `data_keya` VALUES ('12', '1', '10', 'dataKeyDateTime', '1', '无量纲', ',', null, '时间日期', '1', '0');
INSERT INTO `data_keya` VALUES ('13', '1', '10', 'dataKeyEnum', '1', '无量纲', ',', null, '枚举类型', '1', '0');
INSERT INTO `data_keya` VALUES ('14', '1', '10', 'dataKeyFile', '1', '无量纲', ',', null, '文件数据', '1', '0');
INSERT INTO `data_keya` VALUES ('15', '0', '10', 'dataKeyRef', '1', '无量纲', ',', null, '引用数据', '1', '0');
