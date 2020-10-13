/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : orca_bi

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 01/09/2020 15:14:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for good_infos
-- ----------------------------
DROP TABLE IF EXISTS `good_infos`;
CREATE TABLE `good_infos`  (
  `tg_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `tg_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `tg_price` decimal(8, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `tg_unit` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `tg_order` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '排序',
  `tg_type_id` int(11) NULL DEFAULT NULL COMMENT '类型外键编号',
  `update_date` date NULL DEFAULT NULL COMMENT '更新日期',
  `update_datetime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tg_id`) USING BTREE,
  INDEX `tg_type_id`(`tg_type_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of good_infos
-- ----------------------------
INSERT INTO `good_infos` VALUES (1, '金针菇', 5.50, '斤', '1', 3, '2020-09-01', '2020-09-01 15:05:41');
INSERT INTO `good_infos` VALUES (2, '油菜', 12.60, '斤', '2', 1, '2020-09-02', '2020-09-02 15:05:50');

-- ----------------------------
-- Table structure for good_types
-- ----------------------------
DROP TABLE IF EXISTS `good_types`;
CREATE TABLE `good_types`  (
  `tgt_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `tgt_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `tgt_is_show` char(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '是否显示',
  `tgt_order` int(2) NULL DEFAULT NULL COMMENT '类型排序',
  PRIMARY KEY (`tgt_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of good_types
-- ----------------------------
INSERT INTO `good_types` VALUES (1, '绿色蔬菜', '1', 1);
INSERT INTO `good_types` VALUES (2, '根茎类', '1', 2);
INSERT INTO `good_types` VALUES (3, '菌类', '1', 3);

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `u_username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `u_age` int(10) NULL DEFAULT NULL COMMENT '年龄',
  `u_score` double(8, 2) NULL DEFAULT NULL COMMENT '积分',
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------
INSERT INTO `tb_teacher` VALUES (1, 'admin', 12, 45.70);
INSERT INTO `tb_teacher` VALUES (2, 'hengyu', 23, 56.40);
INSERT INTO `tb_teacher` VALUES (3, 'test', 22, 67.80);
INSERT INTO `tb_teacher` VALUES (4, 'jocker', 25, 99.00);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_4wv83hfajry5tdoamn8wsqa6x`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '4564@163.com', '123123', '张三');
INSERT INTO `tb_user` VALUES ('2', 'asdf@163.com', 'qweqwwq', '英文');
INSERT INTO `tb_user` VALUES ('9', '123@123.com', '123123', '张明');

SET FOREIGN_KEY_CHECKS = 1;
