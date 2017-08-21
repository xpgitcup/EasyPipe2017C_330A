/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-08-21 14:17:52
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `data_keya`
-- ----------------------------
DROP TABLE IF EXISTS `data_keya`;
CREATE TABLE `data_keya` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `up_data_key_id` bigint(20) DEFAULT NULL,
  `is_enumeration` bit(1) NOT NULL,
  `dimension` int(11) NOT NULL,
  `data_unit` varchar(255) DEFAULT NULL,
  `append_parameter` varchar(255) DEFAULT NULL,
  `ref_data_model_id` bigint(20) DEFAULT NULL,
  `data_tag` varchar(255) NOT NULL,
  `single` bit(1) NOT NULL,
  `dictionary_id` bigint(20) NOT NULL,
  `order_number` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKokoa47yvmx079u6sq1lu4dq9y` (`up_data_key_id`),
  KEY `FKmbblarevjr5nlixcd5r982xnr` (`ref_data_model_id`),
  KEY `FK9l4diqnlwnipgyqxai15oq3k6` (`dictionary_id`),
  CONSTRAINT `FK9l4diqnlwnipgyqxai15oq3k6` FOREIGN KEY (`dictionary_id`) REFERENCES `data_dictionary` (`id`),
  CONSTRAINT `FKmbblarevjr5nlixcd5r982xnr` FOREIGN KEY (`ref_data_model_id`) REFERENCES `data_keya` (`id`),
  CONSTRAINT `FKokoa47yvmx079u6sq1lu4dq9y` FOREIGN KEY (`up_data_key_id`) REFERENCES `data_keya` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_keya
-- ----------------------------
INSERT INTO `data_keya` VALUES ('1', '1', null, '\0', '1', null, null, null, '节点位置', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('2', '0', '1', '\0', '1', null, null, null, '节点名称', '', '1', '0');
INSERT INTO `data_keya` VALUES ('3', '0', '1', '\0', '1', 'km', null, null, '节点位置', '', '1', '0');
INSERT INTO `data_keya` VALUES ('4', '1', null, '\0', '1', null, null, null, '管段', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('5', '0', '4', '\0', '1', null, null, '2', '起点', '', '1', '0');
INSERT INTO `data_keya` VALUES ('6', '0', '4', '\0', '1', null, null, '2', '终点', '', '1', '0');
INSERT INTO `data_keya` VALUES ('7', '0', null, '\0', '1', null, null, null, '地形', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('8', '0', '7', '\0', '1', null, null, '4', '管段标识', '', '1', '0');
INSERT INTO `data_keya` VALUES ('9', '1', '7', '\0', '2', null, '里程（km）,高程（m）', null, '地形数组', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('10', '0', null, '\0', '1', null, null, null, '地温', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('11', '0', '10', '\0', '1', null, null, '4', '管段标识', '', '1', '0');
INSERT INTO `data_keya` VALUES ('12', '1', '10', '\0', '2', null, '里程（km）,地温（℃）', null, '地温数组', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('13', '0', null, '\0', '1', null, null, null, '总传热系数', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('14', '0', '13', '\0', '1', null, null, '4', '管段标识', '', '1', '0');
INSERT INTO `data_keya` VALUES ('15', '1', '13', '\0', '2', null, '里程（km）,总传热系数（?）', null, '总传热系数数组', '', '1', '0');
INSERT INTO `data_keya` VALUES ('16', '0', null, '\0', '1', null, null, null, '元件类型', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('17', '1', '16', '\0', '1', null, null, null, '类型名称', '', '1', '0');
INSERT INTO `data_keya` VALUES ('18', '0', '16', '\0', '1', null, null, null, '解释说明', '', '1', '0');
INSERT INTO `data_keya` VALUES ('19', '0', null, '\0', '1', null, null, null, '水力学元件', '', '1', '0');
INSERT INTO `data_keya` VALUES ('20', '0', '19', '\0', '1', null, null, null, '名称', '', '1', '0');
INSERT INTO `data_keya` VALUES ('21', '0', '19', '\0', '1', null, null, '16', '类型', '', '1', '0');
INSERT INTO `data_keya` VALUES ('22', '0', '19', '\0', '1', null, null, null, '型号', '', '1', '0');
INSERT INTO `data_keya` VALUES ('23', '0', '19', '\0', '1', null, null, null, '离心泵', '', '1', '0');
INSERT INTO `data_keya` VALUES ('24', '1', '23', '\0', '2', null, '流量（m3/h）,扬程(m)', null, '泵特性', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('25', '0', '23', '\0', '1', 'm3/h', null, null, '额定流量', '', '1', '0');
INSERT INTO `data_keya` VALUES ('26', '0', '23', '\0', '1', null, null, null, '额定扬程', '', '1', '0');
INSERT INTO `data_keya` VALUES ('27', '0', '23', '\0', '1', null, null, null, '额定转速', '', '1', '0');
