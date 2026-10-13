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

 Date: 12/10/2026 19:37:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key ID',
  `order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Order Number',
  `total` decimal(10, 2) NULL DEFAULT NULL COMMENT 'Total Price',
  `user_id` int NULL DEFAULT NULL COMMENT 'Orderer ID',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Status',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Order Time',
  `deliver_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Delivery Type',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'Order Information' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (2, '2026101217918492787203843', 15.00, 16, 'Pending', '2026-10-12 18:54:38', NULL);
INSERT INTO `orders` VALUES (3, '2026101217918506875809065', 97.00, 16, 'Pending', '2026-10-12 19:18:07', NULL);
INSERT INTO `orders` VALUES (5, '2026101217918510095510221', 11.00, 16, 'Pending', '2026-10-12 19:23:29', NULL);

SET FOREIGN_KEY_CHECKS = 1;
