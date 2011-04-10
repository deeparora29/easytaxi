/*
SQLyog Community Edition- MySQL GUI
MySQL - 5.1.48-community 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;
/*
create table `taxi` (
	`userid` varchar (18),
	`plate_number` varchar (21),
	`password` varchar (96),
	`license` varchar (36),
	`company` varchar (768),
	`car_model` varchar (48),
	`charge_model` varchar (48),
	`email` varchar (384),
	`contact_person0` varchar (96),
	`contact_phone0` varchar (42),
	`contact_person1` varchar (96),
	`contact_phone1` varchar (42),
	`status` double ,
	`descr` varchar (768),
	`credit` float ,
	`register_time` datetime ,
	`modified_time` datetime 
); 
*/
INSERT INTO `taxi` (`userid`, `plate_number`, `password`, `license`, `company`, `car_model`, `charge_model`, `email`, `contact_person0`, `contact_phone0`, `contact_person1`, `contact_phone1`, `status`, `descr`, `credit`, `register_time`, `modified_time`) VALUES('T00001','川A123456','123','12345678','万达','速腾','现金支付','test@taxi.com','刘师傅','13912345678','张师傅','13812345678','0','test','3',NOW(),NOW());
