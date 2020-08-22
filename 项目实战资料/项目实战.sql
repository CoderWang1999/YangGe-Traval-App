/*
 Navicat Premium Data Transfer

 Source Server         : mysql_same
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 192.168.23.129:3306
 Source Schema         : db4

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 22/08/2020 16:37:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES ('1', '北京');
INSERT INTO `city` VALUES ('2', '上海');
INSERT INTO `city` VALUES ('3', '广州');
INSERT INTO `city` VALUES ('4', '深圳');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nickname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phoneNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('3306', '枣子哥', '叫爸爸', '19999999999', 'ZZ@qq.com');
INSERT INTO `member` VALUES ('E61D65F673D54F68B0861025C69773DB', '张三', '小三', '18888888888', 'zs@163.com');

-- ----------------------------
-- Table structure for order_traveller
-- ----------------------------
DROP TABLE IF EXISTS `order_traveller`;
CREATE TABLE `order_traveller`  (
  `orderId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `travellerId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`orderId`, `travellerId`) USING BTREE,
  INDEX `travellerId`(`travellerId`) USING BTREE,
  CONSTRAINT `order_traveller_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_traveller_ibfk_2` FOREIGN KEY (`travellerId`) REFERENCES `traveller` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_traveller
-- ----------------------------
INSERT INTO `order_traveller` VALUES ('121212121212', '121212');
INSERT INTO `order_traveller` VALUES ('CA005CF1BE3C4EF68F88ABC7DF30E976', '121212');
INSERT INTO `order_traveller` VALUES ('CA005CF1BE3C4EF68F88ABC7DF30E976', '1313');
INSERT INTO `order_traveller` VALUES ('121212121212', '212121');
INSERT INTO `order_traveller` VALUES ('CA005CF1BE3C4EF68F88ABC7DF30E976', '212121');
INSERT INTO `order_traveller` VALUES ('0E7231DC797C486290E8713CA3C6ECCC', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E');
INSERT INTO `order_traveller` VALUES ('5DC6A48DD4E94592AE904930EA866AFA', '3FE27DF2A4E44A6DBC5D0FE4651D3D3E');
INSERT INTO `order_traveller` VALUES ('3081770BC3984EF092D9E99760FDABDE', 'EE7A71FB6945483FBF91543DBE851960');
INSERT INTO `order_traveller` VALUES ('55F9AF582D5A4DB28FB4EC3199385762', 'EE7A71FB6945483FBF91543DBE851960');
INSERT INTO `order_traveller` VALUES ('CA005CF1BE3C4EF68F88ABC7DF30E976', 'EE7A71FB6945483FBF91543DBE851960');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `orderNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `orderTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `peopleCount` int(11) DEFAULT NULL,
  `orderDesc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `payType` int(11) DEFAULT NULL,
  `orderStatus` int(11) DEFAULT NULL,
  `productId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `memberId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `orderNum`(`orderNum`) USING BTREE,
  INDEX `productId`(`productId`) USING BTREE,
  INDEX `memberId`(`memberId`) USING BTREE,
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`memberId`) REFERENCES `member` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0E7231DC797C486290E8713CA3C6ECCC', '12345', '2020-08-22 15:21:45', 1, '没什么', 0, 1, '676C5BD1D35E429A8C2E114939C5685A', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('121212121212', '1211111', '2020-08-22 16:03:50', 2, '妙极了', 1, 1, '9F71F01CB448476DAFB309AA6DF9497F', '3306');
INSERT INTO `orders` VALUES ('3081770BC3984EF092D9E99760FDABDE', '55555', '2020-08-22 15:25:24', 1, '没什么', 1, 1, '9F71F01CB448476DAFB309AA6DF9497F', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('55F9AF582D5A4DB28FB4EC3199385762', '33333', '2020-08-22 15:25:31', 1, '没什么', 1, 0, '9F71F01CB448476DAFB309AA6DF9497F', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('5DC6A48DD4E94592AE904930EA866AFA', '54321', '2020-08-22 15:21:28', 1, '没什么', 0, 1, '676C5BD1D35E429A8C2E114939C5685A', 'E61D65F673D54F68B0861025C69773DB');
INSERT INTO `orders` VALUES ('CA005CF1BE3C4EF68F88ABC7DF30E976', '44444', '2020-08-22 15:58:13', 4, '没什么', 0, 0, '9F71F01CB448476DAFB309AA6DF9497F', 'E61D65F673D54F68B0861025C69773DB');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permissionName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '权限管理', 'permission');
INSERT INTO `permission` VALUES ('2', '访问日志', 'syslog');
INSERT INTO `permission` VALUES ('3', '订单管理', 'basedata/ordres');
INSERT INTO `permission` VALUES ('4', '用户管理', 'user');
INSERT INTO `permission` VALUES ('5', '角色管理', 'role');
INSERT INTO `permission` VALUES ('6', '产品管理', 'product');
INSERT INTO `permission` VALUES ('633c7065bfa04ca6993066b53336a35c', '城市查询', 'city');
INSERT INTO `permission` VALUES ('b70dded5401544dea6c1bd695caa56f5', '产品的模糊查询', 'basedata');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `productNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `productName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cityName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `DepartureTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `productPrice` double DEFAULT NULL,
  `productDesc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `product`(`id`, `productNum`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('676C5BD1D35E429A8C2E114939C5685A', 'itcast-002', '北京三日游', '北京', '2020-08-22 14:33:27', 1200, '不错的旅行', 0);
INSERT INTO `product` VALUES ('9F71F01CB448476DAFB309AA6DF9497F', 'itcast-003', '上海五日游', '上海', '2019-07-27 20:13:52', 1800, '魔都我来了', 0);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `roleName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleDesc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '老师', '全部权限');
INSERT INTO `role` VALUES ('770b5cfca1b24042a7779c0d32491156', '学生', '浏览页面');
INSERT INTO `role` VALUES ('ff030914fb714bf0b959f95b04a99de7', '管理员', '全部权限');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `permissionId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`permissionId`, `roleId`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` VALUES ('2', '1');
INSERT INTO `role_permission` VALUES ('3', '1');
INSERT INTO `role_permission` VALUES ('4', '1');
INSERT INTO `role_permission` VALUES ('5', '1');
INSERT INTO `role_permission` VALUES ('6', '1');
INSERT INTO `role_permission` VALUES ('633c7065bfa04ca6993066b53336a35c', '1');
INSERT INTO `role_permission` VALUES ('b70dded5401544dea6c1bd695caa56f5', '1');
INSERT INTO `role_permission` VALUES ('2', '770b5cfca1b24042a7779c0d32491156');
INSERT INTO `role_permission` VALUES ('5', '770b5cfca1b24042a7779c0d32491156');
INSERT INTO `role_permission` VALUES ('6', '770b5cfca1b24042a7779c0d32491156');
INSERT INTO `role_permission` VALUES ('1', 'ff030914fb714bf0b959f95b04a99de7');
INSERT INTO `role_permission` VALUES ('2', 'ff030914fb714bf0b959f95b04a99de7');
INSERT INTO `role_permission` VALUES ('3', 'ff030914fb714bf0b959f95b04a99de7');
INSERT INTO `role_permission` VALUES ('4', 'ff030914fb714bf0b959f95b04a99de7');
INSERT INTO `role_permission` VALUES ('5', 'ff030914fb714bf0b959f95b04a99de7');
INSERT INTO `role_permission` VALUES ('6', 'ff030914fb714bf0b959f95b04a99de7');
INSERT INTO `role_permission` VALUES ('633c7065bfa04ca6993066b53336a35c', 'ff030914fb714bf0b959f95b04a99de7');
INSERT INTO `role_permission` VALUES ('b70dded5401544dea6c1bd695caa56f5', 'ff030914fb714bf0b959f95b04a99de7');

-- ----------------------------
-- Table structure for syslog
-- ----------------------------
DROP TABLE IF EXISTS `syslog`;
CREATE TABLE `syslog`  (
  `ID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `visit_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `execution_time` int(255) DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of syslog
-- ----------------------------
INSERT INTO `syslog` VALUES ('03978d231e994627a6addb4c573ce304', '2020-08-22 14:56:03', '111@qq.com', '0:0:0:0:0:0:0:1', '/product', 15, 'toUpdate');
INSERT INTO `syslog` VALUES ('04c4e1d1c5aa42faaaa6b41be8ad4e90', '2020-08-22 15:16:09', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 9, 'toUpdateUser');
INSERT INTO `syslog` VALUES ('05af19bb8c6b417e9e8fe9a11115ce5a', '2020-08-22 15:12:15', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 85, 'findAll');
INSERT INTO `syslog` VALUES ('0744db9e8a1645b0a597c3d006fdd246', '2020-08-22 15:15:54', '111@qq.com', '0:0:0:0:0:0:0:1', '/servlet/role', 12, 'findRoleAndPermission');
INSERT INTO `syslog` VALUES ('09f215a872ae4c01a44e8a559f99069c', '2020-08-22 15:01:48', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 8, 'findAll');
INSERT INTO `syslog` VALUES ('0bd807f9d03143eb8f303acf8bfaba44', '2020-08-22 15:16:53', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 13, 'findAll');
INSERT INTO `syslog` VALUES ('168373041cb14effb92a1c0b920f608f', '2020-08-22 15:15:36', '111@qq.com', '0:0:0:0:0:0:0:1', '/permission', 10, 'save');
INSERT INTO `syslog` VALUES ('16b28e7d37c5436e933c11348a9bbf34', '2020-08-22 15:16:18', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 5, 'toUpdateUser');
INSERT INTO `syslog` VALUES ('186b85114bca46ee9d8bab27c415e4b2', '2020-08-22 14:52:29', '111@qq.com', '0:0:0:0:0:0:0:1', '/product', 4, 'toUpdate');
INSERT INTO `syslog` VALUES ('1c68613aacc24eb2a725f306f9217a11', '2020-08-22 15:17:37', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 10, 'findAll');
INSERT INTO `syslog` VALUES ('1d79dbad23894101bb18892002a06afa', '2020-08-22 15:16:11', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 3, 'toUpdateUser');
INSERT INTO `syslog` VALUES ('1eaf406f13ed42c7afe4aa4dd9894fd5', '2020-08-22 15:56:28', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 84, 'findAll');
INSERT INTO `syslog` VALUES ('20155074e118412aac5d349b6c8119d7', '2020-08-22 15:01:42', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 8, 'findAll');
INSERT INTO `syslog` VALUES ('2073274ec87740a8a09de49fa064d847', '2020-08-22 15:55:09', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 8, 'findAll');
INSERT INTO `syslog` VALUES ('2431a81e695a44b1909a3836a1e954c2', '2020-08-22 15:17:27', '111@qq.com', '0:0:0:0:0:0:0:1', '/product', 5, 'findAll');
INSERT INTO `syslog` VALUES ('246f1d7f49fa4327900977008f4f51e0', '2020-08-22 15:02:27', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 9, 'findAll');
INSERT INTO `syslog` VALUES ('264cbc4e4cc3485cb7ecfb38906ba9f8', '2020-08-22 16:07:36', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 9, 'login');
INSERT INTO `syslog` VALUES ('29fb5cabc9f94babb13f09c68933d8a8', '2020-08-22 15:43:52', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 5, 'show');
INSERT INTO `syslog` VALUES ('2c223a3bbf29414dbaecc2edb531bfb8', '2020-08-22 15:55:19', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 4, 'show');
INSERT INTO `syslog` VALUES ('2c62ce81d5e642e0a03ccb7098d49e4d', '2020-08-22 16:08:27', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 5, 'show');
INSERT INTO `syslog` VALUES ('2e2e46c2bbd94f578664f1d5c2c8cf60', '2020-08-22 15:15:56', '111@qq.com', '0:0:0:0:0:0:0:1', '/servlet/role', 10, 'findRoleAndPermission');
INSERT INTO `syslog` VALUES ('2f565a02567c498593239a0a6ac28e46', '2020-08-22 15:15:40', '111@qq.com', '0:0:0:0:0:0:0:1', '/permission', 5, 'findAll');
INSERT INTO `syslog` VALUES ('2f7a719b7f814f44b974dc6179f2bda9', '2020-08-22 15:27:14', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 4, 'show');
INSERT INTO `syslog` VALUES ('34bb8b0e0f7f47ddb55408e5468fadb8', '2020-08-22 15:57:09', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 6, 'show');
INSERT INTO `syslog` VALUES ('368cea2249004390af7dc373f23a790b', '2020-08-22 16:35:52', '111@qq.com', '0:0:0:0:0:0:0:1', '/servlet/role', 7, 'findAll');
INSERT INTO `syslog` VALUES ('379365ff855c4d93a8360d6a998301e1', '2020-08-22 15:01:10', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 493, 'login');
INSERT INTO `syslog` VALUES ('3dccb36550444974bfe9dcd724f3ad9a', '2020-08-22 15:19:45', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 8, 'login');
INSERT INTO `syslog` VALUES ('420a74021e854551915f9f12462b5deb', '2020-08-22 16:35:34', '111@qq.com', '0:0:0:0:0:0:0:1', '/servlet/role', 79, 'findRoleAndPermission');
INSERT INTO `syslog` VALUES ('432a7b19d21841c4a5298c27317c4e41', '2020-08-22 15:16:31', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 11, 'findAll');
INSERT INTO `syslog` VALUES ('48b271d02b3d45eea07889af5ffa5d06', '2020-08-22 15:01:32', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 9, 'findAll');
INSERT INTO `syslog` VALUES ('494c85daf226406c840d783278b5256f', '2020-08-22 15:16:34', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 8, 'findAll');
INSERT INTO `syslog` VALUES ('4a032d6880dd4025a0a73c12e8b97c57', '2020-08-22 15:01:35', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 12, 'findAll');
INSERT INTO `syslog` VALUES ('4d26e542f639433eb1ee879da1ffe885', '2020-08-22 16:35:42', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 13, 'findAll');
INSERT INTO `syslog` VALUES ('4f0856928c8140b599ed7e834adbfc9a', '2020-08-22 15:31:01', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 9, 'findAll');
INSERT INTO `syslog` VALUES ('58b0626f90de458c854f1690319c2e3b', '2020-08-22 15:04:25', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 7, 'findAll');
INSERT INTO `syslog` VALUES ('5beaaf2622a241349c3609ce4d32cb3c', '2020-08-22 16:07:45', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 7, 'findAll');
INSERT INTO `syslog` VALUES ('5ee006fc099f4be49a74dc89a170f59a', '2020-08-22 16:35:46', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 4, 'toUpdateUser');
INSERT INTO `syslog` VALUES ('61c32baf11964e1f9e638fe9b78d92f0', '2020-08-22 15:02:32', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 8, 'findAll');
INSERT INTO `syslog` VALUES ('62f51db12bc84764ae584a1cf8905c10', '2020-08-22 15:55:09', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 6, 'findAll');
INSERT INTO `syslog` VALUES ('64c4e315942c4dc78a6aee3492979be5', '2020-08-22 15:55:16', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 7, 'show');
INSERT INTO `syslog` VALUES ('66dd78b947554e23ae3b633ea6e521e8', '2020-08-22 15:13:50', '111@qq.com', '0:0:0:0:0:0:0:1', '/permission', 84, 'findAll');
INSERT INTO `syslog` VALUES ('681545b5aefc4536816d91332225f6af', '2020-08-22 15:27:19', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 5, 'show');
INSERT INTO `syslog` VALUES ('6847b97123434eff948c68dc0456a3ad', '2020-08-22 15:31:04', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 6, 'findAll');
INSERT INTO `syslog` VALUES ('69bc352d64a44ffaa73c0f6f2bcc3fec', '2020-08-22 15:15:48', '111@qq.com', '0:0:0:0:0:0:0:1', '/servlet/role', 23, 'updateRolePermission');
INSERT INTO `syslog` VALUES ('6ba0a4f6f7f2426586da59bab187df8b', '2020-08-22 15:04:25', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 8, 'findAll');
INSERT INTO `syslog` VALUES ('6e65699056614891ae6b1820c749697d', '2020-08-22 14:52:26', '111@qq.com', '0:0:0:0:0:0:0:1', '/product', 7, 'findAll');
INSERT INTO `syslog` VALUES ('70b15dfba40149b095264b7b2f9a7b45', '2020-08-22 15:01:24', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 17, 'findAll');
INSERT INTO `syslog` VALUES ('72136a373eed4977bf95984e46388774', '2020-08-22 15:47:03', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 647, 'show');
INSERT INTO `syslog` VALUES ('73a7d2d0e8054ce09377471ec4b5e922', '2020-08-22 14:55:56', '111@qq.com', '0:0:0:0:0:0:0:1', '/product', 136, 'list');
INSERT INTO `syslog` VALUES ('74e1e553a7ac43849a2ed7e0c7b1f71e', '2020-08-22 14:55:51', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 501, 'login');
INSERT INTO `syslog` VALUES ('754001c4f11e4c3e915e85407a1b3148', '2020-08-22 14:55:54', '111@qq.com', '0:0:0:0:0:0:0:1', '/product', 126, 'findAll');
INSERT INTO `syslog` VALUES ('771ca3209a96438fb67f393e31ef1679', '2020-08-22 15:17:53', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 16, 'show');
INSERT INTO `syslog` VALUES ('7f2f9e0b539646d9a3c3e5b6185f04cd', '2020-08-22 14:55:31', '111@qq.com', '0:0:0:0:0:0:0:1', '/product', 161, 'list');
INSERT INTO `syslog` VALUES ('80b0bcb3f0254102b6ce9a87c3550c36', '2020-08-22 16:35:32', '111@qq.com', '0:0:0:0:0:0:0:1', '/servlet/role', 61, 'findAll');
INSERT INTO `syslog` VALUES ('814cc5cd545f416eac1b8d10b72240cc', '2020-08-22 15:17:28', '111@qq.com', '0:0:0:0:0:0:0:1', '/city', 3, 'findAll');
INSERT INTO `syslog` VALUES ('815df64980dc4799b88bf5c24fe8cf3d', '2020-08-22 15:55:27', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 5, 'show');
INSERT INTO `syslog` VALUES ('84dd1c3b06b542cea37c2772bb719982', '2020-08-22 15:16:20', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 3, 'toUpdateUser');
INSERT INTO `syslog` VALUES ('86460e66e3b84be4b2d70d247c8a5d41', '2020-08-22 15:17:02', '111@qq.com', '0:0:0:0:0:0:0:1', '/product', 23, 'findAll');
INSERT INTO `syslog` VALUES ('865c713ccf8c47a2a73a86bab4b05fc3', '2020-08-22 15:01:13', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 593, 'findAll');
INSERT INTO `syslog` VALUES ('87a8bac81d344d619a62d747effb84d5', '2020-08-22 15:14:33', '111@qq.com', '0:0:0:0:0:0:0:1', '/permission', 6, 'findAll');
INSERT INTO `syslog` VALUES ('87b1be4afa0844e6adf8fec01d91e425', '2020-08-22 16:35:44', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 10, 'toUpdateUser');
INSERT INTO `syslog` VALUES ('87c1a66816284849b0ee87e210b43cb1', '2020-08-22 15:12:18', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 12, 'findAll');
INSERT INTO `syslog` VALUES ('88459932641547b2a344f9e3a916f6d3', '2020-08-22 15:26:50', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 10, 'findAll');
INSERT INTO `syslog` VALUES ('8d628f83bd4c4e7db0ed91cf4a58aa46', '2020-08-22 15:40:00', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 5, 'show');
INSERT INTO `syslog` VALUES ('8fbefa05da9641d4b87d8e990a6e6602', '2020-08-22 15:08:10', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 496, 'login');
INSERT INTO `syslog` VALUES ('912354ea993a4bae89a97c7999b00abb', '2020-08-22 15:17:26', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 9, 'findAll');
INSERT INTO `syslog` VALUES ('92531be2cfa748ebb1ce18744185fafe', '2020-08-22 15:19:48', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 6, 'login');
INSERT INTO `syslog` VALUES ('9759dec23a674c7e913924043f9c142e', '2020-08-22 16:09:41', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 8, 'findAll');
INSERT INTO `syslog` VALUES ('98bcad7dbe3848d48bc0ace90f844202', '2020-08-22 15:14:20', '111@qq.com', '0:0:0:0:0:0:0:1', '/permission', 2, 'toSave');
INSERT INTO `syslog` VALUES ('99071c67036d4633a306b3ac27b13ff6', '2020-08-22 15:17:05', '111@qq.com', '0:0:0:0:0:0:0:1', '/city', 174, 'findAll');
INSERT INTO `syslog` VALUES ('99b388186444486b875da1ce779d17d2', '2020-08-22 15:15:21', '111@qq.com', '0:0:0:0:0:0:0:1', '/permission', 2, 'toSave');
INSERT INTO `syslog` VALUES ('9d136a7fa6a74b81a71cd8516eaf0e0d', '2020-08-22 16:07:41', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 10, 'findAll');
INSERT INTO `syslog` VALUES ('9ed49b19861e43078c9bfd68edb9f93d', '2020-08-22 15:56:25', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 507, 'login');
INSERT INTO `syslog` VALUES ('a109fd7996c8466f92801d367d802b51', '2020-08-22 15:57:01', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 9, 'show');
INSERT INTO `syslog` VALUES ('a5c07010424a48efaf226222f7095802', '2020-08-22 15:01:41', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 9, 'findAll');
INSERT INTO `syslog` VALUES ('aa2e7b54781443c483417fc964f83756', '2020-08-22 15:56:33', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 21, 'show');
INSERT INTO `syslog` VALUES ('ade13d3a3cf64dbeb5654e3e271b57c0', '2020-08-22 16:08:32', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 5, 'show');
INSERT INTO `syslog` VALUES ('aec18af7ca5c4df294c0947c478d5950', '2020-08-22 15:12:11', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 692, 'login');
INSERT INTO `syslog` VALUES ('b2448ef2a9524011b4c1fd01b3ed8b73', '2020-08-22 15:04:32', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 8, 'findAll');
INSERT INTO `syslog` VALUES ('b44a3bff15764cbbb752935be5210127', '2020-08-22 15:08:26', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 14, 'findAll');
INSERT INTO `syslog` VALUES ('b4d9ff53e1ae4dffab215a8b7fe26ca7', '2020-08-22 15:43:45', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 9, 'findAll');
INSERT INTO `syslog` VALUES ('b4f2047952274ad6be934d32c1cb7db4', '2020-08-22 15:16:25', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 8, 'login');
INSERT INTO `syslog` VALUES ('b6ba1f9edee846b09edcb5df989ab9ec', '2020-08-22 15:26:59', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 5, 'show');
INSERT INTO `syslog` VALUES ('ba1218d012ee4ab1a0d8c94658694135', '2020-08-22 15:08:19', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 16, 'show');
INSERT INTO `syslog` VALUES ('bbcf445e51cb4976a889b8bc60301e6e', '2020-08-22 16:07:44', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 6, 'findAll');
INSERT INTO `syslog` VALUES ('becc90727d3143ed9c0d949e8a420719', '2020-08-22 16:08:17', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 7, 'show');
INSERT INTO `syslog` VALUES ('bf34cea07f36469490eb7e2d9cea01d8', '2020-08-22 15:57:04', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 6, 'show');
INSERT INTO `syslog` VALUES ('c1f51f9d2d96414f8c52b8747f6941c8', '2020-08-22 15:13:55', '111@qq.com', '0:0:0:0:0:0:0:1', '/permission', 6, 'toSave');
INSERT INTO `syslog` VALUES ('c25f3e63f9d24e5b96ce25a84382dc69', '2020-08-22 15:15:44', '111@qq.com', '0:0:0:0:0:0:0:1', '/servlet/role', 11, 'findRoleAndPermission');
INSERT INTO `syslog` VALUES ('c2b7012e257c4729b87c8a0bad403016', '2020-08-22 15:04:26', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 7, 'findAll');
INSERT INTO `syslog` VALUES ('c89d574d4c3848859c7c692c5e49cceb', '2020-08-22 15:16:05', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 15, 'findAll');
INSERT INTO `syslog` VALUES ('cc825bb3a8a14bb18f36e0b6f5477594', '2020-08-22 15:15:42', '111@qq.com', '0:0:0:0:0:0:0:1', '/servlet/role', 14, 'findAll');
INSERT INTO `syslog` VALUES ('d21999d798b648eab742839e8ff5bee7', '2020-08-22 15:04:24', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 10, 'findAll');
INSERT INTO `syslog` VALUES ('d2e5c6722a2a4d168261380a3cf613bf', '2020-08-22 14:55:28', '111@qq.com', '0:0:0:0:0:0:0:1', '/product', 4, 'findAll');
INSERT INTO `syslog` VALUES ('d48e6b58b21a4ff3afd12ec0ffc284ba', '2020-08-22 15:31:05', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 7, 'findAll');
INSERT INTO `syslog` VALUES ('d49047717c8b44c1b73daa6d4b90484c', '2020-08-22 15:14:31', '111@qq.com', '0:0:0:0:0:0:0:1', '/permission', 58, 'save');
INSERT INTO `syslog` VALUES ('d514e7e246eb4d15932755fbd85270c3', '2020-08-22 16:34:53', '111@qq.com', '0:0:0:0:0:0:0:1', '/system/user', 486, 'login');
INSERT INTO `syslog` VALUES ('d70832c4ac1741c59e0117933013a060', '2020-08-22 15:31:08', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 4, 'show');
INSERT INTO `syslog` VALUES ('dbabc3418aa0432e856b6834eeea3e52', '2020-08-22 16:09:46', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 8, 'findAll');
INSERT INTO `syslog` VALUES ('dc8b9092a22f4e8b8fe702c86d38924e', '2020-08-22 15:17:05', '111@qq.com', '0:0:0:0:0:0:0:1', '/product', 8, 'toUpdate');
INSERT INTO `syslog` VALUES ('dde9973e628340c8add90fe437566fc8', '2020-08-22 15:16:00', '111@qq.com', '0:0:0:0:0:0:0:1', '/servlet/role', 19, 'updateRolePermission');
INSERT INTO `syslog` VALUES ('dfd86c2bbd8647e58398a43d5b7adca8', '2020-08-22 16:36:08', '111@qq.com', '0:0:0:0:0:0:0:1', '/servlet/role', 9, 'list');
INSERT INTO `syslog` VALUES ('e238a3f321de424caf84bcc964868b4f', '2020-08-22 15:01:52', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 8, 'findAll');
INSERT INTO `syslog` VALUES ('e2ceeb365ce64c2891b8258b48e28f02', '2020-08-22 14:55:26', '111@qq.com', '0:0:0:0:0:0:0:1', '/product', 6, 'findAll');
INSERT INTO `syslog` VALUES ('e6d566cc29ef4d6c80a27faa455fb049', '2020-08-22 15:04:27', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 9, 'findAll');
INSERT INTO `syslog` VALUES ('eef24afec5954569b284c5a5a4e43785', '2020-08-22 16:35:38', '111@qq.com', '0:0:0:0:0:0:0:1', '/servlet/role', 8, 'findAll');
INSERT INTO `syslog` VALUES ('efeced45a7674d8d9f9a41cb0f921177', '2020-08-22 15:31:04', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 6, 'findAll');
INSERT INTO `syslog` VALUES ('f563b5437a7444c881452616071246b5', '2020-08-22 15:15:51', '111@qq.com', '0:0:0:0:0:0:0:1', '/servlet/role', 10, 'findRoleAndPermission');
INSERT INTO `syslog` VALUES ('f885dbd13fe74539973b45bf3020bd79', '2020-08-22 15:17:28', '111@qq.com', '0:0:0:0:0:0:0:1', '/product', 4, 'toUpdate');
INSERT INTO `syslog` VALUES ('f9d2a0bce0ea45e9bea7f05a886e8124', '2020-08-22 15:26:52', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 4, 'show');
INSERT INTO `syslog` VALUES ('fbfba42b243b439cacaaf5017877ea61', '2020-08-22 15:01:44', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 7, 'findAll');
INSERT INTO `syslog` VALUES ('fc15e7464dce41b7af0025939b06f56a', '2020-08-22 15:08:14', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 67, 'findAll');
INSERT INTO `syslog` VALUES ('fe9c6cd7873f47daa2ad4ce5ba2bb804', '2020-08-22 15:31:06', '111@qq.com', '0:0:0:0:0:0:0:1', '/basedata/ordres', 7, 'findAll');

-- ----------------------------
-- Table structure for traveller
-- ----------------------------
DROP TABLE IF EXISTS `traveller`;
CREATE TABLE `traveller`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phoneNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `credentialsType` int(11) DEFAULT NULL,
  `credentialsNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `travellerType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of traveller
-- ----------------------------
INSERT INTO `traveller` VALUES ('121212', '柳岩', '女', '18888888888', 0, '8899664465565', 1);
INSERT INTO `traveller` VALUES ('1313', '彭于晏', '男', '16666666666', 0, '52345423523524', 1);
INSERT INTO `traveller` VALUES ('212121', '李思思', '女', '19999999999', 0, '42342424224', 1);
INSERT INTO `traveller` VALUES ('3FE27DF2A4E44A6DBC5D0FE4651D3D3E', '张龙', '男', '13333333333', 0, '123456789009876543', 0);
INSERT INTO `traveller` VALUES ('EE7A71FB6945483FBF91543DBE851960', '张小龙', '男', '15555555555', 0, '987654321123456789', 0);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `PASSWORD` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phoneNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '111@qq.com', 'red', 'xMpCOKC5I4INzFCab3WEmw==', '13856121039', 1);
INSERT INTO `users` VALUES ('2', '333@qq.com', 'pink', 'xMpCOKC5I4INzFCab3WEmw==', '13345608590', 1);

-- ----------------------------
-- Table structure for users_role
-- ----------------------------
DROP TABLE IF EXISTS `users_role`;
CREATE TABLE `users_role`  (
  `userId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `roleId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`userId`, `roleId`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `users_role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `users_role_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users_role
-- ----------------------------
INSERT INTO `users_role` VALUES ('2', '770b5cfca1b24042a7779c0d32491156');
INSERT INTO `users_role` VALUES ('1', 'ff030914fb714bf0b959f95b04a99de7');

SET FOREIGN_KEY_CHECKS = 1;
