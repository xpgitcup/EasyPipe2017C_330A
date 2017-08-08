/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-07-25 14:48:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `data_key`
-- ----------------------------
DROP TABLE IF EXISTS `data_key`;
CREATE TABLE `data_key` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `append_value` varchar(255) DEFAULT NULL,
  `data_value_type` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `import_from_file` bit(1) DEFAULT NULL,
  `is_nullable` bit(1) DEFAULT NULL,
  `key_context` varchar(255) NOT NULL,
  `quantity_unit` varchar(255) DEFAULT NULL,
  `up_key_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2xfnhfia4b2aj067noxxpd2hk` (`up_key_id`),
  CONSTRAINT `FK2xfnhfia4b2aj067noxxpd2hk` FOREIGN KEY (`up_key_id`) REFERENCES `data_key` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_key
-- ----------------------------
INSERT INTO `data_key` VALUES ('1', '0', null, 'project', null, '\0', '\0', '液体管道模拟', null, null);
INSERT INTO `data_key` VALUES ('2', '1', null, 'dataModel', null, '\0', '\0', '水力模拟工程', null, '1');
INSERT INTO `data_key` VALUES ('3', '1', null, 'dataModel', null, '\0', '\0', '管道', null, '1');
INSERT INTO `data_key` VALUES ('4', '1', null, 'dataModel', null, '\0', '\0', '初始条件', null, '1');
INSERT INTO `data_key` VALUES ('5', '2', null, 'dataModel', null, '\0', '\0', '边界条件', null, '1');
INSERT INTO `data_key` VALUES ('6', '1', null, 'dataModel', null, '\0', '\0', '站点', null, '1');
INSERT INTO `data_key` VALUES ('7', '2', null, 'dataModel', null, '\0', '\0', '操作序列', null, '1');
INSERT INTO `data_key` VALUES ('8', '0', null, 'dataModel', null, '\0', '\0', '离心泵', null, '1');
INSERT INTO `data_key` VALUES ('9', '0', null, 'dataModel', null, '\0', '\0', '调节阀', null, '1');
INSERT INTO `data_key` VALUES ('10', '0', null, 'dataModel', null, '\0', '\0', '大罐', null, '1');
INSERT INTO `data_key` VALUES ('11', '0', null, 'dataModel', null, '\0', '\0', '加热炉', null, '1');
INSERT INTO `data_key` VALUES ('12', '0', null, 'dataModel', null, '\0', '', '泄压阀', null, '1');
INSERT INTO `data_key` VALUES ('13', '0', null, 'dataModel', null, '\0', '\0', '截断阀', null, '1');
