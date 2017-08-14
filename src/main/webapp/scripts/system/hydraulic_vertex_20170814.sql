/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-08-14 21:36:06
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
INSERT INTO `hydraulic_vertex` VALUES ('1', '1', '272.216', null, null, '0', '0', null, '金龙镇首站', '0', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('2', '1', '279.31913000977517', null, null, '0.01437685579035871', '0.07746716066015494', null, '@1#手动截断阀', '23', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('3', '1', '291.54848152059134', null, null, '0.03912927097051883', '0.15830245874031662', null, '@2#RTU(702泵站)', '47', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('4', '1', '308.1274', null, null, '0.07268528357489978', '0.24587403166049177', null, '@3#手动截断阀', '73', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('5', '1', '333.27923479188905', null, null, '0.12359302441144429', '0.3381273155944763', null, '703清管站', '100.39', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('6', '1', '349.7443618233618', null, null, '0.15691872119239256', '0.436005389019872', null, '@4#手动截断阀', '129.45', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('7', '1', '370.14050793650796', null, null, '0.198200867365171', '0.5311552711350623', null, '@5#RTU', '157.7', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('8', '1', '396.1775688073394', null, null, '0.2509003208215473', '0.5902660828561805', null, '@6#RTU', '175.25', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('9', '1', '465.9634504347826', null, null, '0.39214813058711184', '0.6980464802963962', null, '@7#RTU(705泵站)', '207.25', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('10', '1', '596.0402296650718', null, null, '0.6554257411749252', '0.7788817783765578', null, '@8#单向阀', '231.25', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('11', '1', '616.3050384615384', null, null, '0.6964420583879077', '0.8597170764567195', null, '@9#单向阀', '255.25', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('12', '1', '716.7030217391303', null, null, '0.8996492818567732', '0.9240316604917482', null, '@10#RTU', '274.345', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('13', '1', '755.6721444833624', null, null, '0.9785234482031029', '0.9600437857864601', null, '@11#RTU', '285.037', null, '1');
INSERT INTO `hydraulic_vertex` VALUES ('14', '1', '761.77', null, null, '0.9908656113442104', '1', null, '王家沟末站', '296.9', null, '1');
