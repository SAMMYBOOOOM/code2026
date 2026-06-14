/*
 Navicat Premium Dump SQL

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : code2026

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 15/06/2026 00:25:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key ID',
  `goods_id` int NULL DEFAULT NULL COMMENT 'Product ID',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Image',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'Carousel Information' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carousel
-- ----------------------------
INSERT INTO `carousel` VALUES (1, 2, 'http://localhost:9090/files/download/1781453919104-1781431915256-Snipaste_2026-06-14_16-50-48.jpg');
INSERT INTO `carousel` VALUES (2, 3, 'http://localhost:9090/files/download/1781454017995-1781432908417-Snipaste_2026-06-14_16-50-55.jpg');
INSERT INTO `carousel` VALUES (3, 4, 'http://localhost:9090/files/download/1781454023564-1781433020519-Snipaste_2026-06-14_16-51-04.jpg');

SET FOREIGN_KEY_CHECKS = 1;
