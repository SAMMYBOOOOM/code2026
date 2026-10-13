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

 Date: 12/10/2026 19:37:36
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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'Order Details' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES (2, 4, 3, 2, 'http://localhost:9090/files/download/1781433020519-Snipaste_2026-06-14_16-51-04.jpg', ' SSelect 血紅素鐵膠囊, 60顆, 2包', 5.00);
INSERT INTO `order_detail` VALUES (3, 4, 4, 3, 'http://localhost:9090/files/download/1781433020519-Snipaste_2026-06-14_16-51-04.jpg', ' SSelect 血紅素鐵膠囊, 60顆, 2包', 5.00);
INSERT INTO `order_detail` VALUES (4, 6, 1, 3, 'http://localhost:9090/files/download/1781433106628-Snipaste_2026-06-14_16-51-17.jpg', 'Chong Kun Dang 鍾根堂 Pharm 鈣&鎂&鋅&維生素D補充錠, 180顆, 4罐', 11.00);
INSERT INTO `order_detail` VALUES (5, 7, 6, 3, 'http://localhost:9090/files/download/1781433171963-Snipaste_2026-06-14_16-51-26.jpg', ' Kellogg\'s 家樂氏 CORN FLAKES 公雞玉米片 原味玉米片, 275g, 2盒', 11.00);
INSERT INTO `order_detail` VALUES (7, 7, 1, 5, 'http://localhost:9090/files/download/1781433171963-Snipaste_2026-06-14_16-51-26.jpg', ' Kellogg\'s 家樂氏 CORN FLAKES 公雞玉米片 原味玉米片, 275g, 2盒', 11.00);

SET FOREIGN_KEY_CHECKS = 1;
