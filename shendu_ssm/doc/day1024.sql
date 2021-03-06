/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50637
Source Host           : 127.0.0.1:3306
Source Database       : day1024

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2019-11-13 20:19:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `attendance_time` datetime DEFAULT NULL COMMENT '记录每天打卡时间',
  `status` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  `time` datetime DEFAULT NULL COMMENT '记录考勤时间',
  `s_id` int(11) DEFAULT NULL COMMENT '对应学生详情表中id 唯一标识',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `s_id` (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('130', '111', '2019-10-24 15:24:00', '1', '2019-10-24 15:24:00', '4');
INSERT INTO `attendance` VALUES ('137', '23', '2019-11-13 17:03:11', '1', '2019-11-13 17:03:16', '45');
INSERT INTO `attendance` VALUES ('139', '刘总', '2019-11-13 17:45:34', '1', '2019-11-13 17:45:40', '9');
INSERT INTO `attendance` VALUES ('141', '曹乐乐', '2019-11-13 16:19:00', '2', '2019-11-13 20:09:56', null);
INSERT INTO `attendance` VALUES ('142', '曹乐乐1', '2019-11-14 16:19:00', null, '2019-11-13 20:09:56', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('24', '18600571419', '刘总', '0', '2');
INSERT INTO `note` VALUES ('25', '18600571419', '刘总', '0', '3');
INSERT INTO `note` VALUES ('26', '18600571419', '刘总2', '0', '1');
INSERT INTO `note` VALUES ('27', '13453673531', '曹乐乐', '0', '2');
INSERT INTO `note` VALUES ('28', '17865687857', '曹乐乐', '0', '3');
INSERT INTO `note` VALUES ('29', '13453673531', '曹乐乐', '0', '1');
INSERT INTO `note` VALUES ('30', '17865687857', '曹乐乐', '0', '1');
INSERT INTO `note` VALUES ('31', '17865687857', '曹乐乐', '0', '1');
INSERT INTO `note` VALUES ('32', '18600571419', '刘总', '0', '1');
INSERT INTO `note` VALUES ('33', '13453673531', '曹乐乐', '0', '2');
INSERT INTO `note` VALUES ('34', '17865687857', '曹乐乐', '0', '3');

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'sysManage', '系统管理', null, null, '1', '2000');
INSERT INTO `permission` VALUES ('2', 'userManage', '用户管理', '/admin/listUser', '1', '2', '100');
INSERT INTO `permission` VALUES ('3', 'roleManage', '角色管理', '/admin/listRole', '1', '2', '70');
INSERT INTO `permission` VALUES ('4', 'permissionManage', '资源权限管理', '/admin/listPermission', '1', '2', '50');
INSERT INTO `permission` VALUES ('5', 'attendanceManage', '考勤管理', null, null, '1', '1500');
INSERT INTO `permission` VALUES ('6', 'attDetail', '考勤信息', '/attendance/findByCreateDate', '5', '2', '100');
INSERT INTO `permission` VALUES ('7', 'stuDetail', '学生详细信息', '/student/findAll', '5', '2', '90');
INSERT INTO `permission` VALUES ('8', 'noteDetail', '短信记录', '/note/findAll', '5', '2', '80');
INSERT INTO `permission` VALUES ('9', 'excelUpload', '考勤Excel上传', '/attendance/upload1', '5', '2', '70');
INSERT INTO `permission` VALUES ('14', 'addUser1', '添加用户', '/admin/addUser1', '2', '3', '1');
INSERT INTO `permission` VALUES ('15', 'editUser', '修改用户', '/admin/editUser', '2', '3', '1');
INSERT INTO `permission` VALUES ('16', 'deleteUser', '删除用户', '/admin/deleteUser', '2', '3', '1');
INSERT INTO `permission` VALUES ('17', 'addRole1', '添加角色', '/admin/addRole1', '3', '3', '1');
INSERT INTO `permission` VALUES ('18', 'editRole', '修改角色', '/admin/editRole', '3', '3', '1');
INSERT INTO `permission` VALUES ('19', 'deleteRole', '删除角色', '/admin/deleteRole', '3', '3', '1');
INSERT INTO `permission` VALUES ('20', 'xiang', '角色详情', '/admin/xiang', '3', '3', '1');
INSERT INTO `permission` VALUES ('21', 'editPermission', '修改权限', '/admin/editPermission', '4', '3', '1');
INSERT INTO `permission` VALUES ('22', 'addPermission1', '添加权限', '/admin/addPermission1', '4', '3', '1');
INSERT INTO `permission` VALUES ('23', 'deletePermission', '删除权限', '/admin/deletePermission', '4', '3', '1');
INSERT INTO `permission` VALUES ('24', 'deleteAtt', '删除考勤信息', '/attendance/deleteAtt', '6', '3', '1');
INSERT INTO `permission` VALUES ('25', 'updateAtt', '修改考勤信息', '/attendance/updateAtt', '6', '3', '1');
INSERT INTO `permission` VALUES ('26', 'messageSend', '一键发送短信', '/attendance/messageSend', '6', '3', '1');
INSERT INTO `permission` VALUES ('27', 'deleteNote', '删除短信记录', '/note/deleteNote', '8', '3', '1');
INSERT INTO `permission` VALUES ('28', 'editStu', '修改学生详细信息', '/student/editStu', '7', '3', '1');
INSERT INTO `permission` VALUES ('29', 'addStudent', '增加学生详细信息', '/student/addStudent', '7', '3', '1');
INSERT INTO `permission` VALUES ('30', 'updateStuClassBatch', '批量修改学生信息', '/student/updateStuClassBatch', '7', '3', '1');
INSERT INTO `permission` VALUES ('31', 'deleteStu', '删除学生详细信息', '/student/deleteStu', '7', '3', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `desc_` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '超级管理员1');
INSERT INTO `role` VALUES ('12', 'ab', 'admin1');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('137', '1', '1');
INSERT INTO `role_permission` VALUES ('138', '1', '2');
INSERT INTO `role_permission` VALUES ('139', '1', '3');
INSERT INTO `role_permission` VALUES ('140', '1', '4');
INSERT INTO `role_permission` VALUES ('141', '1', '5');
INSERT INTO `role_permission` VALUES ('142', '1', '6');
INSERT INTO `role_permission` VALUES ('143', '1', '7');
INSERT INTO `role_permission` VALUES ('144', '1', '8');
INSERT INTO `role_permission` VALUES ('145', '1', '9');
INSERT INTO `role_permission` VALUES ('147', '11', '3');
INSERT INTO `role_permission` VALUES ('148', '11', '2');
INSERT INTO `role_permission` VALUES ('149', '11', '4');
INSERT INTO `role_permission` VALUES ('150', '11', '6');
INSERT INTO `role_permission` VALUES ('151', '1', '14');
INSERT INTO `role_permission` VALUES ('152', '1', '15');
INSERT INTO `role_permission` VALUES ('153', '1', '16');
INSERT INTO `role_permission` VALUES ('154', '1', '17');
INSERT INTO `role_permission` VALUES ('155', '1', '18');
INSERT INTO `role_permission` VALUES ('156', '1', '19');
INSERT INTO `role_permission` VALUES ('157', '1', '20');
INSERT INTO `role_permission` VALUES ('158', '1', '21');
INSERT INTO `role_permission` VALUES ('159', '1', '22');
INSERT INTO `role_permission` VALUES ('160', '1', '23');
INSERT INTO `role_permission` VALUES ('161', '1', '24');
INSERT INTO `role_permission` VALUES ('162', '1', '25');
INSERT INTO `role_permission` VALUES ('163', '1', '26');
INSERT INTO `role_permission` VALUES ('164', '1', '27');
INSERT INTO `role_permission` VALUES ('165', '1', '28');
INSERT INTO `role_permission` VALUES ('166', '1', '29');
INSERT INTO `role_permission` VALUES ('167', '1', '30');
INSERT INTO `role_permission` VALUES ('168', '1', '31');
INSERT INTO `role_permission` VALUES ('169', '12', '31');
INSERT INTO `role_permission` VALUES ('170', '12', '30');
INSERT INTO `role_permission` VALUES ('171', '12', '29');
INSERT INTO `role_permission` VALUES ('172', '12', '28');
INSERT INTO `role_permission` VALUES ('173', '12', '27');
INSERT INTO `role_permission` VALUES ('174', '12', '26');

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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

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
INSERT INTO `student_detail` VALUES ('9', '刘总', null, '框架', '18600571419', '18600571419', '且无法入无人');
INSERT INTO `student_detail` VALUES ('10', '刘总2', null, '框架', '18600571419', '18600571419', '且无法入无人');
INSERT INTO `student_detail` VALUES ('43', '曹乐乐', null, '框架', '13453673531', '17865687857', '且无法入无人');
INSERT INTO `student_detail` VALUES ('44', '123', null, '框架', '123456789001', '12121212121', '且无法入无人');
INSERT INTO `student_detail` VALUES ('45', '23', null, '框架', '13214214', '142141241', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zhang3', 'ec0e95eb0cea5e7a3f0a051337400740', 'HbGMeD4FhGa1SgceuS/Mdw==');
INSERT INTO `user` VALUES ('6', 'li4', '3f39799388212f564e2c200959f27d93', 'ZQuHRh4wqplN/3H7dQadtg==');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL,
  `rid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('71', '6', '12');
INSERT INTO `user_role` VALUES ('72', '1', '1');
INSERT INTO `user_role` VALUES ('73', null, '1');
INSERT INTO `user_role` VALUES ('74', null, '1');
INSERT INTO `user_role` VALUES ('75', null, '1');
INSERT INTO `user_role` VALUES ('76', null, '12');
INSERT INTO `user_role` VALUES ('77', '10', '1');
