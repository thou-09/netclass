/*
 Navicat MySQL Data Transfer

 Source Server         : my
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : netclass

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 21/09/2022 11:14:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_chapter
-- ----------------------------
DROP TABLE IF EXISTS `t_chapter`;
CREATE TABLE `t_chapter`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程与资源的表的主键(课程章节)',
  `course_id` int NULL DEFAULT NULL COMMENT '课程的id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程章节标题',
  `info` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程章节简介',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '课程章节创建时间',
  `status` int NULL DEFAULT NULL COMMENT '1启用-1禁用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_course_id_fk`(`course_id`) USING BTREE,
  CONSTRAINT `t_chapter_course_id_fk` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论表的主键',
  `context` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `user_id` int NULL DEFAULT NULL COMMENT '发布的用户id',
  `resource_id` int NULL DEFAULT NULL COMMENT '被评论的资源id',
  `status` int NULL DEFAULT NULL COMMENT '1启用-1禁用2待审核',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_comment_chapter_id_fk`(`resource_id`) USING BTREE,
  INDEX `t_comment_user_id_fk`(`user_id`) USING BTREE,
  CONSTRAINT `t_comment_resource_id_fk` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_comment_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '课程表主键',
  `course_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名称',
  `course_info` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程简介',
  `author` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程的作者',
  `cover_image_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程封面图片的相对路径',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '课程发布时间',
  `click_number` int NULL DEFAULT 0 COMMENT '课程点击量',
  `status` int NULL DEFAULT NULL COMMENT '课程状态(1启用,-1禁用)',
  `recommendation_grade` int NULL DEFAULT NULL COMMENT '课程推荐等级(0普通,1推荐)',
  `course_type_id` int NULL DEFAULT NULL COMMENT '所属的课程类别的id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_course_type_fk`(`course_type_id`) USING BTREE,
  CONSTRAINT `t_course_type_fk` FOREIGN KEY (`course_type_id`) REFERENCES `t_course_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_course_type
-- ----------------------------
DROP TABLE IF EXISTS `t_course_type`;
CREATE TABLE `t_course_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `parent_id` int NULL DEFAULT NULL COMMENT '父类别id',
  `status` int NULL DEFAULT NULL COMMENT '1启用-1禁用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_type_parent_fk`(`parent_id`) USING BTREE,
  CONSTRAINT `t_type_parent_fk` FOREIGN KEY (`parent_id`) REFERENCES `t_course_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_gold_points
-- ----------------------------
DROP TABLE IF EXISTS `t_gold_points`;
CREATE TABLE `t_gold_points`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '积分金币表主键',
  `user_id` int NULL DEFAULT NULL COMMENT '积分金币所属用户id',
  `point_count` int NULL DEFAULT 0 COMMENT '使用或获得的积分数',
  `gold_count` int NULL DEFAULT 0 COMMENT '使用或获得的金币数',
  `info` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作的内容简单说明',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_gold_points_user_id_fk`(`user_id`) USING BTREE,
  CONSTRAINT `t_gold_points_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_praise
-- ----------------------------
DROP TABLE IF EXISTS `t_praise`;
CREATE TABLE `t_praise`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '点赞记录表主键',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `comment_id` int NULL DEFAULT NULL COMMENT '被点赞的评论的id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_praise_comment_id_fk`(`comment_id`) USING BTREE,
  INDEX `t_praise_user_id_fk`(`user_id`) USING BTREE,
  CONSTRAINT `t_praise_comment_id_fk` FOREIGN KEY (`comment_id`) REFERENCES `t_comment` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_praise_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '资源主键',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源标题',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源相对路径',
  `cover_image_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源封面图片地址',
  `original_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件原始名称',
  `file_size` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件大小(MB)',
  `file_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型(文件后缀名)',
  `total_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'mp4视频的总时间(单位秒,保留两位小数)',
  `click_count` int NULL DEFAULT NULL COMMENT '点击量',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `cost_type` int NULL DEFAULT NULL COMMENT '0积分,1金币',
  `cost_number` int NULL DEFAULT 0 COMMENT '下载文件或查看视频需要的积分或金币',
  `user_id` int NULL DEFAULT NULL COMMENT '上传用户id',
  `chapter_id` int NULL DEFAULT NULL COMMENT '章节id',
  `status` int NULL DEFAULT NULL COMMENT '1启用-1禁用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_resource_user_id_fk`(`user_id`) USING BTREE,
  INDEX `t_resource_chapter_id_fk`(`chapter_id`) USING BTREE,
  CONSTRAINT `t_resource_chapter_id_fk` FOREIGN KEY (`chapter_id`) REFERENCES `t_chapter` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_resource_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `login_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名,登录名',
  `nickname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sign_date` datetime(0) NULL DEFAULT NULL COMMENT '最近一次签到的日期',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '用户创建日期',
  `status` int NULL DEFAULT NULL COMMENT '1启用,-1禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_user_resource`;
CREATE TABLE `t_user_resource`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户已购买的资源表主键',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `resource_id` int NULL DEFAULT NULL COMMENT '资源id',
  `see_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'mp4视频观看到的时间(单位秒,保留两位小数)',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '购买日期',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '最近一次查看的日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_resource_user_user_id_fk`(`user_id`) USING BTREE,
  INDEX `t_resource_user_resource_id_fk`(`resource_id`) USING BTREE,
  CONSTRAINT `t_resource_user_resource_id_fk` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_resource_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
