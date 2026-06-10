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

 Date: 11/06/2026 00:38:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Account',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Password',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Name',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Avatar',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Role',
  `account` decimal(10, 2) NULL DEFAULT NULL COMMENT 'Account',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'Normal user' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'aaa', '123', 'sam0', 'http://localhost:9090/files/download/cat.png', 'user', 111.00);
INSERT INTO `user` VALUES (2, 'bbb', '123', 'sam1', 'http://localhost:9090/files/download/cat.png', 'user', 222.00);
INSERT INTO `user` VALUES (3, 'aaa', '123', 'sam0', 'http://localhost:9090/files/download/cat.png', 'user', 111.00);
INSERT INTO `user` VALUES (4, 'bbb', '123', 'sam1', 'http://localhost:9090/files/download/cat.png', 'user', 222.00);
INSERT INTO `user` VALUES (6, 'bbb', '123', 'sam1', 'http://localhost:9090/files/download/cat.png', 'user', 222.00);
INSERT INTO `user` VALUES (7, 'aaa', '123', 'sam0', 'http://localhost:9090/files/download/cat.png', 'user', 111.00);
INSERT INTO `user` VALUES (8, 'bbb', '123', 'sam1', 'http://localhost:9090/files/download/cat.png', 'user', 222.00);
INSERT INTO `user` VALUES (9, 'aaa', '123', 'sam0', 'http://localhost:9090/files/download/cat.png', 'user', 111.00);
INSERT INTO `user` VALUES (10, 'bbb', '123', 'sam1', 'http://localhost:9090/files/download/cat.png', 'user', 222.00);
INSERT INTO `user` VALUES (11, 'aaa', '123', 'sam0', 'http://localhost:9090/files/download/cat.png', 'user', 111.00);
INSERT INTO `user` VALUES (12, 'bbb', '123', 'sam1', 'http://localhost:9090/files/download/cat.png', 'user', 222.00);
INSERT INTO `user` VALUES (13, 'aaa', '123', 'sam0', 'http://localhost:9090/files/download/cat.png', 'user', 111.00);
INSERT INTO `user` VALUES (14, 'bbb', '123', 'sam1', 'http://localhost:9090/files/download/cat.png', 'user', 222.00);
INSERT INTO `user` VALUES (15, 'aaaa', '123', 'aaa', NULL, 'Normal User', 0.00);
INSERT INTO `user` VALUES (16, 'asd', '123', '1112', 'http://localhost:9090/files/download/1781109045043-avatar.png', 'Normal User', 0.00);
INSERT INTO `user` VALUES (17, '111', '123', 'asd', 'http://localhost:9090/files/download/1781108568311-cat.png', 'Normal User', 0.00);

SET FOREIGN_KEY_CHECKS = 1;
