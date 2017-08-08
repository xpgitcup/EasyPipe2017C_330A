/*
Navicat MySQL Data Transfer

Source Server         : 10.1.16.50
Source Server Version : 50627
Source Host           : 10.1.16.50:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2017-07-04 16:16:41
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `physical_quantity`
-- ----------------------------
DROP TABLE IF EXISTS `physical_quantity`;
CREATE TABLE `physical_quantity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `basic` bit(1) NOT NULL,
  `dimension` varchar(255) DEFAULT NULL,
  `english_name` varchar(255) NOT NULL,
  `quantity_name` varchar(255) NOT NULL,
  `symbol` varchar(255) NOT NULL,
  `unit_name` varchar(255) NOT NULL,
  `unit_symbol` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4wmo55vaoxr4c9r3ko2ors6v7` (`english_name`),
  UNIQUE KEY `UK_iffxi2ig0lqg7purmewhu5gmj` (`quantity_name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of physical_quantity
-- ----------------------------
INSERT INTO `physical_quantity` VALUES ('1', '1', '', '{\"L\":1,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'length', '长度', 'L', '米', 'm');
INSERT INTO `physical_quantity` VALUES ('2', '1', '', '{\"L\":0,\"M\":1,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'mass', '质量', 'M', '千克', 'kg');
INSERT INTO `physical_quantity` VALUES ('3', '1', '', '{\"L\":0,\"M\":0,\"T\":1,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'time', '时间', 'T', '秒', 's');
INSERT INTO `physical_quantity` VALUES ('4', '1', '', '{\"L\":0,\"M\":0,\"T\":0,\"I\":1,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'current', '电流', 'I', '安培', 'A');
INSERT INTO `physical_quantity` VALUES ('5', '1', '', '{\"L\":0,\"M\":0,\"T\":0,\"I\":0,\"K\":1,\"n(v)\":0,\"I(Iv)\":0}', 'temperature', '热力学温度', 'K', '开尔文', 'K');
INSERT INTO `physical_quantity` VALUES ('6', '1', '', '{\"L\":0,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":1,\"I(Iv)\":0}', 'amount of substance', '物质的量', 'n(v)', '摩尔', 'mol');
INSERT INTO `physical_quantity` VALUES ('7', '1', '', '{\"L\":0,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":1}', 'luminous intensity', '发光强度', 'I(Iv)', '坎德拉', 'cd');
INSERT INTO `physical_quantity` VALUES ('8', '0', '\0', '{\"L\":1,\"M\":0,\"T\":-1,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'speed', '速度', 'v', '米/秒', 'm/s');
INSERT INTO `physical_quantity` VALUES ('9', '0', '\0', '{\"L\":2,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'area', '面积', 'A', '平方米', 'm^2');
INSERT INTO `physical_quantity` VALUES ('10', '0', '\0', '{\"L\":3,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'volume', '体积', 'V', '立方米', 'm^3');
INSERT INTO `physical_quantity` VALUES ('11', '0', '\0', '{\"L\":-3,\"M\":1,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'density', '密度', 'ρ', '千克每立方米', 'kg/m³');
INSERT INTO `physical_quantity` VALUES ('12', '0', '\0', '{\"L\":3,\"M\":0,\"T\":-1,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'volume flow rate', '体积流量', 'Q', '立方米每秒', 'm³/s');
INSERT INTO `physical_quantity` VALUES ('13', '0', '\0', '{\"L\":-1,\"M\":1,\"T\":-2,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'pressure', '压力', 'P', '帕', 'Pa');
INSERT INTO `physical_quantity` VALUES ('14', '0', '\0', '{\"L\":2,\"M\":1,\"T\":-3,\"I\":-1,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'voltage', '电压', 'U', '伏', 'V');
INSERT INTO `physical_quantity` VALUES ('15', '0', '\0', '{\"L\":2,\"M\":1,\"T\":-3,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'power', '功率', 'P', '瓦', 'W');
INSERT INTO `physical_quantity` VALUES ('16', '0', '\0', '{\"L\":0,\"M\":0,\"T\":-1,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'revolution', '转数', 'n', '转每秒', 'r/s');
INSERT INTO `physical_quantity` VALUES ('17', '0', '\0', '{\"L\":0,\"M\":1,\"T\":-3,\"I\":0,\"K\":-1,\"n(v)\":0,\"I(Iv)\":0}', 'heat transfer coefficient', '传热系数', 'K', '瓦每平方米每K', 'W/(㎡·K)');
INSERT INTO `physical_quantity` VALUES ('18', '0', '\0', '{\"L\":1,\"M\":1,\"T\":-3,\"I\":0,\"K\":-1,\"n(v)\":0,\"I(Iv)\":0}', 'heat conductivity', '热导率', 'λ', '瓦每米每K', 'W/（m·K）');
INSERT INTO `physical_quantity` VALUES ('19', '0', '\0', '{\"L\":-1,\"M\":1,\"T\":-1,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'dynamic  viscosity', '动力粘度', 'μ', '帕秒', 'Pa·s');
INSERT INTO `physical_quantity` VALUES ('20', '0', '\0', '{\"L\":3,\"M\":0,\"T\":-1,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'standard volume flow rate', '标准体积流量', 'Q', '标方每天', 'Nm3/d');
INSERT INTO `physical_quantity` VALUES ('21', '0', '\0', '{\"L\":0,\"M\":0,\"T\":0,\"I\":0,\"K\":0,\"n(v)\":0,\"I(Iv)\":0}', 'efficiency', '效率', 'η', '·', '·');
INSERT INTO `physical_quantity` VALUES ('22', '0', '\0', '{\"L\":2,\"M\":0,\"T\":-2,\"I\":0,\"K\":-1,\"n(v)\":0,\"I(Iv)\":0}', 'specific heat at constant pressure', '定压比热容', 'cp', '焦耳每千克每开', 'J/（kg·K）');
