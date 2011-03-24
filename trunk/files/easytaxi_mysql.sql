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

/*Table structure for table `locationinfo` */

DROP TABLE IF EXISTS `locationinfo`;

CREATE TABLE `locationinfo` (
  `id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0:taxi;1:passenger',
  `longtitude` float DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `location` varchar(256) DEFAULT NULL,
  `requestid` varchar(12) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `locationinfo` */

/*Table structure for table `passenger` */

DROP TABLE IF EXISTS `passenger`;

CREATE TABLE `passenger` (
  `passenerid` int(20) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `nickname` varchar(64) DEFAULT NULL,
  `phone` varchar(14) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL COMMENT '0:femail;1:mail',
  `picid` int(11) DEFAULT NULL,
  `credit` int(11) DEFAULT '0',
  PRIMARY KEY (`passenerid`)
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
  `requestid` varchar(12) NOT NULL COMMENT 'yyyymmddhh**',
  `passenerid` int(11) DEFAULT NULL,
  `request_time` date DEFAULT NULL,
  `start_long` float DEFAULT NULL,
  `start_lat` float DEFAULT NULL,
  `end_long` float DEFAULT NULL,
  `end_lat` float DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `luggage_number` int(11) DEFAULT NULL,
  `comments` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`requestid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `requestinfo` */

/*Table structure for table `responserecord` */

DROP TABLE IF EXISTS `responserecord`;

CREATE TABLE `responserecord` (
  `requestid` varchar(12) NOT NULL,
  `carid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0:no confirmed; 1: confirmed; 2: canceled',
  `response_time` date DEFAULT NULL,
  `comments` varchar(256) DEFAULT NULL,
  `credit` int(11) DEFAULT NULL,
  `credit_time` date DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `responserecord` */

/*Table structure for table `taxi` */

DROP TABLE IF EXISTS `taxi`;

CREATE TABLE `taxi` (
  `carid` int(11) NOT NULL,
  `plate_number` varchar(7) DEFAULT NULL,
  `company` varchar(256) DEFAULT NULL,
  `car_model` int(11) DEFAULT NULL,
  `charge_model` int(11) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `contact_person0` varchar(32) DEFAULT NULL,
  `contact_phone0` varchar(14) DEFAULT NULL,
  `contact_person1` varchar(32) DEFAULT NULL,
  `contact_phone1` varchar(14) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0:empty;1:hired',
  `descrs` varchar(256) DEFAULT NULL,
  `credit` int(11) DEFAULT '0',
  PRIMARY KEY (`carid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `taxi` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
