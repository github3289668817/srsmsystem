/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : srsmsystem

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2020-06-20 14:37:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_class`
-- ----------------------------
DROP TABLE IF EXISTS `t_class`;
CREATE TABLE `t_class` (
  `classId` int(3) NOT NULL AUTO_INCREMENT,
  `className` varchar(30) NOT NULL,
  `classMajorId` int(2) NOT NULL,
  `classInstructorId` varchar(20) NOT NULL,
  `classDel` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`classId`),
  KEY `fk_class_emp` (`classInstructorId`),
  KEY `fk_class_major` (`classMajorId`),
  CONSTRAINT `fk_class_emp` FOREIGN KEY (`classInstructorId`) REFERENCES `t_emp` (`empNo`),
  CONSTRAINT `fk_class_major` FOREIGN KEY (`classMajorId`) REFERENCES `t_major` (`majorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_class
-- ----------------------------

-- ----------------------------
-- Table structure for `t_department`
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `departmentId` varchar(1) NOT NULL,
  `departmentName` varchar(10) NOT NULL,
  `departmentDel` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES ('1', '教育部', '0');
INSERT INTO `t_department` VALUES ('2', '财务部', '0');
INSERT INTO `t_department` VALUES ('3', '宿管部', '0');
INSERT INTO `t_department` VALUES ('4', '管理部', '0');

-- ----------------------------
-- Table structure for `t_dormitory`
-- ----------------------------
DROP TABLE IF EXISTS `t_dormitory`;
CREATE TABLE `t_dormitory` (
  `dormitoryId` varchar(20) NOT NULL,
  `dormitoryType` varchar(4) NOT NULL,
  PRIMARY KEY (`dormitoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dormitory
-- ----------------------------
INSERT INTO `t_dormitory` VALUES ('A101', '男');
INSERT INTO `t_dormitory` VALUES ('A102', '男');
INSERT INTO `t_dormitory` VALUES ('A103', '男');
INSERT INTO `t_dormitory` VALUES ('A104', '男');
INSERT INTO `t_dormitory` VALUES ('A105', '男');
INSERT INTO `t_dormitory` VALUES ('A106', '男');
INSERT INTO `t_dormitory` VALUES ('A107', '男');
INSERT INTO `t_dormitory` VALUES ('A108', '男');
INSERT INTO `t_dormitory` VALUES ('A109', '男');
INSERT INTO `t_dormitory` VALUES ('A110', '男');
INSERT INTO `t_dormitory` VALUES ('B101', '女');
INSERT INTO `t_dormitory` VALUES ('B102', '女');
INSERT INTO `t_dormitory` VALUES ('B103', '女');
INSERT INTO `t_dormitory` VALUES ('B104', '女');
INSERT INTO `t_dormitory` VALUES ('B105', '女');
INSERT INTO `t_dormitory` VALUES ('B106', '女');
INSERT INTO `t_dormitory` VALUES ('B107', '女');
INSERT INTO `t_dormitory` VALUES ('B108', '女');
INSERT INTO `t_dormitory` VALUES ('B109', '女');
INSERT INTO `t_dormitory` VALUES ('B110', '女');

-- ----------------------------
-- Table structure for `t_emp`
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
  `empNo` varchar(20) NOT NULL,
  `empPassword` varchar(20) NOT NULL,
  `empName` varchar(10) NOT NULL,
  `empRoleId` int(1) NOT NULL,
  `empDepartmentId` varchar(1) NOT NULL,
  `empGender` varchar(2) NOT NULL,
  `empCardNo` varchar(18) NOT NULL,
  `empPhone` varchar(15) DEFAULT NULL,
  `empPhoto` varchar(50) DEFAULT NULL,
  `empDel` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`empNo`),
  KEY `fk_emp_role` (`empRoleId`),
  KEY `fk_emp_department` (`empDepartmentId`),
  CONSTRAINT `fk_emp_department` FOREIGN KEY (`empDepartmentId`) REFERENCES `t_department` (`departmentId`),
  CONSTRAINT `fk_emp_role` FOREIGN KEY (`empRoleId`) REFERENCES `t_role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES ('admin', 'admin', '管理员', '5', '4', '男', '500101111111111111', '17766666666', '1.jpg', '0');

-- ----------------------------
-- Table structure for `t_loginlog`
-- ----------------------------
DROP TABLE IF EXISTS `t_loginlog`;
CREATE TABLE `t_loginlog` (
  `loginLogId` bigint(10) NOT NULL AUTO_INCREMENT,
  `loginLogIp` varchar(20) NOT NULL,
  `loginLogNo` varchar(20) NOT NULL,
  `loginLogCreatetime` datetime NOT NULL,
  PRIMARY KEY (`loginLogId`),
  KEY `fk_loginlog_user` (`loginLogNo`),
  CONSTRAINT `fk_loginlog_user` FOREIGN KEY (`loginLogNo`) REFERENCES `t_user` (`userNo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_loginlog
-- ----------------------------
INSERT INTO `t_loginlog` VALUES ('1', '119.85.172.159', 'admin', '2020-06-20 14:34:35');
INSERT INTO `t_loginlog` VALUES ('2', '119.85.172.159', 'admin', '2020-06-20 14:35:23');

-- ----------------------------
-- Table structure for `t_major`
-- ----------------------------
DROP TABLE IF EXISTS `t_major`;
CREATE TABLE `t_major` (
  `majorId` int(2) NOT NULL AUTO_INCREMENT,
  `majorName` varchar(10) NOT NULL,
  `majorSchoolId` int(2) NOT NULL,
  `majorDel` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`majorId`),
  KEY `fk_major_school` (`majorSchoolId`),
  CONSTRAINT `fk_major_school` FOREIGN KEY (`majorSchoolId`) REFERENCES `t_school` (`schoolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_major
-- ----------------------------

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `permissionId` int(6) NOT NULL,
  `permissionName` varchar(64) NOT NULL,
  `permissionUrl` varchar(64) NOT NULL,
  `permissionType` varchar(16) NOT NULL,
  PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleId` int(1) NOT NULL,
  `roleName` varchar(10) NOT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '学生');
INSERT INTO `t_role` VALUES ('2', '辅导员');
INSERT INTO `t_role` VALUES ('3', '财务');
INSERT INTO `t_role` VALUES ('4', '宿管');
INSERT INTO `t_role` VALUES ('5', '管理员');

-- ----------------------------
-- Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `roleId_permissionId` int(6) NOT NULL,
  `roleId` int(1) NOT NULL,
  `permissionId` int(6) NOT NULL,
  PRIMARY KEY (`roleId_permissionId`),
  KEY `fk_permissionId` (`permissionId`),
  KEY `fk_role` (`roleId`),
  CONSTRAINT `fk_permissionId` FOREIGN KEY (`permissionId`) REFERENCES `t_permission` (`permissionId`),
  CONSTRAINT `fk_role` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `t_school`
-- ----------------------------
DROP TABLE IF EXISTS `t_school`;
CREATE TABLE `t_school` (
  `schoolId` int(2) NOT NULL AUTO_INCREMENT,
  `schoolName` varchar(10) NOT NULL,
  `schoolDel` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`schoolId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_school
-- ----------------------------

-- ----------------------------
-- Table structure for `t_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `studentNo` varchar(20) NOT NULL,
  `studentPassword` varchar(20) NOT NULL,
  `studentRoleId` int(1) NOT NULL,
  `studentName` varchar(10) NOT NULL,
  `studentGender` varchar(2) NOT NULL,
  `studentCardNo` varchar(18) NOT NULL,
  `studentPhone` varchar(15) DEFAULT NULL,
  `studentPhoto` varchar(50) DEFAULT NULL,
  `studentSchoolId` int(2) NOT NULL,
  `studentMajorId` int(2) NOT NULL,
  `studentClassId` int(3) NOT NULL,
  `studentDormitoryId` varchar(20) NOT NULL,
  `studentSemester1` int(1) NOT NULL DEFAULT '0',
  `studentSemester1Money` double(5,2) NOT NULL DEFAULT '0.00',
  `studentSemester2` int(1) NOT NULL DEFAULT '0',
  `studentSemester2Money` double(5,2) NOT NULL DEFAULT '0.00',
  `studentSemester3` int(1) NOT NULL DEFAULT '0',
  `studentSemester3Money` double(5,2) NOT NULL DEFAULT '0.00',
  `studentSemester4` int(1) NOT NULL DEFAULT '0',
  `studentSemester4Money` double(5,2) NOT NULL DEFAULT '0.00',
  `studentSemester5` int(1) NOT NULL DEFAULT '0',
  `studentSemester5Money` double(5,2) NOT NULL DEFAULT '0.00',
  `studentSemester6` int(1) NOT NULL DEFAULT '0',
  `studentSemester6Money` double(5,2) NOT NULL DEFAULT '0.00',
  `studentSemester7` int(1) NOT NULL DEFAULT '0',
  `studentSemester7Money` double(5,2) NOT NULL DEFAULT '0.00',
  `studentSemester8` int(1) NOT NULL DEFAULT '0',
  `studentSemester8Money` double(5,2) NOT NULL DEFAULT '0.00',
  `studentState` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`studentNo`),
  KEY `fk_student_role` (`studentRoleId`),
  KEY `fk_student_dormitory` (`studentDormitoryId`),
  KEY `fk_student_class` (`studentClassId`),
  KEY `fk_studentmajor` (`studentMajorId`),
  KEY `fk_student_school` (`studentSchoolId`),
  CONSTRAINT `fk_studentmajor` FOREIGN KEY (`studentMajorId`) REFERENCES `t_major` (`majorId`),
  CONSTRAINT `fk_student_class` FOREIGN KEY (`studentClassId`) REFERENCES `t_class` (`classId`),
  CONSTRAINT `fk_student_dormitory` FOREIGN KEY (`studentDormitoryId`) REFERENCES `t_dormitory` (`dormitoryId`),
  CONSTRAINT `fk_student_role` FOREIGN KEY (`studentRoleId`) REFERENCES `t_role` (`roleId`),
  CONSTRAINT `fk_student_school` FOREIGN KEY (`studentSchoolId`) REFERENCES `t_school` (`schoolId`),
  CONSTRAINT `fk_student_user` FOREIGN KEY (`studentNo`) REFERENCES `t_user` (`userNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userNo` varchar(20) NOT NULL,
  `userPassword` varchar(20) NOT NULL,
  `userName` varchar(10) NOT NULL,
  `userRoleId` int(1) NOT NULL,
  `userGender` varchar(2) NOT NULL,
  `userPhoto` varchar(50) DEFAULT NULL,
  `userDel` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userNo`),
  KEY `fk_user_role` (`userRoleId`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`userRoleId`) REFERENCES `t_role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('admin', 'admin', '管理员', '5', '男', '17725026645', '0');
