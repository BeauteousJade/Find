/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : androidapp

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-02-25 02:48:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for appreciate
-- ----------------------------
DROP TABLE IF EXISTS `appreciate`;
CREATE TABLE `appreciate` (
  `id` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `noteId` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `time` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `isRead` int(11) unsigned zerofill DEFAULT '00000000000',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of appreciate
-- ----------------------------

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `noteId` varchar(100) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES ('7ba0cd55-3ef6-4d8b-b9c3-278504cbb220', 'pby123@qq.com', '909ccc4e-46c8-4bac-a717-899785f83438', '2018-02-25 01:53:07');

-- ----------------------------
-- Table structure for discuss
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss` (
  `id` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin,
  `images` longtext COLLATE utf8_bin,
  `isChild` int(11) DEFAULT NULL,
  `parentEmail` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `parentContent` longtext COLLATE utf8_bin,
  `time` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `noteId` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `isRead` int(1) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `noteIdIndex` (`noteId`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of discuss
-- ----------------------------
INSERT INTO `discuss` VALUES ('024b3550-5ed7-4f01-b29a-88bb016862d8', '2196995023@qq.com', 0x5B696D6167655F656D6F743030372E706E675D5B696D6167655F656D6F743030372E706E675D5B696D6167655F656D6F743030372E706E675D5B696D6167655F656D6F743030372E706E675D5B696D6167655F656D6F743030372E706E675D5B696D6167655F656D6F743030372E706E675D5B696D6167655F656D6F743030372E706E675D5B696D6167655F656D6F743030372E706E675D5B696D6167655F656D6F743030372E706E675D, 0x5B5D, '0', null, null, '2018-02-25 01:51:52', '909ccc4e-46c8-4bac-a717-899785f83438', '1');
INSERT INTO `discuss` VALUES ('eeb53162-7fbb-4da8-bd19-fcd5891e263d', '2196995023@qq.com', 0x5B696D6167655F656D6F743033332E706E675D5B696D6167655F656D6F743033332E706E675D5B696D6167655F656D6F743033332E706E675D5B696D6167655F656D6F743033332E706E675D5B696D6167655F656D6F743033332E706E675D, 0x5B5D, '0', null, null, '2018-02-25 01:51:57', '909ccc4e-46c8-4bac-a717-899785f83438', '1');
INSERT INTO `discuss` VALUES ('4e8ae0d5-0fa6-487c-acbc-dc0bdd466019', '2196995023@qq.com', 0x5B696D6167655F656D6F743032312E706E675D5B696D6167655F656D6F743032312E706E675D5B696D6167655F656D6F743032312E706E675D, 0x5B5D, '0', null, null, '2018-02-25 01:52:03', '909ccc4e-46c8-4bac-a717-899785f83438', '1');
INSERT INTO `discuss` VALUES ('219409e4-fd6d-4819-8d2a-3dfe80248d00', 'pby123@qq.com', 0x5B696D6167655F656D6F743030302E706E675D5B696D6167655F656D6F743030302E706E675D5B696D6167655F656D6F743030302E706E675D, 0x5B5D, '1', '2196995023@qq.com', 0x5B696D6167655F656D6F743032312E706E675D5B696D6167655F656D6F743032312E706E675D5B696D6167655F656D6F743032312E706E675D, '2018-02-25 01:52:49', '909ccc4e-46c8-4bac-a717-899785f83438', '1');
INSERT INTO `discuss` VALUES ('97e9154c-b1f9-4e76-8cb6-1396b6c56a6d', 'pby123@qq.com', 0x5B696D6167655F656D6F743032312E706E675D5B696D6167655F656D6F743032312E706E675D5B696D6167655F656D6F743032312E706E675D5B696D6167655F656D6F743032312E706E675D5B696D6167655F656D6F743032312E706E675D5B696D6167655F656D6F743032312E706E675D5B696D6167655F656D6F743032312E706E675D5B696D6167655F656D6F743032312E706E675D5B696D6167655F656D6F743032312E706E675D, 0x5B5D, '0', null, null, '2018-02-25 01:52:56', '909ccc4e-46c8-4bac-a717-899785f83438', '1');

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow` (
  `id` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `followEmail` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `time` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `isRead` int(11) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `emailIndex` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES ('26869626-174c-43a1-b2f1-ee34b4f18d4a', 'pby123@qq.com', '2196995023@qq.com', '2018-02-25 01:53:02', '00000000001');
INSERT INTO `follow` VALUES ('2cdd75a5-1c4b-42d4-b81b-bdcbb0c0d3c0', '2196995023@qq.com', 'pby123@qq.com', '2018-02-25 01:55:02', null);

-- ----------------------------
-- Table structure for head
-- ----------------------------
DROP TABLE IF EXISTS `head`;
CREATE TABLE `head` (
  `id` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `head` varchar(100) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of head
-- ----------------------------

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin,
  `images` mediumtext COLLATE utf8_bin,
  `appreciateUserList` longtext COLLATE utf8_bin,
  `readNumber` int(11) DEFAULT NULL,
  `discussNumber` int(11) DEFAULT NULL,
  `areaName` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `time` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `areaIndex` (`areaName`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('74ef86b3-ea19-4e96-a54a-4297d3f7130d', '2196995023@qq.com', 0x5B696D6167655F656D6F743030332E706E675D5B696D6167655F656D6F743030332E706E675D, 0x5B5D, null, null, null, '运动', '2018-02-25 01:51:22');
INSERT INTO `note` VALUES ('ce3b4efd-ad17-4a13-ad24-e7d64ccc3999', '2196995023@qq.com', 0x5B696D6167655F656D6F743031362E706E675D5B696D6167655F656D6F743031362E706E675D, 0x5B5D, null, null, null, '运动', '2018-02-25 01:51:35');
INSERT INTO `note` VALUES ('9a8a7cb1-573d-4756-bda8-1525370c2402', '2196995023@qq.com', 0x5B696D6167655F656D6F743030322E706E675D5B696D6167655F656D6F743030322E706E675D, 0x5B5D, null, null, null, '运动', '2018-02-25 01:51:38');
INSERT INTO `note` VALUES ('909ccc4e-46c8-4bac-a717-899785f83438', '2196995023@qq.com', 0x5B696D6167655F656D6F743032352E706E675D5B696D6167655F656D6F743032352E706E675D, 0x5B5D, null, null, null, '运动', '2018-02-25 01:51:43');

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader` (
  `id` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `noteId` varchar(100) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES ('d9422206-9a47-492e-a668-a67180927f15', '2196995023@qq.com', '909ccc4e-46c8-4bac-a717-899785f83438', '2018-02-25 01:52:06');
INSERT INTO `reader` VALUES ('585ab8e4-0f7b-4bdd-8da4-83bcd9eae387', 'pby123@qq.com', '909ccc4e-46c8-4bac-a717-899785f83438', '2018-02-25 01:53:09');
INSERT INTO `reader` VALUES ('b9f09344-8f24-4871-bda6-1cfbb3758e59', '2196995023@qq.com', '9a8a7cb1-573d-4756-bda8-1525370c2402', '2018-02-25 02:13:14');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `autograph` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `head` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `experience` int(11) DEFAULT '0',
  `level` int(11) DEFAULT NULL,
  `time` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('pby123@qq.com', 'pby123', null, null, null, '0', '0', null);
INSERT INTO `user` VALUES ('pby2196995023@163.com', '12334', null, null, null, '0', null, null);
INSERT INTO `user` VALUES ('2196995023@qq.com', '123456', 'pby', null, '', '40', '0', null);
