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

 Date: 14/06/2026 18:43:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Name',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Image',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT 'Price',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Brief Description',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT 'Details',
  `store` int NULL DEFAULT 0 COMMENT 'Stock',
  `category_id` int NULL DEFAULT NULL COMMENT 'Category ID',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Listing Status',
  `views` int NULL DEFAULT NULL COMMENT 'Page Views',
  `sale_count` int NULL DEFAULT NULL COMMENT 'Sales Count',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Creation Time',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'Product Information' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (2, '馬玉山 高纖大燕麥片, 2600g, 1盒', 'http://localhost:9090/files/download/1781432571308-Snipaste_2026-06-14_16-50-48.jpg', 123.00, '商品特色\n嚴選澳洲燕麥，口感自然，散發淡淡穀物香氣，帶來健康美味的早餐體驗。\n高纖燕麥片，提供豐富膳食纖維，幫助消化，增加飽足感，維持腸道健康。\n沖泡即食，簡單方便，節省時間，隨時享受美味燕麥片，輕鬆補充營養。\n商品主要規格\n有效期限(或保存期限)\n2027-02-03 或以後\n食用/料理方式\n即沖即食\n穀物分類\n燕麥\n是否為素食\n素食\n特殊成分/特色\n高纖維\n酷澎商品編號\n611406516617238 - 450524834906112', '<p>321<img src=\"http://localhost:9090/files/download/1781432588680-Snipaste_2026-06-14_16-50-48.jpg\" alt=\"\" data-href=\"\" style=\"\"/></p>', 123, 3, 'Listed', 0, 0, 'Sun Jun 14 18:23:12 CST 2026');
INSERT INTO `goods` VALUES (3, ' Hello Green 洋車前子殼粉末, 250g, 3個', 'http://localhost:9090/files/download/1781432894866-Snipaste_2026-06-14_16-50-55.jpg', 111.00, '233', '<p><img src=\"http://localhost:9090/files/download/1781432908417-Snipaste_2026-06-14_16-50-55.jpg\" alt=\"\" data-href=\"\" style=\"\"/></p>', 222, 5, 'Listed', 0, 0, 'Sun Jun 14 18:28:33 CST 2026');
INSERT INTO `goods` VALUES (4, ' SSelect 血紅素鐵膠囊, 60顆, 2包', 'http://localhost:9090/files/download/1781433020519-Snipaste_2026-06-14_16-51-04.jpg', 5.00, '商品主要規格\n有效期限(或保存期限)\n2028-02-28 或以後\n保健食品類型\n膠囊\n膠囊/錠片 總數量\n60片\n酷澎商品編號\n21007868203609 - 21022207758587', '<p><img src=\"http://localhost:9090/files/download/1781433047771-Snipaste_2026-06-14_16-51-04.jpg\" alt=\"\" data-href=\"\" style=\"\"/></p>', 222, 1, 'Listed', 0, 0, 'Sun Jun 14 18:30:48 CST 2026');
INSERT INTO `goods` VALUES (5, ' GOODIES 耐嚼型潔牙棒 3支, 85g, 18包, 雞肝風味', 'http://localhost:9090/files/download/1781433059047-Snipaste_2026-06-14_16-51-10.jpg', 110.00, '商品特色\n獨特條狀設計，有助於清潔牙齒和按摩牙齦，促進口腔健康。\n雞肝風味，美味可口，讓狗狗愛不釋手，增加食用樂趣。\n適合各種體型的成犬，無論大小都能享受潔牙的益處。\n商品主要規格\n保存期限\n2027-03-26 或以後\n是否無穀物添加\n無穀\n寵物適用年齡\n成犬\n食品特點\n日常維持\n適用寵物大小\n所有體型\n適用寵物類型\n狗\n產品質地/型態\n條狀\n酷澎商品編號\n568286865489920 - 222951752286220', '<p>1</p>', 22, 2, 'Listed', 0, 0, 'Sun Jun 14 18:31:21 CST 2026');
INSERT INTO `goods` VALUES (6, 'Chong Kun Dang 鍾根堂 Pharm 鈣&鎂&鋅&維生素D補充錠, 180顆, 4罐', 'http://localhost:9090/files/download/1781433106628-Snipaste_2026-06-14_16-51-17.jpg', 11.00, '1111', '<p>商品特色</p><p>補充鈣鎂鋅與維生素D，維持骨骼健康，提升身體保護力，適合全家大小。</p><p>每日輕鬆兩錠，滿足每日所需營養，告別瓶瓶罐罐，方便快捷，守護健康。</p><p>嚴選成分配方，支持神經肌肉功能，促進鈣質吸收，維持正常細胞分裂，增強體力。</p><p>商品主要規格</p><p>有效期限(或保存期限)</p><p>2026-09-30 或以後</p><p>膠囊/錠片 總數量</p><p>720顆</p><p>每份容量/重量/顆數</p><p>180顆</p><p>保健食品類型</p><p>丸片/膠囊</p><p>大容量商品</p><p>6個月供應</p><p>保健食品認證</p><p>有認證</p><p>製造日期</p><p>2024-06-17</p><p>每份重量</p><p>180g</p><p>保健食品使用對象</p><p>通用</p><p>功能性營養補充劑</p><p>身體保健</p><p>保健食品成分</p><p>複合營養劑</p><p>酷澎商品編號</p><p>21005545887952 - 21018822718804</p>', 2, 4, 'Listed', 0, 0, 'Sun Jun 14 18:32:28 CST 2026');
INSERT INTO `goods` VALUES (7, ' Kellogg\'s 家樂氏 CORN FLAKES 公雞玉米片 原味玉米片, 275g, 2盒', 'http://localhost:9090/files/download/1781433171963-Snipaste_2026-06-14_16-51-26.jpg', 11.00, '31', '<p><br></p><h5>商品特色</h5><ul><li>家樂氏玉米片，口感酥脆，搭配牛奶或豆漿，開啟活力滿滿的一天，美味又營養。</li><li>原味玉米片，簡單烘焙保留穀物營養，口感自然，每一口都充滿了玉米的香甜滋味。</li><li>早餐最佳選擇，家樂氏玉米片，方便快捷，只需幾分鐘，即可享受美味早餐，省時又省力。</li><li></li><li></li></ul><h5>商品主要規格</h5><p>有效期限(或保存期限)</p><p>2026-09-30 或以後</p><p>產品質地/型態</p><p>Flaked</p><p>特殊成分/特色</p><p>簡單烘焙/保留穀物營養</p><p>麥片口味</p><p>原味</p><p>包裝類型</p><p>Cardboard box with inner bag</p><p>麥片種類</p><p>Flakes</p><p>穀物分類</p><p>Corn-based</p><p>酷澎商品編號</p><p>293924392370186 - 400522787880962</p>', 231, 5, 'Listed', 0, 0, 'Sun Jun 14 18:33:06 CST 2026');

SET FOREIGN_KEY_CHECKS = 1;
