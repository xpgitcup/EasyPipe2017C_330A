/*
Navicat MySQL Data Transfer

Source Server         : sample
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : easypipedba

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2017-09-09 10:54:53
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `data_keya`
-- ----------------------------
DROP TABLE IF EXISTS `data_keya`;
CREATE TABLE `data_keya` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `is_file` bit(1) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_keya
-- ----------------------------
INSERT INTO `data_keya` VALUES ('1', '2', '\0', null, '\0', '1', '？单位', null, null, '管道', '', '1', '-8');
INSERT INTO `data_keya` VALUES ('2', '1', '\0', '1', '\0', '1', '？单位', null, null, '管道名称', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('3', '1', '\0', null, '\0', '1', '？单位', null, null, '节点位置', '', '1', '-8');
INSERT INTO `data_keya` VALUES ('4', '0', '\0', '3', '\0', '1', '？单位', null, '2', '所属管道', '', '1', '0');
INSERT INTO `data_keya` VALUES ('5', '1', '\0', '3', '\0', '1', '？单位', '节点名称,里程（km）', null, '节点名称', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('6', '1', '\0', '3', '\0', '1', 'km', null, null, '节点位置', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('7', '1', '\0', null, '\0', '1', '？单位', null, null, '地形', '', '1', '-8');
INSERT INTO `data_keya` VALUES ('8', '0', '\0', '7', '\0', '1', '？单位', null, '5', '起点', '', '1', '0');
INSERT INTO `data_keya` VALUES ('9', '0', '\0', '7', '\0', '2', '？单位', '里程（km）,高程（m）', null, '地形数组', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('10', '1', '\0', null, '\0', '1', '？单位', null, null, '地温', '', '1', '-8');
INSERT INTO `data_keya` VALUES ('11', '0', '\0', '10', '\0', '1', '？单位', null, '5', '起点', '', '1', '0');
INSERT INTO `data_keya` VALUES ('12', '1', '\0', '10', '\0', '2', '？单位', '里程（km）,地温（℃）', null, '地温数组', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('13', '1', '\0', null, '\0', '1', '？单位', null, null, '总传热系数', '', '1', '-8');
INSERT INTO `data_keya` VALUES ('14', '0', '\0', '13', '\0', '1', '？单位', null, '5', '起点', '', '1', '0');
INSERT INTO `data_keya` VALUES ('15', '1', '\0', '13', '\0', '2', '？单位', '里程（km）,总传热系数（k）', null, '总传热系数数组', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('16', '1', '\0', null, '\0', '1', '？单位', null, null, '水力学元件类型', '', '1', '-11');
INSERT INTO `data_keya` VALUES ('17', '1', '\0', '16', '\0', '1', '？单位', null, null, '类型名称', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('18', '1', '\0', '16', '\0', '1', '？单位', null, null, '类型说明', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('19', '1', '\0', null, '\0', '1', '？单位', null, null, '水力学元件', '', '1', '-10');
INSERT INTO `data_keya` VALUES ('20', '0', '\0', '19', '\0', '1', '？单位', null, '2', '所属管道', '', '1', '0');
INSERT INTO `data_keya` VALUES ('21', '0', '\0', '19', '\0', '1', '？单位', null, null, '名称', '', '1', '0');
INSERT INTO `data_keya` VALUES ('22', '0', '\0', '19', '\0', '1', '？单位', null, '17', '类型', '', '1', '0');
INSERT INTO `data_keya` VALUES ('23', '0', '\0', '19', '\0', '1', '？单位', null, null, '型号', '', '1', '0');
INSERT INTO `data_keya` VALUES ('24', '0', '\0', '19', '\0', '1', '？单位', null, null, '离心泵', '', '1', '-10');
INSERT INTO `data_keya` VALUES ('25', '1', '\0', '24', '\0', '1', 'm3/h', null, null, '额定流量', '', '1', '0');
INSERT INTO `data_keya` VALUES ('26', '2', '\0', '24', '\0', '1', 'm', null, null, '额定扬程', '', '1', '0');
INSERT INTO `data_keya` VALUES ('27', '0', '\0', '24', '\0', '1', 'rpm', null, null, '额定转速', '', '1', '0');
INSERT INTO `data_keya` VALUES ('28', '1', '\0', '24', '\0', '2', '？单位', '流量（m3/h）,扬程(m)', null, '扬程特性', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('29', '1', '\0', null, '\0', '1', '？单位', null, null, '流体', '', '1', '-9');
INSERT INTO `data_keya` VALUES ('30', '1', '\0', '29', '\0', '2', 'kg/m3', '温度（℃）,密度（kg/m3）', null, '密度特性', '\0', '1', '1');
INSERT INTO `data_keya` VALUES ('31', '1', '\0', '29', '\0', '2', 'mPa.s', '温度（℃）,粘度（mPa.s）', null, '粘度特性', '\0', '1', '1');
INSERT INTO `data_keya` VALUES ('32', '0', '\0', '29', '\0', '1', '？单位', null, null, '流体名称', '', '1', '0');
INSERT INTO `data_keya` VALUES ('33', '0', '\0', '29', '\0', '2', 'MPa', '温度（℃）,饱和蒸汽压(MPa)', null, '饱和蒸汽压特性', '\0', '1', '1');
INSERT INTO `data_keya` VALUES ('34', '1', '\0', '29', '\0', '2', 'MPa', '温度（℃）,压缩系数（MPa）', null, '压缩系数特性', '\0', '1', '1');
INSERT INTO `data_keya` VALUES ('35', '0', '\0', '29', '\0', '2', 'j/kg.℃', '温度（℃）,热容（j/kg.℃）', null, '热熔特性', '\0', '1', '1');
INSERT INTO `data_keya` VALUES ('36', '1', '\0', null, '\0', '1', '？单位', null, null, '钢材', '', '1', '-9');
INSERT INTO `data_keya` VALUES ('37', '0', '\0', '36', '\0', '1', '？单位', null, null, '型号', '', '1', '0');
INSERT INTO `data_keya` VALUES ('38', '1', '\0', '36', '\0', '1', 'MPa', null, null, '弹性模量', '', '1', '0');
INSERT INTO `data_keya` VALUES ('39', '0', '\0', '36', '\0', '1', 'MPa', null, null, '许用应力', '', '1', '0');
INSERT INTO `data_keya` VALUES ('40', '0', '\0', '36', '\0', '1', '无量纲', null, null, '泊松比', '', '1', '0');
INSERT INTO `data_keya` VALUES ('41', '0', '\0', '19', '\0', '1', '？单位', null, null, '通用阀', '', '1', '-10');
INSERT INTO `data_keya` VALUES ('42', '0', '\0', '41', '\0', '2', 'Cd', '开度（小数）,阀特性Cd值', null, '阀特性', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('43', '1', '\0', '44', '\0', '1', '%', null, null, '机械限位', '', '1', '0');
INSERT INTO `data_keya` VALUES ('44', '0', '\0', '41', '\0', '1', '？单位', null, null, '调节阀', '', '1', '0');
INSERT INTO `data_keya` VALUES ('45', '0', '\0', '44', '\0', '1', '%', null, null, '最小阀位', '', '1', '0');
INSERT INTO `data_keya` VALUES ('46', '0', '\0', '41', '\0', '1', '？单位', null, null, '泄压阀', '', '1', '-10');
INSERT INTO `data_keya` VALUES ('47', '0', '\0', '46', '\0', '1', 'm3/h', null, null, '最大泄流量', '', '1', '0');
INSERT INTO `data_keya` VALUES ('48', '0', '\0', '46', '\0', '1', 'MPa', null, null, '设定压力', '', '1', '0');
INSERT INTO `data_keya` VALUES ('49', '0', '\0', '19', '\0', '1', '？单位', null, null, '理想源', '', '1', '0');
INSERT INTO `data_keya` VALUES ('50', '0', '\0', '49', '\0', '1', '输入流体名称', null, '29', '流体', '', '1', '0');
INSERT INTO `data_keya` VALUES ('51', '1', '\0', null, '\0', '1', '无量纲', null, null, '节点关系', '', '1', '0');
INSERT INTO `data_keya` VALUES ('53', '0', '\0', '51', '\0', '2', '？单位', '起点,终点', null, '关系列表', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('54', '0', '\0', '19', '\0', '1', '？单位', null, null, '管段', '', '1', '0');
INSERT INTO `data_keya` VALUES ('55', '0', '\0', '54', '\0', '1', 'mm', null, null, '外径', '', '1', '0');
INSERT INTO `data_keya` VALUES ('56', '0', '\0', '54', '\0', '1', 'mm', null, null, '壁厚', '', '1', '0');
INSERT INTO `data_keya` VALUES ('57', '0', '\0', '54', '\0', '1', '？单位', null, null, '绝对粗糙度', '', '1', '0');
INSERT INTO `data_keya` VALUES ('58', '0', '\0', '54', '\0', '1', '？单位', null, '5', '起点', '', '1', '0');
INSERT INTO `data_keya` VALUES ('59', '0', '\0', '54', '\0', '1', '？单位', null, '5', '终点', '', '1', '0');
INSERT INTO `data_keya` VALUES ('60', '2', '\0', null, '\0', '1', '无量纲', null, null, '模拟工程', '', '1', '-9');
INSERT INTO `data_keya` VALUES ('61', '0', '\0', '60', '\0', '1', '无量纲', null, null, '工程名称', '', '1', '0');
INSERT INTO `data_keya` VALUES ('62', '0', '\0', '60', '\0', '1', '无量纲', null, null, '工程说明', '', '1', '0');
INSERT INTO `data_keya` VALUES ('63', '0', '\0', '60', '\0', '1', '无量纲', null, '1', '管道', '', '1', '0');
INSERT INTO `data_keya` VALUES ('64', '2', '\0', null, '\0', '1', '无量纲', null, null, '站点详情', '', '1', '-8');
INSERT INTO `data_keya` VALUES ('65', '1', '\0', '64', '\0', '1', '无量纲', null, '5', '节点', '', '1', '0');
INSERT INTO `data_keya` VALUES ('66', '0', '\0', '64', '\0', '1', '无量纲', null, '19', '包含元件列表', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('67', '0', '\0', null, '\0', '1', '无量纲', null, null, '站内关系', '', '1', '0');
INSERT INTO `data_keya` VALUES ('68', '0', '\0', '67', '\0', '2', '无量纲', '起点,终点', '19', '关系列表', '\0', '1', '0');
INSERT INTO `data_keya` VALUES ('69', '0', '\0', null, '\0', '1', '无量纲', null, null, '测试性的模型', '', '3', '0');
INSERT INTO `data_keya` VALUES ('70', '0', '\0', '69', '\0', '1', '无量纲', null, null, '数据项1', '', '3', '0');
INSERT INTO `data_keya` VALUES ('71', '1', '', '69', '\0', '1', '无量纲', null, null, '上传文件1', '', '3', '0');
INSERT INTO `data_keya` VALUES ('72', '0', '', '69', '\0', '1', '无量纲', null, null, '上传文件2', '', '3', '0');
INSERT INTO `data_keya` VALUES ('73', '0', '', '69', '\0', '1', '无量纲', null, null, '上传文件3', '', '3', '0');
