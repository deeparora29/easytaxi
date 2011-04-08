/*
SQLyog Community Edition- MySQL GUI
MySQL - 5.1.48-community 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;
/*
create table `requestinfo` (
	`requestNo` varchar (36),
	`userid` varchar (18),
	`request_time` datetime ,
	`start_long` double ,
	`start_lat` double ,
	`start_text` varchar (768),
	`end_long` double ,
	`end_lat` double ,
	`end_text` varchar (768),
	`number` double ,
	`luggage` double ,
	`comments` varchar (384),
	`share` varchar (9),
	`status` double ,
	`operator_time` datetime ,
	`operatorid` varchar (18),
	`operator_type` double ,
	`operator_comments` varchar (384)
); 
*/
insert into `requestinfo` (`requestNo`, `userid`, `request_time`, `start_long`, `start_lat`, `start_text`, `end_long`, `end_lat`, `end_text`, `number`, `luggage`, `comments`, `share`, `status`, `operator_time`, `operatorid`, `operator_type`, `operator_comments`) values('201104081501','P00001',NULL,'0','0',NULL,'0','0',NULL,'1','0','test request','yes','0',NULL,NULL,'0',NULL);
