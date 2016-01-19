CREATE TABLE `jc_address` (
  `Id` bigint(20) NOT NULL auto_increment,
  `parent_id` bigint(20) default NULL COMMENT '父节点',
  `name` varchar(255) NOT NULL default '' COMMENT '城市名称',
  `priority` int(11) default '10' COMMENT '排序',
  PRIMARY KEY  (`Id`),
  KEY `fk_jc_address_parent` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='省、市、县表';

INSERT INTO `jc_address` VALUES (1,NULL,'江西省',1);
INSERT INTO `jc_address` VALUES (7,1,'南昌',10);
INSERT INTO `jc_address` VALUES (8,7,'西湖区',10);
INSERT INTO `jc_address` VALUES (9,NULL,'安徽省',10);
INSERT INTO `jc_address` VALUES (10,NULL,'北京市',10);
INSERT INTO `jc_address` VALUES (11,NULL,'上海市',2);
INSERT INTO `jc_address` VALUES (12,1,'抚州市',NULL);
INSERT INTO `jc_address` VALUES (13,12,'南丰县',NULL);
INSERT INTO `jc_address` VALUES (14,12,'南城县',NULL);
INSERT INTO `jc_address` VALUES (15,10,'通州区',NULL);
INSERT INTO `jc_address` VALUES (16,15,'中仓街道',NULL);
INSERT INTO `jc_address` VALUES (17,11,'黄浦区',NULL);
INSERT INTO `jc_address` VALUES (18,17,'南京东路区域',NULL);
CREATE TABLE `jc_core_admin` (
  `admin_id` bigint(20) NOT NULL auto_increment,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `is_disabled` tinyint(1) NOT NULL default '0' COMMENT '是否禁用',
  `is_viewonly_admin` tinyint(3) NOT NULL default '0' COMMENT '是否只读用户',
  PRIMARY KEY  (`admin_id`),
  KEY `fk_jc_admin_user` (`user_id`),
  KEY `fk_jc_admin_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='核心管理员表';

INSERT INTO `jc_core_admin` VALUES (1,1,1,'2009-06-13',0,0);
INSERT INTO `jc_core_admin` VALUES (2,1,5,'2014-07-12 11:03:23',0,1);
CREATE TABLE `jc_core_admin_role` (
  `role_id` int(11) NOT NULL default '0',
  `admin_id` bigint(11) NOT NULL default '0',
  PRIMARY KEY  (`role_id`,`admin_id`),
  KEY `fk_jc_core_role_admin` (`role_id`),
  KEY `fk_jc_core_admin_role` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `jc_core_admin_role` VALUES (1,1);
INSERT INTO `jc_core_admin_role` VALUES (1,2);
CREATE TABLE `jc_core_global` (
  `global_id` bigint(20) NOT NULL,
  `context_path` varchar(20) default NULL COMMENT '部署路径',
  `port` int(11) default NULL COMMENT '端口号',
  `treaty` longtext COMMENT '用户协议',
  `activescore` int(11) NOT NULL COMMENT '激活积分',
  `stock_warning` int(11) NOT NULL default '5' COMMENT '库存预警值',
  `def_img` varchar(255) NOT NULL default '/r/gou/u/no_picture.gif' COMMENT '图片不存在时默认图片',
  PRIMARY KEY  (`global_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='核心全局表';

INSERT INTO `jc_core_global` VALUES (1,'',8080,'<p><br />\r\n\t&nbsp;</p>\r\n<p>&nbsp;</p>\r\n<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"border_blue mt20\" width=\"650\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td class=\"left_title2\">\r\n\t\t\t\t<strong class=\"blue14\">服务协议</strong></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td align=\"left\" class=\"list_b\">\r\n\t\t\t\t<div class=\"Section1 mlr15\" style=\"layout-grid:  15.6pt none\">\r\n\t\t\t\t\t<p>《服务协议》（以下简称&ldquo;本协议&rdquo;）是由上海益实多电子商务有限公司在提供域名为www.yihaodian.com（以下简称&ldquo;1号店&rdquo;）的网站服务时与1号店用户达成的关于使用1号店网站的各项规则、条款和条件。本协议在1号店用户接受注册时生效。<br />\r\n\t\t\t\t\t\t请在成为1号店用户前，仔细阅读本协议中所述的所有规则、条款和条件，包括被本协议提及而纳入本协议的其他条款和条件。建议阅读本协议时，同时阅读本协议中提及的其他网页所包含的内容，因为其可能包含适用于1号店用户的其他条款和条件。<br />\r\n\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t<strong>一、</strong><strong> </strong><strong>用户注册：</strong><strong> </strong><br />\r\n\t\t\t\t\t\t1. 用户注册是指用户登陆1号店网站，按要求填写相关信息并确认同意本服务协议的过程。用户因进行交易、获取有偿服务等而发生的所有应纳税赋由用户自行承担。<br />\r\n\t\t\t\t\t\t2. 1号店用户必须是具有完全民事行为能力的自然人，或者是具有合法经营资格的实体组织。无民事行为能力人、限制民事行为能力人以及无经营或特定经营资格的组织不得注册为1号店用户或超过其民事权利或行为能力范围与1号店进行交易，如与1号店进行交易的，则服务协议自始无效，1号店有权立即停止与该用户的交易、注销该用户账户，并有权要求其承担相应法律责任。<br />\r\n\t\t\t\t\t\t&nbsp;</p>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t</tbody>\r\n</table>\r\n<p>&nbsp;</p>',10,100,'/r/gou/u/no_picture.gif');
CREATE TABLE `jc_core_log` (
  `log_id` int(11) NOT NULL auto_increment,
  `user_id` bigint(20) default NULL,
  `website_id` bigint(20) default NULL,
  `category` int(11) NOT NULL COMMENT '日志类型',
  `log_time` datetime NOT NULL COMMENT '日志时间',
  `ip` varchar(50) default NULL COMMENT 'IP地址',
  `url` varchar(255) default NULL COMMENT 'URL地址',
  `title` varchar(255) default NULL COMMENT '日志标题',
  `content` varchar(255) default NULL COMMENT '日志内容',
  PRIMARY KEY  (`log_id`),
  KEY `fk_jc_log_site` (`website_id`),
  KEY `fk_jc_log_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='jspgou日志表';

INSERT INTO `jc_core_log` VALUES (21,NULL,NULL,1,'2014-08-12 10:47:49',NULL,NULL,'4.3','1.修复关联了商品的规格修改报错，修复规格字段名称重复引起的款式问题。 2.修复跨站脚本攻击漏洞。3.修复子分类子城市添加修改，保存成功后返回顶级界面问题。4.优惠劵');
INSERT INTO `jc_core_log` VALUES (22,NULL,NULL,1,'2014-08-23 08:48:18',NULL,NULL,'4.4','1.修复IE下搜索分页出现乱码2.修复提交订单报错3.修复购物车小数异常4.新增支付宝担保交易接口');
INSERT INTO `jc_core_log` VALUES (23,NULL,NULL,1,'2014-08-23 08:51:39',NULL,NULL,'4.4','1.修复IE下搜索分页出现乱码2.修复提交订单报错3.修复购物车小数异常4.新增支付宝担保交易接口');
INSERT INTO `jc_core_log` VALUES (24,NULL,NULL,1,'2014-08-23 08:51:54',NULL,NULL,'4.4','1.修复IE下搜索分页出现乱码2.修复提交订单报错3.修复购物车小数异常4.新增支付宝担保交易接口');
INSERT INTO `jc_core_log` VALUES (25,NULL,NULL,1,'2014-09-08 14:49:25',NULL,NULL,'4.5','1.修复支付宝即时支付2.修复扩展属性3.捆绑销售');
CREATE TABLE `jc_core_member` (
  `member_id` bigint(20) NOT NULL auto_increment,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `create_time` datetime default NULL COMMENT '创建时间',
  `is_disabled` tinyint(1) NOT NULL default '0' COMMENT '是否禁用',
  `is_active` tinyint(1) NOT NULL COMMENT '是否激活',
  `activation_Code` char(32) default NULL COMMENT '激活码',
  PRIMARY KEY  (`member_id`),
  KEY `fk_jc_member_user` (`user_id`),
  KEY `fk_jc_member_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='核心会员表';

INSERT INTO `jc_core_member` VALUES (1,1,1,'2009-07-06',0,1,NULL);
INSERT INTO `jc_core_member` VALUES (4,4,1,'2014-07-09 15:37:30',0,1,'fe184138d63842729ab608548e23d8a8');
INSERT INTO `jc_core_member` VALUES (5,6,1,'2014-08-05 15:33:52',0,1,'2dd7392944434b198d6bc35b4de8998a');
CREATE TABLE `jc_core_message_tpl` (
  `website_id` bigint(20) NOT NULL,
  `message_name` varchar(50) NOT NULL COMMENT '信息名称',
  `message_subject` varchar(255) default NULL COMMENT '信息主题',
  `message_text` longtext COMMENT '信息内容',
  `active_title` varchar(255) default NULL COMMENT '用户激活标题',
  `active_txt` longtext COMMENT '用户激活内容',
  KEY `fk_jc_msgtpl_website` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息模板';

INSERT INTO `jc_core_message_tpl` VALUES (1,'resetPassword','找回密码请求','<p>您好</p>\r\n<p>${username}: 我们收到您的发出的找回密码请求。如果这不是您本人发出的请求，可以不予理会；如果您频繁收到这样的邮件，请尽快与管理员联系。</p>\r\n<p>新密码为：${resetPwd} 点击此地址，新密码即可生效：${base}/reset_password.jspx?uid=${uid}&amp;activationCode=${activationCode}</p>\r\n<p>百年老店</p>\r\n<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"650\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td align=\"middle\" rowspan=\"2\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com?node=1&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"1\"><img alt=\"\" border=\"0\" height=\"61\" src=\"http://www.yihaodian.com/images/edm/logo.gif\" title=\"1号店网上超市\" width=\"65\" /></a></td>\r\n\t\t\t<td align=\"left\" rowspan=\"2\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com?node=2&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"2\"><img alt=\"\" border=\"0\" src=\"http://www.yihaodian.com/images/pengpeng/slogen.jpg\" style=\"margin-top: 5px\" /></a></td>\r\n\t\t\t<td align=\"right\" height=\"38\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com?node=3&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #666666; font-size: 12px; text-decoration: underline\" target=\"_blank\" yihaodiannode=\"3\">如果邮件没有显示，请点击此处&gt;&gt;</a></td>\r\n\t\t\t<td>\r\n\t\t\t\t&nbsp;</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td align=\"right\" style=\"color: #cccccc; font-size: 12px\">\r\n\t\t\t\t<img align=\"absMiddle\" alt=\"\" height=\"14\" src=\"http://www.yihaodian.com/images/edm/icon1.gif\" width=\"15\" /> <a href=\"https://passport.yihaodian.com/passport/login_input.do?node=4&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #666666; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"4\">购物车</a> | <img align=\"absMiddle\" alt=\"\" height=\"14\" src=\"http://www.yihaodian.com/images/edm/icon2.gif\" width=\"15\" /> <a href=\"http://www.yihaodian.com/cms/view.do?topicId=9864&amp;node=5&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #666666; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"5\">帮助中心</a> | <img align=\"absMiddle\" alt=\"\" height=\"14\" src=\"http://www.yihaodian.com/images/edm/icon3.gif\" width=\"15\" /> <a href=\"http://www.yihaodian.com/usermanager/order/myIndex.do?node=6&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #666666; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"6\">我的账号</a> | <img alt=\"\" border=\"0\" height=\"14\" src=\"http://www.yihaodian.com/images/tongxiao/wm/edm/sina-logo.jpg\" width=\"15\" /> <a href=\"http://weibo.com/yihaodian?node=7&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #666666; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"7\">新浪微博分享你购物快乐</a></td>\r\n\t\t\t<td>\r\n\t\t\t\t&nbsp;</td>\r\n\t\t</tr>\r\n\t</tbody>\r\n</table>\r\n<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\r\n<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-top: 2px; height: 28px; overflow: hidden\" width=\"650\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com?node=8&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"8\">首页</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/channel/channelPage.do?channelId=5135&amp;merchant=1&amp;node=9&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"9\">食品饮料</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/channel/channelPage.do?channelId=8644&amp;merchant=1&amp;node=10&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"10\">进口食品</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/channel/channelPage.do?channelId=5009&amp;merchant=1&amp;node=11&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"11\">美容护理</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/channel/channelPage.do?channelId=5117&amp;merchant=1&amp;node=12&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"12\">母婴</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/channel/channelPage.do?channelId=5134&amp;merchant=1&amp;node=13&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"13\">厨卫清洁</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/dajiadian?node=14&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"14\">电器</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/shouji?node=15&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"15\">手机数码</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/diannao?node=16&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"16\">电脑</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/fuzhuang/clothes/index.action?merchant=1&amp;node=17&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"17\">服饰</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/channel/channelPage.do?channelId=8704&amp;node=18&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"18\">保健</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://search.yihaodian.com/s/c22906?node=19&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"19\">钟表</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/tushu?node=20&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"20\">图书音像</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/qiche?node=21&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"21\">汽车生活</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/lipin?node=22&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"22\">礼品卡</a></td>\r\n\t\t</tr>\r\n\t</tbody>\r\n</table>\r\n<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\r\n<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"650\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=23&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" title=\"1号店周年庆1千万热销单品免费领\" yihaodiannode=\"23\"><img alt=\"\" border=\"0\" height=\"203\" src=\"http://www.yihaodian.com/images/tongxiao/lp/0704_1.jpg\" style=\"vertical-align: bottom\" width=\"650\" /></a></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td style=\"background: #c00102\">\r\n\t\t\t\t<div style=\"border-bottom: #addd28 12px solid; border-left: #addd28 12px solid; padding-bottom: 5px; margin: 0px 8px; padding-left: 0px; width: 608px; padding-right: 0px; background: #fff; border-top: #addd28 12px solid; border-right: #addd28 12px solid; padding-top: 5px\">\r\n\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background: #fff\" width=\"100%\">\r\n\t\t\t\t\t\t<tbody>\r\n\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t<td width=\"316\">\r\n\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=24&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" title=\"1号店周年庆1千万热销单品免费领\" yihaodiannode=\"24\"><img alt=\"\" border=\"0\" height=\"234\" src=\"http://www.yihaodian.com/images/tongxiao/lp/0704_2.jpg\" style=\"vertical-align: bottom\" width=\"316\" /></a></td>\r\n\t\t\t\t\t\t\t\t<td align=\"left\">\r\n\t\t\t\t\t\t\t\t\t<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"line-height: 22px; font-family: \'微软雅黑\'; font-size: 12px\" width=\"85%\">\r\n\t\t\t\t\t\t\t\t\t\t<tbody>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=25&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"25\">A：蒙牛特仑苏纯牛奶250ml/包 X 3 组合装</a></td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=26&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"26\">B：Paiter鼻毛修剪器</a></td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=27&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"27\">C：李锦记卤水汁115ml</a></td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=28&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"28\">D：丝路宝典(新疆红马奶葡萄干220g）</a></td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=29&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"29\">E：凌仕 男士醒体沐浴露-诱因 50ml</a></td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<div align=\"right\"><a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=30&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"30\">领取更多免费商品</a><br />\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t1号店店粉们,每天免费商品有变化,需天天关注</div>\r\n\t\t\t\t\t\t\t\t\t\t\t\t</td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t</tbody>\r\n\t\t\t\t\t\t\t\t\t</table>\r\n\t\t\t\t\t\t\t\t</td>\r\n\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t</tbody>\r\n\t\t\t\t\t</table>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td style=\"background: #c00102\">\r\n\t\t\t\t<div style=\"border-bottom: #ef5608 12px solid; border-left: #ef5608 12px solid; padding-bottom: 5px; margin: 10px 8px; padding-left: 0px; width: 608px; padding-right: 0px; font-family: \'微软雅黑\'; background: #fff; font-size: 14px; border-top: #ef5608 12px solid; font-weight: bold; border-right: #ef5608 12px solid; padding-top: 5px\">\r\n\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background: #fff\" width=\"100%\">\r\n\t\t\t\t\t\t<tbody>\r\n\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t<td align=\"middle\" width=\"18%\">\r\n\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=31&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" title=\"iPhone4S (16G黑)非合约版4688元 （限50件）\" yihaodiannode=\"31\"><img alt=\"\" border=\"0\" height=\"117\" src=\"http://www.yihaodian.com/images/tongxiao/lp/0704_3.jpg\" style=\"vertical-align: bottom\" width=\"84\" /></a></td>\r\n\t\t\t\t\t\t\t\t<td width=\"30%\">\r\n\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=32&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"32\">iPhone4S (16G黑)非合约版</a><br />\r\n\t\t\t\t\t\t\t\t\t<span style=\"color: #ef5608\">4688元（限50件）</span></td>\r\n\t\t\t\t\t\t\t\t<td width=\"52%\">\r\n\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=33&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" title=\"iPad2 16G WIFI版 白色2578元(限392件)\" yihaodiannode=\"33\"><img alt=\"\" border=\"0\" height=\"139\" src=\"http://www.yihaodian.com/images/tongxiao/lp/0704_4.jpg\" style=\"vertical-align: bottom\" width=\"295\" /></a></td>\r\n\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t&nbsp;</td>\r\n\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=34&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" title=\"限时抢\" yihaodiannode=\"34\"><img alt=\"\" border=\"0\" height=\"46\" src=\"http://www.yihaodian.com/images/tongxiao/lp/0704_5.jpg\" style=\"vertical-align: bottom\" width=\"143\" /></a></td>\r\n\t\t\t\t\t\t\t\t<td style=\"padding-left: 100px\">\r\n\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=35&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"35\">iPad2 16G WIFI版 白色</a><br />\r\n\t\t\t\t\t\t\t\t\t<span style=\"color: #ef5608\">2578元（限392件）</span></td>\r\n\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t</tbody>\r\n\t\t\t\t\t</table>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<img alt=\"\" border=\"0\" height=\"118\" src=\"http://www.yihaodian.com/images/100104/EDM/20120601093427_01.jpg\" width=\"650\" /></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td align=\"left\" height=\"26\" style=\"line-height: 20px; padding-left: 15px; color: #666666; font-size: 12px\">\r\n\t\t\t\t4. 您如果不愿收到邮件，请<a href=\"http://www.yihaodian.com/subscriber/unsubscribe.do?email=1244860016%40qq.com&amp;&amp;node=36&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #666666; text-decoration: underline\" yihaodiannode=\"36\">点击这里</a>退订邮件。</td>\r\n\t\t</tr>\r\n\t</tbody>\r\n</table>\r\n<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\r\n<p style=\"background-color: #dc3538; margin: 0px; width: 650px; display: block; font-family: Arial, Helvetica, sans-serif; color: #ffffff; font-size: 12px; padding-top: 5px\"><a href=\"http://www.yihaodian.com/cms/view.do?topicId=8101&amp;node=37&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; text-decoration: none\" target=\"_blank\" yihaodiannode=\"37\">1号店 只为更好的生活</a></p>\r\n<p>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; <img alt=\"\" height=\"8\" src=\"http://www.yihaodian.com/images/edm/img_bottom.gif\" style=\"display: block\" width=\"650\" /> <img alt=\"\" src=\"http://tracker.yihaodian.com/tracker/info.do?1=1&amp;edmActivity=Y\" style=\"display: block\" width=\"0\" /></p>','欢迎您注册JSPGOU用户','<p>${userName}您好：</p>\r\n<p>欢迎您注册JSPGOU系统会员请点击以下链接进行激活 ${base}/active.jspx?userName=${userName}&amp;activationCode=${activationCode}</p>\r\n<p>请在24小时内进行激活，否则注册无效。</p>\r\n<p>&nbsp;</p>\r\n<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"650\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td align=\"middle\" rowspan=\"2\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com?node=1&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"1\"><img alt=\"\" border=\"0\" height=\"61\" src=\"http://www.yihaodian.com/images/edm/logo.gif\" title=\"1号店网上超市\" width=\"65\" /></a></td>\r\n\t\t\t<td align=\"left\" rowspan=\"2\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com?node=2&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"2\"><img alt=\"\" border=\"0\" src=\"http://www.yihaodian.com/images/pengpeng/slogen.jpg\" style=\"margin-top: 5px\" /></a></td>\r\n\t\t\t<td align=\"right\" height=\"38\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com?node=3&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #666666; font-size: 12px; text-decoration: underline\" target=\"_blank\" yihaodiannode=\"3\">如果邮件没有显示，请点击此处&gt;&gt;</a></td>\r\n\t\t\t<td>\r\n\t\t\t\t&nbsp;</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td align=\"right\" style=\"color: #cccccc; font-size: 12px\">\r\n\t\t\t\t<img align=\"absMiddle\" alt=\"\" height=\"14\" src=\"http://www.yihaodian.com/images/edm/icon1.gif\" width=\"15\" /> <a href=\"https://passport.yihaodian.com/passport/login_input.do?node=4&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #666666; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"4\">购物车</a> | <img align=\"absMiddle\" alt=\"\" height=\"14\" src=\"http://www.yihaodian.com/images/edm/icon2.gif\" width=\"15\" /> <a href=\"http://www.yihaodian.com/cms/view.do?topicId=9864&amp;node=5&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #666666; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"5\">帮助中心</a> | <img align=\"absMiddle\" alt=\"\" height=\"14\" src=\"http://www.yihaodian.com/images/edm/icon3.gif\" width=\"15\" /> <a href=\"http://www.yihaodian.com/usermanager/order/myIndex.do?node=6&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #666666; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"6\">我的账号</a> | <img alt=\"\" border=\"0\" height=\"14\" src=\"http://www.yihaodian.com/images/tongxiao/wm/edm/sina-logo.jpg\" width=\"15\" /> <a href=\"http://weibo.com/yihaodian?node=7&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #666666; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"7\">新浪微博分享你购物快乐</a></td>\r\n\t\t\t<td>\r\n\t\t\t\t&nbsp;</td>\r\n\t\t</tr>\r\n\t</tbody>\r\n</table>\r\n<p>&nbsp;</p>\r\n<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\r\n<p>&nbsp;</p>\r\n<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin-top: 2px; height: 28px; overflow: hidden\" width=\"650\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com?node=8&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"8\">首页</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/channel/channelPage.do?channelId=5135&amp;merchant=1&amp;node=9&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"9\">食品饮料</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/channel/channelPage.do?channelId=8644&amp;merchant=1&amp;node=10&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"10\">进口食品</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/channel/channelPage.do?channelId=5009&amp;merchant=1&amp;node=11&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"11\">美容护理</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/channel/channelPage.do?channelId=5117&amp;merchant=1&amp;node=12&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"12\">母婴</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/channel/channelPage.do?channelId=5134&amp;merchant=1&amp;node=13&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"13\">厨卫清洁</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/dajiadian?node=14&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"14\">电器</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/shouji?node=15&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"15\">手机数码</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/diannao?node=16&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"16\">电脑</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/fuzhuang/clothes/index.action?merchant=1&amp;node=17&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"17\">服饰</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/channel/channelPage.do?channelId=8704&amp;node=18&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"18\">保健</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://search.yihaodian.com/s/c22906?node=19&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"19\">钟表</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/tushu?node=20&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"20\">图书音像</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/qiche?node=21&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"21\">汽车生活</a></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<img alt=\"\" height=\"12\" src=\"http://www.yihaodian.com/images/edm/nav_bg.gif\" width=\"2\" /></td>\r\n\t\t\t<td align=\"middle\">\r\n\t\t\t\t<a href=\"http://channel.yihaodian.com/lipin?node=22&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; font-size: 12px; text-decoration: none\" target=\"_blank\" yihaodiannode=\"22\">礼品卡</a></td>\r\n\t\t</tr>\r\n\t</tbody>\r\n</table>\r\n<p>&nbsp;</p>\r\n<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\r\n<p>&nbsp;</p>\r\n<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"650\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=23&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" title=\"1号店周年庆1千万热销单品免费领\" yihaodiannode=\"23\"><img alt=\"\" border=\"0\" height=\"203\" src=\"http://www.yihaodian.com/images/tongxiao/lp/0704_1.jpg\" style=\"vertical-align: bottom\" width=\"650\" /></a></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td style=\"background: #c00102\">\r\n\t\t\t\t<div style=\"border-bottom: #addd28 12px solid; border-left: #addd28 12px solid; padding-bottom: 5px; margin: 0px 8px; padding-left: 0px; width: 608px; padding-right: 0px; background: #fff; border-top: #addd28 12px solid; border-right: #addd28 12px solid; padding-top: 5px\">\r\n\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background: #fff\" width=\"100%\">\r\n\t\t\t\t\t\t<tbody>\r\n\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t<td width=\"316\">\r\n\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=24&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" title=\"1号店周年庆1千万热销单品免费领\" yihaodiannode=\"24\"><img alt=\"\" border=\"0\" height=\"234\" src=\"http://www.yihaodian.com/images/tongxiao/lp/0704_2.jpg\" style=\"vertical-align: bottom\" width=\"316\" /></a></td>\r\n\t\t\t\t\t\t\t\t<td align=\"left\">\r\n\t\t\t\t\t\t\t\t\t<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"line-height: 22px; font-family: \'微软雅黑\'; font-size: 12px\" width=\"85%\">\r\n\t\t\t\t\t\t\t\t\t\t<tbody>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=25&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"25\">A：蒙牛特仑苏纯牛奶250ml/包 X 3 组合装</a></td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=26&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"26\">B：Paiter鼻毛修剪器</a></td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=27&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"27\">C：李锦记卤水汁115ml</a></td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=28&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"28\">D：丝路宝典(新疆红马奶葡萄干220g）</a></td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=29&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"29\">E：凌仕 男士醒体沐浴露-诱因 50ml</a></td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t<div align=\"right\"><a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=30&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"30\">领取更多免费商品</a><br />\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t1号店店粉们,每天免费商品有变化,需天天关注</div>\r\n\t\t\t\t\t\t\t\t\t\t\t\t</td>\r\n\t\t\t\t\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t\t\t\t</tbody>\r\n\t\t\t\t\t\t\t\t\t</table>\r\n\t\t\t\t\t\t\t\t</td>\r\n\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t</tbody>\r\n\t\t\t\t\t</table>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td style=\"background: #c00102\">\r\n\t\t\t\t<div style=\"border-bottom: #ef5608 12px solid; border-left: #ef5608 12px solid; padding-bottom: 5px; margin: 10px 8px; padding-left: 0px; width: 608px; padding-right: 0px; font-family: \'微软雅黑\'; background: #fff; font-size: 14px; border-top: #ef5608 12px solid; font-weight: bold; border-right: #ef5608 12px solid; padding-top: 5px\">\r\n\t\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background: #fff\" width=\"100%\">\r\n\t\t\t\t\t\t<tbody>\r\n\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t<td align=\"middle\" width=\"18%\">\r\n\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=31&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" title=\"iPhone4S (16G黑)非合约版4688元 （限50件）\" yihaodiannode=\"31\"><img alt=\"\" border=\"0\" height=\"117\" src=\"http://www.yihaodian.com/images/tongxiao/lp/0704_3.jpg\" style=\"vertical-align: bottom\" width=\"84\" /></a></td>\r\n\t\t\t\t\t\t\t\t<td width=\"30%\">\r\n\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=32&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"32\">iPhone4S (16G黑)非合约版</a><br />\r\n\t\t\t\t\t\t\t\t\t<span style=\"color: #ef5608\">4688元（限50件）</span></td>\r\n\t\t\t\t\t\t\t\t<td width=\"52%\">\r\n\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=33&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" title=\"iPad2 16G WIFI版 白色2578元(限392件)\" yihaodiannode=\"33\"><img alt=\"\" border=\"0\" height=\"139\" src=\"http://www.yihaodian.com/images/tongxiao/lp/0704_4.jpg\" style=\"vertical-align: bottom\" width=\"295\" /></a></td>\r\n\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t&nbsp;</td>\r\n\t\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=34&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" title=\"限时抢\" yihaodiannode=\"34\"><img alt=\"\" border=\"0\" height=\"46\" src=\"http://www.yihaodian.com/images/tongxiao/lp/0704_5.jpg\" style=\"vertical-align: bottom\" width=\"143\" /></a></td>\r\n\t\t\t\t\t\t\t\t<td style=\"padding-left: 100px\">\r\n\t\t\t\t\t\t\t\t\t<a href=\"http://www.yihaodian.com/cmsPage/show.do?pageId=6558&amp;node=35&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" target=\"_blank\" yihaodiannode=\"35\">iPad2 16G WIFI版 白色</a><br />\r\n\t\t\t\t\t\t\t\t\t<span style=\"color: #ef5608\">2578元（限392件）</span></td>\r\n\t\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t</tbody>\r\n\t\t\t\t\t</table>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<img alt=\"\" border=\"0\" height=\"118\" src=\"http://www.yihaodian.com/images/100104/EDM/20120601093427_01.jpg\" width=\"650\" /></td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td align=\"left\" height=\"26\" style=\"line-height: 20px; padding-left: 15px; color: #666666; font-size: 12px\">\r\n\t\t\t\t4. 您如果不愿收到邮件，请<a href=\"http://www.yihaodian.com/subscriber/unsubscribe.do?email=1244860016%40qq.com&amp;&amp;node=36&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #666666; text-decoration: underline\" yihaodiannode=\"36\">点击这里</a>退订邮件。</td>\r\n\t\t</tr>\r\n\t</tbody>\r\n</table>\r\n<p>&nbsp;</p>\r\n<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>\r\n<p style=\"background-color: #dc3538; margin: 0px; width: 650px; display: block; font-family: Arial, Helvetica, sans-serif; color: #ffffff; font-size: 12px; padding-top: 5px\"><a href=\"http://www.yihaodian.com/cms/view.do?topicId=8101&amp;node=37&amp;templateid=12284&amp;tracker_u=3310&amp;tracker_src=24635&amp;website_id=24635&amp;tracker_type=5&amp;edmEmail=1244860016@qq.com&amp;userid=117046088&amp;randNum=&amp;couponNum=null&amp;userName=1%E5%8F%B7%E5%BA%97%E7%94%A8%E6%88%B7\" style=\"color: #ffffff; text-decoration: none\" target=\"_blank\" yihaodiannode=\"37\">1号店 只为更好的生活</a></p>\r\n<p>&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; <img alt=\"\" height=\"8\" src=\"http://www.yihaodian.com/images/edm/img_bottom.gif\" style=\"display: block\" width=\"650\" /> <img alt=\"\" src=\"http://tracker.yihaodian.com/tracker/info.do?1=1&amp;edmActivity=Y\" style=\"display: block\" width=\"0\" /></p>');
CREATE TABLE `jc_core_role` (
  `role_id` int(11) NOT NULL auto_increment,
  `role_name` varchar(100) default NULL COMMENT '角色名称',
  `priority` int(11) default '10' COMMENT '排列顺序',
  `is_super` char(1) default '0' COMMENT '拥有所有权限',
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `jc_core_role` VALUES (1,'管理员',10,'1');
CREATE TABLE `jc_core_role_permission` (
  `role_id` int(11) NOT NULL default '0',
  `uri` varchar(100) NOT NULL default '',
  KEY `fk_jc_core_permission_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色授权表';

INSERT INTO `jc_core_role_permission` VALUES (1,'/top.do');
INSERT INTO `jc_core_role_permission` VALUES (1,'/right.do');
INSERT INTO `jc_core_role_permission` VALUES (1,'/main.do');
INSERT INTO `jc_core_role_permission` VALUES (1,'/left.do');
INSERT INTO `jc_core_role_permission` VALUES (1,'/index.do');
INSERT INTO `jc_core_role_permission` VALUES (1,'/logout.do');
CREATE TABLE `jc_core_user` (
  `user_id` bigint(20) NOT NULL auto_increment,
  `username` varchar(100) NOT NULL COMMENT '登录名',
  `email` varchar(100) default NULL COMMENT '电子邮箱',
  `password` char(32) NOT NULL COMMENT '密码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `login_count` bigint(20) NOT NULL default '0' COMMENT '总共登录次数',
  `register_ip` varchar(50) default NULL COMMENT '注册IP',
  `last_login_time` datetime default NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) default NULL COMMENT '最后登录IP',
  `current_login_time` datetime default NULL COMMENT '当前登录时间',
  `current_login_ip` varchar(50) default NULL COMMENT '当前登录IP',
  `reset_key` char(32) default NULL COMMENT '找回密码KEY',
  `reset_pwd` char(10) default NULL COMMENT '重置密码',
  `errTime` datetime default NULL COMMENT '登入错误时间',
  `errCount` int(11) NOT NULL default '0' COMMENT '登入错误次数',
  PRIMARY KEY  (`user_id`),
  UNIQUE KEY `ak_login_name` (`username`),
  UNIQUE KEY `ak_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='统一用户表';

INSERT INTO `jc_core_user` VALUES (1,'admin','admin@hotmail.com','5f4dcc3b5aa765d61d8327deb882cf99','2009-08-22',2339,NULL,'2014-09-09 08:48:25','127.0.0.1','2014-09-09 08:50:20','127.0.0.1',NULL,NULL,NULL,0);
INSERT INTO `jc_core_user` VALUES (4,'test','1220788677@qq.com','e10adc3949ba59abbe56e057f20f883e','2014-07-09 15:35:28',1,'127.0.0.1','2014-07-09 15:38:44','192.168.0.144','2014-07-09 15:38:44','192.168.0.144',NULL,NULL,NULL,0);
INSERT INTO `jc_core_user` VALUES (5,'test1','test@test.com','5a105e8b9d40e1329780d62ea2265d8a','2014-07-12 11:03:23',3,'127.0.0.1','2014-07-12 11:05:21','127.0.0.1','2014-07-12 11:05:44','127.0.0.1',NULL,NULL,NULL,0);
INSERT INTO `jc_core_user` VALUES (6,'test2','1873136502@qq.com','a1e8597f91a1ec724bc79eb03f7282ec','2014-08-05 15:33:52',1,'127.0.0.1','2014-08-05 15:49:56','192.168.0.144','2014-08-05 15:49:56','192.168.0.144',NULL,NULL,NULL,0);
CREATE TABLE `jc_core_website` (
  `website_id` bigint(20) NOT NULL auto_increment,
  `admin_id` bigint(20) default NULL COMMENT '创始人ID',
  `global_id` bigint(20) NOT NULL COMMENT '全局表ID',
  `parent_id` bigint(20) default NULL COMMENT '父站点ID',
  `domain` varchar(100) NOT NULL COMMENT '域名',
  `name` varchar(150) NOT NULL COMMENT '站点名称',
  `additionalTitle` varchar(255) default NULL COMMENT '附加标题',
  `current_system` varchar(20) NOT NULL COMMENT '当前系统（jeecms,jeeshop,jeeshop）',
  `suffix` varchar(20) NOT NULL default '.jhtml' COMMENT '访问后缀',
  `lft` int(11) NOT NULL default '1' COMMENT '树左边',
  `rgt` int(11) NOT NULL default '2' COMMENT '树右边',
  `create_time` datetime NOT NULL COMMENT '站点创建时间',
  `base_domain` varchar(100) default NULL COMMENT '根域名',
  `domain_alias` varchar(255) default NULL COMMENT '多个别名用;分割',
  `short_name` varchar(20) default NULL COMMENT '站点简称',
  `close_reason` varchar(255) default '' COMMENT '关闭原因',
  `is_close` tinyint(1) NOT NULL default '0' COMMENT '是否关闭网站（后台仍可访问）',
  `front_encoding` varchar(20) NOT NULL default 'GBK' COMMENT '前台编码',
  `front_content_type` varchar(50) NOT NULL default 'text/html; charset=gb2312' COMMENT '前台Content-Type',
  `locale_front` varchar(20) NOT NULL default 'zh_CN' COMMENT '前台本地化信息',
  `locale_admin` varchar(20) NOT NULL default 'zh_CN' COMMENT '后台本地化信息',
  `control_reserved` longtext COMMENT '用户信息保留字',
  `control_name_minlen` int(11) NOT NULL default '4' COMMENT '用户名最短几个字符',
  `control_front_ips` longtext COMMENT '前台允许访问的IP',
  `control_admin_ips` longtext COMMENT '后台允许访问的IP',
  `company` varchar(255) NOT NULL default '' COMMENT '公司名称',
  `copyright` varchar(255) NOT NULL default '' COMMENT '版权信息',
  `record_code` varchar(255) NOT NULL default '' COMMENT '备案号',
  `email` varchar(255) NOT NULL default '' COMMENT '电子邮箱',
  `phone` varchar(255) NOT NULL default '' COMMENT '电话号码',
  `mobile` varchar(255) NOT NULL default '' COMMENT '手机号码',
  `is_relative_path` tinyint(1) NOT NULL default '0' COMMENT '使用相对路径',
  `email_host` varchar(50) default NULL COMMENT '邮件发送服务器',
  `email_encoding` varchar(20) default NULL COMMENT '邮件发送编码',
  `email_username` varchar(100) default NULL COMMENT '邮箱用户名',
  `email_personal` varchar(100) default NULL COMMENT '邮箱发件人',
  `email_password` varchar(100) default NULL COMMENT '邮箱密码',
  `version` varchar(255) default NULL COMMENT '版本信息',
  `restart` tinyint(3) NOT NULL default '0',
  PRIMARY KEY  (`website_id`),
  UNIQUE KEY `ak_domain` (`domain`),
  KEY `fk_jc_website_admin` (`admin_id`),
  KEY `fk_jc_website_global` (`global_id`),
  KEY `fk_jc_website_parent` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='站点表';

INSERT INTO `jc_core_website` VALUES (1,1,1,NULL,'192.168.0.144','jeegou开发站','- Powered by JSPGOU','jspgou','.htm',1,2,'2009-06-13','',NULL,NULL,'网站暂时关闭',0,'GBK','text/html; charset=gbk','zh_CN','zh_CN',NULL,4,NULL,NULL,'','','','','','',1,'smtp.163.com','UTF-8','jspgou@163.com','百年老店','password','4.5',0);
CREATE TABLE `jc_data_backup` (
  `Id` int(11) NOT NULL auto_increment,
  `dataBasePath` varchar(255) default NULL COMMENT '数据库路径',
  `address` varchar(255) default NULL COMMENT '数据库地址',
  `dataBaseName` varchar(255) default NULL COMMENT '数据库名称',
  `username` varchar(255) default NULL COMMENT '数据库用户名',
  `password` varchar(255) default NULL COMMENT '数据库密码',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='数据备份信息';

INSERT INTO `jc_data_backup` VALUES (1,'C:\\\\Program Files\\\\MySQL\\\\MySQL Server 5.0\\\\bin\\\\','localhost','gou','root','password');
CREATE TABLE `jc_online_customerservice` (
  `Id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL default '' COMMENT '在线客服昵称',
  `content` varchar(255) NOT NULL default '' COMMENT 'QQ号',
  `priority` int(11) NOT NULL default '10' COMMENT '排序',
  `is_disable` tinyint(1) NOT NULL default '0' COMMENT '是否禁用',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='在线客服';

INSERT INTO `jc_online_customerservice` VALUES (1,'jspgou','1220788677',10,0);
CREATE TABLE `jc_popularity_group` (
  `Id` bigint(20) NOT NULL auto_increment,
  `name` varchar(150) default NULL COMMENT '名称',
  `beginTime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '结束时间',
  `price` double(11,2) default NULL COMMENT '组合价格',
  `description` varchar(255) default NULL COMMENT '活动描述',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='人气组合';

INSERT INTO `jc_popularity_group` VALUES (19,'test','2014-09-04 11:26:55','2014-09-04 11:26:57',60,'');
INSERT INTO `jc_popularity_group` VALUES (20,'test2','2014-09-01 17:11:56','2014-09-10 17:12:01',20,'');
CREATE TABLE `jc_popularity_group_product` (
  `Id` bigint(20) NOT NULL default '0' COMMENT '组合购买Id',
  `product_id` bigint(20) NOT NULL default '0' COMMENT '商品Id',
  PRIMARY KEY  (`Id`,`product_id`),
  KEY `fk_jc_popularity_group_product` (`Id`),
  KEY `fk_jc_popularity_product_group` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组合购买商品表';

INSERT INTO `jc_popularity_group_product` VALUES (19,1);
INSERT INTO `jc_popularity_group_product` VALUES (19,2);
INSERT INTO `jc_popularity_group_product` VALUES (19,3);
INSERT INTO `jc_popularity_group_product` VALUES (19,4);
INSERT INTO `jc_popularity_group_product` VALUES (19,5);
INSERT INTO `jc_popularity_group_product` VALUES (20,4);
INSERT INTO `jc_popularity_group_product` VALUES (20,5);
CREATE TABLE `jc_popularity_item` (
  `popularityitem_id` bigint(20) NOT NULL auto_increment,
  `cart_id` bigint(20) NOT NULL COMMENT '购物车ID',
  `count` int(11) NOT NULL COMMENT '数量',
  `popularity_id` bigint(20) default NULL,
  PRIMARY KEY  (`popularityitem_id`),
  KEY `fk_jc_shop_popularityitem_cart` (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='购物车';

CREATE TABLE `jc_shop_admin` (
  `admin_id` bigint(20) NOT NULL,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `firstname` varchar(100) default NULL COMMENT '名',
  `lastname` varchar(100) default NULL COMMENT '姓',
  PRIMARY KEY  (`admin_id`),
  KEY `fk_jc_shop_admin_website` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城管理员';

INSERT INTO `jc_shop_admin` VALUES (1,1,'',NULL);
INSERT INTO `jc_shop_admin` VALUES (2,1,'',NULL);
CREATE TABLE `jc_shop_advertise` (
  `Id` int(11) NOT NULL auto_increment,
  `adspace_id` int(11) default NULL COMMENT '广告位',
  `name` varchar(50) default NULL COMMENT '广告名字',
  `weight` int(1) default NULL COMMENT '广告权重',
  `display_count` int(11) default NULL COMMENT '展现次数',
  `click_count` int(11) default NULL COMMENT '点击次数',
  `starttime` datetime default NULL COMMENT '开始时间',
  `endTime` datetime default NULL COMMENT '节束时间',
  `is_enabled` char(1) default NULL COMMENT '是否启用',
  PRIMARY KEY  (`Id`),
  KEY `jc_shop_adspace_fk` (`adspace_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='广告';

INSERT INTO `jc_shop_advertise` VALUES (1,1,'.net云计算1',1,1264,0,NULL,NULL,'1');
CREATE TABLE `jc_shop_advertise_attr` (
  `Id` int(11) NOT NULL default '0',
  `attr_name` varchar(100) default NULL COMMENT '属性名字',
  `attr_value` varchar(255) default NULL COMMENT '属性值',
  KEY `fk_jc_params_advertising` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `jc_shop_advertise_attr` VALUES (1,'image_url','/u/201407/091116578hgi.jpg');
INSERT INTO `jc_shop_advertise_attr` VALUES (1,'image_width','981');
INSERT INTO `jc_shop_advertise_attr` VALUES (1,'image_height','70');
INSERT INTO `jc_shop_advertise_attr` VALUES (1,'image_target','_blank');
INSERT INTO `jc_shop_advertise_attr` VALUES (1,'image_link','http://demo3.jeecms.com');
CREATE TABLE `jc_shop_advertise_space` (
  `Id` int(11) NOT NULL auto_increment,
  `ad_name` varchar(50) default NULL COMMENT '版位名字',
  `description` varchar(255) default NULL COMMENT '版位描述',
  `is_enabled` char(1) default NULL COMMENT '是否启用',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='广告版位';

INSERT INTO `jc_shop_advertise_space` VALUES (1,'页头广告位','页头广告位','1');
CREATE TABLE `jc_shop_article` (
  `article_id` bigint(20) NOT NULL auto_increment,
  `website_id` bigint(20) NOT NULL,
  `channel_id` bigint(20) NOT NULL,
  `title` varchar(150) NOT NULL COMMENT '标题',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `is_disabled` tinyint(1) NOT NULL default '0' COMMENT '是否禁用',
  `link` varchar(255) default NULL COMMENT '外部链接',
  `param2` varchar(255) default NULL COMMENT '扩展数据2',
  `param3` varchar(255) default NULL COMMENT '扩展数据3',
  PRIMARY KEY  (`article_id`),
  KEY `fk_jc_shop_article_channel` (`channel_id`),
  KEY `fk_jc_shop_article_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='商城文章表';

INSERT INTO `jc_shop_article` VALUES (1,1,1,'积分说明','2014-07-04 09:29:32',0,'','','');
INSERT INTO `jc_shop_article` VALUES (2,1,1,'购买流程','2014-07-04 09:30:10',0,'','','');
INSERT INTO `jc_shop_article` VALUES (3,1,1,'账户注册','2014-07-04 09:30:55',0,'','','');
INSERT INTO `jc_shop_article` VALUES (4,1,1,'大宗购物','2014-07-04 09:31:40',0,'','','');
INSERT INTO `jc_shop_article` VALUES (5,1,2,'最新商品','2014-07-04 09:42:32',0,'','','');
INSERT INTO `jc_shop_article` VALUES (6,1,3,'jspgou v4.0即将发布','2014-07-04 10:11:58',0,'','','');
INSERT INTO `jc_shop_article` VALUES (7,1,4,'热门促销','2014-07-04 10:48:51',0,'','','');
INSERT INTO `jc_shop_article` VALUES (8,1,5,'订单拆分','2014-07-04 10:52:47',0,'','','');
INSERT INTO `jc_shop_article` VALUES (9,1,5,'海外配送','2014-07-04 10:54:14',0,'','','');
INSERT INTO `jc_shop_article` VALUES (10,1,5,'国内配送','2014-07-04 10:55:03',0,'','','');
INSERT INTO `jc_shop_article` VALUES (11,1,6,'其他支付方式','2014-07-04 10:57:28',0,'','','');
INSERT INTO `jc_shop_article` VALUES (12,1,6,'在线支付','2014-07-04 10:58:41',0,'','','');
INSERT INTO `jc_shop_article` VALUES (13,1,6,'货到付款','2014-07-04 10:59:24',0,'','','');
CREATE TABLE `jc_shop_article_content` (
  `article_id` bigint(20) NOT NULL,
  `content` longtext,
  KEY `fk_jc_shop_content_article` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城文章内容';

INSERT INTO `jc_shop_article_content` VALUES (1,'<div id=\"container\">\r\n\t<div class=\"clearfix mt10\">\r\n\t\t<div class=\"right_content\">\r\n\t\t\t<div class=\"help_box\">\r\n\t\t\t\t<div class=\"help_detail\">\r\n\t\t\t\t\t<dl style=\"padding-bottom: 0px; padding-left: 40px; padding-right: 40px; padding-top: 0px\">\r\n\t\t\t\t\t\t<dt>\r\n\t\t\t\t\t\t\t一、积分获得</dt>\r\n\t\t\t\t\t\t<dd>\r\n\t\t\t\t\t\t\t<p><strong>购物</strong></p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">1．您购买的商品由于积分不同，以订单提交时页面标注的积分分数为准。</p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">2．客户购物且订单生成后，积分将自动打入客户的积分帐户，状态为冻结，此时积分不可用。当客户此次购物为有效购物后，积分变为完成状态，便可以使用。</p>\r\n\t\t\t\t\t\t</dd>\r\n\t\t\t\t\t\t<dd>\r\n\t\t\t\t\t\t\t<p><strong>评论商品</strong></p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">1．您购物成功后可以对所购的商品进行评论，每条评论展示后可获得2积分（积分获得初期为冻结状态，订单完成5天后解冻；若订单一直未完成，订单出库后7天解冻）</p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">2．欢迎您提供优质的评论，将有机会被置顶展示并可最多获得积分50分额外奖励；</p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">3．您购买家电或3C类商品后进行评论，并成功展示将获得更多的积分（以售出时价格为准）：</p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">a)商品金额&le;200元 ，可获得积分：12分；</p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">b)商品金额200－1000元（不含200元和1000元），可获得积分：32分；</p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">c)商品金额&ge;1000元 可获得积分：62分；</p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">4．每个商品页面前5名被展示的评论，将额外获得10积分；（可与置顶奖励累计获得）</p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">5．单个订单中的商品只能参与一次评论。</p>\r\n\t\t\t\t\t\t</dd>\r\n\t\t\t\t\t\t<dd>\r\n\t\t\t\t\t\t\t<p><strong>一键分享</strong></p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">一键把您在1号店的消费感受分享给您在各大社交网站的好友与粉丝们，赚取积分，让那里的朋友也能同步分享您的生活与快乐！</p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">1．首次评论购买的商品后，并将评论分享到指定网站，都可获额外的1个积分。</p>\r\n\t\t\t\t\t\t\t<p class=\"txt_indent\">2．每条评论成功发表且分享只能一次，重复分享不获积分。</p>\r\n\t\t\t\t\t\t</dd>\r\n\t\t\t\t\t\t<dd>\r\n\t\t\t\t\t\t\t&nbsp;</dd>\r\n\t\t\t\t\t\t<dt>\r\n\t\t\t\t\t\t\t二、积分使用途径</dt>\r\n\t\t\t\t\t\t<dd>\r\n\t\t\t\t\t\t\t1．客户可以用积分至1号店<a href=\"http://www.yihaodian.com/point2/pointIndex.do\"><span class=\"Apple-converted-space\">&nbsp;</span><span class=\"blue\">&quot;积分商城&quot;</span></a>频道免费兑换或积分+现金的形式购买特惠专享商品。</dd>\r\n\t\t\t\t\t\t<dd>\r\n\t\t\t\t\t\t\t2．积分不可用于兑换现金，仅限参加1号店指定兑换物品、参与积分抽奖活动。</dd>\r\n\t\t\t\t\t\t<dd>\r\n\t\t\t\t\t\t\t3．如客户在1号店网站上作出违反1号店服务协议的行为，1号店将有权利取消注销客户用户的账户及积分。</dd>\r\n\t\t\t\t\t\t<dt>\r\n\t\t\t\t\t\t\t三、积分的交易状态说明</dt>\r\n\t\t\t\t\t\t<dd>\r\n\t\t\t\t\t\t\t<p>1.冻结：</p>\r\n\t\t\t\t\t\t\t<p>订单生成到订单完成过程中，购物积分将显示为冻结状态；暂时不可使用；</p>\r\n\t\t\t\t\t\t</dd>\r\n\t\t\t\t\t\t<dd>\r\n\t\t\t\t\t\t\t<p>2.取消：</p>\r\n\t\t\t\t\t\t\t<p>当您取消订单，则相应的购物积分状态显示为取消，这部分积分将从您的积分冻结帐户中扣除。</p>\r\n\t\t\t\t\t\t</dd>\r\n\t\t\t\t\t\t<dd>\r\n\t\t\t\t\t\t\t<p>3.完成：</p>\r\n\t\t\t\t\t\t\t<p>当购物流程完成，冻结积分会转化为可用积分，此时积分可在1号店消费</p>\r\n\t\t\t\t\t\t</dd>\r\n\t\t\t\t\t\t<dd>\r\n\t\t\t\t\t\t\t<p>4.积分何时解冻：</p>\r\n\t\t\t\t\t\t\t<p>当订单状态为：客户已收到或换货完成，退货完成和订单完成，积分会自动解冻成为可用积分</p>\r\n\t\t\t\t\t\t\t<p>即：下单后最迟15天积分会自动解冻。若订单中途换货，则积分会在申请换货9天后自动解冻 。</p>\r\n\t\t\t\t\t\t</dd>\r\n\t\t\t\t\t</dl>\r\n\t\t\t\t</div>\r\n\t\t\t</div>\r\n\t\t</div>\r\n\t</div>\r\n</div>\r\n<p><br class=\"Apple-interchange-newline\" />\r\n\t&nbsp;</p>');
INSERT INTO `jc_shop_article_content` VALUES (2,'<p align=\"left\">&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(0,0,0)\"><span style=\"font-family: \'Microsoft YaHei\'; color: rgb(229,51,51); font-size: 14px\"><strong><span style=\"color: rgb(161,0,0)\">Step.1</span></strong></span><span style=\"font-family: \'Microsoft YaHei\'; color: rgb(229,51,51); font-size: 14px\"><strong>&nbsp;</strong><span style=\"font-family: Tahoma; color: rgb(0,0,0); font-size: x-small\"><font size=\"1\"><span class=\"Apple-converted-space\">&nbsp;</span></font></span></span>进入gou网站后，点击左上方&ldquo;注册&rdquo;按钮进行注册。</span></p>\r\n<p><br />\r\n\t&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><span style=\"color: rgb(0,0,0)\"><span style=\"font-family: \'Microsoft YaHei\'; color: rgb(229,51,51); font-size: 14px\"><strong><span style=\"color: rgb(161,0,0)\">Step.2</span></strong></span>&nbsp;选择注册方式后，根据相关的提示内容填写注册信息。</span></p>\r\n<p align=\"center\">&nbsp;</p>\r\n<p>&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(0,0,0)\"><span style=\"font-family: \'Microsoft YaHei\'; color: rgb(229,51,51); font-size: 14px\"><strong><span style=\"color: rgb(161,0,0)\">Step.3</span></strong></span><span style=\"color: rgb(229,51,51); font-size: 14px\"><span style=\"font-family: \'Microsoft YaHei\'\"><strong><em>&nbsp;</em></strong><span style=\"font-family: Tahoma; color: rgb(0,0,0); font-size: x-small\"><font size=\"1\"><span class=\"Apple-converted-space\">&nbsp;</span></font></span></span></span>完成注册后，系统自动登录。您可以进入&#39;gou</span><span style=\"color: rgb(0,0,0)\">&#39;<span class=\"Apple-converted-space\">&nbsp;</span><span style=\"color: rgb(0,0,0)\">编</span>辑个人档案，或直接挑选所需购买的商品。</span></p>');
INSERT INTO `jc_shop_article_content` VALUES (3,'<div class=\"biaoti\"><b>账户注册</b></div>\r\n<div class=\"content\" id=\"helpcontent\" sizcache=\"0\" sizset=\"72\">\r\n\t<p><br />\r\n\t\t&nbsp;</p>\r\n\t<p>&nbsp;&nbsp;&nbsp; 您只要通过&nbsp;gou 注册开户，即可成为&nbsp;gou 会员。<br />\r\n\t\t&nbsp;&nbsp;&nbsp; 在&nbsp;gou 注册开户有以下途径：<br />\r\n\t\t<br />\r\n\t\t&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong>1、网站注册</strong><br />\r\n\t\t&nbsp;&nbsp;&nbsp; 1）进入gou网站后，点击页面左上方&ldquo;注册&rdquo;，将出现新页面，在新页面中选择相应的注册方式（Email注册），根据提示内容输入注册账号、密码（密码设置不要过于简单）、验证码等，确认无误后点击&ldquo;同意以上条款并注册&rdquo;即可，注册用户名是唯一的。注册成功后，您可以到网站&#39;gou&#39;进行个人信息的更新。</p>\r\n</div>\r\n<p>&nbsp;</p>');
INSERT INTO `jc_shop_article_content` VALUES (4,'<div class=\"biaoti\"><b>大宗购物优惠</b></div>\r\n<div class=\"content\" id=\"helpcontent\">\r\n\t<p><br />\r\n\t\t&nbsp;&nbsp;&nbsp; 针对企业用户，gou提供LOGO刺绣服务；此外，当订购数量达到一定金额，还可享受相应折扣。</p>\r\n\t<p>&nbsp;</p>\r\n\t<p>&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong>优惠政策</strong><br />\r\n\t\t&nbsp;&nbsp;&nbsp; 请注意以下几点&mdash;&mdash;<br />\r\n\t\t&nbsp;&nbsp;&nbsp; 1）大宗购物优惠政策与会员优惠政策不能同时享受。<br />\r\n\t\t&nbsp;&nbsp;&nbsp; 2）大宗购物优惠政策仅限购买单品享受，抢购、限时抢购、促销活动不能享受大宗购物优惠。<br />\r\n\t\t&nbsp;&nbsp;&nbsp; 3）单张特例品（不包含抢购、限时抢购产品）订单金额满5000元则最高享受95折优惠，促销活动不享受此折扣。<br />\r\n\t\t&nbsp;&nbsp;&nbsp; 4）此处所指的采购金额为单张订单的采购金额，不支持不同订单的合并累计计算。<br />\r\n\t\t&nbsp;&nbsp;&nbsp; 5）采购金额的折扣优惠完全严格按照如下的标准执行，所以gou不提供另外议价的服务。</p>\r\n\t<p align=\"center\">&nbsp;</p>\r\n\t<table align=\"center\" border=\"1\" bordercolor=\"#000000\" cellpadding=\"2\" cellspacing=\"0\" style=\"width: 985px\">\r\n\t\t<tbody>\r\n\t\t</tbody>\r\n\t</table>\r\n\t<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"ke-zeroborder\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; width: 510pt; border-collapse: collapse; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\">\r\n\t\t<colgroup>\r\n\t\t\t<col span=\"3\" style=\"width: 170pt\" width=\"226\" />\r\n\t\t</colgroup>\r\n\t\t<tbody>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl67\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: rgb(242,242,242); width: 170pt; height: 13.5pt; border-top: rgb(216,216,216) 1pt solid; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">从</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl67\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: rgb(242,242,242); width: 170pt; border-top: rgb(216,216,216) 1pt solid; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">至</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl67\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: rgb(242,242,242); width: 170pt; border-top: rgb(216,216,216) 1pt solid; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">折扣（单位：元）</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl65\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: transparent; width: 170pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">5,000</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl65\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 170pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">9,999</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 170pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">9.5折</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl65\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: transparent; width: 170pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">10,000</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl65\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 170pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">49,999</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 170pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">9折</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl65\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: transparent; width: 170pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">50,000</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl65\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 170pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">199,999</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 170pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">8.5折</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl65\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: transparent; width: 170pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">200,000</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 170pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">以上</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 170pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"226\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">8折</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t</tbody>\r\n\t</table>\r\n\t<p>&nbsp;</p>\r\n\t<p align=\"center\">&nbsp;</p>\r\n\t<p align=\"left\">&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong>LOGO刺绣</strong><br />\r\n\t\t&nbsp;&nbsp;&nbsp; 1）对于需要提供LOGO刺绣服务的企业订单，只能接受款到送货的服务，款到后两周交货（如您需要刺绣的商品超过一千件，交货时间我们会再次与您沟通）。<br />\r\n\t\t&nbsp;&nbsp;&nbsp; 2）Logo要求：由于技术限制，刺绣LOGO的大小不超过10*10cm，颜色不超过6种。</p>\r\n\t<p align=\"center\">&nbsp;</p>\r\n\t<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"ke-zeroborder\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; width: 511pt; border-collapse: collapse; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\" width=\"679\">\r\n\t\t<colgroup>\r\n\t\t\t<col style=\"width: 35pt\" width=\"46\" />\r\n\t\t\t<col style=\"width: 58pt\" width=\"77\" />\r\n\t\t\t<col style=\"width: 127pt\" width=\"169\" />\r\n\t\t\t<col style=\"width: 76pt\" width=\"101\" />\r\n\t\t\t<col style=\"width: 139pt\" width=\"185\" />\r\n\t\t\t<col style=\"width: 76pt\" width=\"101\" />\r\n\t\t</colgroup>\r\n\t\t<tbody>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl67\" colspan=\"6\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: rgb(242,242,242); width: 511pt; height: 13.5pt; border-top: rgb(216,216,216) 1pt solid; border-right: rgb(216,216,216) 1pt solid\" width=\"679\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">刺绣位置及尺码范围标准</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl68\" colspan=\"2\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: rgb(242,242,242); height: 13.5pt; border-top: rgb(216,216,216) 1pt solid; border-right: rgb(216,216,216) 1pt solid\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">服装位置</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl67\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: rgb(242,242,242); width: 127pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"169\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">刺绣位置</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl67\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: rgb(242,242,242); width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">LOGO大小</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl67\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: rgb(242,242,242); width: 139pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"185\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">刺绣位置</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl69\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: rgb(242,242,242); width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">LOGO大小</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl70\" height=\"122\" rowspan=\"6\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: transparent; height: 91.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">衬衫</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl71\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">　</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl72\" colspan=\"2\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 203pt; border-top: rgb(216,216,216) 1pt solid; border-right: rgb(216,216,216) 1pt solid\" width=\"270\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">长袖衬衫</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl72\" colspan=\"2\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 215pt; border-top: rgb(216,216,216) 1pt solid; border-right: rgb(216,216,216) 1pt solid\" width=\"286\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">短袖衬衫</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl72\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 58pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"77\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">左前胸</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl73\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 127pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"169\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">口袋上方1.5cm处左右居中</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl75\" rowspan=\"2\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;10cm*10cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl74\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 139pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"185\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">口袋上方1.5cm处，左右居中</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl75\" rowspan=\"2\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;10cm*10cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl72\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 58pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"77\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">右前胸</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl73\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 127pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"169\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">与左胸对称</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl74\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 139pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"185\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">与左胸对称</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"32\" style=\"height: 24pt\">\r\n\t\t\t\t<td class=\"xl72\" height=\"32\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 58pt; height: 24pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"77\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">左<span style=\"font-size: 12px\">&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span></span>袖</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl73\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 127pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"169\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">袖口往上3.5cm处与袖口第一颗纽扣对称</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;4cm*7cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl74\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 139pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"185\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">袖口往上4.0cm处，左右居中</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;4cmcm*6cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl72\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 58pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"77\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">右<span style=\"font-size: 12px\">&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span></span>袖</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl73\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 127pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"169\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">与左袖对称</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;4cm*7cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl74\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 139pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"185\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">与左袖对称</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;4cmcm*6cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl72\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 58pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"77\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">右下摆</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl73\" colspan=\"2\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 203pt; border-top: rgb(216,216,216) 1pt solid; border-right: rgb(216,216,216) 1pt solid\" width=\"270\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">不提供</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl73\" colspan=\"2\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 215pt; border-top: rgb(216,216,216) 1pt solid; border-right: rgb(216,216,216) 1pt solid\" width=\"286\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">不提供</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl70\" height=\"126\" rowspan=\"7\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: transparent; height: 94.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">T恤</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl70\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">　</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl72\" colspan=\"2\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 203pt; border-top: rgb(216,216,216) 1pt solid; border-right: rgb(216,216,216) 1pt solid\" width=\"270\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">长袖T恤</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl72\" colspan=\"2\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 215pt; border-top: rgb(216,216,216) 1pt solid; border-right: rgb(216,216,216) 1pt solid\" width=\"286\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">短袖T恤</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl72\" height=\"36\" rowspan=\"2\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: transparent; width: 58pt; height: 27pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"77\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">左前胸</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl73\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 127pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"169\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">高低与前门襟底部齐平</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" rowspan=\"2\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;10cm*10cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl74\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 139pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"185\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">高低与前门襟底部齐平</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" rowspan=\"2\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;10cm*10cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl73\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 127pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"169\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">左右位置从门襟到挂肩居中</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl74\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 139pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"185\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">左右位置从门襟到挂肩居中</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl72\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 58pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"77\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">右前胸</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl73\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 127pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"169\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">与左胸对称</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;10cm*10cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl74\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 139pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"185\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">与左胸对称</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;10cm*10cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl72\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 58pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"77\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">左<span style=\"font-size: 12px\">&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span></span>袖</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl73\" colspan=\"2\" rowspan=\"2\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1pt solid; background-color: transparent; width: 203pt; border-top: rgb(216,216,216) 1pt solid; border-right: rgb(216,216,216) 1pt solid\" width=\"270\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">不提供</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl74\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 139pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"185\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">袖口往上4.0cm处，左右居中</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;4cmcm*6cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl72\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 58pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"77\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">右<span style=\"font-size: 12px\">&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span></span>袖</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl74\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 139pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"185\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">与左袖对称</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;4cmcm*6cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr height=\"18\" style=\"height: 13.5pt\">\r\n\t\t\t\t<td class=\"xl72\" height=\"18\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 58pt; height: 13.5pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"77\">\r\n\t\t\t\t\t<p align=\"center\"><strong><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">右下摆</span></strong></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl73\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 127pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"169\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">底边往上4cm，侧缝往左4cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;3cmcm*5cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl74\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 139pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"185\">\r\n\t\t\t\t\t<p align=\"left\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">底边往上4cm，侧缝往左4cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td class=\"xl66\" style=\"border-bottom: rgb(216,216,216) 1pt solid; border-left: rgb(216,216,216) 1px dotted; background-color: transparent; width: 76pt; border-top: rgb(216,216,216) 1px dotted; border-right: rgb(216,216,216) 1pt solid\" width=\"101\">\r\n\t\t\t\t\t<p align=\"center\"><span style=\"font-family: 宋体; color: rgb(128,128,128); font-size: 12px\">&le;3cmcm*5cm</span></p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t</tbody>\r\n\t</table>\r\n\t<p>&nbsp;</p>\r\n\t<p align=\"left\">&nbsp;&nbsp;&nbsp; 3）客户需要提供LOGO本身颜色对应的色卡号码（最好是潘东色号），gou会依此选择相同或相近的颜色进行LOGO的刺绣。<br />\r\n\t\t&nbsp;&nbsp;&nbsp; 4）刺绣费用：如果您是第一次订购，或者更换新的LOGO，您需要另外支付制版费200元/个LOGO；若第二次订购，使用相同的LOGO，免制版费用；20件以上可以为您安排刺绣，刺绣价格为10元/个（如需要为刺绣图案打底，费用为30元/个）。</p>\r\n\t<p align=\"left\">&nbsp;</p>\r\n\t<p><span style=\"font-size: 12px\">&nbsp;</span>&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong>大宗购物温馨提示</strong><br />\r\n\t\t&nbsp;&nbsp;&nbsp; 1）大宗购物订单，需要款到发货。<br />\r\n\t\t&nbsp;&nbsp;&nbsp; 2）大宗购物订单送到时，请您务必对照发货单核对商品，如果出现商品数量缺少、商品破损等情况，请您当场与VANCL大宗购物部联系，我们会及时为您解决；核实无误后方可签收商品。</p>\r\n\t<p>&nbsp;</p>\r\n\t<p>&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong>大宗购物退换货原则</strong><br />\r\n\t\t&nbsp;&nbsp;&nbsp; 1）大宗购物商品签收后在未经穿着、洗涤、修改、加工等情况下7天内可以进行一次更换或退货。<br />\r\n\t\t&nbsp;&nbsp;&nbsp; 2）刺绣产品请收到后对其刺绣品质进行验收，如存在质量问题，请在7天内提出并与gou大宗购物部联系，确认质量问题后予以办理退换货服务。</p>\r\n\t<p>&nbsp;</p>\r\n\t<p>&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><span style=\"color: rgb(161,0,0)\">如需大宗购物或对大宗购物有任何疑问，请联系大宗购物部&mdash;&mdash;</span><br />\r\n\t\t&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong>邮箱：</strong><a href=\"mailto:tuangou@gou.cn\"><span style=\"color: rgb(0,51,153)\">tuangou@gou.cn</span></a><br />\r\n\t\t&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong>电话：</strong>4006361055（工作时间：周一至周五9：00- 18：00 ）<br />\r\n\t\t&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong>传真：</strong>0571-59763401（请注明大宗购物部收）</p>\r\n</div>\r\n<p>&nbsp;</p>');
INSERT INTO `jc_shop_article_content` VALUES (5,'<p>先添加一部分测试商品，以配合美工团队做模板设计制作，争取早日发布jspgou v4.0</p>');
INSERT INTO `jc_shop_article_content` VALUES (6,'<div style=\"background-color: rgb(255,255,255); font-family: Arial, Verdana, sans-serif; color: rgb(34,34,34); font-size: 12px\">\r\n\t<p>现在jspgou v4.0程序测试已接近尾声，该修复的bug已经修复完毕了，就等前台模板制作了，模板制作完毕即发布jspgou v4.0。</p>\r\n</div>\r\n<p>&nbsp;</p>');
INSERT INTO `jc_shop_article_content` VALUES (7,'<div style=\"background-color: rgb(255,255,255); font-family: Arial, Verdana, sans-serif; color: rgb(34,34,34); font-size: 12px\">\r\n\t<p>这个栏目开的很有意思</p>\r\n</div>\r\n<p>&nbsp;</p>');
INSERT INTO `jc_shop_article_content` VALUES (8,'<p>&nbsp;gou北京、上海、广州三地库房均可直接发货至全国，当订购的商品在某一个库房缺货，系统会自动将订单进行拆分，缺货商品将从库存充足的库房发出，以确保您能更快捷的收到所选商品。</p>\r\n<p>&nbsp;&nbsp; &nbsp;<span><strong>温馨提示：</strong>订单拆单后享受原订单的促销及优惠，相关的赠送优惠及运费收取均按未分单前进行计算。</span></p>\r\n<p><br class=\"Apple-interchange-newline\" />\r\n\t&nbsp;</p>');
INSERT INTO `jc_shop_article_content` VALUES (9,'<p><span class=\"Apple-converted-space\">&nbsp;</span><strong><span style=\"color: rgb(0,0,0)\">海外订单配送费用计算方法</span></strong><br />\r\n\t<span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp; 海外订单配送费用为订单的起送价格与订单中所有商品配送费用总和的较高者。</span><br />\r\n\t<span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp; 海外订单不享受任何免运费的促销活动。</span></p>\r\n<p><span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp;</span></p>\r\n<p><span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong><span style=\"color: rgb(161,0,0)\">可配送的国家和地区</span></strong></span><br />\r\n\t<span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp; 目前海外订单可支持包括港澳台在内的全球70多个国家和地区的配送，具体国家/地区的配送情况请查询：</span></p>\r\n<p><span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span></span><strong><span style=\"color: rgb(0,0,0)\">可海外配送的商品</span></strong><br />\r\n\t<span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp; 目前仅部分商品支持海外配送，支持海外配送的商品会在单品页右上方显示&ldquo;海外配送&rdquo;的标签，如下图：</span></p>\r\n<p style=\"text-align: center\"><img alt=\"\" border=\"0\" src=\"http://images.vancl.com/HelpUpload/201108/26/14_44_34_845_4181_u142.png\" style=\"cursor: default\" /></p>\r\n<p>&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong><span style=\"color: rgb(0,0,0)\">注：</span></strong><span style=\"color: rgb(0,0,0)\">海外配送订单暂时仅从北京库房发货，北京库房缺货的商品暂不支持海外配送。</span></p>\r\n<p>&nbsp;</p>\r\n<p>&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong><span style=\"color: rgb(0,0,0)\">海外订单的订购</span></strong><br />\r\n\t<span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp; 可海外配送的商品加入购物车结算，填写收货地址时，选择&ldquo;海外地址&rdquo;或点击&ldquo;去填写海外地址&rdquo;，即可填写海外地址进行订购。</span></p>\r\n<p align=\"center\"><img alt=\"\" border=\"0\" src=\"http://images.vancl.com/HelpUpload/201109/1/9_58_3_370_1499_20110901_095544.jpg\" style=\"width: 711px; height: 280px; cursor: default\" /></p>\r\n<p>&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong><span style=\"color: rgb(0,0,0)\">注：</span></strong><span style=\"color: rgb(0,0,0)\">为了保证</span><span style=\"color: rgb(0,0,0)\">订单顺利送达，请</span><span style=\"color: rgb(0,0,0)\">您在填写</span><span style=\"color: rgb(0,0,0)\">配送</span><span style=\"color: rgb(0,0,0)\">信息时</span><span style=\"color: rgb(0,0,0)\">使用英文</span><span style=\"color: rgb(0,0,0)\">进行</span><span style=\"color: rgb(0,0,0)\">填写。</span></p>\r\n<p><span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp;</span></p>\r\n<p><span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span></span><strong><span style=\"color: rgb(0,0,0)\">海外订单支付</span></strong><br />\r\n\t<span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp; 配送到海外的订单暂时不支持货到付款，您可以使用国内的在线支付或者邮局汇款支付。同时还可以使用海外信用卡（visa或mastercard ）或paypal支付。</span><br />\r\n\t<span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp; 选择海外信用卡支付，系统会自动跳转到我们的合作伙伴首信易支付，您可以在首信易支付平台使用海外信用卡支付订单。</span><br />\r\n\t<span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp; 选择paypal支付，由于paypal用美元核算，订单金额将自动转为美元金额以方便您的支付。人民币转换美元的汇率以您支付时的汇率为准。</span></p>\r\n<p><span style=\"color: rgb(0,0,0)\">&nbsp;</span></p>\r\n<p><span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span></span><strong><span style=\"color: rgb(0,0,0)\">海外订单售后服务</span></strong></p>\r\n<p><span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp; 海外订单暂时不享受无条件退换货服务，您的订单出库后将不能取消或修改订单，请您下单前考虑周全。<br />\r\n\t&nbsp;&nbsp;&nbsp; 如果您购买的商品出现质量问题，请通过电子邮件方式联系凡客诚品：<a href=\"mailto:service_intl@gou.com\"><span style=\"color: rgb(0,51,153); text-decoration: none\">service_intl@gou.com</span></a>。</span></p>\r\n<p><span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong><span style=\"color: rgb(0,0,0)\">温馨提示：</span></strong></span></p>\r\n<p>&nbsp;</p>\r\n<p><span style=\"color: rgb(0,0,0)\">&nbsp;&nbsp; &nbsp;在购买商品前请您尽量充分了解订单收件人所在国家的相关海关税收政策，我们将参考您的订单金额为您报关，您可能需要为某些货物支付进口关税，具体税额以收货人当地的海关政策为准。<br />\r\n\t&nbsp;&nbsp; &nbsp;如遇海外配送订单问题可发送邮件至：<a href=\"mailto:service_intl@goucom\"><span style=\"color: rgb(0,51,153); text-decoration: none\">service_intl@goucom</span></a>，我们将竭诚为您服务。</span></p>');
INSERT INTO `jc_shop_article_content` VALUES (10,'<div style=\"background-color: rgb(255,255,255); font-family: Arial, Verdana, sans-serif; color: rgb(34,34,34); font-size: 12px\">\r\n\t<div class=\"liucheng\">\r\n\t\t<div class=\"biaoti\"><b>国内配送</b></div>\r\n\t\t<div class=\"content\">\r\n\t\t\t<p><span class=\"red\" style=\"line-height: 25px\">请您根据收货人地址选择正确的省、市、区/县后，系统会提示您可供选择的送货方式及相关配送信息。</span></p>\r\n\t\t\t<p>友情提示：<br />\r\n\t\t\t\t1．订购成功后，您可以通过我们给您发的确认邮件或登陆我们的网站在&ldquo;gou-订单查询&rdquo;中查看订单信息，选择非货到付款方式的订单请及时支付货款，款到后我们会尽早为您安排发货。<br />\r\n\t\t\t\t2．gou已开通三地库房全国发货服务，系统会根据实际库存情况为您选择发货库房，以确保您能更快收到订购的商品。<br />\r\n\t\t\t\t3．因发货库房不同，货物配送到您手中的配送时间会有所不同，提交订单后系统会提示您准确的配送时间，请您关注。<br />\r\n\t\t\t\t4．为了确保商品及时准确的配送到您手中，请您在订购时填写详细的收货人信息及联系方式。<br />\r\n\t\t\t\t5．配送费收费标准为：5元/单，全场购物满59元免运费。<br />\r\n\t\t\t\t6．gou目前未开通自提服务，您的订单将由gou的配送网络直接送到您指定地点。<br />\r\n\t\t\t\t7．北京、上海（崇明除外）、广州、深圳、江苏、四川、重庆、安徽、湖北、浙江、山东等地区支持上门退换货业务，上门退换货办理时间与正常配送时间一致。<br />\r\n\t\t\t\t8．我们的送货时间为：早8：30－晚19：00，请您合理安排收货时间，如您有特殊需求请联系我们，我们会尽力为您协商解决。</p>\r\n\t\t</div>\r\n\t</div>\r\n</div>\r\n<p>&nbsp;</p>');
INSERT INTO `jc_shop_article_content` VALUES (11,'<div style=\"background-color: rgb(255,255,255); font-family: Arial, Verdana, sans-serif; color: rgb(34,34,34); font-size: 12px\">\r\n\t<div class=\"biaoti\" style=\"text-align: center\">\r\n\t\t<table bgcolor=\"#67a9e5\" border=\"0\" cellpadding=\"0\" cellspacing=\"1\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\" width=\"600\">\r\n\t\t\t<tbody>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td background=\"http://www.jeecms.com/3C92DF\" bgcolor=\"#3c92df\" height=\"28\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\" width=\"260\">\r\n\t\t\t\t\t\t<b><strong style=\"color: rgb(255,255,255)\">支付方式</strong></b></td>\r\n\t\t\t\t\t<td background=\"http://www.jeecms.com/3C92DF\" bgcolor=\"#3c92df\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\" width=\"340\">\r\n\t\t\t\t\t\t<b><strong style=\"color: rgb(255,255,255)\">帐户信息</strong></b></td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td bgcolor=\"#ffffff\" height=\"70\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\">\r\n\t\t\t\t\t\t<b><img alt=\"商业购买\" src=\"http://www.jeecms.com/res_base/jeecms_com_www/default/article/img/bank/nh.jpg\" style=\"cursor: default\" /></b></td>\r\n\t\t\t\t\t<td bgcolor=\"#ffffff\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; line-height: 22px; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\">\r\n\t\t\t\t\t\t<b>&nbsp;户 名：黄春荣<br />\r\n\t\t\t\t\t\t&nbsp;卡 号：6228　4809　2010　9896　414<br />\r\n\t\t\t\t\t\t&nbsp;开 户：南昌市洪城大市场支行</b></td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td bgcolor=\"#ffffff\" height=\"70\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\">\r\n\t\t\t\t\t\t<b><img alt=\"商业购买\" height=\"50\" src=\"http://www.jeecms.com/res_base/jeecms_com_www/default/article/img/bank/jh.jpg\" style=\"cursor: default\" width=\"200\" /></b></td>\r\n\t\t\t\t\t<td bgcolor=\"#ffffff\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; line-height: 22px; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\">\r\n\t\t\t\t\t\t<b>&nbsp;户 名：黄春荣<br />\r\n\t\t\t\t\t\t&nbsp;卡 号：6227　0020　2158　0046　990<br />\r\n\t\t\t\t\t\t&nbsp;开 户：南昌市洪城分理处</b></td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td bgcolor=\"#ffffff\" height=\"70\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\">\r\n\t\t\t\t\t\t<b><img alt=\"商业购买\" height=\"50\" src=\"http://www.jeecms.com/res_base/jeecms_com_www/default/article/img/bank/gh.jpg\" style=\"cursor: default\" width=\"200\" /></b></td>\r\n\t\t\t\t\t<td bgcolor=\"#ffffff\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; line-height: 22px; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\">\r\n\t\t\t\t\t\t<b>&nbsp;户 名：叶媛<br />\r\n\t\t\t\t\t\t&nbsp;卡 号：9558　8215　0200　1391　515<br />\r\n\t\t\t\t\t\t&nbsp;开 户：南昌市青山湖支行</b></td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td bgcolor=\"#ffffff\" height=\"70\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\">\r\n\t\t\t\t\t\t<b><img alt=\"商业购买\" height=\"50\" src=\"http://www.jeecms.com/res_base/jeecms_com_www/default/article/img/bank/alipay.gif\" style=\"cursor: default\" width=\"180\" /></b></td>\r\n\t\t\t\t\t<td bgcolor=\"#ffffff\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; line-height: 22px; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\">\r\n\t\t\t\t\t\t<b>&nbsp;户 名：叶媛<br />\r\n\t\t\t\t\t\t&nbsp;帐 号：yeyuan823@163.com<br />\r\n\t\t\t\t\t\t&nbsp;开 户：支付宝</b></td>\r\n\t\t\t\t</tr>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td bgcolor=\"#ffffff\" height=\"70\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\">\r\n\t\t\t\t\t\t<b>对公帐号</b></td>\r\n\t\t\t\t\t<td bgcolor=\"#ffffff\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; line-height: 22px; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\">\r\n\t\t\t\t\t\t<b>&nbsp;户 名：江西金磊科技发展有限公司<br />\r\n\t\t\t\t\t\t&nbsp;帐 号：1502 　2010 　0900 　4557 　361<br />\r\n\t\t\t\t\t\t&nbsp;开 户：工商银行南昌市中山路支行</b></td>\r\n\t\t\t\t</tr>\r\n\t\t\t</tbody>\r\n\t\t</table>\r\n\t</div>\r\n\t<div class=\"biaoti\">&nbsp;</div>\r\n\t<div class=\"biaoti\">&nbsp;</div>\r\n\t<div class=\"biaoti\">&nbsp;</div>\r\n\t<div class=\"biaoti\">&nbsp;</div>\r\n\t<div class=\"biaoti\">&nbsp;</div>\r\n\t<div class=\"biaoti\">&nbsp;</div>\r\n\t<div class=\"biaoti\" style=\"text-align: center\">\r\n\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; line-height: 30px; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\" width=\"600\">\r\n\t\t\t<tbody>\r\n\t\t\t\t<tr>\r\n\t\t\t\t\t<td style=\"border-bottom: rgb(211,211,211) 1px dotted; border-left: rgb(211,211,211) 1px dotted; border-top: rgb(211,211,211) 1px dotted; border-right: rgb(211,211,211) 1px dotted\">\r\n\t\t\t\t\t\t<b>　　使用网上银行转帐更加方便快捷，相关情况请到各银行营业厅咨询办理。推荐您使用支付宝方式付款，可以为您节省手续费。 银行汇款时请注意带些零头，以作区分。如原来需汇1000元就实存1000.5元。汇款成功后，请保留存款收据。 汇完款后及时跟我们联系确认汇款，以便我们及时为您提供服务。</b></td>\r\n\t\t\t\t</tr>\r\n\t\t\t</tbody>\r\n\t\t</table>\r\n\t</div>\r\n</div>\r\n<p><br class=\"Apple-interchange-newline\" />\r\n\t&nbsp;</p>');
INSERT INTO `jc_shop_article_content` VALUES (12,'<p><b>在线支付</b></p>');
INSERT INTO `jc_shop_article_content` VALUES (13,'<div class=\"biaoti\"><b>货到付款</b></div>\r\n<div class=\"content\" id=\"helpcontent\" sizcache=\"1\" sizset=\"72\"><br />\r\n\t<p>&nbsp;&nbsp;&nbsp; 货到付款是gou配送员送货上门，客户收单验货后，直接将货款交给配送员的一种结算方式（注：目前暂未开通刷卡服务的地区，货到后需要现金支付）。</p>\r\n\t<p>&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong>注：</strong>配送费收费标准：5元/单，全场购物满59元免运费。</p>\r\n</div>\r\n<p>&nbsp;</p>');
CREATE TABLE `jc_shop_brand` (
  `brand_id` bigint(20) NOT NULL auto_increment,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `alias` varchar(255) default NULL COMMENT '别名',
  `web_url` varchar(255) default NULL COMMENT '网址',
  `logo_path` varchar(255) default NULL COMMENT 'LOGO',
  `priority` int(11) NOT NULL default '10' COMMENT '排序',
  `is_sift` tinyint(3) default NULL COMMENT '是否精选',
  `is_disabled` tinyint(3) NOT NULL COMMENT '是否禁用',
  `firstcharacter` varchar(255) NOT NULL COMMENT '品牌首字母',
  PRIMARY KEY  (`brand_id`),
  KEY `fk_jc_shop_brand_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='品牌';

INSERT INTO `jc_shop_brand` VALUES (1,1,'森马','','http://www.semir.com','/u/201405/26084559f4fx.gif',1,0,0,'s');
INSERT INTO `jc_shop_brand` VALUES (2,1,'摩托罗拉','','http://www.motorola.com.cn/','/u/201405/26084614quwz.png',10,0,0,'m');
INSERT INTO `jc_shop_brand` VALUES (3,1,'iPhone','','http://www.apple.com/','/u/201405/26084706lpep.jpg',10,0,0,'i');
INSERT INTO `jc_shop_brand` VALUES (4,1,'联想','联想','http://www.lenovo.com.cn/','/u/201406/17114011pzq7.gif',10,0,0,'l');
INSERT INTO `jc_shop_brand` VALUES (5,1,'美的','美的','http://www.midea.com/cn/','/u/201406/17114535if8u.png',10,0,0,'m');
INSERT INTO `jc_shop_brand` VALUES (6,1,'尼康','尼康','','',10,0,0,'n');
INSERT INTO `jc_shop_brand` VALUES (7,1,'康佳','','','',10,0,0,'k');
INSERT INTO `jc_shop_brand` VALUES (8,1,'梦特娇','','','',10,0,0,'m');
INSERT INTO `jc_shop_brand` VALUES (9,1,'欧莱若','','','',10,0,0,'o');
INSERT INTO `jc_shop_brand` VALUES (10,1,'仙子宜岱','','','',10,0,0,'x');
INSERT INTO `jc_shop_brand` VALUES (11,1,'诺基亚','','','',10,0,0,'n');
INSERT INTO `jc_shop_brand` VALUES (12,1,'海尔','','','',10,0,0,'h');
INSERT INTO `jc_shop_brand` VALUES (13,1,'特步','','','',10,0,0,'t');
INSERT INTO `jc_shop_brand` VALUES (14,1,'惠普','','','',10,0,0,'h');
INSERT INTO `jc_shop_brand` VALUES (15,1,'华硕','','','',10,0,0,'h');
INSERT INTO `jc_shop_brand` VALUES (16,1,'戴尔','','','',10,0,0,'d');
INSERT INTO `jc_shop_brand` VALUES (17,1,'浪莎','','','',10,0,0,'l');
CREATE TABLE `jc_shop_brand_text` (
  `brand_id` bigint(20) NOT NULL,
  `text` longtext COMMENT '详细信息',
  PRIMARY KEY  (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `jc_shop_brand_text` VALUES (1,'<p>森马，国内休闲服行业迅速崛起的领军品牌。森马集团有限公司以&ldquo;创大众服饰名牌&rdquo;为发展宗旨，积极推行特许经营发展模式，休闲装和童装品牌连锁网点遍布全国二十九个省、市、自治区、直辖市，形成了完整的市场网络格局。集团公司现有休闲装 &ldquo;semir&rdquo;及童装&ldquo;balabala&rdquo;等两个知名服装品牌。森马集团有限公司于1996年12月18日创立于浙江省温州市，是一家以虚拟经营模式为特色，以系列休闲服饰为主导产业的无区域集团。公司注册资本为人民币2.38亿元，总资产达10多亿元，是温州市大企业大集团之一。</p>');
INSERT INTO `jc_shop_brand_text` VALUES (2,'<p>&nbsp;</p>\r\n<p><a href=\"http://baike.baidu.com/view/195002.htm\" target=\"_blank\">托罗拉公司</a>（Motorola Inc ），原名：Galvin Manufacturing Corporation，成立于1928年。1947年，改名为<a href=\"http://baike.baidu.com/view/39480.htm\" target=\"_blank\">Motorola</a>，从1930年代开始作为商标使用。总部设在美国<a href=\"http://baike.baidu.com/view/190007.htm\" target=\"_blank\">伊利诺伊州</a>绍姆堡，位于<a href=\"http://baike.baidu.com/view/36045.htm\" target=\"_blank\">芝加哥</a>市郊。世界财富百强企业之一，是全球芯片制造、电子通讯的领导者。</p>\r\n<p>摩托罗拉经过90多年的发展，摩托罗拉使用无线电、宽频及网际网路，并提供嵌入晶片系统，以及端对端整体网路通讯解决方案，以达到加强个人、工作团体、车辆及家庭的操控及联系能力。</p>\r\n<p>摩托罗拉公司有三大业务集团，分别是企业移动解决方案部、宽带及移动网络事业部和<a href=\"http://baike.baidu.com/view/1954381.htm\" target=\"_blank\">移动终端</a>事业部。</p>');
INSERT INTO `jc_shop_brand_text` VALUES (3,'<div class=\"mod-top\" id=\"card-container\" style=\"clear: both\">\r\n\t<div class=\"card-summary nslog-area clearfix\" data-nslog-type=\"72\">\r\n\t\t<div class=\"card-summary-content\">\r\n\t\t\t<div class=\"para\">iPhone，是苹果公司旗下研发的<a href=\"http://baike.baidu.com/view/535.htm\" target=\"_blank\">智能手机</a>系列，它搭载苹果公司研发的<a href=\"http://baike.baidu.com/view/158983.htm\" target=\"_blank\">iOS</a><a href=\"http://baike.baidu.com/view/148382.htm\" target=\"_blank\">手机操作系统</a>。第一代iPhone于2007年1月9日由<a href=\"http://baike.baidu.com/view/15181.htm\" target=\"_blank\">苹果公司</a>前首席执行官的<a href=\"http://baike.baidu.com/view/90660.htm\" target=\"_blank\">史蒂夫&middot;乔布斯</a>发布，并在同年6月29日正式发售。2013年9月10日，苹果公司在<a href=\"http://baike.baidu.com/view/2398.htm\" target=\"_blank\">美国</a><a href=\"http://baike.baidu.com/view/526914.htm\" target=\"_blank\">加州</a>举行新产品发布会上，推出第七代产品<a href=\"http://baike.baidu.com/view/7626773.htm\" target=\"_blank\">iPhone 5S</a>及<a href=\"http://baike.baidu.com/view/10817151.htm\" target=\"_blank\">iPhone 5C</a>首次发布会还在<a href=\"http://baike.baidu.com/view/2621.htm\" target=\"_blank\">北京</a>、<a href=\"http://baike.baidu.com/view/31528.htm\" target=\"_blank\">柏林</a>和<a href=\"http://baike.baidu.com/view/19319.htm\" target=\"_blank\">东京</a>三地设置分会场，再在各地分别举行了发布会。第七代的iPhone5S和iPhone5C于2013年9月10日发布，同年9月20日正式发售。第八代的iPhone6将于2014年八月中旬前后发布。<a href=\"http://baike.baidu.com/view/15181.htm\" target=\"_blank\">苹果公司</a>旗下智能手机iPhone，是全球销量第一的智能手机。</div>\r\n\t\t</div>\r\n\t</div>\r\n</div>\r\n<p>&nbsp;</p>');
INSERT INTO `jc_shop_brand_text` VALUES (4,'<p>&nbsp;</p>\r\n<div class=\"para\">联想集团成立于1984年，由中科院计算所投资20万元人民币、11名<a href=\"http://baike.baidu.com/view/1043923.htm\" target=\"_blank\">科技人员</a>创办，是一家在信息产业内多元化发展的大型<a href=\"http://baike.baidu.com/view/70550.htm\" target=\"_blank\">企业集团</a>，富有创新性的国际化的科技公司。由联想及原<a href=\"http://baike.baidu.com/view/1937.htm\" target=\"_blank\">IBM</a>个人电脑事业部所组成。从1996年开始，<a href=\"http://baike.baidu.com/view/16950.htm\" target=\"_blank\">联想电脑</a>销量一直位居中国国内市场首位，2013年；联想电脑销售量升居世界第1，成为全球最大的PC生产厂商。</div>\r\n<div class=\"para\">作为<a href=\"http://baike.baidu.com/view/151814.htm\" target=\"_blank\">全球</a><a href=\"http://baike.baidu.com/view/203808.htm\" target=\"_blank\">个人电脑</a>市场的领导企业，联想从事开发、制造并销售可靠的、安全易用的技术产品及优质专业的服务，帮助全球客户和合作伙伴取得成功。联想公司主要生产<a href=\"http://baike.baidu.com/view/3624172.htm\" target=\"_blank\">台式电脑</a>、<a href=\"http://baike.baidu.com/view/899.htm\" target=\"_blank\">服务器</a>、<a href=\"http://baike.baidu.com/view/7690.htm\" target=\"_blank\">笔记本电脑</a>、<a href=\"http://baike.baidu.com/view/7836.htm\" target=\"_blank\">打印机</a>、<a href=\"http://baike.baidu.com/view/30816.htm\" target=\"_blank\">掌上电脑</a>、<a href=\"http://baike.baidu.com/view/1143.htm\" target=\"_blank\">主板</a>、<a href=\"http://baike.baidu.com/view/1455.htm\" target=\"_blank\">手机</a> 、<a href=\"http://baike.baidu.com/view/2695326.htm\" target=\"_blank\">一体机电脑</a>等商品。</div>\r\n<div class=\"para\">自2014年4月1日起， 联想集团将会成立四个新的、相对独立的业务集团，分别是<a href=\"http://baike.baidu.com/subview/13621/4882082.htm\" target=\"_blank\">PC</a>业务集团、<a href=\"http://baike.baidu.com/subview/50463/6847219.htm\" target=\"_blank\">移动</a>业务集团、企业级业务集团、云服务业务集团。</div>');
INSERT INTO `jc_shop_brand_text` VALUES (5,'<p>&nbsp;</p>\r\n<div class=\"mod-top\" id=\"card-container\" style=\"CLEAR: both\">\r\n\t<div class=\"card-summary nslog-area clearfix\" data-nslog-type=\"72\">\r\n\t\t<div class=\"card-summary-content\">\r\n\t\t\t<div class=\"para\">美的集团（SZ.000333）是一家以家电制造业为主，涉足<a href=\"http://baike.baidu.com/view/7927230.htm\" target=\"_blank\">照明电器</a>、<a href=\"http://baike.baidu.com/view/9346.htm\" target=\"_blank\">房地产</a>、物流宝贝等领域的<b>大型</b>综合性<a href=\"http://baike.baidu.com/view/70550.htm\" target=\"_blank\">企业集团</a>，于2013年9月18日在深交所上市，旗下拥有<a href=\"http://baike.baidu.com/subview/15662/5134480.htm\" target=\"_blank\">小天鹅</a>（SZ000418）、<a href=\"http://baike.baidu.com/view/770074.htm\" target=\"_blank\">威灵</a>控股（HK00382）两家子上市公司。2013年，美的集团整体实现销售收入达1209.75亿元，同比增长17.91%。2013年&ldquo;中国最有价值品牌&rdquo;评价中，美的品牌价值达到653.36亿元，名列全国最有价值品牌第5位。</div>\r\n\t\t</div>\r\n\t</div>\r\n</div>\r\n<p>&nbsp;</p>');
INSERT INTO `jc_shop_brand_text` VALUES (6,'');
INSERT INTO `jc_shop_brand_text` VALUES (7,'');
INSERT INTO `jc_shop_brand_text` VALUES (8,'');
INSERT INTO `jc_shop_brand_text` VALUES (9,'');
INSERT INTO `jc_shop_brand_text` VALUES (10,'');
INSERT INTO `jc_shop_brand_text` VALUES (11,'');
INSERT INTO `jc_shop_brand_text` VALUES (12,'');
INSERT INTO `jc_shop_brand_text` VALUES (13,'');
INSERT INTO `jc_shop_brand_text` VALUES (14,'');
INSERT INTO `jc_shop_brand_text` VALUES (15,'');
INSERT INTO `jc_shop_brand_text` VALUES (16,'');
INSERT INTO `jc_shop_brand_text` VALUES (17,'');
CREATE TABLE `jc_shop_cardgift` (
  `Id` int(11) NOT NULL auto_increment,
  `cartId` bigint(20) default NULL COMMENT '所属购物车',
  `websiteId` bigint(20) default NULL COMMENT '所属站点',
  `giftId` bigint(20) default NULL COMMENT '礼品id',
  `count` int(11) default NULL COMMENT '礼品数量',
  PRIMARY KEY  (`Id`),
  KEY `fk_jc_cardgift_cart` (`cartId`),
  KEY `fk_jc_cardgift_website` (`websiteId`),
  KEY `fk_jc_cardgift_gift` (`giftId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `jc_shop_cart` (
  `cart_id` bigint(20) NOT NULL,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `total_items` int(11) NOT NULL default '0' COMMENT '商品总数量',
  `total_gifts` int(11) default NULL COMMENT '礼品总数量',
  PRIMARY KEY  (`cart_id`),
  KEY `fk_jc_shop_cart_website` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车';

INSERT INTO `jc_shop_cart` VALUES (1,1,3,NULL);
CREATE TABLE `jc_shop_cart_item` (
  `cartitem_id` bigint(20) NOT NULL auto_increment,
  `website_id` bigint(20) NOT NULL default '0' COMMENT '站点ID',
  `cart_id` bigint(20) NOT NULL COMMENT '购物车ID',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `productFash_id` bigint(20) default NULL COMMENT '商品款式外键（新加的[wangzewu]）',
  `count` int(11) NOT NULL COMMENT '数量',
  `score` int(11) default NULL COMMENT '购物车商品预送积分',
  `popularity_id` bigint(20) default NULL,
  PRIMARY KEY  (`cartitem_id`),
  KEY `fk_jc_shop_cartitem_product` (`product_id`),
  KEY `fk_jc_shop_cartitem_website` (`website_id`),
  KEY `fk_jc_shop_cartitem_cart` (`cart_id`),
  KEY `fk_jc_shop_cartitem_productFash` (`productFash_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='购物车';

INSERT INTO `jc_shop_cart_item` VALUES (1,1,1,5,NULL,1,0,NULL);
INSERT INTO `jc_shop_cart_item` VALUES (2,1,1,5,NULL,1,0,NULL);
CREATE TABLE `jc_shop_category` (
  `category_id` bigint(20) NOT NULL auto_increment,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `ptype_id` bigint(20) NOT NULL COMMENT '类型ID',
  `parent_id` bigint(20) default NULL COMMENT '父类别ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `path` varchar(100) NOT NULL COMMENT '访问路径',
  `lft` int(11) NOT NULL default '1' COMMENT '树左边',
  `rgt` int(11) NOT NULL default '2' COMMENT '树右边',
  `priority` int(11) NOT NULL default '10' COMMENT '排列顺序',
  `keywords` varchar(255) default NULL COMMENT '页面关键字',
  `description` varchar(255) default NULL COMMENT '页面描述',
  `tpl_channel` varchar(50) default NULL COMMENT '栏目页模板',
  `tpl_content` varchar(50) default NULL COMMENT '内容页模板',
  `image_path` varchar(100) default NULL COMMENT '图片路径',
  `title` varchar(255) default NULL COMMENT '页面标题',
  `is_colorsize` tinyint(1) default '0' COMMENT '是否需要尺寸和样式',
  PRIMARY KEY  (`category_id`),
  KEY `fk_jc_shop_category_parent` (`parent_id`),
  KEY `fk_jc_shop_category_ptype` (`ptype_id`),
  KEY `fk_jc_shop_cateory_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='类别';

INSERT INTO `jc_shop_category` VALUES (1,1,1,NULL,'数码','shuma',1,12,1,'','','/category/分类.html','/product/商品.html','','',0);
INSERT INTO `jc_shop_category` VALUES (2,1,2,NULL,'服装','fuzhuang',13,24,2,'','','/category/分类.html','/product/有款式商品.html','','',1);
INSERT INTO `jc_shop_category` VALUES (3,1,8,1,'手机','shouji',2,3,1,'','','/category/分类.html','/product/商品.html','/u/201405/23140259och6.jpg','',1);
INSERT INTO `jc_shop_category` VALUES (4,1,2,2,'女装','woman',14,15,10,'','','','','/u/201405/231405489rv9.gif','',1);
INSERT INTO `jc_shop_category` VALUES (5,1,9,1,'笔记本','bjb',4,5,2,'','','','','/u/201406/17115058ic2f.jpg','',1);
INSERT INTO `jc_shop_category` VALUES (6,1,3,NULL,'鞋包配饰','xiebao',25,36,10,'','','','','','',0);
INSERT INTO `jc_shop_category` VALUES (7,1,4,NULL,'日用百货','riyongbaihuo',37,38,10,'','','','','','',0);
INSERT INTO `jc_shop_category` VALUES (8,1,5,NULL,'家居建材','jiajujiancai',39,40,10,'','','','','','',0);
INSERT INTO `jc_shop_category` VALUES (9,1,6,NULL,'珠宝手表','zhubaoshoubiao',41,42,10,'','','','','','',1);
INSERT INTO `jc_shop_category` VALUES (10,1,1,1,'台式机','tsj',6,7,10,'','','/category/分类.html','/product/商品.html','/u/201406/171352362dn3.jpg','',0);
INSERT INTO `jc_shop_category` VALUES (11,1,1,1,'办公','bg',8,9,10,'','','','','/u/201406/17135424g50b.jpg','',0);
INSERT INTO `jc_shop_category` VALUES (12,1,1,1,'相机','xj',10,11,10,'','','','','/u/201406/17135611o9rv.jpg','',0);
INSERT INTO `jc_shop_category` VALUES (13,1,2,2,'男装','man',16,17,10,'','','','','/u/201406/171358477227.jpg','',0);
INSERT INTO `jc_shop_category` VALUES (14,1,2,2,'童装','child',18,19,10,'','','','','/u/201406/17135932xem2.jpg','',0);
INSERT INTO `jc_shop_category` VALUES (15,1,2,2,'内衣','neiyi',20,21,10,'','','','','/u/201406/17140013u2gx.jpg','',0);
INSERT INTO `jc_shop_category` VALUES (16,1,2,2,'裤子','kuzi',22,23,10,'','','','','/u/201406/17140102a9n3.jpg','',0);
INSERT INTO `jc_shop_category` VALUES (17,1,3,6,'女包','nvbao',26,27,10,'','','','','/u/201406/17140251zg8t.gif','',0);
INSERT INTO `jc_shop_category` VALUES (18,1,3,6,'男鞋','nanxie',28,29,10,'','','','','/u/201406/17140420kg46.jpg','',0);
INSERT INTO `jc_shop_category` VALUES (19,1,3,6,'童鞋','tongxie',30,31,10,'','','','','/u/201406/17140512r68w.jpg','',0);
INSERT INTO `jc_shop_category` VALUES (20,1,3,6,'女鞋','nvxie',32,33,10,'','','','','/u/201406/171406077u7h.jpg','',0);
INSERT INTO `jc_shop_category` VALUES (21,1,3,6,'男包','nanbao',34,35,10,'','','','','/u/201406/17140806p11u.jpg','',0);
CREATE TABLE `jc_shop_category_attr` (
  `category_id` bigint(20) NOT NULL default '0',
  `attr_name` varchar(30) NOT NULL default '' COMMENT '名称',
  `attr_value` varchar(255) default NULL COMMENT '值',
  KEY `fk_jc_shop_category_attr` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='jspgou类型扩展属性表';

CREATE TABLE `jc_shop_category_brand` (
  `brand_id` bigint(22) NOT NULL default '0',
  `category_id` bigint(22) NOT NULL default '0',
  PRIMARY KEY  (`brand_id`,`category_id`),
  KEY `fk_jc_shop_category_brand` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `jc_shop_category_brand` VALUES (1,2);
INSERT INTO `jc_shop_category_brand` VALUES (1,4);
INSERT INTO `jc_shop_category_brand` VALUES (2,1);
INSERT INTO `jc_shop_category_brand` VALUES (2,3);
INSERT INTO `jc_shop_category_brand` VALUES (2,5);
INSERT INTO `jc_shop_category_brand` VALUES (3,1);
INSERT INTO `jc_shop_category_brand` VALUES (3,3);
INSERT INTO `jc_shop_category_brand` VALUES (4,1);
INSERT INTO `jc_shop_category_brand` VALUES (4,5);
INSERT INTO `jc_shop_category_brand` VALUES (5,1);
INSERT INTO `jc_shop_category_brand` VALUES (6,1);
INSERT INTO `jc_shop_category_brand` VALUES (6,12);
INSERT INTO `jc_shop_category_brand` VALUES (7,1);
INSERT INTO `jc_shop_category_brand` VALUES (8,1);
INSERT INTO `jc_shop_category_brand` VALUES (9,1);
INSERT INTO `jc_shop_category_brand` VALUES (10,1);
INSERT INTO `jc_shop_category_brand` VALUES (15,1);
INSERT INTO `jc_shop_category_brand` VALUES (15,5);
INSERT INTO `jc_shop_category_brand` VALUES (16,1);
CREATE TABLE `jc_shop_category_property` (
  `Id` int(11) NOT NULL auto_increment,
  `category_id` bigint(20) NOT NULL default '0' COMMENT '关联类型Id',
  `name` varchar(255) NOT NULL default '' COMMENT '属性名称',
  `is_start` int(11) NOT NULL default '0' COMMENT '是否启用',
  `is_notNull` int(11) NOT NULL default '0' COMMENT '是否必填',
  `priority` varchar(255) NOT NULL default '10' COMMENT '排序',
  PRIMARY KEY  (`Id`),
  KEY `fk_jc_shop_category_property` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类型属性表';

CREATE TABLE `jc_shop_category_sdtype` (
  `category_id` bigint(20) NOT NULL default '0',
  `standardtype_id` int(11) NOT NULL default '0',
  PRIMARY KEY  (`category_id`,`standardtype_id`),
  KEY `fk_jc_shop_category_sdtype` (`category_id`),
  KEY `fk_jc_shop_sdtype_category` (`standardtype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类别和规则类型关联表';

INSERT INTO `jc_shop_category_sdtype` VALUES (3,3);
INSERT INTO `jc_shop_category_sdtype` VALUES (3,5);
INSERT INTO `jc_shop_category_sdtype` VALUES (3,6);
INSERT INTO `jc_shop_category_sdtype` VALUES (4,1);
INSERT INTO `jc_shop_category_sdtype` VALUES (4,2);
INSERT INTO `jc_shop_category_sdtype` VALUES (5,7);
INSERT INTO `jc_shop_category_sdtype` VALUES (9,1);
INSERT INTO `jc_shop_category_sdtype` VALUES (9,2);
INSERT INTO `jc_shop_category_sdtype` VALUES (9,3);
INSERT INTO `jc_shop_category_sdtype` VALUES (9,5);
INSERT INTO `jc_shop_category_sdtype` VALUES (9,6);
INSERT INTO `jc_shop_category_sdtype` VALUES (9,7);
CREATE TABLE `jc_shop_channel` (
  `channel_id` bigint(20) NOT NULL auto_increment,
  `website_id` bigint(20) NOT NULL,
  `path` varchar(50) default NULL COMMENT '访问路径',
  `name` varchar(100) NOT NULL COMMENT '栏目名称',
  `type` int(11) NOT NULL COMMENT '栏目类型',
  `outer_url` varchar(255) default NULL COMMENT '外部链接',
  `is_display` tinyint(1) NOT NULL default '1' COMMENT '是否显示',
  `tpl_channel` varchar(50) default NULL COMMENT '栏目页模板',
  `tpl_content` varchar(50) default NULL COMMENT '内容页模板',
  `parent_id` bigint(20) default NULL COMMENT '父栏目ID',
  `lft` int(11) NOT NULL default '1' COMMENT '树左边',
  `rgt` int(11) NOT NULL default '2' COMMENT '树右边',
  `is_blank` tinyint(1) NOT NULL default '0' COMMENT '是否新窗口打开',
  `priority` int(11) NOT NULL default '10' COMMENT '排列顺序',
  `param1` varchar(255) default NULL COMMENT '扩展数据1',
  `param2` varchar(255) default NULL COMMENT '扩展数据2',
  `param3` varchar(255) default NULL COMMENT '扩展数据3',
  PRIMARY KEY  (`channel_id`),
  KEY `fk_jc_shop_channel_website` (`website_id`),
  KEY `fk_jc_shop_channel_parent` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='商城栏目表';

INSERT INTO `jc_shop_channel` VALUES (1,1,'xsrm','新手入门',2,NULL,1,'/channel/网站公告栏目.html','/article/网站公告内容.html',NULL,1,2,0,10,'','','');
INSERT INTO `jc_shop_channel` VALUES (2,1,'wzgg','网站公告',2,NULL,1,'/channel/帮助中心栏目.html','/article/帮助中心内容.html',NULL,3,4,0,10,'','','');
INSERT INTO `jc_shop_channel` VALUES (3,1,'news','新闻中心',2,NULL,1,'','',NULL,5,6,0,10,'','','');
INSERT INTO `jc_shop_channel` VALUES (4,1,'remen','热门促销',2,NULL,1,'/channel/新闻中心栏目.html','/article/新闻中心内容.html',NULL,7,8,0,10,'','','');
INSERT INTO `jc_shop_channel` VALUES (5,1,'ps','配送范围及时间',2,NULL,1,'','',NULL,9,10,0,10,'','','');
INSERT INTO `jc_shop_channel` VALUES (6,1,'zffs','支付方式',2,NULL,0,'','',NULL,11,12,0,10,'','','');
INSERT INTO `jc_shop_channel` VALUES (7,1,'gsjj','公司简介',1,NULL,1,'/channel/单页.html',NULL,NULL,13,14,0,10,'','','');
CREATE TABLE `jc_shop_channel_content` (
  `channel_id` bigint(20) NOT NULL,
  `content` longtext,
  KEY `fk_jc_shop_content_channel` (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目内容';

INSERT INTO `jc_shop_channel_content` VALUES (1,'');
INSERT INTO `jc_shop_channel_content` VALUES (2,'');
INSERT INTO `jc_shop_channel_content` VALUES (3,'');
INSERT INTO `jc_shop_channel_content` VALUES (4,'');
INSERT INTO `jc_shop_channel_content` VALUES (5,'');
INSERT INTO `jc_shop_channel_content` VALUES (6,'');
INSERT INTO `jc_shop_channel_content` VALUES (7,'<div style=\"background-color: rgb(255,255,255); font-family: Arial, Verdana, sans-serif; color: rgb(34,34,34); font-size: 12px\">\r\n\t<p>&nbsp;&nbsp;江西金磊科技发展有限公司（以下简称金磊科技）成立于2003年，旗下产品JEECMS内容管理系统是国内java开源CMS行业知名度最高、用户量最大的站群管理系统。金磊科技是一家专注java WEB应用软件研发高新技术企业。&nbsp;</p>\r\n\t<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;金磊科技自2003年成立至今，一直致力研究和应用java技术，2003年成立至2007年，公司专注于政府、财政、烟草和矿业等Java应用软件开发外包业务；2008年开始自主研发并经营JEECMS、JEEBBS、JSPGOU、总枢纽、数据交换系统等专利产品，目前JEECMS系列产品以其强大、稳定、安全、高效、跨平台等多方面的优点，已经广泛应用于政府（部委和省级政府部门、市、县、乡及委办局）、教育科研（大、中、小学及各地方教育局）、电信运营商、金融（证券、保险及银行）、企业（矿业、煤炭、旅游、石油集团及大中型制造类企业）、新闻媒体（报社、网媒）等数字化信息平台建设领域，并获业内一致的好评。</p>\r\n\t<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 金磊科技视创新和服务为企业生存之本，致力研发高品质软件产品的同时，全心全意服务好客户，尽心尽力为客户创造价值。每一年、每一天，金磊科技都伴随着您一起成长！</p>\r\n</div>\r\n<p>&nbsp;</p>');
CREATE TABLE `jc_shop_collect` (
  `Id` int(11) NOT NULL auto_increment,
  `member_id` bigint(20) default NULL COMMENT '收藏人',
  `product_id` bigint(20) NOT NULL default '0' COMMENT '商品Id',
  `fashion_id` bigint(20) default NULL COMMENT '收藏商品款式',
  `time` datetime default NULL COMMENT '收藏时间',
  PRIMARY KEY  (`Id`),
  KEY `fk_jc_shop_collect_member` (`member_id`),
  KEY `fk_jc_shop_collect_product` (`product_id`),
  KEY `fk_jc_shop_collect_fashion` (`fashion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='收藏商品';

INSERT INTO `jc_shop_collect` VALUES (1,1,3,NULL,'2014-07-18 15:07:07');
INSERT INTO `jc_shop_collect` VALUES (2,1,1,8,'2014-07-18 15:08:27');
CREATE TABLE `jc_shop_config` (
  `config_id` bigint(20) NOT NULL,
  `shop_price_name` varchar(50) NOT NULL default '商城价' COMMENT '商城价名称',
  `market_price_name` varchar(50) NOT NULL default '市场价' COMMENT '市场价名称',
  `favorite_size` int(11) NOT NULL default '200' COMMENT '收藏夹大小',
  `register_group_id` bigint(20) NOT NULL COMMENT '默认注册会员组ID',
  `register_auto` tinyint(1) NOT NULL default '1' COMMENT '是否自动注册',
  PRIMARY KEY  (`config_id`),
  KEY `fk_jc_shop_config_group` (`register_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城配置表';

INSERT INTO `jc_shop_config` VALUES (1,'商城价','市场价',200,1,1);
CREATE TABLE `jc_shop_consult` (
  `Id` int(11) NOT NULL auto_increment,
  `consult` varchar(255) default NULL COMMENT '咨询内容',
  `adminReplay` varchar(255) default NULL COMMENT 'admin回复',
  `time` datetime default NULL COMMENT '咨询时间',
  `member_id` bigint(11) default NULL COMMENT '咨询会员',
  `product_id` bigint(11) default NULL COMMENT '对应商品',
  PRIMARY KEY  (`Id`),
  KEY `fk_product_consult` (`product_id`),
  KEY `fk_member_consult` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购买商品咨询表';

CREATE TABLE `jc_shop_coupon` (
  `Id` bigint(11) NOT NULL auto_increment,
  `website_id` bigint(20) default NULL COMMENT '站点Id',
  `coupon_name` varchar(50) default NULL COMMENT '优惠劵名称',
  `coupon_begintime` datetime default NULL COMMENT '开始时间',
  `coupon_endTime` datetime default NULL COMMENT '结束时间',
  `coupon_pic` varchar(50) default NULL COMMENT '图片地址',
  `is_using` tinyint(3) default NULL COMMENT '是否启用',
  `coupon_price` decimal(10,2) default NULL COMMENT '优惠倦值',
  `coupon_leastPrice` decimal(10,2) default NULL COMMENT '至少消费',
  `coupon_count` int(11) default NULL COMMENT '数量',
  PRIMARY KEY  (`Id`),
  KEY `website_id` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='优惠劵';

INSERT INTO `jc_shop_coupon` VALUES (1,1,'优惠券','2014-08-12 10:55:01','2014-08-29 10:55:04','/u/201408/20141935bnbf.jpg',1,50,0,10);
CREATE TABLE `jc_shop_dictionary` (
  `Id` bigint(20) NOT NULL auto_increment,
  `type_id` int(11) NOT NULL default '0' COMMENT '字典表类型ID',
  `name` varchar(255) NOT NULL default '' COMMENT '名称',
  `priority` int(11) NOT NULL default '10' COMMENT '排列顺序',
  PRIMARY KEY  (`Id`),
  KEY `fk_jc_dictionary_type` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='字典表';

INSERT INTO `jc_shop_dictionary` VALUES (1,1,'学生',10);
INSERT INTO `jc_shop_dictionary` VALUES (2,1,'在职',10);
INSERT INTO `jc_shop_dictionary` VALUES (3,1,'自由职业',10);
INSERT INTO `jc_shop_dictionary` VALUES (4,1,'家庭主妇',10);
INSERT INTO `jc_shop_dictionary` VALUES (5,1,'退休',10);
INSERT INTO `jc_shop_dictionary` VALUES (6,2,'1人',10);
INSERT INTO `jc_shop_dictionary` VALUES (7,2,'2人',10);
INSERT INTO `jc_shop_dictionary` VALUES (8,2,'3人',10);
INSERT INTO `jc_shop_dictionary` VALUES (9,2,'4人以上',10);
INSERT INTO `jc_shop_dictionary` VALUES (10,3,'2000元及以下',10);
INSERT INTO `jc_shop_dictionary` VALUES (11,3,'2000―5000元(包含5000元)',10);
INSERT INTO `jc_shop_dictionary` VALUES (12,3,'5000―10000元(包含10000元)',10);
INSERT INTO `jc_shop_dictionary` VALUES (13,3,'10000―20000元(包含20000元)',10);
INSERT INTO `jc_shop_dictionary` VALUES (14,3,'20000―40000元(包含40000元)',10);
INSERT INTO `jc_shop_dictionary` VALUES (15,3,'40000元以上',10);
INSERT INTO `jc_shop_dictionary` VALUES (16,5,'高中以下',1);
INSERT INTO `jc_shop_dictionary` VALUES (17,5,'中专',10);
INSERT INTO `jc_shop_dictionary` VALUES (18,5,'大专',10);
INSERT INTO `jc_shop_dictionary` VALUES (19,5,'本科',10);
INSERT INTO `jc_shop_dictionary` VALUES (20,5,'硕士',10);
INSERT INTO `jc_shop_dictionary` VALUES (21,5,'博士',10);
INSERT INTO `jc_shop_dictionary` VALUES (22,4,'1年',10);
INSERT INTO `jc_shop_dictionary` VALUES (23,4,'2年',10);
INSERT INTO `jc_shop_dictionary` VALUES (24,4,'3年',10);
INSERT INTO `jc_shop_dictionary` VALUES (25,4,'4年以上',10);
INSERT INTO `jc_shop_dictionary` VALUES (26,6,'缺货',10);
INSERT INTO `jc_shop_dictionary` VALUES (27,6,'协商一致退款',10);
INSERT INTO `jc_shop_dictionary` VALUES (28,6,'未按约定时间发货',10);
INSERT INTO `jc_shop_dictionary` VALUES (29,6,'其他',10);
INSERT INTO `jc_shop_dictionary` VALUES (30,7,'卖家未发货，全额退款',10);
INSERT INTO `jc_shop_dictionary` VALUES (31,8,'质量有问题',10);
INSERT INTO `jc_shop_dictionary` VALUES (32,9,'我的账户',10);
INSERT INTO `jc_shop_dictionary` VALUES (33,9,'支付宝',10);
CREATE TABLE `jc_shop_dictionary_type` (
  `Id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL COMMENT '字典表的类型',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='字典表的类型';

INSERT INTO `jc_shop_dictionary_type` VALUES (1,'身份');
INSERT INTO `jc_shop_dictionary_type` VALUES (2,'家庭成员');
INSERT INTO `jc_shop_dictionary_type` VALUES (3,'个人收入状况');
INSERT INTO `jc_shop_dictionary_type` VALUES (4,'工作年限');
INSERT INTO `jc_shop_dictionary_type` VALUES (5,'教育程度');
INSERT INTO `jc_shop_dictionary_type` VALUES (6,'未发货退款原因');
INSERT INTO `jc_shop_dictionary_type` VALUES (7,'退款类型');
INSERT INTO `jc_shop_dictionary_type` VALUES (8,'已收获退款原因');
INSERT INTO `jc_shop_dictionary_type` VALUES (9,'退款方式类型');
CREATE TABLE `jc_shop_discuss` (
  `Id` bigint(22) NOT NULL auto_increment,
  `content` varchar(255) default NULL COMMENT '评论内容',
  `time` datetime default NULL COMMENT '评论时间',
  `member_id` bigint(20) default NULL COMMENT '会员',
  `product_id` bigint(20) default NULL COMMENT '商品id',
  PRIMARY KEY  (`Id`),
  KEY `fk_jc_disucss_member` (`member_id`),
  KEY `fk_jc_disucss_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品评论表';

INSERT INTO `jc_shop_discuss` VALUES (1,'test','2014-07-09 14:10:29',1,3);
INSERT INTO `jc_shop_discuss` VALUES (2,'test','2014-07-09 14:10:36',1,3);
CREATE TABLE `jc_shop_exended` (
  `exended_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL COMMENT '名称',
  `field` varchar(255) default NULL COMMENT '字段名称',
  `dataType` tinyint(3) NOT NULL default '0' COMMENT '数据类型',
  `priority` int(11) default NULL COMMENT '排序',
  PRIMARY KEY  (`exended_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品扩展属性';

INSERT INTO `jc_shop_exended` VALUES (1,'价格','price',2,10);
INSERT INTO `jc_shop_exended` VALUES (2,'尺寸','measure',2,10);
CREATE TABLE `jc_shop_exended_item` (
  `Id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL COMMENT '属性值',
  `priority` int(11) default NULL COMMENT '排序',
  `exended_id` int(11) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

INSERT INTO `jc_shop_exended_item` VALUES (15,'1800-2699',NULL,1);
INSERT INTO `jc_shop_exended_item` VALUES (16,'1300-1799',NULL,1);
INSERT INTO `jc_shop_exended_item` VALUES (17,'2700-5499',NULL,1);
INSERT INTO `jc_shop_exended_item` VALUES (18,'5500-9399',NULL,1);
INSERT INTO `jc_shop_exended_item` VALUES (19,'700-1299',NULL,1);
INSERT INTO `jc_shop_exended_item` VALUES (20,'9400以上',NULL,1);
INSERT INTO `jc_shop_exended_item` VALUES (21,'0-699',NULL,1);
INSERT INTO `jc_shop_exended_item` VALUES (22,'10.1英寸及以下',NULL,2);
INSERT INTO `jc_shop_exended_item` VALUES (23,'11英寸',NULL,2);
INSERT INTO `jc_shop_exended_item` VALUES (24,'12英寸',NULL,2);
INSERT INTO `jc_shop_exended_item` VALUES (25,'13英寸',NULL,2);
INSERT INTO `jc_shop_exended_item` VALUES (26,'14英寸',NULL,2);
INSERT INTO `jc_shop_exended_item` VALUES (27,'15英寸',NULL,2);
INSERT INTO `jc_shop_exended_item` VALUES (28,'16英寸',NULL,2);
INSERT INTO `jc_shop_exended_item` VALUES (29,'17英寸17英寸以上',NULL,2);
CREATE TABLE `jc_shop_fashion_standard` (
  `fashion_id` bigint(11) NOT NULL auto_increment,
  `standard_id` bigint(11) NOT NULL default '0',
  PRIMARY KEY  (`fashion_id`,`standard_id`),
  KEY `fk_jc_shop_fashion_standard` (`fashion_id`),
  KEY `fk_jc_shop_standard_fashion` (`standard_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='商品规格表';

INSERT INTO `jc_shop_fashion_standard` VALUES (1,23);
INSERT INTO `jc_shop_fashion_standard` VALUES (1,25);
INSERT INTO `jc_shop_fashion_standard` VALUES (1,28);
INSERT INTO `jc_shop_fashion_standard` VALUES (2,23);
INSERT INTO `jc_shop_fashion_standard` VALUES (2,25);
INSERT INTO `jc_shop_fashion_standard` VALUES (2,27);
INSERT INTO `jc_shop_fashion_standard` VALUES (3,23);
INSERT INTO `jc_shop_fashion_standard` VALUES (3,25);
INSERT INTO `jc_shop_fashion_standard` VALUES (3,26);
INSERT INTO `jc_shop_fashion_standard` VALUES (4,23);
INSERT INTO `jc_shop_fashion_standard` VALUES (4,24);
INSERT INTO `jc_shop_fashion_standard` VALUES (4,28);
INSERT INTO `jc_shop_fashion_standard` VALUES (5,23);
INSERT INTO `jc_shop_fashion_standard` VALUES (5,24);
INSERT INTO `jc_shop_fashion_standard` VALUES (5,27);
INSERT INTO `jc_shop_fashion_standard` VALUES (6,23);
INSERT INTO `jc_shop_fashion_standard` VALUES (6,24);
INSERT INTO `jc_shop_fashion_standard` VALUES (6,26);
INSERT INTO `jc_shop_fashion_standard` VALUES (7,22);
INSERT INTO `jc_shop_fashion_standard` VALUES (7,25);
INSERT INTO `jc_shop_fashion_standard` VALUES (7,28);
INSERT INTO `jc_shop_fashion_standard` VALUES (8,22);
INSERT INTO `jc_shop_fashion_standard` VALUES (8,25);
INSERT INTO `jc_shop_fashion_standard` VALUES (8,27);
INSERT INTO `jc_shop_fashion_standard` VALUES (9,22);
INSERT INTO `jc_shop_fashion_standard` VALUES (9,25);
INSERT INTO `jc_shop_fashion_standard` VALUES (9,26);
INSERT INTO `jc_shop_fashion_standard` VALUES (10,22);
INSERT INTO `jc_shop_fashion_standard` VALUES (10,24);
INSERT INTO `jc_shop_fashion_standard` VALUES (10,28);
INSERT INTO `jc_shop_fashion_standard` VALUES (11,22);
INSERT INTO `jc_shop_fashion_standard` VALUES (11,24);
INSERT INTO `jc_shop_fashion_standard` VALUES (11,27);
INSERT INTO `jc_shop_fashion_standard` VALUES (12,22);
INSERT INTO `jc_shop_fashion_standard` VALUES (12,24);
INSERT INTO `jc_shop_fashion_standard` VALUES (12,26);
INSERT INTO `jc_shop_fashion_standard` VALUES (13,30);
INSERT INTO `jc_shop_fashion_standard` VALUES (14,29);
INSERT INTO `jc_shop_fashion_standard` VALUES (15,32);
INSERT INTO `jc_shop_fashion_standard` VALUES (16,31);
CREATE TABLE `jc_shop_gift` (
  `Id` bigint(20) NOT NULL auto_increment,
  `giftName` varchar(100) default NULL COMMENT '礼品名称',
  `giftScore` int(11) default NULL COMMENT '礼品换取所需积分',
  `giftStock` int(11) default NULL COMMENT '礼品库存',
  `giftPicture` varchar(100) default NULL COMMENT '礼品图片',
  `isgift` tinyint(3) default NULL COMMENT '是否发放',
  `introduced` longtext,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `jc_shop_gift` VALUES (1,'天天',400,1,'/u/201407/09112222y6v6.png',1,'');
INSERT INTO `jc_shop_gift` VALUES (2,'test',10000,0,'/u/201407/09112204ybg6.png',1,'');
CREATE TABLE `jc_shop_gift_exchange` (
  `Id` int(11) NOT NULL auto_increment,
  `member_id` bigint(20) default NULL COMMENT '会员',
  `gift_id` bigint(20) default NULL COMMENT '礼品',
  `score` int(11) default NULL COMMENT '积分',
  `amount` int(11) default NULL COMMENT '数量',
  `create_time` datetime default NULL COMMENT '兑换时间',
  `total_score` int(11) default NULL COMMENT '总积分',
  `detailaddress` varchar(255) default NULL COMMENT '收货地址',
  `status` tinyint(3) default NULL COMMENT '状态（1.待发货2.已发货3.完成）',
  `waybill` varchar(255) default NULL COMMENT '货运单号',
  PRIMARY KEY  (`Id`),
  KEY `fk_jc_shop_exchange_member` (`member_id`),
  KEY `fk_jc_shop_exchange_gift` (`gift_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分兑换';

CREATE TABLE `jc_shop_keyword_q` (
  `Id` int(11) NOT NULL auto_increment,
  `keyword` varchar(50) default NULL COMMENT '关键字',
  `times` int(11) default NULL COMMENT '收索次数',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

INSERT INTO `jc_shop_keyword_q` VALUES (5,'数码',108);
INSERT INTO `jc_shop_keyword_q` VALUES (6,'服装',43);
INSERT INTO `jc_shop_keyword_q` VALUES (7,'日用百货',46);
INSERT INTO `jc_shop_keyword_q` VALUES (14,'梦特娇',1);
INSERT INTO `jc_shop_keyword_q` VALUES (15,'钻石',1);
INSERT INTO `jc_shop_keyword_q` VALUES (23,'apple',2);
INSERT INTO `jc_shop_keyword_q` VALUES (24,'手机',3);
INSERT INTO `jc_shop_keyword_q` VALUES (27,'商品',1);
INSERT INTO `jc_shop_keyword_q` VALUES (30,'电脑',5);
INSERT INTO `jc_shop_keyword_q` VALUES (42,'谍影重重4',1);
INSERT INTO `jc_shop_keyword_q` VALUES (43,'笔记本',1);
INSERT INTO `jc_shop_keyword_q` VALUES (48,'璨经典奢华镶钻水晶开口戒 浅粉',1);
INSERT INTO `jc_shop_keyword_q` VALUES (49,'璨经典奢华镶钻水晶开',1);
INSERT INTO `jc_shop_keyword_q` VALUES (53,'test',2);
CREATE TABLE `jc_shop_logistics` (
  `logistics_id` int(11) NOT NULL auto_increment,
  `name` varchar(100) default NULL COMMENT '名称',
  `web_url` varchar(255) default NULL COMMENT '网址',
  `logo_path` varchar(255) default NULL COMMENT 'LOGO',
  `priority` int(11) default NULL COMMENT '排序',
  PRIMARY KEY  (`logistics_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='物流';

INSERT INTO `jc_shop_logistics` VALUES (4,'圆通速递','http://www.yto.net.cn','/u/201406/0513273912gq.png',10);
INSERT INTO `jc_shop_logistics` VALUES (5,'顺丰速递','http://www.sf-express.com','/u/201406/05133220gtct.png',10);
CREATE TABLE `jc_shop_logistics_text` (
  `logistics_id` bigint(20) NOT NULL,
  `text` longtext COMMENT '详细信息',
  PRIMARY KEY  (`logistics_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `jc_shop_logistics_text` VALUES (4,'<p>圆通速递</p>');
INSERT INTO `jc_shop_logistics_text` VALUES (5,'<p>顺丰速递</p>');
CREATE TABLE `jc_shop_member` (
  `member_id` bigint(20) NOT NULL,
  `group_id` bigint(20) NOT NULL COMMENT '会员组ID',
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `userdegree_id` bigint(20) default NULL COMMENT '字典表身份ID',
  `familymembers_id` bigint(20) default NULL COMMENT '字典表家庭成员',
  `incomedesc_id` bigint(20) default NULL COMMENT '字典表个人收入情况',
  `workseniority_id` bigint(20) default NULL COMMENT '字典表工作年限',
  `degree_id` bigint(20) default NULL COMMENT '字典表教育程度',
  `realname` varchar(100) default NULL COMMENT '真实姓名',
  `gender` tinyint(1) default NULL COMMENT '性别',
  `birthday` date default NULL COMMENT '生日日期',
  `mobile` varchar(30) default NULL COMMENT '手机',
  `tel` varchar(30) default NULL COMMENT '电话',
  `score` int(11) default NULL COMMENT '积分',
  `freezeScore` int(11) default '0' COMMENT '冻结的积分',
  `money` double(11,2) default NULL COMMENT '账户金额',
  `company` varchar(100) default NULL COMMENT '公司',
  `avatar` varchar(100) default NULL COMMENT '会员头像',
  `marriage` tinyint(1) default NULL COMMENT '婚姻状况(空,保密;1,已婚;0,未婚)',
  `is_car` tinyint(1) default NULL COMMENT '是否有车(0:无 1：有)',
  `position` varchar(255) default NULL COMMENT '职位',
  `schoolTag` varchar(255) default NULL COMMENT '毕业学校',
  `schoolTagDate` date default NULL COMMENT '毕业时间',
  `favoriteBrand` varchar(255) default NULL COMMENT '最喜爱的品牌',
  `favoriteStar` varchar(255) default NULL COMMENT '最喜爱的明星',
  `favoriteMovie` varchar(255) default NULL COMMENT '最喜爱的电影',
  `favoritePersonage` varchar(255) default NULL COMMENT '最喜爱的人物',
  `address` varchar(255) default NULL COMMENT '地址',
  PRIMARY KEY  (`member_id`),
  KEY `fk_jc_shop_member_mgroup` (`group_id`),
  KEY `fk_jc_shop_member_website` (`website_id`),
  KEY `fk_jc_shop_dictionary_userdegree` (`userdegree_id`),
  KEY `fk_jc_shop_dictionary_familymembers` (`familymembers_id`),
  KEY `fk_jc_shop_dictionary_incomedesc` (`incomedesc_id`),
  KEY `fk_jc_shop_dictionary_workseniority` (`workseniority_id`),
  KEY `fk_jc_shop_dictionary_degree` (`degree_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城会员';

INSERT INTO `jc_shop_member` VALUES (1,3,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','',40,0,4798,NULL,'/u/201406/18140736wuc5.jpg',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_member` VALUES (4,2,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_member` VALUES (5,2,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,10,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
CREATE TABLE `jc_shop_member_address` (
  `address_id` bigint(20) NOT NULL auto_increment,
  `member_id` bigint(20) NOT NULL COMMENT '会员',
  `province_id` bigint(20) NOT NULL default '0' COMMENT '省份',
  `city_id` bigint(20) NOT NULL default '0' COMMENT '城市Id',
  `country_id` bigint(20) NOT NULL default '0' COMMENT '县Id',
  `username` varchar(100) NOT NULL default '' COMMENT '姓名',
  `gender` tinyint(1) default NULL COMMENT '性别',
  `detailaddress` varchar(255) NOT NULL default '' COMMENT '详细地址',
  `postcode` varchar(20) default NULL COMMENT '邮编',
  `tel` varchar(30) default NULL COMMENT '手机',
  `areacode` varchar(30) default NULL COMMENT '电话区号',
  `phone` varchar(255) default NULL COMMENT '电话部分',
  `extnumber` varchar(255) default NULL COMMENT '分机号',
  `is_default` tinyint(1) NOT NULL default '0' COMMENT '是否是默认地址',
  PRIMARY KEY  (`address_id`),
  KEY `fk_jc_address_province` (`province_id`),
  KEY `fk_jc_address_city` (`city_id`),
  KEY `fk_jc_address_country` (`country_id`),
  KEY `fk_jc_address_member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='地址';

INSERT INTO `jc_shop_member_address` VALUES (1,1,1,7,8,'test',1,'test','330000','1599004480','','','',1);
CREATE TABLE `jc_shop_member_coupon` (
  `Id` int(11) NOT NULL auto_increment,
  `code` varchar(11) default NULL COMMENT '优惠劵验证码',
  `is_use` tinyint(3) default NULL COMMENT '是否使用',
  `member_id` bigint(20) default NULL COMMENT '对应的会员',
  `coupon_id` bigint(20) default NULL COMMENT '对应的优惠劵',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `jc_shop_member_coupon` VALUES (1,NULL,1,1,1);
CREATE TABLE `jc_shop_member_favorite` (
  `product_id` bigint(20) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`product_id`,`member_id`),
  KEY `fk_jc_shop_favorite_member` (`member_id`),
  KEY `fk_jc_shop_favorite_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品收藏夹';

CREATE TABLE `jc_shop_member_group` (
  `group_id` bigint(20) NOT NULL auto_increment,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `score` int(11) NOT NULL default '0' COMMENT '最低积分',
  `discount` int(11) NOT NULL default '100' COMMENT '折扣',
  PRIMARY KEY  (`group_id`),
  KEY `fk_jc_shop_mgroup_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商城会员组';

INSERT INTO `jc_shop_member_group` VALUES (1,1,'普通会员',0,90);
INSERT INTO `jc_shop_member_group` VALUES (2,1,'高级会员',5,80);
INSERT INTO `jc_shop_member_group` VALUES (3,1,'金牌会员',10,70);
CREATE TABLE `jc_shop_money` (
  `Id` bigint(20) NOT NULL auto_increment,
  `member_id` bigint(20) NOT NULL default '0' COMMENT '会员Id',
  `name` varchar(255) NOT NULL default '' COMMENT '来源/用途',
  `money` double(11,2) NOT NULL default '0.00' COMMENT '金额',
  `status` tinyint(1) NOT NULL default '0' COMMENT '0是支出，1,是收入',
  `create_time` datetime NOT NULL default '0000-00-00 00:00:00' COMMENT '时间',
  `remark` varchar(255) default NULL COMMENT '备注',
  PRIMARY KEY  (`Id`),
  KEY `fk_jc_shop_money_member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户记录';

CREATE TABLE `jc_shop_order` (
  `order_id` bigint(20) NOT NULL auto_increment,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `payment_id` bigint(20) NOT NULL COMMENT '支付方式',
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `shipping_id` bigint(20) NOT NULL default '0' COMMENT '配送方式',
  `code` bigint(20) NOT NULL default '0' COMMENT '订单号',
  `status` int(11) default '0' COMMENT '订单状态',
  `payment_status` int(11) default '0' COMMENT '支付状态',
  `shipping_status` int(11) default NULL COMMENT '配送状态',
  `product_price` double(11,2) default NULL COMMENT '商品总价格',
  `weight` int(11) NOT NULL default '0' COMMENT '商品重量',
  `freight` double(11,2) default NULL COMMENT '运费',
  `total` double(11,2) NOT NULL default '0.00' COMMENT '订单总额',
  `score` int(11) NOT NULL default '0' COMMENT '订单积分',
  `ip` varchar(50) NOT NULL COMMENT 'IP地址',
  `create_time` datetime NOT NULL COMMENT '下单日期',
  `finished_time` datetime default NULL COMMENT '结单日期',
  `last_modified` datetime NOT NULL COMMENT '最后修改时间',
  `comments` varchar(255) default NULL COMMENT '订单附言',
  `shipping_time` datetime default NULL COMMENT '发货日期',
  `coupon_price` double(11,2) default NULL,
  `productName` varchar(500) default NULL COMMENT '订单商品名字的组合',
  `tradeno` varchar(255) default NULL COMMENT '支付宝交易号',
  `return_id` bigint(1) default NULL COMMENT '退款Id',
  `receive_name` varchar(255) default NULL COMMENT '收货人姓名',
  `receive_address` varchar(255) default NULL COMMENT '收货人地址',
  `receive_zip` varchar(255) default NULL COMMENT '收货人邮编',
  `receive_phone` varchar(255) default NULL COMMENT '收货人电话号码',
  `receive_mobile` varchar(255) default NULL COMMENT '收货人手机号码',
  `payment_code` varchar(255) default NULL COMMENT '支付方式',
  PRIMARY KEY  (`order_id`),
  KEY `fk_jc_shop_order_member` (`member_id`),
  KEY `fk_jc_shop_order_payment` (`payment_id`),
  KEY `fk_jc_shop_order_website` (`website_id`),
  KEY `fk_jc_shop_order_shipping` (`shipping_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='订单';

INSERT INTO `jc_shop_order` VALUES (1,1,1,1,1,1408428099188,2,2,2,0.01,0,0,0.01,0,'127.0.0.1','2014-08-19 14:01:39',NULL,'2014-08-19 14:01:39','',NULL,NULL,'null支付测试','2014082215226633',NULL,'test','test','330000',NULL,'1599004480','alipayPartner');
INSERT INTO `jc_shop_order` VALUES (2,1,1,1,1,1408428678141,2,2,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-19 14:11:18',NULL,'2014-08-19 14:11:18','',NULL,NULL,'null支付测试','2014082056173158',NULL,'test','test','330000',NULL,'1599004480','alipayPartner');
INSERT INTO `jc_shop_order` VALUES (3,1,1,1,1,1408432320797,2,2,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-19 15:12:00',NULL,'2014-08-19 15:12:00','',NULL,NULL,'null支付测试','2014082056173158',NULL,'test','test','330000',NULL,'1599004480','alipayPartner');
INSERT INTO `jc_shop_order` VALUES (4,1,1,1,1,1408611542954,2,2,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-21 16:59:02',NULL,'2014-08-21 16:59:02','',NULL,NULL,'null支付测试','2014082056173158',NULL,'test','test','330000',NULL,'1599004480','alipayPartner');
INSERT INTO `jc_shop_order` VALUES (5,1,3,1,2,1408611794360,2,1,1,0.1,0,10,10.1,0,'127.0.0.1','2014-08-21 17:03:14',NULL,'2014-08-21 17:03:14','',NULL,NULL,'nulltest',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (6,1,1,1,1,1408770103829,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-23 13:01:43',NULL,'2014-08-23 13:01:43','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (7,1,1,1,1,1408926871093,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 08:34:31',NULL,'2014-08-25 08:34:31','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (8,1,1,1,1,1408927101187,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 08:38:21',NULL,'2014-08-25 08:38:21','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (9,1,1,1,1,1408927767828,1,1,1,0.03,0,0,0.03,0,'127.0.0.1','2014-08-25 08:49:27',NULL,'2014-08-25 08:49:27','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (10,1,1,1,1,1408927877031,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 08:51:17',NULL,'2014-08-25 08:51:17','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (11,1,1,1,1,1408928003937,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 08:53:23',NULL,'2014-08-25 08:53:23','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (12,1,1,1,1,1408928215672,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 08:56:55',NULL,'2014-08-25 08:56:55','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (13,1,1,1,1,1408928230203,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 08:57:10',NULL,'2014-08-25 08:57:10','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (14,1,1,1,1,1408928586859,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 09:03:06',NULL,'2014-08-25 09:03:06','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (15,1,1,1,1,1408928674875,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 09:04:34',NULL,'2014-08-25 09:04:34','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (16,1,1,1,1,1408933206953,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 10:20:06',NULL,'2014-08-25 10:20:06','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (17,1,1,1,1,1408933359093,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 10:22:39',NULL,'2014-08-25 10:22:39','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (18,1,1,1,1,1408933424984,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 10:23:44',NULL,'2014-08-25 10:23:44','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (19,1,1,1,1,1408933438297,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 10:23:58',NULL,'2014-08-25 10:23:58','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (20,1,1,1,1,1408933548406,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 10:25:48',NULL,'2014-08-25 10:25:48','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (21,1,1,1,1,1408933887703,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 10:31:27',NULL,'2014-08-25 10:31:27','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (22,1,3,1,1,1408933969453,4,2,2,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 10:32:49',NULL,'2014-08-25 17:16:42','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (23,1,2,1,1,1408934061531,4,2,2,0.01,0,0,0.01,0,'127.0.0.1','2014-08-25 10:34:21',NULL,'2014-08-25 17:13:19','',NULL,NULL,'null支付测试',NULL,1,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (24,1,1,1,1,1409204695454,1,1,1,0.02,0,0,0.02,0,'127.0.0.1','2014-08-28 13:44:55',NULL,'2014-08-28 13:44:55','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (25,1,1,1,1,1409279763172,1,1,1,0.01,0,0,0.01,0,'127.0.0.1','2014-08-29 10:36:03',NULL,'2014-08-29 10:36:03','',NULL,NULL,'null支付测试',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
INSERT INTO `jc_shop_order` VALUES (26,1,1,1,1,1410222418704,1,1,1,0.21,0,0,-19.79,0,'127.0.0.1','2014-09-09 08:26:58',NULL,'2014-09-09 08:26:58','',NULL,NULL,'nulltest支付测试test',NULL,NULL,'test','test','330000',NULL,'1599004480',NULL);
CREATE TABLE `jc_shop_order_gathering` (
  `Id` int(11) NOT NULL auto_increment,
  `order_id` bigint(20) default NULL,
  `admin_id` bigint(20) default NULL,
  `bank` varchar(255) default NULL COMMENT '银行',
  `accounts` varchar(255) default NULL COMMENT '帐号',
  `money` double default NULL COMMENT '金额',
  `drawee` varchar(255) default NULL COMMENT '付款人',
  `comment` varchar(255) default NULL COMMENT '备注',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='收款';

INSERT INTO `jc_shop_order_gathering` VALUES (1,23,1,'ddd','11',11,'11','');
INSERT INTO `jc_shop_order_gathering` VALUES (2,22,1,'111','111',11,'11','');
CREATE TABLE `jc_shop_order_item` (
  `orderitem_id` bigint(20) NOT NULL auto_increment,
  `order_id` bigint(20) NOT NULL,
  `website_id` bigint(20) NOT NULL default '0',
  `product_id` bigint(20) NOT NULL,
  `productFash_id` bigint(11) default NULL COMMENT '商品款式外键（wangzewu）',
  `count` int(11) NOT NULL COMMENT '数量',
  `sale_price` double(11,2) default NULL COMMENT '销售价',
  `member_price` double(11,2) default NULL COMMENT '会员价',
  `cost_price` double(11,2) default NULL COMMENT '成本价',
  `final_price` double(11,2) default NULL COMMENT '成交价',
  `seckillprice` double(11,2) default NULL COMMENT '秒杀价',
  PRIMARY KEY  (`orderitem_id`),
  KEY `fk_jc_shop_item_order` (`order_id`),
  KEY `fk_jc_shop_orderitem_website` (`website_id`),
  KEY `fk_jc_shop_orderitem_product` (`product_id`),
  KEY `fk_jc_shop_orderitem_productFash` (`productFash_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='订单项';

INSERT INTO `jc_shop_order_item` VALUES (8,1,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (9,2,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (10,3,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (11,4,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (12,5,1,5,NULL,1,0.1,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (13,6,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (14,7,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (15,8,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (16,9,1,4,NULL,3,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (17,10,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (18,11,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (19,12,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (20,13,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (21,14,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (22,15,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (23,16,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (24,17,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (25,18,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (26,19,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (27,20,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (28,21,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (29,22,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (30,23,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (31,24,1,4,NULL,2,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (32,25,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (33,26,1,5,NULL,1,0.1,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (34,26,1,4,NULL,1,0.01,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_order_item` VALUES (35,26,1,5,NULL,1,0.1,NULL,NULL,NULL,NULL);
CREATE TABLE `jc_shop_order_return` (
  `return_id` bigint(20) NOT NULL auto_increment,
  `code` varchar(255) NOT NULL default '' COMMENT '退款编码',
  `order_id` bigint(20) NOT NULL default '0',
  `reason_id` bigint(20) NOT NULL default '0' COMMENT '退款选项原因',
  `payType` int(11) NOT NULL default '0' COMMENT '退款支付类型',
  `returnType` int(11) NOT NULL default '0' COMMENT '退款类型',
  `alipayId` varchar(255) default NULL COMMENT '支付宝账号',
  `reason` varchar(500) default NULL COMMENT '退款书面原因',
  `status` int(3) NOT NULL default '0' COMMENT '1待审，2已审，3拒绝',
  `money` double(11,2) NOT NULL default '0.00' COMMENT '退款数额',
  `sellerMoney` double(11,2) default '0.00' COMMENT '支付给卖家的钱',
  `create_time` datetime NOT NULL default '0000-00-00 00:00:00' COMMENT '申请退款时间',
  `finished_time` datetime default NULL COMMENT '退款完成时间',
  PRIMARY KEY  (`return_id`),
  KEY `fk_jc_shop_order_return` (`order_id`),
  KEY `fk_jc_shop_reason_return` (`reason_id`),
  KEY `fk_jc_shop_paytype_return` (`payType`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='FComment';

INSERT INTO `jc_shop_order_return` VALUES (1,'1408958255351',23,31,2,1,'','',6,0.01,0,'2014-08-25 17:17:35',NULL);
CREATE TABLE `jc_shop_order_return_picture` (
  `return_id` bigint(20) NOT NULL default '0',
  `priority` int(11) NOT NULL default '0' COMMENT '排列顺序',
  `img_path` varchar(100) NOT NULL default '' COMMENT '图片地址',
  `description` varchar(255) default NULL COMMENT '描述',
  PRIMARY KEY  (`return_id`,`priority`),
  KEY `fk_jc_shop_order_return_picture` (`return_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款图片表';

INSERT INTO `jc_shop_order_return_picture` VALUES (1,0,'/u/201408/25171730ktna.jpg','');
CREATE TABLE `jc_shop_order_shipments` (
  `Id` int(11) NOT NULL auto_increment,
  `order_id` bigint(20) default NULL,
  `admin_id` bigint(20) default NULL,
  `waybill` varchar(255) default NULL COMMENT '货运单号',
  `money` double default NULL COMMENT '金额',
  `receiving` varchar(255) default NULL COMMENT '收货信息',
  `comment` varchar(255) default NULL COMMENT '备注',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='发货信息';

INSERT INTO `jc_shop_order_shipments` VALUES (8,4,1,'1111111',1.11111111111111E17,NULL,'111111111111111');
INSERT INTO `jc_shop_order_shipments` VALUES (9,1,1,'111111111111',20,NULL,'qqqqqqqqqqqqqqqqqqqqqqqqq');
INSERT INTO `jc_shop_order_shipments` VALUES (10,1,1,'111111111',11111111,NULL,'');
INSERT INTO `jc_shop_order_shipments` VALUES (11,23,1,'test',1,NULL,'ddddddddddddddddd');
INSERT INTO `jc_shop_order_shipments` VALUES (12,22,1,'11',11,NULL,'11');
CREATE TABLE `jc_shop_pay` (
  `Id` int(11) NOT NULL auto_increment,
  `address` varchar(100) default NULL COMMENT '公司地址',
  `bankNum` varchar(100) default NULL COMMENT '银行账户',
  `bankid` varchar(100) default NULL COMMENT '网银在线商户id',
  `bankkey` varchar(100) default NULL COMMENT '网银在线key',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单付款';

INSERT INTO `jc_shop_pay` VALUES (1,'南昌×××.','农业银行:3621×××,工商银行:121×××','22304030','abcdefg');
CREATE TABLE `jc_shop_payment` (
  `payment_id` bigint(20) NOT NULL auto_increment,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `name` varchar(150) NOT NULL COMMENT '支付名称',
  `description` longtext COMMENT '描述',
  `priority` int(11) NOT NULL default '10' COMMENT '排列顺序',
  `is_disabled` tinyint(1) NOT NULL default '0' COMMENT '是否禁用',
  `is_default` tinyint(1) NOT NULL default '0' COMMENT '是否是默认',
  `type` tinyint(3) default NULL COMMENT '支付类型',
  `introduce` varchar(255) default NULL COMMENT '介绍',
  PRIMARY KEY  (`payment_id`),
  KEY `fk_jc_shop_payment_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='支付方式';

INSERT INTO `jc_shop_payment` VALUES (1,1,'网上支付','<p>支持支付宝、财付通、以及大多数网上银行支付</p>',1,0,1,1,'支持支付宝、财付通、以及大多数网上银行支付');
INSERT INTO `jc_shop_payment` VALUES (2,1,'银行汇款','<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"line-height: 25px; margin-top: 20px\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<table bgcolor=\"#67a9e5\" border=\"0\" cellpadding=\"0\" cellspacing=\"1\" width=\"600\">\r\n\t\t\t\t\t<tbody>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td align=\"middle\" background=\"3C92DF\" bgcolor=\"#3c92df\" height=\"28\" width=\"260\">\r\n\t\t\t\t\t\t\t\t<strong style=\"color: #ffffff\">支付方式</strong></td>\r\n\t\t\t\t\t\t\t<td align=\"middle\" background=\"3C92DF\" bgcolor=\"#3c92df\" width=\"340\">\r\n\t\t\t\t\t\t\t\t<strong style=\"color: #ffffff\">帐户信息</strong></td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td align=\"middle\" bgcolor=\"#ffffff\" height=\"70\">\r\n\t\t\t\t\t\t\t\t<img alt=\"商业购买\" src=\"http://www.jeecms.com/res_base/jeecms_com_www/default/article/img/bank/nh.jpg\" /></td>\r\n\t\t\t\t\t\t\t<td bgcolor=\"#ffffff\" style=\"line-height: 22px\">\r\n\t\t\t\t\t\t\t\t&nbsp;户 名：黄春荣<br />\r\n\t\t\t\t\t\t\t\t&nbsp;卡 号：6228　4809　2010　9896　414<br />\r\n\t\t\t\t\t\t\t\t&nbsp;开 户：南昌市洪城大市场支行</td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td align=\"middle\" bgcolor=\"#ffffff\" height=\"70\">\r\n\t\t\t\t\t\t\t\t<img alt=\"商业购买\" height=\"50\" src=\"http://www.jeecms.com/res_base/jeecms_com_www/default/article/img/bank/jh.jpg\" width=\"200\" /></td>\r\n\t\t\t\t\t\t\t<td bgcolor=\"#ffffff\" style=\"line-height: 22px\">\r\n\t\t\t\t\t\t\t\t&nbsp;户 名：黄春荣<br />\r\n\t\t\t\t\t\t\t\t&nbsp;卡 号：6227　0020　2158　0046　990<br />\r\n\t\t\t\t\t\t\t\t&nbsp;开 户：南昌市洪城分理处</td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td align=\"middle\" bgcolor=\"#ffffff\" height=\"70\">\r\n\t\t\t\t\t\t\t\t<img alt=\"商业购买\" height=\"50\" src=\"http://www.jeecms.com/res_base/jeecms_com_www/default/article/img/bank/gh.jpg\" width=\"200\" /></td>\r\n\t\t\t\t\t\t\t<td bgcolor=\"#ffffff\" style=\"line-height: 22px\">\r\n\t\t\t\t\t\t\t\t&nbsp;户 名：叶媛<br />\r\n\t\t\t\t\t\t\t\t&nbsp;卡 号：9558　8215　0200　1391　515<br />\r\n\t\t\t\t\t\t\t\t&nbsp;开 户：南昌市青山湖支行</td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td align=\"middle\" bgcolor=\"#ffffff\" height=\"70\">\r\n\t\t\t\t\t\t\t\t<img alt=\"商业购买\" height=\"50\" src=\"http://www.jeecms.com/res_base/jeecms_com_www/default/article/img/bank/alipay.gif\" width=\"180\" /></td>\r\n\t\t\t\t\t\t\t<td bgcolor=\"#ffffff\" style=\"line-height: 22px\">\r\n\t\t\t\t\t\t\t\t&nbsp;户 名：叶媛<br />\r\n\t\t\t\t\t\t\t\t&nbsp;帐 号：yeyuan823@163.com<br />\r\n\t\t\t\t\t\t\t\t&nbsp;开 户：支付宝</td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td align=\"middle\" bgcolor=\"#ffffff\" height=\"70\">\r\n\t\t\t\t\t\t\t\t对公帐号</td>\r\n\t\t\t\t\t\t\t<td bgcolor=\"#ffffff\" style=\"line-height: 22px\">\r\n\t\t\t\t\t\t\t\t&nbsp;户 名：江西金磊科技发展有限公司<br />\r\n\t\t\t\t\t\t\t\t&nbsp;帐 号：1502 　2010 　0900 　4557 　361<br />\r\n\t\t\t\t\t\t\t\t&nbsp;开 户：工商银行南昌市中山路支行</td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t</tbody>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td>\r\n\t\t\t\t<br />\r\n\t\t\t\t<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"line-height: 30px\" width=\"600\">\r\n\t\t\t\t\t<tbody>\r\n\t\t\t\t\t\t<tr>\r\n\t\t\t\t\t\t\t<td>\r\n\t\t\t\t\t\t\t\t　　使用网上银行转帐更加方便快捷，相关情况请到各银行营业厅咨询办理。推荐您使用支付宝方式付款，可以为您节省手续费。 银行汇款时请注意带些零头，以作区分。如原来需汇1000元就实存1000.5元。汇款成功后，请保留存款收据。 汇完款后及时跟我们联系确认汇款，以便我们及时为您提供服务。</td>\r\n\t\t\t\t\t\t</tr>\r\n\t\t\t\t\t</tbody>\r\n\t\t\t\t</table>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t</tbody>\r\n</table>\r\n<p>&nbsp;</p>',2,0,0,2,'支持工行、建行、农行汇款支付，收款时间一般为汇款后的1-2个工作日');
INSERT INTO `jc_shop_payment` VALUES (3,1,'货到付款','<p>由快递公司送货上门，您签收后直接将货款交付给快递员</p>',10,0,0,2,'由快递公司送货上门，您签收后直接将货款交付给快递员');
CREATE TABLE `jc_shop_payment_plugins` (
  `plugins_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL COMMENT '名称',
  `description` longtext COMMENT '描述',
  `priority` int(11) default NULL COMMENT '排列顺序',
  `partner` varchar(255) default NULL COMMENT '合作身份者ID',
  `seller_email` varchar(255) default NULL COMMENT '签约账号',
  `seller_key` varchar(255) default NULL COMMENT '交易安全检验码',
  `img_path` varchar(255) default NULL COMMENT '图片地址',
  `code` varchar(255) default NULL,
  PRIMARY KEY  (`plugins_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='支付插件';

INSERT INTO `jc_shop_payment_plugins` VALUES (2,'支付宝担保交易','',2,'1111111111111111','11111111111','cccccccccccccccccccccccccccccccc','/u/201408/29105140zh3l.gif','alipayPartner');
INSERT INTO `jc_shop_payment_plugins` VALUES (3,'支付宝即时交易','',1,'2222222222222222','22222222222','eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee','/u/201408/291039531mhu.gif','alipay');
CREATE TABLE `jc_shop_payment_shipping` (
  `payment_id` bigint(20) NOT NULL auto_increment,
  `shipping_id` bigint(20) NOT NULL default '0',
  PRIMARY KEY  (`payment_id`,`shipping_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `jc_shop_payment_shipping` VALUES (1,1);
INSERT INTO `jc_shop_payment_shipping` VALUES (1,2);
INSERT INTO `jc_shop_payment_shipping` VALUES (2,1);
INSERT INTO `jc_shop_payment_shipping` VALUES (2,2);
INSERT INTO `jc_shop_payment_shipping` VALUES (2,6);
INSERT INTO `jc_shop_payment_shipping` VALUES (3,2);
CREATE TABLE `jc_shop_poster` (
  `Id` int(11) NOT NULL auto_increment,
  `picUrl` varchar(255) default NULL COMMENT '图片路径',
  `time` datetime default NULL COMMENT '时间',
  `interUrl` varchar(100) default NULL COMMENT '链接地址',
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

INSERT INTO `jc_shop_poster` VALUES (2,'/u/201405/26084133ztri.jpg','2014-05-26 08:44:12','');
INSERT INTO `jc_shop_poster` VALUES (3,'/u/201405/26084242g27o.jpg','2014-05-26 08:44:12','');
INSERT INTO `jc_shop_poster` VALUES (4,'/u/201405/260843018b66.jpg','2014-05-26 08:44:12','');
INSERT INTO `jc_shop_poster` VALUES (5,'/u/201405/2608432911kh.jpg','2014-05-26 08:44:12','');
INSERT INTO `jc_shop_poster` VALUES (6,'/u/201407/21110157fyil.jpg','2014-07-21 11:02:00','');
CREATE TABLE `jc_shop_product` (
  `product_id` bigint(20) NOT NULL auto_increment,
  `ptype_id` bigint(20) NOT NULL COMMENT '类型ID',
  `brand_id` bigint(20) default NULL COMMENT '品牌ID',
  `category_id` bigint(20) NOT NULL COMMENT '类别ID',
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `config_id` bigint(20) NOT NULL COMMENT '配置ID',
  `name` varchar(150) NOT NULL COMMENT '商品名称',
  `market_price` double(11,2) NOT NULL default '0.00' COMMENT '市场价',
  `sale_price` double(11,2) NOT NULL default '0.00' COMMENT '销售价',
  `cost_price` double(11,2) NOT NULL default '0.00' COMMENT '成本价',
  `view_count` bigint(20) NOT NULL default '0' COMMENT '浏览次数',
  `sale_count` int(11) NOT NULL default '0' COMMENT '销售量',
  `stock_count` int(11) NOT NULL default '10' COMMENT '库存',
  `alert_inventory` int(11) default NULL COMMENT '警戒库存',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `expire_time` datetime default NULL COMMENT '到期时间',
  `on_sale` tinyint(1) NOT NULL default '1' COMMENT '是否上架',
  `is_recommend` tinyint(1) NOT NULL default '0' COMMENT '是否推荐',
  `is_special` tinyint(1) NOT NULL default '0' COMMENT '是否特价',
  `is_hotsale` tinyint(3) NOT NULL COMMENT '是否热卖',
  `is_newProduct` tinyint(3) NOT NULL COMMENT '是否新品',
  `score` int(11) NOT NULL default '0' COMMENT '商品送积分',
  `shareContent` varchar(255) NOT NULL COMMENT '分享内容',
  `lack_remind` tinyint(1) NOT NULL default '1' COMMENT '是否库存预警',
  `li_run` double(11,2) NOT NULL default '0.00' COMMENT '利润',
  PRIMARY KEY  (`product_id`),
  KEY `fk_jc_shop_product_brand` (`brand_id`),
  KEY `fk_jc_shop_product_category` (`category_id`),
  KEY `fk_jc_shop_product_config` (`config_id`),
  KEY `fk_jc_shop_product_ptype` (`ptype_id`),
  KEY `fk_jc_shop_product_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商品';

INSERT INTO `jc_shop_product` VALUES (1,8,3,3,1,1,'苹果4S',2348,2248,1248,0,0,144,NULL,'2014-06-18 14:32:34',NULL,1,0,0,0,0,0,'',1,0);
INSERT INTO `jc_shop_product` VALUES (2,9,15,5,1,1,'华硕R510VC',4000,3399,2399,0,0,45,NULL,'2014-06-18 14:52:41',NULL,1,0,0,0,0,0,'',1,0);
INSERT INTO `jc_shop_product` VALUES (3,1,6,12,1,1,'佳能EOS450D数码相机',50000,40000,10000,0,0,8,NULL,'2014-06-18 15:48:20',NULL,1,0,0,0,0,0,'',1,0);
INSERT INTO `jc_shop_product` VALUES (4,1,NULL,11,1,1,'支付测试',0.01,0.01,0.01,0,12,92,NULL,'2014-08-19 14:00:48',NULL,1,0,0,0,0,0,'',1,0);
INSERT INTO `jc_shop_product` VALUES (5,1,NULL,11,1,1,'test',0.1,0.1,0.1,0,0,97,NULL,'2014-08-20 10:44:41',NULL,1,0,0,0,0,0,'',1,0);
CREATE TABLE `jc_shop_product_attr` (
  `product_id` bigint(20) NOT NULL default '0',
  `attr_name` varchar(30) NOT NULL default '' COMMENT '名称',
  `attr_value` varchar(255) default NULL COMMENT '值',
  KEY `fK_jc_shop_product_attr` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='jspgou商品扩展属性表';

CREATE TABLE `jc_shop_product_exended` (
  `product_id` bigint(20) NOT NULL default '0',
  `priority` int(11) NOT NULL default '10' COMMENT '排序',
  `attr_name` varchar(30) NOT NULL default '' COMMENT '名称',
  `attr_value` varchar(255) default NULL COMMENT '值',
  KEY `fk_jc_shop_product_exended` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品和规则属性关联表';

INSERT INTO `jc_shop_product_exended` VALUES (5,0,'price','19');
INSERT INTO `jc_shop_product_exended` VALUES (5,1,'measure','22');
INSERT INTO `jc_shop_product_exended` VALUES (1,0,'price','16');
INSERT INTO `jc_shop_product_exended` VALUES (1,1,'measure','25');
INSERT INTO `jc_shop_product_exended` VALUES (2,0,'price','17');
INSERT INTO `jc_shop_product_exended` VALUES (2,1,'measure','26');
INSERT INTO `jc_shop_product_exended` VALUES (3,0,'price','16');
INSERT INTO `jc_shop_product_exended` VALUES (3,1,'measure','26');
INSERT INTO `jc_shop_product_exended` VALUES (4,0,'price','20');
INSERT INTO `jc_shop_product_exended` VALUES (4,1,'measure','29');
CREATE TABLE `jc_shop_product_ext` (
  `product_id` bigint(20) NOT NULL,
  `code` bigint(20) NOT NULL default '0' COMMENT '商品编号',
  `description` varchar(255) default NULL COMMENT '商品简介',
  `mtitle` varchar(255) default NULL COMMENT '页面TITLE',
  `mkeywords` varchar(255) default NULL COMMENT '页面KEYWORDS',
  `mdescription` varchar(255) default NULL COMMENT '页面DESCRIPTION',
  `detail_img` varchar(150) default '' COMMENT '详细图',
  `list_img` varchar(150) default NULL COMMENT '列表图',
  `min_img` varchar(150) default NULL COMMENT '最小图',
  `cover_img` varchar(255) default NULL COMMENT '封面图片',
  `weight` int(11) NOT NULL default '0' COMMENT '重量(g)',
  `unit` varchar(20) NOT NULL default '' COMMENT '单位',
  `product_imgs` longtext COMMENT '商品全方位图片',
  `product_img_desc` longtext COMMENT '全方位图片描述',
  `is_limitTime` tinyint(3) default NULL COMMENT '是否限时购买',
  `limitTime` datetime default NULL COMMENT '限时时间',
  `seckillprice` double(11,2) default NULL COMMENT '秒杀价',
  PRIMARY KEY  (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `jc_shop_product_ext` VALUES (1,20140618143235,NULL,'','','',NULL,NULL,NULL,'/u/201406/181425267rxb.jpg',0,'',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_product_ext` VALUES (2,20140618145243,NULL,'','','',NULL,NULL,NULL,'/u/201406/18144920vgvt.jpg',0,'',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_product_ext` VALUES (3,20140618154823,NULL,'','','',NULL,NULL,NULL,'/u/201406/18155613n1i8.jpg',0,'',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_product_ext` VALUES (4,20140819140052,NULL,'','','',NULL,NULL,NULL,'/u/201408/20094626rwjs.jpg',0,'',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `jc_shop_product_ext` VALUES (5,20140820104446,NULL,'','','',NULL,NULL,NULL,'/u/201408/20104409pr3u.jpg',0,'',NULL,NULL,NULL,NULL,NULL);
CREATE TABLE `jc_shop_product_fashion` (
  `fashion_id` bigint(11) NOT NULL auto_increment,
  `product_id` bigint(20) default NULL,
  `stock_count` int(11) NOT NULL default '0' COMMENT '库存',
  `on_sale` int(1) default NULL,
  `create_time` datetime default NULL,
  `market_price` double(10,2) default NULL COMMENT '市场价',
  `sale_price` double(10,2) default NULL COMMENT '销售价',
  `cost_price` double(10,2) default NULL COMMENT '成本价',
  `fashion_style` longtext,
  `sale_count` int(11) default NULL COMMENT '销量',
  `product_code` varchar(255) default NULL COMMENT '商品编号',
  `lackRemind` int(1) default '1' COMMENT '是否提醒库存预警（作废）',
  `fashion_pic` varchar(255) default NULL,
  `fashion_color` varchar(50) default NULL COMMENT '商品款式颜色',
  `fashion_size` varchar(50) default NULL COMMENT '商品款式尺码',
  `is_default` tinyint(1) NOT NULL default '0' COMMENT '是否问默认显示款式',
  `nature` varchar(255) default NULL,
  `attitude` varchar(255) default NULL,
  PRIMARY KEY  (`fashion_id`),
  KEY `fk_fashion_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='商品款式';

INSERT INTO `jc_shop_product_fashion` VALUES (1,1,12,NULL,'2014-08-28 17:23:39',2499,2399,1499,NULL,0,NULL,1,NULL,NULL,NULL,0,'23_25_28',' 白 电信3G 0元购机');
INSERT INTO `jc_shop_product_fashion` VALUES (2,1,12,NULL,'2014-08-28 17:23:39',2348,2248,1248,NULL,0,NULL,1,NULL,NULL,NULL,0,'23_25_27',' 白 电信3G 购机入网送话费');
INSERT INTO `jc_shop_product_fashion` VALUES (3,1,12,NULL,'2014-08-28 17:23:39',2498,2398,1498,NULL,0,NULL,1,NULL,NULL,NULL,0,'23_25_26',' 白 电信3G 非合约机');
INSERT INTO `jc_shop_product_fashion` VALUES (4,1,12,NULL,'2014-08-28 17:23:39',2499,2399,1499,NULL,0,NULL,1,NULL,NULL,NULL,0,'23_24_28',' 白 联通3G 0元购机');
INSERT INTO `jc_shop_product_fashion` VALUES (5,1,12,NULL,'2014-08-28 17:23:39',2348,2248,1248,NULL,0,NULL,1,NULL,NULL,NULL,0,'23_24_27',' 白 联通3G 购机入网送话费');
INSERT INTO `jc_shop_product_fashion` VALUES (6,1,12,NULL,'2014-08-28 17:23:39',2498,2398,1498,NULL,0,NULL,1,NULL,NULL,NULL,0,'23_24_26',' 白 联通3G 非合约机');
INSERT INTO `jc_shop_product_fashion` VALUES (7,1,12,NULL,'2014-08-28 17:23:39',2499,2399,1499,NULL,0,NULL,1,NULL,NULL,NULL,0,'22_25_28',' 黑 电信3G 0元购机');
INSERT INTO `jc_shop_product_fashion` VALUES (8,1,12,NULL,'2014-08-28 17:23:39',2348,2248,1248,NULL,0,NULL,1,NULL,NULL,NULL,1,'22_25_27',' 黑 电信3G 购机入网送话费');
INSERT INTO `jc_shop_product_fashion` VALUES (9,1,12,NULL,'2014-08-28 17:23:40',2498,2398,1498,NULL,0,NULL,1,NULL,NULL,NULL,0,'22_25_26',' 黑 电信3G 非合约机');
INSERT INTO `jc_shop_product_fashion` VALUES (10,1,12,NULL,'2014-08-28 17:23:39',2499,2399,1499,NULL,0,NULL,1,NULL,NULL,NULL,0,'22_24_28',' 黑 联通3G 0元购机');
INSERT INTO `jc_shop_product_fashion` VALUES (11,1,12,NULL,'2014-08-28 17:23:39',2348,2248,1248,NULL,0,NULL,1,NULL,NULL,NULL,0,'22_24_27',' 黑 联通3G 购机入网送话费');
INSERT INTO `jc_shop_product_fashion` VALUES (12,1,12,NULL,'2014-08-28 17:23:39',2498,2398,1498,NULL,0,NULL,1,NULL,NULL,NULL,0,'22_24_26',' 黑 联通3G 非合约机');
INSERT INTO `jc_shop_product_fashion` VALUES (13,2,9,NULL,'2014-08-28 17:25:52',4000,3399,2399,NULL,0,NULL,1,NULL,NULL,NULL,1,'30',' i3-3110M GT710M');
INSERT INTO `jc_shop_product_fashion` VALUES (14,2,12,NULL,'2014-08-28 17:25:53',5000,4399,3399,NULL,0,NULL,1,NULL,NULL,NULL,0,'29',' i5-3230M 750G 蓝牙');
INSERT INTO `jc_shop_product_fashion` VALUES (15,2,12,NULL,'2014-08-28 17:25:52',6000,5399,4399,NULL,0,NULL,1,NULL,NULL,NULL,0,'32',' i5-3230M 500G');
INSERT INTO `jc_shop_product_fashion` VALUES (16,2,12,NULL,'2014-08-28 17:25:52',7000,6399,5399,NULL,0,NULL,1,NULL,NULL,NULL,0,'31',' i5-4200U 高速硬盘 GT820M 蓝牙');
CREATE TABLE `jc_shop_product_keyword` (
  `product_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `priority` int(11) NOT NULL,
  KEY `fk_jc_shop_keyword_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品关键字';

CREATE TABLE `jc_shop_product_picture` (
  `product_id` bigint(20) NOT NULL default '0' COMMENT '商品Id',
  `priority` int(11) NOT NULL default '0' COMMENT '排列顺序',
  `picture_path` varchar(255) default NULL COMMENT '商品款式图',
  `bigpicture_path` varchar(255) default NULL COMMENT '大图',
  `apppicture_path` varchar(255) default NULL COMMENT '放大图',
  PRIMARY KEY  (`product_id`,`priority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品图片';

INSERT INTO `jc_shop_product_picture` VALUES (1,0,'/u/201406/18142536tneg.jpg','/u/201406/18142544h0ef.jpg','/u/201406/181425514q7u.jpg');
INSERT INTO `jc_shop_product_picture` VALUES (1,1,'/u/201406/1814260034lw.jpg','/u/201406/18142607gpvs.jpg','/u/201406/18142615rnpc.jpg');
INSERT INTO `jc_shop_product_picture` VALUES (2,0,'/u/201406/18145136za92.jpg','/u/201406/181451411694.jpg','/u/201406/181451469xgz.jpg');
INSERT INTO `jc_shop_product_picture` VALUES (3,0,'/u/201406/181556207hte.jpg','/u/201406/18155625wpur.jpg','/u/201406/18155631rqkw.jpg');
INSERT INTO `jc_shop_product_picture` VALUES (4,0,'/u/201408/20094626rwjs.jpg','/u/201408/20094626rwjs.jpg','/u/201408/20094626rwjs.jpg');
INSERT INTO `jc_shop_product_picture` VALUES (5,0,'/u/201408/20104418eyd2.jpg','/u/201408/201044248e3l.jpg','/u/201408/20104438ytsa.jpg');
CREATE TABLE `jc_shop_product_standard` (
  `ps_id` bigint(20) NOT NULL auto_increment,
  `product_id` bigint(20) NOT NULL default '0' COMMENT '与商品外键',
  `standard_id` bigint(20) NOT NULL default '0' COMMENT '与颜色规则外键',
  `dataType` tinyint(1) default NULL COMMENT '数据类型',
  `type_id` bigint(20) NOT NULL default '0' COMMENT '规格类型Id',
  `img_path` varchar(255) default NULL COMMENT '颜色图片',
  PRIMARY KEY  (`ps_id`),
  KEY `fk_jc_shop_product_color` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='商品规则存储表';

INSERT INTO `jc_shop_product_standard` VALUES (99,1,23,1,3,'/u/201406/181426427yjv.jpg');
INSERT INTO `jc_shop_product_standard` VALUES (100,1,22,1,3,'/u/201406/18142633mkyr.jpg');
INSERT INTO `jc_shop_product_standard` VALUES (101,1,25,0,5,NULL);
INSERT INTO `jc_shop_product_standard` VALUES (102,1,24,0,5,NULL);
INSERT INTO `jc_shop_product_standard` VALUES (103,1,28,0,6,NULL);
INSERT INTO `jc_shop_product_standard` VALUES (104,1,27,0,6,NULL);
INSERT INTO `jc_shop_product_standard` VALUES (105,1,26,0,6,NULL);
INSERT INTO `jc_shop_product_standard` VALUES (106,2,30,0,7,NULL);
INSERT INTO `jc_shop_product_standard` VALUES (107,2,29,0,7,NULL);
INSERT INTO `jc_shop_product_standard` VALUES (108,2,32,0,7,NULL);
INSERT INTO `jc_shop_product_standard` VALUES (109,2,31,0,7,NULL);
CREATE TABLE `jc_shop_product_tag` (
  `stag_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY  (`stag_id`,`product_id`),
  KEY `fk_jc_shop_tag_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `jc_shop_product_text` (
  `product_id` bigint(20) NOT NULL,
  `text` longtext,
  `text1` longtext,
  `text2` longtext,
  PRIMARY KEY  (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品详情';

INSERT INTO `jc_shop_product_text` VALUES (1,'<p style=\"text-align: center\">双核，让 A5 芯片双倍强大，图形处理快达 7 倍1。你会感觉到它所带来的效果 &mdash; 飞快。iPhone 4S 快速，反应灵敏，当你启动应用软件、浏览网页，以及做各种操作时，就会体验到它所成就的与众不同。而且无论你正在做什么，都可以持续进行。A5 芯片具有极高的效能，赋予 iPhone 4S 超长的电池使用时间。<img alt=\"\" src=\"/u/jspgou//201406/18143137t4t5.jpg\" style=\"width: 743px; height: 328px\" /></p>','<p style=\"text-align: center\">这可能是迄今最出色的手机摄像头。配备全新光学技术的它，或许就是你所需要的唯一一部相机。您可以使用 800 万像素高分辨率和具有更大光圈的 f/2.4 定制镜头拍摄，还有更先进的背部照度传感器、出色的自动白平衡功能、更理想的色彩保真度和面部检测，图像模糊的情况也会减少。这一切都意味着，无论有多少人，多少光线，你捕捉了多少动作，每张照片都会呈现其真实本色。等着看照片吧。<img alt=\"\" src=\"/u/jspgou//201406/18143204tvoa.jpg\" style=\"width: 750px; height: 371px\" /></p>',NULL);
INSERT INTO `jc_shop_product_text` VALUES (2,'<p style=\"text-align: center\"><img alt=\"\" src=\"/u/jspgou//201406/18145208dqsc.jpg\" style=\"width: 750px; height: 509px\" /></p>','<p style=\"text-align: center\"><img alt=\"\" src=\"/u/jspgou//201406/18145222aer4.jpg\" style=\"width: 750px; height: 439px\" /></p>',NULL);
INSERT INTO `jc_shop_product_text` VALUES (3,'<p style=\"text-align: center\"><img alt=\"\" src=\"/u/jspgou//201406/1815505964wi.jpg\" style=\"width: 750px; height: 300px\" /></p>','<p style=\"text-align: center\"><img alt=\"\" src=\"/u/jspgou//201406/18155120tdqq.jpg\" style=\"width: 750px; height: 371px\" /></p>',NULL);
INSERT INTO `jc_shop_product_text` VALUES (4,'','',NULL);
INSERT INTO `jc_shop_product_text` VALUES (5,'','',NULL);
CREATE TABLE `jc_shop_ptype` (
  `ptype_id` bigint(20) NOT NULL auto_increment,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `name` varchar(150) NOT NULL COMMENT '类型名称',
  `path` varchar(150) default NULL COMMENT '访问路径',
  `alias` varchar(255) default NULL COMMENT '类型别名',
  `channel_prefix` varchar(20) NOT NULL default 'channel' COMMENT '栏目页模板前缀',
  `content_prefix` varchar(20) NOT NULL default 'content' COMMENT '内容页模板前缀',
  `props` longtext COMMENT '属性',
  `detail_img_width` int(11) NOT NULL default '310' COMMENT '详细图宽',
  `detail_img_height` int(11) NOT NULL default '310' COMMENT '详细图高',
  `list_img_width` int(11) NOT NULL default '140' COMMENT '列表图宽',
  `list_img_height` int(11) NOT NULL default '140' COMMENT '列表图高',
  `min_img_width` int(11) NOT NULL default '100' COMMENT '缩略图宽',
  `min_img_height` int(11) NOT NULL default '100' COMMENT '缩略图高',
  PRIMARY KEY  (`ptype_id`),
  KEY `fk_jc_shop_ptype_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='商品类型';

INSERT INTO `jc_shop_ptype` VALUES (1,1,'数码',NULL,NULL,'分类','商品',NULL,310,310,140,140,100,100);
INSERT INTO `jc_shop_ptype` VALUES (2,1,'服装',NULL,NULL,'分类','商品',NULL,310,310,140,140,100,100);
INSERT INTO `jc_shop_ptype` VALUES (3,1,'鞋包配饰',NULL,NULL,'分类','商品',NULL,310,310,140,140,100,100);
INSERT INTO `jc_shop_ptype` VALUES (4,1,'日用百货',NULL,NULL,'分类','商品',NULL,310,310,140,140,100,100);
INSERT INTO `jc_shop_ptype` VALUES (5,1,'家居建材',NULL,NULL,'分类','商品',NULL,310,310,140,140,100,100);
INSERT INTO `jc_shop_ptype` VALUES (6,1,'珠宝手表',NULL,NULL,'分类','商品',NULL,310,310,140,140,100,100);
INSERT INTO `jc_shop_ptype` VALUES (7,1,'酒店',NULL,NULL,'分类','商品',NULL,310,310,140,140,100,100);
INSERT INTO `jc_shop_ptype` VALUES (8,1,'手机',NULL,NULL,'分类','商品',NULL,310,310,140,140,100,100);
INSERT INTO `jc_shop_ptype` VALUES (9,1,'笔记本',NULL,NULL,'分类','商品',NULL,310,310,140,140,100,100);
CREATE TABLE `jc_shop_ptype_exended` (
  `ptype_id` bigint(20) NOT NULL default '0' COMMENT '商品类型Id',
  `exended_id` bigint(20) NOT NULL default '0' COMMENT '扩展属性Id',
  PRIMARY KEY  (`exended_id`,`ptype_id`),
  KEY `fk_jc_shop_ptype_exended` (`ptype_id`),
  KEY `fk_jc_shop_exended_ptype` (`exended_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类型和扩展属性关联表';

INSERT INTO `jc_shop_ptype_exended` VALUES (1,1);
INSERT INTO `jc_shop_ptype_exended` VALUES (2,1);
INSERT INTO `jc_shop_ptype_exended` VALUES (3,1);
INSERT INTO `jc_shop_ptype_exended` VALUES (4,1);
INSERT INTO `jc_shop_ptype_exended` VALUES (5,1);
INSERT INTO `jc_shop_ptype_exended` VALUES (6,1);
INSERT INTO `jc_shop_ptype_exended` VALUES (7,1);
INSERT INTO `jc_shop_ptype_exended` VALUES (8,1);
INSERT INTO `jc_shop_ptype_exended` VALUES (9,1);
INSERT INTO `jc_shop_ptype_exended` VALUES (1,2);
INSERT INTO `jc_shop_ptype_exended` VALUES (2,2);
INSERT INTO `jc_shop_ptype_exended` VALUES (3,2);
INSERT INTO `jc_shop_ptype_exended` VALUES (4,2);
INSERT INTO `jc_shop_ptype_exended` VALUES (5,2);
INSERT INTO `jc_shop_ptype_exended` VALUES (6,2);
INSERT INTO `jc_shop_ptype_exended` VALUES (7,2);
INSERT INTO `jc_shop_ptype_exended` VALUES (8,2);
INSERT INTO `jc_shop_ptype_exended` VALUES (9,2);
CREATE TABLE `jc_shop_ptype_property` (
  `type_property_id` bigint(20) NOT NULL auto_increment,
  `ptype_id` bigint(20) default NULL,
  `field` varchar(255) NOT NULL default '' COMMENT '字段',
  `property_name` varchar(255) default NULL COMMENT '属性名',
  `sort` int(11) default NULL COMMENT '排序顺序',
  `def_value` varchar(255) default NULL COMMENT '默认值',
  `opt_value` varchar(255) default NULL COMMENT '可选项',
  `area_rows` varchar(255) default NULL COMMENT '文本行数',
  `area_cols` varchar(255) default NULL COMMENT '文本列数',
  `is_start` int(1) default NULL COMMENT '是否启用',
  `is_notNull` int(1) default NULL COMMENT '是否必填',
  `data_type` varchar(255) NOT NULL default '1' COMMENT '数据类型',
  `is_single` tinyint(1) NOT NULL default '1' COMMENT '是否独占一行',
  `is_category` tinyint(1) NOT NULL default '1' COMMENT '是否类别模型项',
  `is_custom` tinyint(1) NOT NULL default '1' COMMENT '是否自定义',
  PRIMARY KEY  (`type_property_id`),
  KEY `fk_type_property` (`ptype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=391 DEFAULT CHARSET=utf8 COMMENT='商品类型属性表';

INSERT INTO `jc_shop_ptype_property` VALUES (1,1,'name','分类名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (2,1,'path','访问路径',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (3,1,'title','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (4,1,'keywords','页面关键词',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (5,1,'description','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (6,1,'tplChannel','分类模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (7,1,'tplContent','商品模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (8,1,'priority','排序',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (9,1,'image','图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (10,1,'refBrand','关联品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (11,1,'standardTypeIds','扩展属性',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (12,1,'colorsize','规格',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (124,1,'name','商品名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (125,1,'categoryId','分类',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (126,1,'brandId','品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (127,1,'price','价格',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (128,1,'stockCount','商品库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (129,1,'weight','商品重量',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (130,1,'attr','设置',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (131,1,'score','可得积分',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (132,1,'alertInventory','警戒库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (133,1,'tagIds','标签',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (134,1,'coverImg','封面图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (135,1,'productstyle','商品款式集',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (136,1,'shareContent','分享内容',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (137,1,'mtitle','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (138,1,'productKeywords','关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (139,1,'mkeywords','页面关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (140,1,'mdescription','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (141,1,'text','商品详情',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (142,1,'text1','售后服务',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (143,2,'name','分类名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (144,2,'path','访问路径',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (145,2,'title','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (146,2,'keywords','页面关键词',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (147,2,'description','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (148,2,'tplChannel','分类模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (149,2,'tplContent','商品模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (150,2,'priority','排序',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (151,2,'image','图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (152,2,'refBrand','关联品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (153,2,'standardTypeIds','扩展属性',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (154,2,'colorsize','规格',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (155,2,'name','商品名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (156,2,'categoryId','分类',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (157,2,'brandId','品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (158,2,'price','价格',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (159,2,'stockCount','商品库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (160,2,'weight','商品重量',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (161,2,'attr','设置',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (162,2,'score','可得积分',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (163,2,'alertInventory','警戒库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (164,2,'tagIds','标签',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (165,2,'coverImg','封面图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (166,2,'productstyle','商品款式集',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (167,2,'shareContent','分享内容',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (168,2,'mtitle','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (169,2,'productKeywords','关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (170,2,'mkeywords','页面关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (171,2,'mdescription','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (172,2,'text','商品详情',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (173,2,'text1','售后服务',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (174,3,'name','分类名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (175,3,'path','访问路径',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (176,3,'title','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (177,3,'keywords','页面关键词',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (178,3,'description','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (179,3,'tplChannel','分类模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (180,3,'tplContent','商品模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (181,3,'priority','排序',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (182,3,'image','图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (183,3,'refBrand','关联品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (184,3,'standardTypeIds','扩展属性',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (185,3,'colorsize','规格',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (186,3,'name','商品名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (187,3,'categoryId','分类',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (188,3,'brandId','品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (189,3,'price','价格',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (190,3,'stockCount','商品库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (191,3,'weight','商品重量',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (192,3,'attr','设置',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (193,3,'score','可得积分',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (194,3,'alertInventory','警戒库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (195,3,'tagIds','标签',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (196,3,'coverImg','封面图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (197,3,'productstyle','商品款式集',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (198,3,'shareContent','分享内容',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (199,3,'mtitle','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (200,3,'productKeywords','关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (201,3,'mkeywords','页面关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (202,3,'mdescription','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (203,3,'text','商品详情',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (204,3,'text1','售后服务',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (205,7,'name','商品名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (206,7,'categoryId','分类',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (207,7,'brandId','品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (208,7,'price','价格',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (209,7,'stockCount','商品库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (210,7,'weight','商品重量',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (211,7,'attr','设置',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (212,7,'score','可得积分',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (213,7,'alertInventory','警戒库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (214,7,'tagIds','标签',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (215,7,'coverImg','封面图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (216,7,'productstyle','商品款式集',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (217,7,'shareContent','分享内容',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (218,7,'mtitle','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (219,7,'productKeywords','关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (220,7,'mkeywords','页面关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (221,7,'mdescription','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (222,7,'text','商品详情',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (223,7,'text1','售后服务',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (224,6,'name','商品名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (225,6,'categoryId','分类',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (226,6,'brandId','品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (227,6,'price','价格',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (228,6,'stockCount','商品库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (229,6,'weight','商品重量',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (230,6,'attr','设置',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (231,6,'score','可得积分',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (232,6,'alertInventory','警戒库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (233,6,'tagIds','标签',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (234,6,'coverImg','封面图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (235,6,'productstyle','商品款式集',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (236,6,'shareContent','分享内容',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (237,6,'mtitle','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (238,6,'productKeywords','关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (239,6,'mkeywords','页面关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (240,6,'mdescription','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (241,6,'text','商品详情',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (242,6,'text1','售后服务',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (243,5,'name','商品名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (244,5,'categoryId','分类',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (245,5,'brandId','品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (246,5,'price','价格',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (247,5,'stockCount','商品库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (248,5,'weight','商品重量',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (249,5,'attr','设置',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (250,5,'score','可得积分',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (251,5,'alertInventory','警戒库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (252,5,'tagIds','标签',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (253,5,'coverImg','封面图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (254,5,'productstyle','商品款式集',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (255,5,'shareContent','分享内容',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (256,5,'mtitle','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (257,5,'productKeywords','关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (258,5,'mkeywords','页面关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (259,5,'mdescription','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (260,5,'text','商品详情',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (261,5,'text1','售后服务',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (262,4,'name','商品名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (263,4,'categoryId','分类',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (264,4,'brandId','品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (265,4,'price','价格',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (266,4,'stockCount','商品库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (267,4,'weight','商品重量',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (268,4,'attr','设置',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (269,4,'score','可得积分',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (270,4,'alertInventory','警戒库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (271,4,'tagIds','标签',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (272,4,'coverImg','封面图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (273,4,'productstyle','商品款式集',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (274,4,'shareContent','分享内容',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (275,4,'mtitle','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (276,4,'productKeywords','关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (277,4,'mkeywords','页面关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (278,4,'mdescription','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (279,4,'text','商品详情',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (280,4,'text1','售后服务',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (281,7,'name','分类名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (282,7,'path','访问路径',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (283,7,'title','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (284,7,'keywords','页面关键词',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (285,7,'description','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (286,7,'tplChannel','分类模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (287,7,'tplContent','商品模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (288,7,'priority','排序',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (289,7,'image','图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (290,7,'refBrand','关联品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (291,7,'standardTypeIds','扩展属性',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (292,7,'colorsize','规格',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (293,6,'name','分类名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (294,6,'path','访问路径',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (295,6,'title','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (296,6,'keywords','页面关键词',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (297,6,'description','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (298,6,'tplChannel','分类模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (299,6,'tplContent','商品模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (300,6,'priority','排序',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (301,6,'image','图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (302,6,'refBrand','关联品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (303,6,'standardTypeIds','扩展属性',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (304,6,'colorsize','规格',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (305,5,'name','分类名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (306,5,'path','访问路径',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (307,5,'title','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (308,5,'keywords','页面关键词',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (309,5,'description','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (310,5,'tplChannel','分类模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (311,5,'tplContent','商品模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (312,5,'priority','排序',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (313,5,'image','图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (314,5,'refBrand','关联品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (315,5,'standardTypeIds','扩展属性',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (316,5,'colorsize','规格',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (317,4,'name','分类名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (318,4,'path','访问路径',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (319,4,'title','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (320,4,'keywords','页面关键词',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (321,4,'description','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (322,4,'tplChannel','分类模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (323,4,'tplContent','商品模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (324,4,'priority','排序',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (325,4,'image','图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (326,4,'refBrand','关联品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (327,4,'standardTypeIds','扩展属性',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (328,4,'colorsize','规格',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (329,8,'name','分类名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (330,8,'path','访问路径',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (331,8,'title','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (332,8,'keywords','页面关键词',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (333,8,'description','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (334,8,'tplChannel','分类模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (335,8,'tplContent','商品模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (336,8,'priority','排序',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (337,8,'image','图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (338,8,'refBrand','关联品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (339,8,'standardTypeIds','扩展属性',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (340,8,'colorsize','规格',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (341,8,'name','商品名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (342,8,'categoryId','分类',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (343,8,'brandId','品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (344,8,'price','价格',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (345,8,'stockCount','商品库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (346,8,'weight','商品重量',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (347,8,'attr','设置',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (348,8,'score','可得积分',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (349,8,'alertInventory','警戒库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (350,8,'tagIds','标签',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (351,8,'coverImg','封面图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (352,8,'productstyle','商品款式集',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (353,8,'shareContent','分享内容',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (354,8,'mtitle','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (355,8,'productKeywords','关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (356,8,'mkeywords','页面关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (357,8,'mdescription','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (358,8,'text','商品详情',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (359,8,'text1','售后服务',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (360,9,'name','分类名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (361,9,'path','访问路径',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (362,9,'title','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (363,9,'keywords','页面关键词',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (364,9,'description','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (365,9,'tplChannel','分类模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (366,9,'tplContent','商品模板',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (367,9,'priority','排序',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (368,9,'image','图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (369,9,'refBrand','关联品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (370,9,'standardTypeIds','扩展属性',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (371,9,'colorsize','规格',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,1,0);
INSERT INTO `jc_shop_ptype_property` VALUES (372,9,'name','商品名称',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (373,9,'categoryId','分类',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (374,9,'brandId','品牌',10,NULL,NULL,NULL,NULL,NULL,NULL,'6',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (375,9,'price','价格',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (376,9,'stockCount','商品库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (377,9,'weight','商品重量',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (378,9,'attr','设置',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (379,9,'score','可得积分',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (380,9,'alertInventory','警戒库存',10,NULL,NULL,NULL,NULL,NULL,NULL,'2',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (381,9,'tagIds','标签',10,NULL,NULL,NULL,NULL,NULL,NULL,'7',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (382,9,'coverImg','封面图片',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (383,9,'productstyle','商品款式集',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',0,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (384,9,'shareContent','分享内容',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (385,9,'mtitle','页面标题',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (386,9,'productKeywords','关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (387,9,'mkeywords','页面关键字',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (388,9,'mdescription','页面描述',10,NULL,NULL,NULL,NULL,NULL,NULL,'1',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (389,9,'text','商品详情',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
INSERT INTO `jc_shop_ptype_property` VALUES (390,9,'text1','售后服务',10,NULL,NULL,NULL,NULL,NULL,NULL,'4',1,0,0);
CREATE TABLE `jc_shop_score` (
  `Id` bigint(20) NOT NULL auto_increment,
  `member_id` bigint(20) NOT NULL default '0' COMMENT '会员关联',
  `name` varchar(255) default NULL COMMENT '积分来源/用途',
  `score` int(11) default NULL COMMENT '积分',
  `scoreTime` datetime default NULL COMMENT '积分生成时间',
  `scoreType` int(11) NOT NULL default '0' COMMENT '积分的类型',
  `useStatus` tinyint(1) NOT NULL default '0' COMMENT '0为收入，1为支出',
  `status` tinyint(1) NOT NULL default '0' COMMENT '积分状况（0为冻结，1为可用）',
  `remark` varchar(255) default '' COMMENT '备注',
  `code` varchar(255) default NULL COMMENT '订单号',
  PRIMARY KEY  (`Id`),
  KEY `fk_jc_shop_score_member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='积分明细';

INSERT INTO `jc_shop_score` VALUES (4,1,'苹果4S',0,'2014-06-19 11:09:53',11,0,0,NULL,'1403147393251');
INSERT INTO `jc_shop_score` VALUES (5,1,'佳能EOS450D数码相机',0,'2014-06-19 11:24:05',11,0,1,NULL,'1403148245954');
INSERT INTO `jc_shop_score` VALUES (6,4,'邮箱验证送积分',NULL,'2014-07-09 15:38:36',10,0,1,NULL,NULL);
INSERT INTO `jc_shop_score` VALUES (7,1,'佳能EOS450D数码相机',0,'2014-07-10 11:58:39',11,0,0,NULL,'1404964719610');
INSERT INTO `jc_shop_score` VALUES (8,1,'佳能EOS450D数码相机',0,'2014-07-16 14:35:04',11,0,0,NULL,'1405492504501');
INSERT INTO `jc_shop_score` VALUES (9,1,'华硕R510VC',0,'2014-07-16 15:09:40',11,0,1,NULL,'1405494580344');
INSERT INTO `jc_shop_score` VALUES (10,1,'苹果4S',0,'2014-07-16 15:42:47',11,0,1,NULL,'1405496567563');
INSERT INTO `jc_shop_score` VALUES (11,1,'苹果4S',0,'2014-07-16 15:48:22',11,0,1,NULL,'1405496902672');
INSERT INTO `jc_shop_score` VALUES (12,1,'佳能EOS450D数码相机',0,'2014-07-16 15:48:51',11,0,1,NULL,'1405496931501');
INSERT INTO `jc_shop_score` VALUES (19,1,'美女',1,'2014-07-24 15:12:56',11,1,1,NULL,NULL);
INSERT INTO `jc_shop_score` VALUES (20,1,'美女',1,'2014-07-24 15:36:15',11,1,1,NULL,NULL);
INSERT INTO `jc_shop_score` VALUES (21,1,'佳能EOS450D数码相机',0,'2014-08-01 08:48:47',11,0,0,NULL,'1406854127797');
INSERT INTO `jc_shop_score` VALUES (22,5,'邮箱验证送积分',NULL,'2014-08-05 15:34:13',10,0,1,NULL,NULL);
INSERT INTO `jc_shop_score` VALUES (23,1,'华硕R510VC',0,'2014-08-07 16:40:15',11,0,0,NULL,'1407400814954');
INSERT INTO `jc_shop_score` VALUES (24,1,'佳能EOS450D数码相机',0,'2014-08-12 10:56:28',11,0,0,NULL,'1407812188829');
INSERT INTO `jc_shop_score` VALUES (25,1,'华硕R510VC',0,'2014-08-12 14:38:20',11,0,0,NULL,'1407825500641');
INSERT INTO `jc_shop_score` VALUES (26,1,'华硕R510VC',0,'2014-08-18 10:52:20',11,0,0,NULL,'1408330340644');
INSERT INTO `jc_shop_score` VALUES (27,1,'佳能EOS450D数码相机',0,'2014-08-18 10:54:51',11,0,0,NULL,'1408330491707');
INSERT INTO `jc_shop_score` VALUES (28,1,'佳能EOS450D数码相机',0,'2014-08-19 11:06:04',11,0,0,NULL,'1408417564407');
INSERT INTO `jc_shop_score` VALUES (29,1,'支付测试',0,'2014-08-19 14:01:39',11,0,0,NULL,'1408428099188');
INSERT INTO `jc_shop_score` VALUES (30,1,'支付测试',0,'2014-08-19 14:11:18',11,0,0,NULL,'1408428678141');
INSERT INTO `jc_shop_score` VALUES (31,1,'支付测试',0,'2014-08-19 15:12:00',11,0,0,NULL,'1408432320797');
INSERT INTO `jc_shop_score` VALUES (32,1,'支付测试',0,'2014-08-21 16:59:02',11,0,0,NULL,'1408611542954');
INSERT INTO `jc_shop_score` VALUES (33,1,'test',0,'2014-08-21 17:03:14',11,0,0,NULL,'1408611794360');
INSERT INTO `jc_shop_score` VALUES (34,1,'支付测试',0,'2014-08-23 13:01:43',11,0,0,NULL,'1408770103829');
INSERT INTO `jc_shop_score` VALUES (35,1,'支付测试',0,'2014-08-25 08:34:31',11,0,0,NULL,'1408926871093');
INSERT INTO `jc_shop_score` VALUES (36,1,'支付测试',0,'2014-08-25 08:38:21',11,0,0,NULL,'1408927101187');
INSERT INTO `jc_shop_score` VALUES (37,1,'支付测试',0,'2014-08-25 08:49:27',11,0,0,NULL,'1408927767828');
INSERT INTO `jc_shop_score` VALUES (38,1,'支付测试',0,'2014-08-25 08:51:17',11,0,0,NULL,'1408927877031');
INSERT INTO `jc_shop_score` VALUES (39,1,'支付测试',0,'2014-08-25 08:53:23',11,0,0,NULL,'1408928003937');
INSERT INTO `jc_shop_score` VALUES (40,1,'支付测试',0,'2014-08-25 08:56:55',11,0,0,NULL,'1408928215672');
INSERT INTO `jc_shop_score` VALUES (41,1,'支付测试',0,'2014-08-25 08:57:10',11,0,0,NULL,'1408928230203');
INSERT INTO `jc_shop_score` VALUES (42,1,'支付测试',0,'2014-08-25 09:03:06',11,0,0,NULL,'1408928586859');
INSERT INTO `jc_shop_score` VALUES (43,1,'支付测试',0,'2014-08-25 09:04:34',11,0,0,NULL,'1408928674875');
INSERT INTO `jc_shop_score` VALUES (44,1,'支付测试',0,'2014-08-25 10:20:06',11,0,0,NULL,'1408933206953');
INSERT INTO `jc_shop_score` VALUES (45,1,'支付测试',0,'2014-08-25 10:22:39',11,0,0,NULL,'1408933359093');
INSERT INTO `jc_shop_score` VALUES (46,1,'支付测试',0,'2014-08-25 10:23:44',11,0,0,NULL,'1408933424984');
INSERT INTO `jc_shop_score` VALUES (47,1,'支付测试',0,'2014-08-25 10:23:58',11,0,0,NULL,'1408933438297');
INSERT INTO `jc_shop_score` VALUES (48,1,'支付测试',0,'2014-08-25 10:25:48',11,0,0,NULL,'1408933548406');
INSERT INTO `jc_shop_score` VALUES (49,1,'支付测试',0,'2014-08-25 10:31:27',11,0,0,NULL,'1408933887703');
INSERT INTO `jc_shop_score` VALUES (50,1,'支付测试',0,'2014-08-25 10:32:49',11,0,1,NULL,'1408933969453');
INSERT INTO `jc_shop_score` VALUES (51,1,'支付测试',0,'2014-08-25 10:34:21',11,0,1,NULL,'1408934061531');
INSERT INTO `jc_shop_score` VALUES (52,1,'支付测试',0,'2014-08-28 13:44:55',11,0,0,NULL,'1409204695454');
INSERT INTO `jc_shop_score` VALUES (53,1,'支付测试',0,'2014-08-29 10:36:03',11,0,0,NULL,'1409279763172');
INSERT INTO `jc_shop_score` VALUES (54,1,'test',0,'2014-09-09 08:26:58',11,0,0,NULL,'1410222418704');
INSERT INTO `jc_shop_score` VALUES (55,1,'test',0,'2014-09-09 08:26:58',11,0,0,NULL,'1410222418704');
INSERT INTO `jc_shop_score` VALUES (56,1,'test',0,'2014-09-09 08:26:58',11,0,0,NULL,'1410222418704');
CREATE TABLE `jc_shop_shipping` (
  `shipping_id` bigint(20) NOT NULL auto_increment,
  `website_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL COMMENT '名称',
  `description` varchar(255) default NULL COMMENT '描述',
  `uniform_price` double(11,2) default NULL COMMENT '统一价格',
  `first_weight` int(11) default NULL COMMENT '首重量(g)',
  `first_price` double(11,2) default NULL COMMENT '首重价',
  `additional_weight` int(11) default NULL COMMENT '续重量(g)',
  `additional_price` double(11,2) default NULL COMMENT '续重价',
  `method` int(11) NOT NULL default '1' COMMENT '1:固定价格;2按重量计价;:3:按国家计价;',
  `priority` int(11) NOT NULL default '10' COMMENT '排列顺序',
  `is_disabled` tinyint(1) NOT NULL default '0' COMMENT '是否禁用',
  `is_default` tinyint(1) NOT NULL default '0' COMMENT '是否默认',
  `logistics_id` bigint(20) default NULL,
  `logistics_type` varchar(255) default NULL COMMENT '物流类型',
  PRIMARY KEY  (`shipping_id`),
  KEY `fk_jc_shop_shipping_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='发货方式';

INSERT INTO `jc_shop_shipping` VALUES (1,1,'普通快递','系统将根据您的收货地址自动匹配快递公司进行配送，享受免运费服务',0,1,1,1,1,1,1,0,1,4,'EXPRESS');
INSERT INTO `jc_shop_shipping` VALUES (2,1,'顺丰快递','需支付10元配送费用，不享受免运费服务',10,500,20,500,10,1,2,0,0,5,'EXPRESS');
CREATE TABLE `jc_shop_tag` (
  `stag_id` bigint(20) NOT NULL auto_increment,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `count` int(11) NOT NULL default '0' COMMENT '关联商品个数',
  PRIMARY KEY  (`stag_id`),
  KEY `fk_jc_shop_tag_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='标签';

INSERT INTO `jc_shop_tag` VALUES (1,1,'惠民',28);
CREATE TABLE `jc_standard` (
  `standard_id` bigint(20) NOT NULL auto_increment,
  `type_id` int(11) NOT NULL default '0' COMMENT '规格类型Id',
  `name` varchar(255) NOT NULL default '' COMMENT '规格名称',
  `color` varchar(10) default NULL COMMENT '颜色',
  `priority` int(11) NOT NULL default '10' COMMENT '排序',
  PRIMARY KEY  (`standard_id`),
  KEY `fc_jc_standard_type` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='商品规格表';

INSERT INTO `jc_standard` VALUES (22,3,'黑','#000000',1);
INSERT INTO `jc_standard` VALUES (23,3,'白','#FFFFFF',2);
INSERT INTO `jc_standard` VALUES (24,5,'联通3G','',1);
INSERT INTO `jc_standard` VALUES (25,5,'电信3G','',2);
INSERT INTO `jc_standard` VALUES (26,6,'非合约机','',1);
INSERT INTO `jc_standard` VALUES (27,6,'购机入网送话费','',2);
INSERT INTO `jc_standard` VALUES (28,6,'0元购机','',3);
INSERT INTO `jc_standard` VALUES (29,7,'i5-3230M 750G 蓝牙','',1);
INSERT INTO `jc_standard` VALUES (30,7,'i3-3110M GT710M','',2);
INSERT INTO `jc_standard` VALUES (31,7,'i5-4200U 高速硬盘 GT820M 蓝牙','',3);
INSERT INTO `jc_standard` VALUES (32,7,'i5-3230M 500G','',4);
INSERT INTO `jc_standard` VALUES (33,1,'红色','#FF0000',1);
INSERT INTO `jc_standard` VALUES (34,1,'绿色','#00FF40',2);
INSERT INTO `jc_standard` VALUES (35,1,'蓝色','#0000FF',3);
INSERT INTO `jc_standard` VALUES (36,2,'s','',1);
INSERT INTO `jc_standard` VALUES (37,2,'m','',2);
INSERT INTO `jc_standard` VALUES (38,2,'l','',3);
CREATE TABLE `jc_standard_type` (
  `standardtype_id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL default '' COMMENT '规格类型名称',
  `field` varchar(255) NOT NULL default '' COMMENT '字段名称',
  `remark` varchar(255) default NULL COMMENT '备注',
  `dataType` tinyint(1) default NULL COMMENT '数据类型',
  `priority` int(11) default NULL COMMENT '排序',
  PRIMARY KEY  (`standardtype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='商品规格类型表';

INSERT INTO `jc_standard_type` VALUES (1,'颜色','yanse','服装',1,10);
INSERT INTO `jc_standard_type` VALUES (2,'尺寸','chicu','服装',0,10);
INSERT INTO `jc_standard_type` VALUES (3,'颜色','yanse1','手机',1,10);
INSERT INTO `jc_standard_type` VALUES (5,'版本','banben','手机',0,10);
INSERT INTO `jc_standard_type` VALUES (6,'购买方式','goumaifangshi','手机',0,10);
INSERT INTO `jc_standard_type` VALUES (7,'版本','bonben','电脑',0,10);

ALTER TABLE `jc_address`
ADD CONSTRAINT `fk_jc_address_parent` FOREIGN KEY (`parent_id`) REFERENCES `jc_address` (`Id`);

ALTER TABLE `jc_core_admin`
ADD CONSTRAINT `fk_jc_admin_user` FOREIGN KEY (`user_id`) REFERENCES `jc_core_user` (`user_id`),
ADD CONSTRAINT `fk_jc_admin_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_core_admin_role`
ADD CONSTRAINT `fk_jc_core_admin_role` FOREIGN KEY (`admin_id`) REFERENCES `jc_core_admin` (`admin_id`),
ADD CONSTRAINT `fk_jc_core_role_admin` FOREIGN KEY (`role_id`) REFERENCES `jc_core_role` (`role_id`);

ALTER TABLE `jc_core_log`
ADD CONSTRAINT `fk_jc_log_site` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`),
ADD CONSTRAINT `fk_jc_log_user` FOREIGN KEY (`user_id`) REFERENCES `jc_core_user` (`user_id`);

ALTER TABLE `jc_core_member`
ADD CONSTRAINT `fk_jc_member_user` FOREIGN KEY (`user_id`) REFERENCES `jc_core_user` (`user_id`),
ADD CONSTRAINT `fk_jc_member_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_core_message_tpl`
ADD CONSTRAINT `fk_jc_msgtpl_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_core_role_permission`
ADD CONSTRAINT `fk_jc_core_permission_role` FOREIGN KEY (`role_id`) REFERENCES `jc_core_role` (`role_id`);

ALTER TABLE `jc_core_website`
ADD CONSTRAINT `fk_jc_website_admin` FOREIGN KEY (`admin_id`) REFERENCES `jc_core_admin` (`admin_id`),
ADD CONSTRAINT `fk_jc_website_global` FOREIGN KEY (`global_id`) REFERENCES `jc_core_global` (`global_id`),
ADD CONSTRAINT `fk_jc_website_parent` FOREIGN KEY (`parent_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_popularity_group_product`
ADD CONSTRAINT `fk_jc_popularity_product_group` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`),
ADD CONSTRAINT `jc_popularity_group_product` FOREIGN KEY (`Id`) REFERENCES `jc_popularity_group` (`Id`);

ALTER TABLE `jc_popularity_item`
ADD CONSTRAINT `fk_jc_shop_popularityitem_cart` FOREIGN KEY (`cart_id`) REFERENCES `jc_shop_cart` (`cart_id`);

ALTER TABLE `jc_shop_admin`
ADD CONSTRAINT `fk_jc_shop_admin_core` FOREIGN KEY (`admin_id`) REFERENCES `jc_core_admin` (`admin_id`),
ADD CONSTRAINT `fk_jc_shop_admin_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_advertise`
ADD CONSTRAINT `jc_shop_adspace_fk` FOREIGN KEY (`adspace_id`) REFERENCES `jc_shop_advertise_space` (`Id`);

ALTER TABLE `jc_shop_advertise_attr`
ADD CONSTRAINT `jc_shop_advertise_fk` FOREIGN KEY (`Id`) REFERENCES `jc_shop_advertise` (`Id`);

ALTER TABLE `jc_shop_article`
ADD CONSTRAINT `fk_jc_shop_article_channel` FOREIGN KEY (`channel_id`) REFERENCES `jc_shop_channel` (`channel_id`),
ADD CONSTRAINT `fk_jc_shop_article_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_article_content`
ADD CONSTRAINT `fk_jc_shop_content_article` FOREIGN KEY (`article_id`) REFERENCES `jc_shop_article` (`article_id`);

ALTER TABLE `jc_shop_brand`
ADD CONSTRAINT `fk_jc_shop_brand_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_brand_text`
ADD CONSTRAINT `fk_jc_shop_text_brand` FOREIGN KEY (`brand_id`) REFERENCES `jc_shop_brand` (`brand_id`);

ALTER TABLE `jc_shop_cardgift`
ADD CONSTRAINT `fk_jc_cardgift_cart` FOREIGN KEY (`cartId`) REFERENCES `jc_shop_cart` (`cart_id`),
ADD CONSTRAINT `fk_jc_cardgift_gift` FOREIGN KEY (`giftId`) REFERENCES `jc_shop_gift` (`Id`),
ADD CONSTRAINT `fk_jc_cardgift_website` FOREIGN KEY (`websiteId`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_cart`
ADD CONSTRAINT `fk_jc_shop_cart_member` FOREIGN KEY (`cart_id`) REFERENCES `jc_shop_member` (`member_id`),
ADD CONSTRAINT `fk_jc_shop_cart_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_cart_item`
ADD CONSTRAINT `fk_jc_shop_cartitem_cart` FOREIGN KEY (`cart_id`) REFERENCES `jc_shop_cart` (`cart_id`),
ADD CONSTRAINT `fk_jc_shop_cartitem_product` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`),
ADD CONSTRAINT `fk_jc_shop_cartitem_productFash` FOREIGN KEY (`productFash_id`) REFERENCES `jc_shop_product_fashion` (`fashion_id`),
ADD CONSTRAINT `fk_jc_shop_cartitem_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_category`
ADD CONSTRAINT `fk_jc_shop_category_parent` FOREIGN KEY (`parent_id`) REFERENCES `jc_shop_category` (`category_id`),
ADD CONSTRAINT `fk_jc_shop_category_ptype` FOREIGN KEY (`ptype_id`) REFERENCES `jc_shop_ptype` (`ptype_id`),
ADD CONSTRAINT `fk_jc_shop_cateory_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_category_attr`
ADD CONSTRAINT `fK_jc_shop_category_attr` FOREIGN KEY (`category_id`) REFERENCES `jc_shop_category` (`category_id`);

ALTER TABLE `jc_shop_category_brand`
ADD CONSTRAINT `fk_jc_shop_brand_category` FOREIGN KEY (`brand_id`) REFERENCES `jc_shop_brand` (`brand_id`),
ADD CONSTRAINT `fk_jc_shop_category_brand` FOREIGN KEY (`category_id`) REFERENCES `jc_shop_category` (`category_id`);

ALTER TABLE `jc_shop_category_property`
ADD CONSTRAINT `fk_jc_shop_category_property` FOREIGN KEY (`category_id`) REFERENCES `jc_shop_category` (`category_id`);

ALTER TABLE `jc_shop_category_sdtype`
ADD CONSTRAINT `fk_jc_shop_category_sdtype` FOREIGN KEY (`category_id`) REFERENCES `jc_shop_category` (`category_id`),
ADD CONSTRAINT `fk_jc_shop_sdtype_category` FOREIGN KEY (`standardtype_id`) REFERENCES `jc_standard_type` (`standardtype_id`);

ALTER TABLE `jc_shop_channel`
ADD CONSTRAINT `fk_jc_shop_channel_parent` FOREIGN KEY (`parent_id`) REFERENCES `jc_shop_channel` (`channel_id`),
ADD CONSTRAINT `fk_jc_shop_channel_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_channel_content`
ADD CONSTRAINT `fk_jc_shop_content_channel` FOREIGN KEY (`channel_id`) REFERENCES `jc_shop_channel` (`channel_id`);

ALTER TABLE `jc_shop_collect`
ADD CONSTRAINT `fk_jc_shop_collect_fashion` FOREIGN KEY (`fashion_id`) REFERENCES `jc_shop_product_fashion` (`fashion_id`),
ADD CONSTRAINT `fk_jc_shop_collect_member` FOREIGN KEY (`member_id`) REFERENCES `jc_shop_member` (`member_id`),
ADD CONSTRAINT `fk_jc_shop_collect_product` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`);

ALTER TABLE `jc_shop_config`
ADD CONSTRAINT `fk_jc_shop_config_group` FOREIGN KEY (`register_group_id`) REFERENCES `jc_shop_member_group` (`group_id`),
ADD CONSTRAINT `fk_jc_shop_config_website` FOREIGN KEY (`config_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_consult`
ADD CONSTRAINT `fk_member_consult` FOREIGN KEY (`member_id`) REFERENCES `jc_shop_member` (`member_id`),
ADD CONSTRAINT `fk_product_consult` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`);

ALTER TABLE `jc_shop_coupon`
ADD CONSTRAINT `jc_shop_coupon_ibfk_2` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_dictionary`
ADD CONSTRAINT `fk_jc_dictionary_type` FOREIGN KEY (`type_id`) REFERENCES `jc_shop_dictionary_type` (`Id`);

ALTER TABLE `jc_shop_discuss`
ADD CONSTRAINT `fk_jc_disucss_member` FOREIGN KEY (`member_id`) REFERENCES `jc_shop_member` (`member_id`),
ADD CONSTRAINT `fk_jc_disucss_product` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`);

ALTER TABLE `jc_shop_fashion_standard`
ADD CONSTRAINT `fk_jc_shop_fashion_standard` FOREIGN KEY (`fashion_id`) REFERENCES `jc_shop_product_fashion` (`fashion_id`),
ADD CONSTRAINT `fk_jc_shop_standard_fashion` FOREIGN KEY (`standard_id`) REFERENCES `jc_standard` (`standard_id`);

ALTER TABLE `jc_shop_gift_exchange`
ADD CONSTRAINT `fk_jc_shop_exchange_gift` FOREIGN KEY (`gift_id`) REFERENCES `jc_shop_gift` (`Id`),
ADD CONSTRAINT `fk_jc_shop_exchange_member` FOREIGN KEY (`member_id`) REFERENCES `jc_shop_member` (`member_id`);

ALTER TABLE `jc_shop_member`
ADD CONSTRAINT `fk_jc_shop_dictionary_familymembers` FOREIGN KEY (`familymembers_id`) REFERENCES `jc_shop_dictionary` (`Id`),
ADD CONSTRAINT `fk_jc_shop_dictionary_incomedesc` FOREIGN KEY (`incomedesc_id`) REFERENCES `jc_shop_dictionary` (`Id`),
ADD CONSTRAINT `fk_jc_shop_dictionary_userdegree` FOREIGN KEY (`userdegree_id`) REFERENCES `jc_shop_dictionary` (`Id`),
ADD CONSTRAINT `fk_jc_shop_dictionary_workseniority` FOREIGN KEY (`workseniority_id`) REFERENCES `jc_shop_dictionary` (`Id`),
ADD CONSTRAINT `fk_jc_shop_member_mgroup` FOREIGN KEY (`group_id`) REFERENCES `jc_shop_member_group` (`group_id`),
ADD CONSTRAINT `fk_jc_shop_member_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_member_address`
ADD CONSTRAINT `fk_jc_address_city` FOREIGN KEY (`city_id`) REFERENCES `jc_address` (`Id`),
ADD CONSTRAINT `fk_jc_address_country` FOREIGN KEY (`country_id`) REFERENCES `jc_address` (`Id`),
ADD CONSTRAINT `fk_jc_address_member` FOREIGN KEY (`member_id`) REFERENCES `jc_shop_member` (`member_id`),
ADD CONSTRAINT `fk_jc_address_province` FOREIGN KEY (`province_id`) REFERENCES `jc_address` (`Id`);

ALTER TABLE `jc_shop_member_favorite`
ADD CONSTRAINT `fk_jc_shop_favorite_member` FOREIGN KEY (`member_id`) REFERENCES `jc_shop_member` (`member_id`),
ADD CONSTRAINT `fk_jc_shop_favorite_product` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`);

ALTER TABLE `jc_shop_member_group`
ADD CONSTRAINT `fk_jc_shop_mgroup_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_money`
ADD CONSTRAINT `fk_jc_shop_money_member` FOREIGN KEY (`member_id`) REFERENCES `jc_shop_member` (`member_id`);

ALTER TABLE `jc_shop_order`
ADD CONSTRAINT `fk_jc_shop_order_member` FOREIGN KEY (`member_id`) REFERENCES `jc_shop_member` (`member_id`),
ADD CONSTRAINT `fk_jc_shop_order_payment` FOREIGN KEY (`payment_id`) REFERENCES `jc_shop_payment` (`payment_id`),
ADD CONSTRAINT `fk_jc_shop_order_shipping` FOREIGN KEY (`shipping_id`) REFERENCES `jc_shop_shipping` (`shipping_id`),
ADD CONSTRAINT `fk_jc_shop_order_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_order_item`
ADD CONSTRAINT `fk_jc_shop_item_order` FOREIGN KEY (`order_id`) REFERENCES `jc_shop_order` (`order_id`),
ADD CONSTRAINT `fk_jc_shop_orderitem_product` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`),
ADD CONSTRAINT `fk_jc_shop_orderitem_productFash` FOREIGN KEY (`productFash_id`) REFERENCES `jc_shop_product_fashion` (`fashion_id`),
ADD CONSTRAINT `fk_jc_shop_orderitem_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_order_return_picture`
ADD CONSTRAINT `fk_jc_shop_order_return_picture` FOREIGN KEY (`return_id`) REFERENCES `jc_shop_order_return` (`return_id`);

ALTER TABLE `jc_shop_payment`
ADD CONSTRAINT `fk_jc_shop_payment_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_product`
ADD CONSTRAINT `fk_jc_shop_product_brand` FOREIGN KEY (`brand_id`) REFERENCES `jc_shop_brand` (`brand_id`),
ADD CONSTRAINT `fk_jc_shop_product_category` FOREIGN KEY (`category_id`) REFERENCES `jc_shop_category` (`category_id`),
ADD CONSTRAINT `fk_jc_shop_product_config` FOREIGN KEY (`config_id`) REFERENCES `jc_shop_config` (`config_id`),
ADD CONSTRAINT `fk_jc_shop_product_ptype` FOREIGN KEY (`ptype_id`) REFERENCES `jc_shop_ptype` (`ptype_id`),
ADD CONSTRAINT `fk_jc_shop_product_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_product_attr`
ADD CONSTRAINT `fk_jc_shop_product_attr` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`);

ALTER TABLE `jc_shop_product_ext`
ADD CONSTRAINT `fk_jc_shop_ext_product` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`);

ALTER TABLE `jc_shop_product_fashion`
ADD CONSTRAINT `fk_fashion_product` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`);

ALTER TABLE `jc_shop_product_keyword`
ADD CONSTRAINT `fk_jc_shop_keyword_product` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`);

ALTER TABLE `jc_shop_product_standard`
ADD CONSTRAINT `fk_jc_shop_product_standard` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`);

ALTER TABLE `jc_shop_product_tag`
ADD CONSTRAINT `fk_jc_shop_product_tag` FOREIGN KEY (`stag_id`) REFERENCES `jc_shop_tag` (`stag_id`),
ADD CONSTRAINT `fk_jc_shop_tag_product` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`);

ALTER TABLE `jc_shop_product_text`
ADD CONSTRAINT `fk_jc_shop_text_product` FOREIGN KEY (`product_id`) REFERENCES `jc_shop_product` (`product_id`);

ALTER TABLE `jc_shop_ptype`
ADD CONSTRAINT `fk_jc_shop_ptype_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_ptype_property`
ADD CONSTRAINT `fk_type_property` FOREIGN KEY (`ptype_id`) REFERENCES `jc_shop_ptype` (`ptype_id`);

ALTER TABLE `jc_shop_score`
ADD CONSTRAINT `fk_jc_shop_score_member` FOREIGN KEY (`member_id`) REFERENCES `jc_shop_member` (`member_id`);

ALTER TABLE `jc_shop_shipping`
ADD CONSTRAINT `fk_jc_shop_shipping_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_shop_tag`
ADD CONSTRAINT `fk_jc_shop_tag_website` FOREIGN KEY (`website_id`) REFERENCES `jc_core_website` (`website_id`);

ALTER TABLE `jc_standard`
ADD CONSTRAINT `fc_jc_standard_type` FOREIGN KEY (`type_id`) REFERENCES `jc_standard_type` (`standardtype_id`);