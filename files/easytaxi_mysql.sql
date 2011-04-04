/*
SQLyog Community Edition- MySQL GUI v8.04 
MySQL - 5.1.48-community : Database - easytaxi
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`easytaxi` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `easytaxi`;

/*Table structure for table `creditrecord` */

DROP TABLE IF EXISTS `creditrecord`;

CREATE TABLE `creditrecord` (
  `requestNo` varchar(12) NOT NULL,
  `userid` varchar(6) DEFAULT NULL COMMENT 'user id',
  `type` int(2) DEFAULT '0' COMMENT '0:taxi;1:passenger',
  `comments` varchar(256) DEFAULT NULL,
  `credit` float DEFAULT '3',
  `credit_time` datetime DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `creditrecord` */

/*Table structure for table `locationinfo` */

DROP TABLE IF EXISTS `locationinfo`;

CREATE TABLE `locationinfo` (
  `id` varchar(6) NOT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0:taxi;1:passenger',
  `longtitude` double DEFAULT '0',
  `latitude` double DEFAULT '0',
  `location` varchar(256) DEFAULT NULL,
  `routenumber` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `locationinfo` */

/*Table structure for table `loginrecord` */

DROP TABLE IF EXISTS `loginrecord`;

CREATE TABLE `loginrecord` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(6) NOT NULL COMMENT 'user id',
  `type` int(1) DEFAULT '0' COMMENT '0:taxi;1:passenger',
  `login_time` datetime DEFAULT NULL,
  `phone` varchar(14) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longtitude` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `loginrecord` */

/*Table structure for table `passenger` */

DROP TABLE IF EXISTS `passenger`;

CREATE TABLE `passenger` (
  `userid` varchar(6) NOT NULL,
  `firstname` varchar(16) DEFAULT NULL,
  `lastname` varchar(16) DEFAULT NULL,
  `nickname` varchar(64) DEFAULT NULL,
  `phone` varchar(14) NOT NULL,
  `email` varchar(64) NOT NULL,
  `gender` int(1) DEFAULT '1' COMMENT '0:femail;1:mail',
  `picid` int(11) DEFAULT '0',
  `credit` float DEFAULT '3',
  `agreement` varchar(3) DEFAULT 'yes',
  `register_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `descr` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `passenger` */

/*Table structure for table `pictures` */

DROP TABLE IF EXISTS `pictures`;

CREATE TABLE `pictures` (
  `picid` int(11) NOT NULL AUTO_INCREMENT,
  `picname` varchar(16) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  PRIMARY KEY (`picid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `pictures` */

/*Table structure for table `requestinfo` */

DROP TABLE IF EXISTS `requestinfo`;

CREATE TABLE `requestinfo` (
  `requestNo` varchar(12) NOT NULL COMMENT 'yyyymmddhh**',
  `userid` varchar(6) NOT NULL,
  `request_time` datetime DEFAULT NULL,
  `start_long` double DEFAULT '0',
  `start_lat` double DEFAULT '0',
  `start_text` varchar(256) DEFAULT NULL,
  `end_long` double DEFAULT '0',
  `end_lat` double DEFAULT '0',
  `end_text` varchar(256) DEFAULT NULL,
  `number` int(2) DEFAULT '1',
  `luggage_number` int(2) DEFAULT '0',
  `comments` varchar(128) DEFAULT NULL,
  `share` varchar(3) DEFAULT 'yes',
  `status` int(1) DEFAULT '0' COMMENT '0:isvalid;1:canceled by passenger;2:canceled by taxi;3:isvalid',
  `operator_time` datetime DEFAULT NULL,
  `operatorid` varchar(6) DEFAULT NULL COMMENT 'operator id',
  `operator_type` int(1) DEFAULT '0' COMMENT '0:taxi;1:passenger',
  PRIMARY KEY (`requestNo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `requestinfo` */

/*Table structure for table `routes` */

DROP TABLE IF EXISTS `routes`;

CREATE TABLE `routes` (
  `routenumber` int(11) NOT NULL,
  `routename` varchar(50) DEFAULT NULL,
  `markercolor` varchar(10) DEFAULT NULL COMMENT 'rgb',
  `routecolor` varchar(10) DEFAULT NULL COMMENT 'rgb',
  `routenote` varchar(256) DEFAULT NULL COMMENT 'rgb',
  `requestNo` varchar(12) DEFAULT NULL,
  `userid` varchar(6) DEFAULT NULL,
  `type` int(1) DEFAULT '0' COMMENT '0:taxi;1:passenger',
  `begintime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `trackfile` varchar(32) DEFAULT NULL COMMENT 'track file name',
  PRIMARY KEY (`routenumber`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `routes` */

/*Table structure for table `taxi` */

DROP TABLE IF EXISTS `taxi`;

CREATE TABLE `taxi` (
  `userid` varchar(6) NOT NULL,
  `plate_number` varchar(7) NOT NULL,
  `license` varchar(12) DEFAULT NULL,
  `company` varchar(256) DEFAULT NULL,
  `car_model` varchar(16) DEFAULT NULL,
  `charge_model` varchar(16) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `contact_person0` varchar(32) DEFAULT NULL,
  `contact_phone0` varchar(14) DEFAULT NULL,
  `contact_person1` varchar(32) DEFAULT NULL,
  `contact_phone1` varchar(14) DEFAULT NULL,
  `status` int(1) DEFAULT '0' COMMENT '0:empty;1:hired;2:share',
  `descr` varchar(256) DEFAULT NULL,
  `credit` float DEFAULT '3',
  `register_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `taxi` */

/*Table structure for table `trackhistory` */

DROP TABLE IF EXISTS `trackhistory`;

CREATE TABLE `trackhistory` (
  `trackid` varchar(12) NOT NULL COMMENT 'yyyymmdd0000',
  `userid` varchar(6) NOT NULL,
  `type` int(1) DEFAULT '0' COMMENT '0:taxi;1:passenger',
  `begintime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `trackfile` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`trackid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `trackhistory` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

CREATE TABLE `et_sys_var` (
  `id` int(11) NOT NULL auto_increment,
  `field_name` varchar(40) NOT NULL,
  `field_value` varchar(80) NOT NULL,
  `field_date` varchar(8) default NULL,
  `comments` varchar(100) default NULL,
  `seri_num` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=lati4',n1;
INSERT INTO `et_sys_var` (`id`, `field_name`, `field_value`, `field_date`, `comments`, `seri_num`) VALUES (1,'taxi_no','1','20110404','test',1059);
INSERT INTO `et_sys_var` (`id`, `field_name`, `field_value`, `field_date`, `comments`, `seri_num`) VALUES (2,'t_user_id','1','20110404','taxi no',26);
INSERT INTO `et_sys_var` (`id`, `field_name`, `field_value`, `field_date`, `comments`, `seri_num`) VALUES (3,'request_no','1','20110404','request no',3);
INSERT INTO `et_sys_var` (`id`, `field_name`, `field_value`, `field_date`, `comments`, `seri_num`) VALUES (4,'p_user_id','1','20110404','passenger no',2);
INSERT INTO `et_sys_var` (`id`, `field_name`, `field_value`, `field_date`, `comments`, `seri_num`) VALUES (5,'track_id','1','2011040'track id',14);