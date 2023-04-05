/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : campus

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2023-04-05 14:17:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `username` varchar(11) NOT NULL COMMENT '用户名',
  `password` varchar(500) NOT NULL COMMENT '密码',
  `token` varchar(255) DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '用户状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='管理员用户表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0');
INSERT INTO `admin` VALUES ('2', 'admin1', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0');
INSERT INTO `admin` VALUES ('3', 'admin2', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0');
INSERT INTO `admin` VALUES ('4', 'admin3', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0');
INSERT INTO `admin` VALUES ('5', 'admin4', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0');
INSERT INTO `admin` VALUES ('6', 'admin5', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0');
INSERT INTO `admin` VALUES ('7', 'admin6', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0');
INSERT INTO `admin` VALUES ('8', 'admin7', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0');
INSERT INTO `admin` VALUES ('9', 'admin8', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0');
INSERT INTO `admin` VALUES ('10', 'admin9', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0');
INSERT INTO `admin` VALUES ('11', 'admin10', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0');
INSERT INTO `admin` VALUES ('12', 'admin11', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '0');

-- ----------------------------
-- Table structure for articles_manage
-- ----------------------------
DROP TABLE IF EXISTS `articles_manage`;
CREATE TABLE `articles_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `title` varchar(500) NOT NULL COMMENT '标题',
  `pub_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `pub_author` varchar(500) NOT NULL COMMENT '发布作者',
  `pub_content` varchar(500) DEFAULT NULL COMMENT '发布内容',
  `pub_type` varchar(100) NOT NULL COMMENT '文章类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='文章管理表：包括疫情新闻、通知通告、校园动态、媒体校园';

-- ----------------------------
-- Records of articles_manage
-- ----------------------------
INSERT INTO `articles_manage` VALUES ('5', '光明日报：广外教授赵龙跃谈全球治理体系改革和建设的中国力量', '2023-02-21 16:34:12', '赵龙跃', 'https://epaper.gmw.cn/gmrb/html/2023-02/14/nw.D110000gmrb_20230214_1-16.htm', '媒体校园');
INSERT INTO `articles_manage` VALUES ('6', '法律出版社副总编张雪纯带队到我校调研交流', '2023-02-21 16:35:43', '法学院', 'https://news.gdufs.edu.cn/info/1003/94641.htm', '校园动态');
INSERT INTO `articles_manage` VALUES ('7', '中国新闻出版广电报：广外教授侯迎忠谈校媒牵手提升湾区国际传播效能', '2023-02-21 16:36:26', '徐平', 'http://data.chinaxwcb.com/epaper2023/epaper/d7904/d8b/202302/131239.html', '媒体校园');
INSERT INTO `articles_manage` VALUES ('8', '马克思主义学院组织全体教师参加“周末理论大讲堂”', '2023-02-23 17:41:20', '马克思主义学院', 'https://news.gdufs.edu.cn/info/1003/94665.htm', '校园动态');
INSERT INTO `articles_manage` VALUES ('9', '奔赴团圆的广外人：校里校外 寻味温暖', '2023-02-23 17:41:55', '文字：张帅 郑浩鹏 龙星宇 陈欣欣 刘秋怡 杨欣灵 苏映绮 图片：广外摄影部 受访者提供', 'https://news.gdufs.edu.cn/info/1003/94608.htm', '校园动态');
INSERT INTO `articles_manage` VALUES ('10', '羊城晚报：广外教授曾荣谈新时代文化自信的本质要求与世界意义', '2023-02-23 17:42:43', '曾荣', 'https://ep.ycwb.com/epaper/ycwb/html/2023-02/21/content_6_555741.htm', '媒体校园');
INSERT INTO `articles_manage` VALUES ('11', '南方日报：广外党委书记、校长石佑启谈深度融入广东高水平对外开放新格局', '2023-02-21 17:44:12', '广东教育头条', 'https://static.nfapp.southcn.com/content/202301/30/c7308488.html?specialTopicId=7308488&from=weChatMessage&date=bnVsbA==&layer=11', '媒体校园');
INSERT INTO `articles_manage` VALUES ('12', '南方日报：广外教授宋善文谈坚定不移走好中国特色社会主义这条必由之路', '2023-02-23 17:44:58', '宋善文', 'https://epaper.southcn.com/nfdaily/html/202301/28/content_10048862.html', '媒体校园');
INSERT INTO `articles_manage` VALUES ('13', '教育学院到中共三大会址纪念馆开展主题党日活动', '2023-02-24 10:34:46', '英语教育学院', 'https://news.gdufs.edu.cn/info/1003/94676.htm', '校园动态');
INSERT INTO `articles_manage` VALUES ('14', '奔赴团圆的广外人：校里校外 寻味温暖', '2023-01-21 10:35:27', '文字：张帅 郑浩鹏 龙星宇 陈欣欣 刘秋怡 杨欣灵 苏映绮 图片：广外摄影部 受访者提供', 'https://news.gdufs.edu.cn/info/1003/94608.htm', '校园动态');
INSERT INTO `articles_manage` VALUES ('15', '我校完成2023年硕士研究生招生考试初试评卷工作', '2023-01-18 10:35:59', '研究生院', 'https://news.gdufs.edu.cn/info/1003/94605.htm', '校园动态');
INSERT INTO `articles_manage` VALUES ('16', '中国社会科学报：广外教授申明浩谈多措并举助力扩大内需', '2023-03-24 15:03:18', '申明浩', 'http://www.cssn.cn/skgz/bwyc/202303/t20230324_5615504.shtml', '媒体校园');
INSERT INTO `articles_manage` VALUES ('17', '大公报：广外副教授杨永聪谈粤港澳大湾区产业发展', '2023-03-23 15:04:14', '大公报', 'https://news.gdufs.edu.cn/info/1301/94802.htm', '媒体校园');
INSERT INTO `articles_manage` VALUES ('18', '广州日报：引领学习青年榜样，广外举行2022年学生表彰大会', '2023-03-20 15:05:14', '王婧', 'https://www.gzdaily.cn/amucsite/pad/index.html?id=1992692#/detail/1992692?site4&columnID=0', '媒体校园');
INSERT INTO `articles_manage` VALUES ('19', '南方网：“凤鸣广外”年度盛典举行，激励学子学习青年榜样', '2023-03-18 15:06:41', '南方网', 'https://edu.southcn.com/node_4d76952560/f65c1c23d4.shtml', '媒体校园');
INSERT INTO `articles_manage` VALUES ('20', '学习强国：广外研究员谢文新谈实施创新驱动发展战略 建设科技创新中心', '2023-03-09 15:07:48', '谢文新', 'https://article.xuexi.cn/articles/index.html?art_id=14915117454280885540&t=1678257535313&showmenu=false&cdn=https%3A%2F%2Fregion-guangdong-resource&study_style_id=feeds_opaque&source=share&share_to=wx_single&item_id=14915117454280885540&ref_read_id=82a313', '媒体校园');
INSERT INTO `articles_manage` VALUES ('21', '深圳特区报：广外研究员谢文新谈实施创新驱动发展战略 建设科技创新中心', '2023-03-07 15:08:29', '谢文新', 'http://sztqb.sznews.com/PC/layout/202303/07/node_B03.html#content_3036475', '媒体校园');
INSERT INTO `articles_manage` VALUES ('22', '中国社会科学网：国家社科基金重大项目“人类文明新形态建设中的算法文化引导机制研究”开题论证会举行', '2023-03-27 15:09:57', '李永杰 杨欣', 'http://www.cssn.cn/xwcbx/xwcbx_cmkx/202303/t20230323_5615393.shtml', '媒体校园');
INSERT INTO `articles_manage` VALUES ('23', '我校召开新一轮本科教育教学审核评估工作动员大会', '2023-03-21 10:11:08', '文字：教学质量监测与评估中心 图片：教学质量监测与评估中心 图片：教学质量监测与评估中心 责任编辑：周喆 白志华', 'https://www.gdufs.edu.cn/info/1106/59469.htm', '校园动态');
INSERT INTO `articles_manage` VALUES ('24', '我校学子在第十届法语人才竞赛决赛获佳绩', '2023-03-24 15:13:09', '文字：西语学院 图片：西语学院 编辑：周喆', 'https://news.gdufs.edu.cn/info/1003/94816.htm', '校园动态');
INSERT INTO `articles_manage` VALUES ('25', '我校国际组织实验室揭牌并举办首场讲座', '2023-03-24 15:13:36', '文字：国际关系学院 图片：国际关系学院 编辑：周喆', 'https://news.gdufs.edu.cn/info/1003/94815.htm', '校园动态');
INSERT INTO `articles_manage` VALUES ('26', '近50名工会干部赴佛山感受岭南文化', '2023-03-24 15:14:07', '文字：校工会 图片：校工会 编辑：周喆', 'https://news.gdufs.edu.cn/info/1003/94814.htm', '校园动态');
INSERT INTO `articles_manage` VALUES ('27', '艺术学院赴珠海开展主题党日活动', '2023-03-23 15:14:29', '文字：艺术学院 图片：艺术学院 编辑：周喆', 'https://news.gdufs.edu.cn/info/1003/94803.htm', '校园动态');
INSERT INTO `articles_manage` VALUES ('28', '【疫情防控小贴士】一图了解：应对新冠，我们应该做什么？', '2022-12-08 15:16:29', '文字：伍宇杰 图片：央视新闻 编辑：周喆 实习编辑伍宇杰', 'https://news.gdufs.edu.cn/info/1002/94426.htm', '疫情新闻');
INSERT INTO `articles_manage` VALUES ('29', '【疫情防控小贴士】疫情期间，居家办公如何做好个人防护？这份指南请收下', '2022-11-29 15:17:09', '文字：广东疾控 何家安 图片：广东疾控 编辑：实习编辑何家安', 'https://news.gdufs.edu.cn/info/1002/94359.htm', '疫情新闻');
INSERT INTO `articles_manage` VALUES ('30', '【疫情防控小贴士】6个戴口罩的坏习惯，可能无法预防“新冠病毒”！', '2022-11-24 15:17:35', '文字：何家安 图片：央视新闻 编辑：周喆 实习编辑何家安', 'https://news.gdufs.edu.cn/info/1002/94348.htm', '疫情新闻');
INSERT INTO `articles_manage` VALUES ('31', '【疫情防控小贴士】学起来！校园新冠肺炎疫情防控健康科普', '2022-11-23 15:18:09', '文字：伍宇杰 图片：中国健康教育中心 编辑：周喆 实习编辑伍宇杰', 'https://news.gdufs.edu.cn/info/1002/94344.htm', '疫情新闻');
INSERT INTO `articles_manage` VALUES ('32', '校党委专题部署校园疫情防控和安全稳定工作', '2022-10-12 15:18:54', '文字：党办校办 图片： 编辑：宾锐光 周喆 白志华', 'https://news.gdufs.edu.cn/info/1002/94180.htm', '疫情新闻');
INSERT INTO `articles_manage` VALUES ('33', '抗击疫情不松懈 教学服务不停歇', '2022-09-22 15:19:27', '文字：教务处 图片：教务处 编辑：宾锐光 颜梁柱', 'https://news.gdufs.edu.cn/info/1002/94041.htm', '疫情新闻');
INSERT INTO `articles_manage` VALUES ('34', '【征兵宣传】2022年夏季征兵及兵役登记工作通知', '2022-06-09 15:20:38', '文字： 图片： 编辑：', 'https://news.gdufs.edu.cn/info/1002/93673.htm', '通知通告');
INSERT INTO `articles_manage` VALUES ('35', '关于做好“五一“假期疫情防控工作的通知', '2022-04-28 15:20:38', '文字： 图片： 编辑：', 'https://news.gdufs.edu.cn/info/1002/93479.htm', '通知通告');
INSERT INTO `articles_manage` VALUES ('36', '关于2022年清明节放假的通知', '2022-03-30 15:24:05', '文字： 图片： 编辑：', 'https://news.gdufs.edu.cn/info/1002/93337.htm', '通知通告');
INSERT INTO `articles_manage` VALUES ('37', '2022年春季学期开学返校疫情防控工作通知', '2022-02-11 15:24:37', '文字：学校防控新冠肺炎疫情工作领导小组办公室 图片： 编辑：', 'https://news.gdufs.edu.cn/info/1002/93185.htm', '通知通告');
INSERT INTO `articles_manage` VALUES ('38', '中国世界经济学会国际贸易论坛（2019）征文通知', '2019-06-04 15:25:01', '文字： 图片： 编辑：', 'https://news.gdufs.edu.cn/info/1016/24502.htm', '通知通告');
INSERT INTO `articles_manage` VALUES ('39', '2019年下半年(第56次)全国计算机等级考试（NCRE） 广外番禺大学城和白云山校区考点报考通知', '2019-06-04 15:25:01', '文字： 图片： 编辑：', 'https://news.gdufs.edu.cn/info/1016/24617.htm', '通知通告');

-- ----------------------------
-- Table structure for back
-- ----------------------------
DROP TABLE IF EXISTS `back`;
CREATE TABLE `back` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `username` varchar(11) NOT NULL COMMENT '用户名',
  `name` varchar(45) NOT NULL COMMENT '姓名',
  `plan_go` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '计划出发时间',
  `plan_reach` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '计划到达时间',
  `becity` varchar(45) NOT NULL COMMENT '出发地点',
  `emergency_tel` varchar(45) NOT NULL COMMENT '紧急联系人',
  `evidences` varchar(500) DEFAULT NULL COMMENT '学校要求的返校证明材料',
  `remarks` varchar(45) DEFAULT NULL COMMENT '备注说明',
  `status` varchar(45) NOT NULL DEFAULT '0' COMMENT '审批状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='返校管理表';

-- ----------------------------
-- Records of back
-- ----------------------------
INSERT INTO `back` VALUES ('1', '20191000003', '小明', '2023-03-25 21:47:57', '2023-02-14 17:00:00', '广东省广州市越秀区', '13333333555', null, null, '1');
INSERT INTO `back` VALUES ('2', '20191000002', '小红', '2023-03-29 16:02:49', '2023-02-22 23:00:00', '广东省佛山市顺德区', '15999999884', null, null, '0');
INSERT INTO `back` VALUES ('3', '20191000001', '小宋', '2023-03-27 15:36:49', '2023-03-29 18:00:00', '广东省深圳市', '15222222222', null, null, '2');

-- ----------------------------
-- Table structure for departure
-- ----------------------------
DROP TABLE IF EXISTS `departure`;
CREATE TABLE `departure` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `username` varchar(11) NOT NULL COMMENT '用户名',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `plan_leave` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '计划离校时间',
  `plan_reach` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '计划到达时间',
  `destination` varchar(200) NOT NULL COMMENT '目的地',
  `emergency_tel` varchar(45) NOT NULL COMMENT '紧急联系人',
  `plan_back` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '计划返校时间',
  `evidences` varchar(500) DEFAULT NULL COMMENT '学校要求的离校证明材料',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注说明',
  `status` varchar(45) NOT NULL DEFAULT '0' COMMENT '审批状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='离校管理表';

-- ----------------------------
-- Records of departure
-- ----------------------------
INSERT INTO `departure` VALUES ('1', '20191000003', '小明', '2023-02-22 21:17:33', '2023-02-22 23:00:00', '广东省佛山市高明区', '13555555998', '2023-02-27 21:17:12', null, null, '2');
INSERT INTO `departure` VALUES ('2', '20191000001', '小宋', '2023-03-26 13:31:40', '2023-03-26 16:00:00', '广东省深圳市', '15222222222', '2023-03-29 15:00:00', '', '', '1');
INSERT INTO `departure` VALUES ('3', '20191000002', '小红', '2023-03-27 15:36:33', '0000-00-00 00:00:00', '广东省深圳市', '15999999884', '2023-03-27 22:00:00', null, null, '0');

-- ----------------------------
-- Table structure for health_manage
-- ----------------------------
DROP TABLE IF EXISTS `health_manage`;
CREATE TABLE `health_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `username` varchar(11) NOT NULL COMMENT '用户名',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `temperature` float(50,1) NOT NULL COMMENT '体温',
  `address` varchar(500) NOT NULL COMMENT '地址',
  `health` varchar(500) NOT NULL COMMENT '身体状况',
  `contact_infection` varchar(500) NOT NULL DEFAULT '否' COMMENT '是否接触感染人员',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注说明',
  `u_type` varchar(100) NOT NULL COMMENT '用户类型',
  `last_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='健康管理表';

-- ----------------------------
-- Records of health_manage
-- ----------------------------
INSERT INTO `health_manage` VALUES ('1', '20191000003', '小明', '36.8', '广东省广州市', '健康状况正常', '否', null, '学生', '2023-03-01 20:44:27');
INSERT INTO `health_manage` VALUES ('2', '2017001', '小宋', '36.8', '广东省广州市', '健康状况正常', '否', null, '教师', '2023-03-01 12:34:48');
INSERT INTO `health_manage` VALUES ('4', '20191000003', '小明', '36.8', '广东省广州市', '健康状况正常', '否', null, '学生', '2023-03-02 22:11:01');
INSERT INTO `health_manage` VALUES ('5', '20191000003', '小明', '37.8', '广东省广州市', '发烧、干咳、乏力、咽痛', '否', null, '学生', '2023-03-03 15:47:45');
INSERT INTO `health_manage` VALUES ('6', '2017001', '小宋', '37.5', '广东省广州市', '发烧、干咳、乏力、咽痛', '否', null, '教师', '2023-03-03 12:34:50');
INSERT INTO `health_manage` VALUES ('7', '20191000001', '小宋', '36.5', '广东省广州市', '健康状况正常', '否', '', '学生', '2023-03-04 13:04:55');
INSERT INTO `health_manage` VALUES ('8', '20191000001', '小宋', '36.8', '广东省广州市', '健康状况正常', '否', '', '学生', '2023-03-05 13:05:27');
INSERT INTO `health_manage` VALUES ('9', '20191000004', '小黄', '36.7', '广东省广州市', '健康状况正常', '否', null, '学生', '2023-03-06 10:27:43');
INSERT INTO `health_manage` VALUES ('10', '20191000001', '小宋', '36.7', '广东省广州市', '健康状况正常', '否', null, '学生', '2023-03-06 11:28:16');
INSERT INTO `health_manage` VALUES ('11', '20191000003', '小明', '36.6', '广东省广州市', '健康状况正常', '否', null, '学生', '2023-03-06 15:28:42');
INSERT INTO `health_manage` VALUES ('12', '20191000002', '小红', '36.5', '广东省广州市', '健康状况正常', '否', null, '学生', '2023-03-06 15:29:07');
INSERT INTO `health_manage` VALUES ('13', '2016001', '小邓', '36.8', '广东省广州市', '健康状况正常', '否', null, '教师', '2023-03-06 15:29:59');
INSERT INTO `health_manage` VALUES ('14', '2017001', '小宋', '36.7', '广东省广州市', '健康状况正常', '否', null, '教师', '2023-03-06 16:30:29');
INSERT INTO `health_manage` VALUES ('15', '2010001', '小瑟', '37.9', '广东省广州市', '发烧、干咳、乏力、咽痛', '否', null, '教师', '2023-03-06 16:31:22');

-- ----------------------------
-- Table structure for leave_application
-- ----------------------------
DROP TABLE IF EXISTS `leave_application`;
CREATE TABLE `leave_application` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `username` varchar(11) NOT NULL COMMENT '用户名',
  `name` varchar(45) NOT NULL COMMENT '姓名',
  `leave_type` varchar(45) NOT NULL COMMENT '请假类型',
  `start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `reason` varchar(500) NOT NULL COMMENT '请假事由',
  `leave_yn` varchar(45) NOT NULL DEFAULT '0' COMMENT '是否离校',
  `emergency_tel` varchar(11) NOT NULL COMMENT '紧急联系人',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注说明',
  `status` varchar(45) NOT NULL DEFAULT '0' COMMENT '审批状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='请假管理表';

-- ----------------------------
-- Records of leave_application
-- ----------------------------
INSERT INTO `leave_application` VALUES ('1', '20191000003', '小明', '事假', '2023-03-25 20:54:16', '2023-02-23 16:00:00', '有事情，需回家一趟', '1', '13555555998', null, '2');
INSERT INTO `leave_application` VALUES ('2', '20191000002', '小红', '病假', '2023-03-27 15:32:38', '2023-02-23 10:00:00', '肚子疼', '0', '15999999999', null, '1');
INSERT INTO `leave_application` VALUES ('3', '20191000001', '小宋', '事假', '2023-03-27 15:33:58', '2023-03-27 09:30:00', '回家', '1', '18111111111', null, '1');
INSERT INTO `leave_application` VALUES ('4', '20191000005', '小朱', '实习', '2023-03-01 08:00:00', '2023-03-31 22:00:00', '实习', '1', '16564565436', null, '0');
INSERT INTO `leave_application` VALUES ('5', '20201000001', '高高', '实习', '2023-03-02 08:00:00', '2023-04-30 22:00:00', '实习', '0', '19521546513', null, '0');

-- ----------------------------
-- Table structure for materials
-- ----------------------------
DROP TABLE IF EXISTS `materials`;
CREATE TABLE `materials` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `goods_name` varchar(100) NOT NULL COMMENT '物资名称',
  `storage` varchar(100) NOT NULL COMMENT '库存量',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='物资库存表';

-- ----------------------------
-- Records of materials
-- ----------------------------
INSERT INTO `materials` VALUES ('1', 'N95口罩', '200(1/份）');
INSERT INTO `materials` VALUES ('2', '普通口罩', '500(10/份）');
INSERT INTO `materials` VALUES ('3', '核酸试纸', '100(1/份）');
INSERT INTO `materials` VALUES ('4', '午餐', '1000(1/份）');
INSERT INTO `materials` VALUES ('5', '晚餐', '1000(1/份）');
INSERT INTO `materials` VALUES ('6', '核酸抗原', '50(1/份）');
INSERT INTO `materials` VALUES ('7', '医用口罩', '1000(10/份）');

-- ----------------------------
-- Table structure for materials_application
-- ----------------------------
DROP TABLE IF EXISTS `materials_application`;
CREATE TABLE `materials_application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '申请者姓名',
  `goods_name` varchar(100) NOT NULL COMMENT '物资名称',
  `application` int(11) NOT NULL COMMENT '申请量',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注说明',
  `status` varchar(100) NOT NULL DEFAULT '0' COMMENT '审批状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='物资申请表';

-- ----------------------------
-- Records of materials_application
-- ----------------------------
INSERT INTO `materials_application` VALUES ('1', '20191000002', '小红', '普通口罩', '1', null, '2');
INSERT INTO `materials_application` VALUES ('2', '20191000001', '小宋', 'N95口罩', '5', null, '1');
INSERT INTO `materials_application` VALUES ('3', '20191000001', '小宋', '午餐', '4', '13-412宿舍隔离', '1');
INSERT INTO `materials_application` VALUES ('4', '20191000001', '小宋', '晚餐', '4', '13-412宿舍隔离', '0');
INSERT INTO `materials_application` VALUES ('5', '20191000009', '小鹿', '医用口罩', '1', '', '0');
INSERT INTO `materials_application` VALUES ('6', '20181000001', '飞飞', '午餐', '1', '学校公寓隔离', '0');
INSERT INTO `materials_application` VALUES ('7', '20181000001', '飞飞', '晚餐', '1', '学校公寓隔离', '0');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `username` varchar(11) NOT NULL COMMENT '用户名',
  `password` varchar(500) NOT NULL COMMENT '密码',
  `token` varchar(255) DEFAULT NULL,
  `name` varchar(20) NOT NULL COMMENT '真实姓名',
  `stuClass` varchar(50) NOT NULL COMMENT '班级',
  `dormitory` varchar(20) NOT NULL COMMENT '宿舍号',
  `tel` varchar(20) NOT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(100) NOT NULL COMMENT '家庭地址',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '用户状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='学生用户表';

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '20191000001', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小宋', '软工1901', '13-412', '13345678910', '1111111@qq.com', '广东省广州市', '0');
INSERT INTO `student` VALUES ('2', '20191000002', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小红', '计科1901', '3-225', '14345612345', '22222@qq.com', '湖南省长沙市', '0');
INSERT INTO `student` VALUES ('3', '20191000003', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小明', '网安1901', '9-224', '15398745655', '33333@qq.com', '广东省广州市', '0');
INSERT INTO `student` VALUES ('4', '20191000004', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小黄', '软工1903', '6-518', '16345678999', '666666@qq.com', '广东省深圳市', '0');
INSERT INTO `student` VALUES ('5', '20191000005', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小朱', '软工1901', '8-225', '15966443325', '55555@qq.com', '海南省海口市', '0');
INSERT INTO `student` VALUES ('6', '20191000006', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小夏', '软工1902', '6-515', '17355664477', '7777@qq.com', '浙江省金华市', '0');
INSERT INTO `student` VALUES ('8', '20191000007', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小李', '计科1902', '5-525', '13814554685', '9999@qq.com', '广东省深圳市', '0');
INSERT INTO `student` VALUES ('9', '20191000008', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '白白', '计科1902', '9-223', '13468546555', '', '广东省潮州市', '0');
INSERT INTO `student` VALUES ('10', '20191000009', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小鹿', '网安1903', '13-524', '1955555555', '', '河南省', '0');
INSERT INTO `student` VALUES ('12', '20191000010', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小瑟', '网安1901', '11-422', '1594245424', '', '广东省潮州市', '0');
INSERT INTO `student` VALUES ('13', '20181000050', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '丽丽', '计科1803', '5-235', '1525353535', '', '福建省', '0');
INSERT INTO `student` VALUES ('14', '20181000001', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '飞飞', '计科1801', '13-255', '1920000001', null, '江西省', '0');
INSERT INTO `student` VALUES ('15', '20201000001', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '高高', '软工2001', '9-515', '1952352312', null, '湖北省', '0');
INSERT INTO `student` VALUES ('16', '20211000001', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '尊尊', '计科2101', '13-512', '1595221123', null, '云南省大理市', '0');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `username` varchar(11) NOT NULL COMMENT '用户名',
  `password` varchar(500) NOT NULL COMMENT '密码',
  `token` varchar(255) DEFAULT NULL,
  `name` varchar(20) NOT NULL COMMENT '真实姓名',
  `tel` varchar(11) NOT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(100) NOT NULL COMMENT '家庭地址',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '用户状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='教师用户表';

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '2017001', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小宋', '19345678912', '1111111@qq.com', '广东省广州市', '0');
INSERT INTO `teacher` VALUES ('2', '2018001', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小谢', '15256543545', '222@qq.com', '广东省佛山市', '0');
INSERT INTO `teacher` VALUES ('3', '2015001', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小黄', '15954564564', '666@qq.com', '湖南省长沙市', '0');
INSERT INTO `teacher` VALUES ('4', '2017002', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小张', '13555555555', '', '广西省柳州市', '0');
INSERT INTO `teacher` VALUES ('5', '2018002', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小肖', '19200000122', null, '广东省广州市', '0');
INSERT INTO `teacher` VALUES ('6', '2016001', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小邓', '18522231232', null, '广东省潮州市', '0');
INSERT INTO `teacher` VALUES ('7', '2014050', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小李', '17542525351', null, '云南省昆明市', '0');
INSERT INTO `teacher` VALUES ('8', '2010001', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小瑟', '19548464513', null, '广东省广州市', '0');
INSERT INTO `teacher` VALUES ('9', '2011111', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小康', '15486564845', null, '浙江省金华市', '0');
INSERT INTO `teacher` VALUES ('10', '2016005', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小霞', '16451213546', null, '浙江省金华市', '0');
INSERT INTO `teacher` VALUES ('11', '2020001', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小夏', '17545213652', null, '广东省广州市', '0');
INSERT INTO `teacher` VALUES ('12', '2021001', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', null, '小玲', '19522222222', '', '广东省佛山市', '0');
