/*
 Navicat Premium Dump SQL

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80046 (8.0.46)
 Source Host           : localhost:3306
 Source Schema         : code2026

 Target Server Type    : MySQL
 Target Server Version : 80046 (8.0.46)
 File Encoding         : 65001

 Date: 13/10/2026 16:37:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key ID',
  `score` double(10, 1) NULL DEFAULT NULL COMMENT 'Rating',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Review Content',
  `user_id` int NULL DEFAULT NULL COMMENT 'Reviewer',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Time',
  `order_id` int NULL DEFAULT NULL COMMENT 'Order ID',
  `goods_id` int NULL DEFAULT NULL COMMENT 'Goods ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'Order Review' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (2, 4.0, 'Soso ', 16, '2026-10-13 16:25:15', 15, 6);
INSERT INTO `comment` VALUES (3, 4.5, 'ah ha, it\'s good👋 123\nedited', 16, '2026-10-13 16:25:34', 15, 4);

SET FOREIGN_KEY_CHECKS = 1;
