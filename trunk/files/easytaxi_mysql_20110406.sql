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
  `userid` varchar(6) DEFAULT NULL COMMENT 'userid被评价者',
  `type` int(2) DEFAULT '0' COMMENT '0:taxi;1:passenger',
  `comments` varchar(256) DEFAULT NULL,
  `credit` float DEFAULT '3',
  `credit_userid` varchar(6) DEFAULT NULL COMMENT '评价者',
  `credit_time` datetime DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `creditrecord` */

insert  into `creditrecord`(`requestNo`,`userid`,`type`,`comments`,`credit`,`credit_userid`,`credit_time`,`id`) values ('201104081501','P00001',1,'test comments',4.5,NULL,'2011-04-10 20:24:12',1),('201104081501','T00001',0,'taxi comments',3,NULL,'2011-04-10 20:25:10',2);

/*Table structure for table `et_sys_var` */

DROP TABLE IF EXISTS `et_sys_var`;

CREATE TABLE `et_sys_var` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `field_name` varchar(40) NOT NULL,
  `field_value` varchar(80) NOT NULL,
  `field_date` varchar(8) DEFAULT NULL,
  `comments` varchar(100) DEFAULT NULL,
  `seri_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `et_sys_var` */

insert  into `et_sys_var`(`id`,`field_name`,`field_value`,`field_date`,`comments`,`seri_num`) values (1,'taxi_no','1','20110404','test',1059),(2,'t_user_id','1','20110404','taxi no',26),(3,'request_no','1','20110404','request no',3),(4,'p_user_id','1','20110404','passenger no',2),(5,'track_id','1','2011040','track id',14);

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
  `login_time` datetime DEFAULT NULL,
  `account` varchar(32) DEFAULT NULL,
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
  `password` varchar(32) NOT NULL,
  `gender` varchar(6) DEFAULT 'male' COMMENT 'female/male',
  `picid` int(11) DEFAULT '0',
  `credit` float DEFAULT '3',
  `agreement` varchar(3) DEFAULT 'yes' COMMENT 'share agreement,',
  `register_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `descr` varchar(256) DEFAULT NULL,
  `province` varchar(32) DEFAULT NULL,
  `city` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `passenger` */

insert  into `passenger`(`userid`,`firstname`,`lastname`,`nickname`,`phone`,`email`,`password`,`gender`,`picid`,`credit`,`agreement`,`register_time`,`modified_time`,`descr`,`province`,`city`) values ('P00001','Ren','Mian','Anne','13088063731','anne.mian.ren@gmail.com','123456','female',0,3,'yes','2011-04-10 21:09:52','2011-04-10 21:09:52','test','Sichuan','Chengdu');

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
  `luggage` int(2) DEFAULT '0',
  `comments` varchar(128) DEFAULT NULL,
  `share` varchar(3) DEFAULT 'yes',
  `status` int(1) DEFAULT '0' COMMENT '0:isvalid;1:canceled by passenger;2:canceled by taxi;3:isvalid',
  `operator_time` datetime DEFAULT NULL,
  `operatorid` varchar(6) DEFAULT NULL COMMENT 'operator id',
  `operator_type` int(1) DEFAULT '0' COMMENT '0:taxi;1:passenger',
  `operator_comments` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`requestNo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `requestinfo` */

insert  into `requestinfo`(`requestNo`,`userid`,`request_time`,`start_long`,`start_lat`,`start_text`,`end_long`,`end_lat`,`end_text`,`number`,`luggage`,`comments`,`share`,`status`,`operator_time`,`operatorid`,`operator_type`,`operator_comments`) values ('201104081501','P00001',NULL,0,0,NULL,0,0,NULL,1,0,'test request','yes',0,NULL,NULL,0,NULL);

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
  `plate_number` varchar(10) NOT NULL,
  `password` varchar(32) NOT NULL,
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

insert  into `taxi`(`userid`,`plate_number`,`password`,`license`,`company`,`car_model`,`charge_model`,`email`,`contact_person0`,`contact_phone0`,`contact_person1`,`contact_phone1`,`status`,`descr`,`credit`,`register_time`,`modified_time`) values ('T00001','川A12345','123','12345678','万达','速腾','现金支付','test@taxi.com','刘师傅','13912345678','张师傅','13812345678',0,'test',3,'2011-04-10 21:10:07','2011-04-10 21:10:07');

/*Table structure for table `trackhistory` */

DROP TABLE IF EXISTS `trackhistory`;

CREATE TABLE `trackhistory` (
  `trackid` varchar(12) NOT NULL COMMENT 'yyyymmdd0000',
  `userid` varchar(6) NOT NULL,
  `type` int(1) DEFAULT '0' COMMENT '0:taxi;1:passenger',
  `begintime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `trackfile` varchar(32) DEFAULT NULL,
  `lat` double DEFAULT '0',
  `lng` double DEFAULT '0',
  PRIMARY KEY (`trackid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `trackhistory` */

/* Procedure structure for procedure `get_serial_num` */

/*!50003 DROP PROCEDURE IF EXISTS  `get_serial_num` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `get_serial_num`(
        IN s_type VARCHAR(40),
        IN s_length INTEGER,
        IN s_date VARCHAR(8),
        OUT s_value VARCHAR(40)
    )
BEGIN
    DECLARE stmt VARCHAR(2000);
    DECLARE sqlstr VARCHAR(2000);  
    DECLARE v_recnum INT;   
    DECLARE v_today VARCHAR(10); 
    DECLARE v_today_h VARCHAR(10);    
    DECLARE v_oldday VARCHAR(8);      
    DECLARE field_value VARCHAR(40);
    DECLARE v_length INT;      
    DECLARE temp_num INT;  
    DECLARE temp_value VARCHAR(1000);   
    DECLARE s_where VARCHAR(100);
	SELECT COUNT(1) INTO v_recnum FROM et_sys_var WHERE field_name=s_type ;
     
    IF v_recnum = 0 THEN  
        BEGIN
    		SET s_value = '-1';
  		END;   
    ELSE    
    	begin      
        	select date_format( now(),'%Y%m%d') into v_today ; 
        	if s_type = 'request_no' then 
        		select date_format( now(),'%Y%m%d%H') into v_today_h; 
            end if ;
            
    		select field_date into v_oldday from et_sys_var where field_name=s_type ; 
            set @v_today = v_today ;     
            set @s_type = s_type ;
            #跨天修改当天的流水号为1 ，若为当天则将流水号加1
    	    if v_oldday <> v_today then    
            	begin            
                	select date_format( now(),'%Y%m%d%H') into v_today;
                	set @sqlstr = 'update et_sys_var set field_date = ? ,seri_num = 1 where field_name=?';
					prepare stmt from @sqlstr;
                    execute stmt USING  @v_today , @s_type ; 
                    deallocate prepare stmt; 
                    commit;  
            	end;
            else
                begin         
					set @sqlstr = 'update et_sys_var set seri_num = seri_num+1  where field_name=? ';
                    prepare stmt from @sqlstr;
                    execute stmt USING  @s_type ; 
                    deallocate prepare stmt;  
                    commit;
                    
                end;
            end if;
    	end; 
        
        #根据s_type生成流水号
        select CONCAT(seri_num,'') into temp_value from et_sys_var where field_name=s_type ;
   
        if s_type = 't_user_id' then
        	begin
            	select length(temp_value) into temp_num;
    			set v_length = temp_num;
    			while (v_length<s_length) do 
        			set temp_value = CONCAT('0', temp_value);
        			set v_length = v_length + 1;
    			end WHILE;
                #set s_value = concat('T',temp_value) ;
        	end;  
        end if;
       
        
        #乘客id
        if s_type = 'p_user_id' then 
        	begin
            	select length(temp_value) into temp_num;
    			set v_length = temp_num;
    			while (v_length<s_length) do 
        			set temp_value = CONCAT('0', temp_value);
        			set v_length = v_length + 1;
    			end WHILE;
                set s_value = concat('P',temp_value) ;
        	end;         
        #出租车id
        elseif s_type = 't_user_id' then
        	begin
            	select length(temp_value) into temp_num;
    			set v_length = temp_num;
    			while (v_length<s_length) do 
        			set temp_value = CONCAT('0', temp_value);
        			set v_length = v_length + 1;
    			end WHILE;
                set s_value = concat('T',temp_value) ;
        	end;  
        #请求编号
        elseif s_type = 'request_no' then
            begin  	
                if s_date = 'true' then 
    				begin
        				select length(temp_value)+10 into temp_num;
        			end ;
                else 
                	select length(temp_value) into temp_num;
    			end if ;   
                
    			set v_length = temp_num;
    			while (v_length<s_length) do 
        			set temp_value = CONCAT('0', temp_value);
        			set v_length = v_length + 1;
    			end WHILE;
                
                if s_date = 'true' then
    				begin
        				set s_value = concat(v_today_h,temp_value) ;
        			end ;
                else 
                	set s_value = temp_value ; 
    			end if ; 
            end ;
        elseif s_type = 'track_id' then
            begin  	
                if s_date = 'true' then 
    				begin
        				select length(temp_value)+8 into temp_num;
        			end ;
                else 
                	select length(temp_value) into temp_num;
    			end if ;   
                
    			set v_length = temp_num;
    			while (v_length<s_length) do 
        			set temp_value = CONCAT('0', temp_value);
        			set v_length = v_length + 1;
    			end WHILE;
                if s_date = 'true' then
    				begin
        				set s_value = concat(v_today,temp_value) ;
        			end ;
                else 
                	set s_value = temp_value ; 
    			end if ; 
            end ;
        else
            begin
            	set s_value = templue ; 	
        	end;  
        end if ;      
    end if; 
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;






