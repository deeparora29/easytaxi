/*
SQLyog Community Edition- MySQL GUI
MySQL - 5.1.48-community 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;
/*
create table `passenger` (
	`userid` varchar (18),
	`firstname` varchar (48),
	`lastname` varchar (48),
	`nickname` varchar (192),
	`phone` varchar (42),
	`email` varchar (192),
	`password` varchar (96),
	`gender` varchar (18),
	`picid` double ,
	`credit` float ,
	`agreement` varchar (9),
	`register_time` datetime ,
	`modified_time` datetime ,
	`descr` varchar (768),
	`province` varchar (96),
	`city` varchar (96)
); 
*/
INSERT INTO `passenger` (`userid`, `firstname`, `lastname`, `nickname`, `phone`, `email`, `password`, `gender`, `picid`, `credit`, `agreement`, `descr`, `province`, `city`, `register_time`, `modified_time`) VALUES('P00001','Ren','Mian','Anne','13088063731','anne.mian.ren@gmail.com','123456','female','0','3','yes','test','Sichuan','Chengdu', NOW(), NOW());
