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

 Date: 13/10/2026 00:57:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key ID',
  `goods_id` int NULL DEFAULT NULL COMMENT 'Goods ID',
  `num` int NULL DEFAULT NULL COMMENT 'Quantity',
  `order_id` int NULL DEFAULT NULL COMMENT 'Order ID',
  `goods_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Goods Image',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Goods Name',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT 'Unit Price',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'Order Details' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (8, 6, 2, 15, 'http://localhost:9090/files/download/1781433106628-Snipaste_2026-06-14_16-51-17.jpg', 'Chong Kun Dang 鍾根堂 Pharm 鈣&鎂&鋅&維生素D補充錠, 180顆, 4罐', 11.00);
INSERT INTO `order_detail` VALUES (9, 4, 5, 15, 'http://localhost:9090/files/download/1781433020519-Snipaste_2026-06-14_16-51-04.jpg', ' SSelect 血紅素鐵膠囊, 60顆, 2包', 5.00);
INSERT INTO `order_detail` VALUES (10, 4, 3, 16, 'http://localhost:9090/files/download/1781433020519-Snipaste_2026-06-14_16-51-04.jpg', ' SSelect 血紅素鐵膠囊, 60顆, 2包', 5.00);

SET FOREIGN_KEY_CHECKS = 1;
