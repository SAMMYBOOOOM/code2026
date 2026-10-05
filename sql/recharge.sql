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

 Date: 05/10/2026 00:03:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for recharge
-- ----------------------------
DROP TABLE IF EXISTS `recharge`;
CREATE TABLE `recharge`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT 'Recharge Amount',
  `user_id` int NULL DEFAULT NULL COMMENT 'Recharger',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Payment Method',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Recharge Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'Recharge Record' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recharge
-- ----------------------------
INSERT INTO `recharge` VALUES (1, 123.00, 16, 'Visa card', '2026-10-04 23:55:46');
INSERT INTO `recharge` VALUES (2, 321.00, 16, 'Master card', '2026-10-04 23:55:56');
INSERT INTO `recharge` VALUES (3, 11.00, 15, 'Visa card', '2026-10-04 23:56:23');
INSERT INTO `recharge` VALUES (4, 122.00, 15, 'Master card', '2026-10-04 23:56:28');
INSERT INTO `recharge` VALUES (5, 1.00, 16, 'Visa card', '2026-10-04 23:58:13');
INSERT INTO `recharge` VALUES (6, 122.00, 16, 'Master card', '2026-10-04 23:58:22');

SET FOREIGN_KEY_CHECKS = 1;
