/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-08-14 21:36:50
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `overall_heat_transfer_coefficient`
-- ----------------------------
DROP TABLE IF EXISTS `overall_heat_transfer_coefficient`;
CREATE TABLE `overall_heat_transfer_coefficient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `start_id` bigint(20) NOT NULL,
  `end_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `pipe_network_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m082n3dpl3s32o17m8npp8wcb` (`name`),
  KEY `FK7l59o9hocj92pqno0vh548ymb` (`start_id`),
  KEY `FKeaemn8jspxl6lfpsoa6rahx5b` (`end_id`),
  KEY `FKnbphkcoho5lyjdtnx1lc3pjbh` (`pipe_network_id`),
  CONSTRAINT `FK7l59o9hocj92pqno0vh548ymb` FOREIGN KEY (`start_id`) REFERENCES `hydraulic_vertex` (`id`),
  CONSTRAINT `FKeaemn8jspxl6lfpsoa6rahx5b` FOREIGN KEY (`end_id`) REFERENCES `hydraulic_vertex` (`id`),
  CONSTRAINT `FKnbphkcoho5lyjdtnx1lc3pjbh` FOREIGN KEY (`pipe_network_id`) REFERENCES `pipe_network` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of overall_heat_transfer_coefficient
-- ----------------------------
INSERT INTO `overall_heat_transfer_coefficient` VALUES ('1', '0', '1', '14', '克乌线-高程-里程', '1');
