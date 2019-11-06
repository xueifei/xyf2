/*
Navicat MySQL Data Transfer

Source Server         : xue
Source Server Version : 50637
Source Host           : 127.0.0.1:3306
Source Database       : day1024

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2019-11-06 17:07:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `attendance_time` datetime DEFAULT NULL COMMENT '记录每天打卡时间',
  `status` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '状态',
  `time` datetime NOT NULL COMMENT '记录考勤时间',
  `s_id` int(11) NOT NULL COMMENT '对应学生详情表中id 唯一标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('1', '张三', '1899-12-31 00:00:00', '1', '2019-11-02 22:33:47', '1');
INSERT INTO `attendance` VALUES ('2', '李四', '2019-10-24 00:00:00', '0', '2019-11-02 22:33:51', '2');
INSERT INTO `attendance` VALUES ('3', '小五', '2019-10-24 00:00:00', '3', '2019-11-02 22:33:54', '3');
INSERT INTO `attendance` VALUES ('4', '张三', '1899-12-31 00:00:00', '1', '2019-11-02 22:34:00', '3');
INSERT INTO `attendance` VALUES ('5', '李四', '2019-10-24 15:24:00', '0', '2019-11-02 22:34:03', '3');
INSERT INTO `attendance` VALUES ('6', '小五', '2019-10-24 15:24:00', '1', '2019-11-02 00:09:22', '1');
INSERT INTO `attendance` VALUES ('7', '张三', '1899-12-31 00:00:00', '2', '2019-11-02 00:09:26', '2');
INSERT INTO `attendance` VALUES ('8', '李四', '2019-10-24 15:24:00', '2', '2019-11-01 00:07:29', '3');
INSERT INTO `attendance` VALUES ('115', '张三', '1899-12-31 00:00:00', '1', '2019-10-30 14:36:00', '1');
INSERT INTO `attendance` VALUES ('116', '李四', '2019-10-24 15:24:00', '0', '2019-10-30 14:36:00', '2');
INSERT INTO `attendance` VALUES ('117', '小五', '2019-10-24 15:24:00', '3', '2019-10-30 14:36:00', '3');
INSERT INTO `attendance` VALUES ('118', '111', '2019-10-24 15:24:00', '1', '2019-10-24 15:24:00', '4');
INSERT INTO `attendance` VALUES ('119', '张三', null, '1', '2019-10-30 14:36:00', '1');
INSERT INTO `attendance` VALUES ('120', '李四', '2019-10-24 15:24:00', '0', '2019-10-30 14:36:00', '2');
INSERT INTO `attendance` VALUES ('121', '小五', '2019-10-24 15:24:00', '3', '2019-10-30 14:36:00', '3');
INSERT INTO `attendance` VALUES ('122', '111', '2019-10-24 15:24:00', '1', '2019-10-24 15:24:00', '4');
INSERT INTO `attendance` VALUES ('123', '张三', '1899-12-31 00:00:00', '1', '2019-10-30 14:36:00', '1');
INSERT INTO `attendance` VALUES ('124', '李四', '2019-10-24 15:24:00', '0', '2019-10-30 14:36:00', '2');
INSERT INTO `attendance` VALUES ('125', '小五', '2019-10-24 15:24:00', '3', '2019-10-30 14:36:00', '3');
INSERT INTO `attendance` VALUES ('126', '111', '2019-10-24 15:24:00', '1', '2019-10-24 15:24:00', '4');
INSERT INTO `attendance` VALUES ('127', '张三', '1899-12-31 00:00:00', '1', '2019-10-30 14:36:00', '1');
INSERT INTO `attendance` VALUES ('128', '李四', '2019-10-24 15:24:00', '0', '2019-10-30 14:36:00', '2');
INSERT INTO `attendance` VALUES ('129', '小五', '2019-10-24 15:24:00', '3', '2019-10-30 14:36:00', '3');
INSERT INTO `attendance` VALUES ('130', '111', '2019-10-24 15:24:00', '1', '2019-10-24 15:24:00', '4');

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '短信内容',
  `status` int(11) DEFAULT NULL COMMENT '0为成功/1为失败  发送状态',
  `template_id` int(11) DEFAULT NULL COMMENT '0为模板一/1为模板二    短信模板',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('1', '111', 'aa', '1', '1');
INSERT INTO `note` VALUES ('2', '222', 'www', '1', '1');
INSERT INTO `note` VALUES ('3', '15666363902', '小五', '1', '1');
INSERT INTO `note` VALUES ('5', '15666363902', '张三', '1', '3');
INSERT INTO `note` VALUES ('6', '156666666615666363902', '李四', '1', '2');
INSERT INTO `note` VALUES ('7', '15666363902', '李四', '1', '3');
INSERT INTO `note` VALUES ('8', '15666363902', '小五', '1', '1');
INSERT INTO `note` VALUES ('9', '15666363902', '张三', '1', '2');
INSERT INTO `note` VALUES ('10', '15666363902', '张三', '1', '3');
INSERT INTO `note` VALUES ('11', '156666666615666363902', '李四', '1', '2');
INSERT INTO `note` VALUES ('12', '15666363902', '李四', '1', '3');
INSERT INTO `note` VALUES ('13', '15666363902', '小五', '1', '1');
INSERT INTO `note` VALUES ('14', '15666363902', '张三', '1', '2');
INSERT INTO `note` VALUES ('15', '15666363902', '张三', '1', '3');
INSERT INTO `note` VALUES ('16', '156666666615666363902', '李四', '1', '2');
INSERT INTO `note` VALUES ('17', '15666363902', '李四', '1', '3');
INSERT INTO `note` VALUES ('18', '15666363902', '小五', '1', '1');
INSERT INTO `note` VALUES ('19', '15666363902', '张三', '1', '2');
INSERT INTO `note` VALUES ('20', '15666363902', '张三', '1', '3');
INSERT INTO `note` VALUES ('21', '15666363902', '小五', '1', '1');
INSERT INTO `note` VALUES ('22', '15666363902', '张三', '1', '2');
INSERT INTO `note` VALUES ('23', '15666363902', '张三', '1', '3');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `desc_` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单id',
  `tag_type` int(11) DEFAULT NULL COMMENT '菜单级别',
  `sort` int(11) DEFAULT NULL COMMENT '排序优先级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'sysManage', '系统管理', null, null, '1', '200');
INSERT INTO `permission` VALUES ('2', 'userManage', '用户管理', '/admin/listUser', '1', '2', '100');
INSERT INTO `permission` VALUES ('3', 'roleManage', '角色管理', '/admin/listRole', '1', '2', '90');
INSERT INTO `permission` VALUES ('4', 'permissionManage', '资源权限管理', '/admin/listPermission', '1', '2', '80');
INSERT INTO `permission` VALUES ('5', 'attendanceManage', '考勤管理', null, null, '1', '150');
INSERT INTO `permission` VALUES ('6', 'attDetail', '考勤信息', '/attendance/findByCreateDate', '5', '2', '100');
INSERT INTO `permission` VALUES ('7', 'stuDetail', '学生详细信息', '/student/findAll', '5', '2', '90');
INSERT INTO `permission` VALUES ('8', 'noteDetail', '短信记录', '/note/findAll', '5', '2', '80');
INSERT INTO `permission` VALUES ('9', 'excelUpload', '考勤Excel上传', '/attendance/upload1', '5', '2', '70');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `desc_` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '超级管理员1');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('137', '1', '1');
INSERT INTO `role_permission` VALUES ('138', '1', '2');
INSERT INTO `role_permission` VALUES ('139', '1', '3');
INSERT INTO `role_permission` VALUES ('140', '2', '4');
INSERT INTO `role_permission` VALUES ('141', '1', '5');
INSERT INTO `role_permission` VALUES ('142', '1', '6');
INSERT INTO `role_permission` VALUES ('143', '1', '7');
INSERT INTO `role_permission` VALUES ('144', '1', '8');
INSERT INTO `role_permission` VALUES ('145', '1', '9');

-- ----------------------------
-- Table structure for student_detail
-- ----------------------------
DROP TABLE IF EXISTS `student_detail`;
CREATE TABLE `student_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `address` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `stu_class` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '班级',
  `phone` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '学生手机号',
  `parent_phone` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '家长手机号',
  `identity` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of student_detail
-- ----------------------------
INSERT INTO `student_detail` VALUES ('1', '张三', '北京111111111111111111', '框架', '15666363902', '15666363902', '142222222222222222');
INSERT INTO `student_detail` VALUES ('2', '李四', '北京', '框架', '15666363902', '15666363902', '142222222222222222');
INSERT INTO `student_detail` VALUES ('3', '小五', '北京', '框架', '1566666666', '15666363902', '142222222222222222');
INSERT INTO `student_detail` VALUES ('4', 'zhang3111111111111111', '北京11', '框架', '15666363902', '15666363902', '====');
INSERT INTO `student_detail` VALUES ('5', 'zhang3', '北京11', '框架', '15666363902', '15666363902', '且无法入无人');
INSERT INTO `student_detail` VALUES ('6', 'zhang300', '0000111111111111', '00000', '15666363902', '15666363902', '0000');
INSERT INTO `student_detail` VALUES ('7', 'zhang3', '北京111111111111111111', '00000', '15666666661111', '11111111111111111111111111111111', '且无法入无人');

-- ----------------------------
-- Table structure for template
-- ----------------------------
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `template` text COLLATE utf8_bin NOT NULL COMMENT '模板内容  ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of template
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zhang3', 'a7d59dfc5332749cb801f86a24f5f590', 'e5ykFiNwShfCXvBRPr3wXg==');
INSERT INTO `user` VALUES ('2', 'li4', '43e28304197b9216e45ab1ce8dac831b', 'jPz19y7arvYIGhuUjsb6sQ==');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL,
  `rid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('43', '2', '1');
INSERT INTO `user_role` VALUES ('45', '1', '1');
