DELIMITER $$

DROP PROCEDURE IF EXISTS `mobilesoft`.`get_serial_num`$$

CREATE DEFINER=`mobilesoft`@`%` PROCEDURE `get_serial_num`(
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
    	BEGIN      
        	SELECT DATE_FORMAT( NOW(),'%Y%m%d') INTO v_today ; 
        	IF s_type = 'request_no' THEN 
        		SELECT DATE_FORMAT( NOW(),'%Y%m%d%H') INTO v_today_h; 
            END IF ;
            
    		
            SET @v_today = v_today ;     
            SET @s_type = s_type ;
            
            #update t_user_id & p_user_id
            IF (s_type = 't_user_id') OR (s_type = 'p_user_id') THEN
		 BEGIN         
		    SET @sqlstr = 'update et_sys_var set field_date = ?  where field_name=? ';
		    PREPARE stmt FROM @sqlstr;
                    EXECUTE stmt USING  @v_today , @s_type ;
                    DEALLOCATE PREPARE stmt;  
                    COMMIT;
                    
                END;
            END IF;
            
            SELECT field_date INTO v_oldday FROM et_sys_var WHERE field_name=s_type ; 
            
            #跨天修改当天的流水号为1 ，若为当天则将流水号加1
    	    IF v_oldday <> v_today THEN
            	BEGIN            
                	SELECT DATE_FORMAT( NOW(),'%Y%m%d%H') INTO v_today;
                	SET @sqlstr = 'update et_sys_var set field_date = ? ,seri_num = 1 where field_name=?';
					PREPARE stmt FROM @sqlstr;
                    EXECUTE stmt USING  @v_today , @s_type ; 
                    DEALLOCATE PREPARE stmt; 
                    COMMIT;  
            	END;
            ELSE
                BEGIN         
					SET @sqlstr = 'update et_sys_var set seri_num = seri_num+1  where field_name=? ';
                    PREPARE stmt FROM @sqlstr;
                    EXECUTE stmt USING  @s_type ; 
                    DEALLOCATE PREPARE stmt;  
                    COMMIT;
                    
                END;
            END IF;
    	END; 
        
        #根据s_type生成流水号
        SELECT CONCAT(seri_num,'') INTO temp_value FROM et_sys_var WHERE field_name=s_type ;
   
        IF s_type = 't_user_id' THEN
        	BEGIN
            	SELECT LENGTH(temp_value) INTO temp_num;
    			SET v_length = temp_num;
    			WHILE (v_length<s_length) DO 
        			SET temp_value = CONCAT('0', temp_value);
        			SET v_length = v_length + 1;
    			END WHILE;
                #set s_value = concat('T',temp_value) ;
        	END;  
        END IF;
       
        
        #乘客id
        IF s_type = 'p_user_id' THEN 
        	BEGIN
            	SELECT LENGTH(temp_value) INTO temp_num;
    			SET v_length = temp_num;
    			WHILE (v_length<s_length) DO 
        			SET temp_value = CONCAT('0', temp_value);
        			SET v_length = v_length + 1;
    			END WHILE;
                SET s_value = CONCAT('P',temp_value) ;
        	END;         
        #出租车id
        ELSEIF s_type = 't_user_id' THEN
        	BEGIN
            	SELECT LENGTH(temp_value) INTO temp_num;
    			SET v_length = temp_num;
    			WHILE (v_length<s_length) DO 
        			SET temp_value = CONCAT('0', temp_value);
        			SET v_length = v_length + 1;
    			END WHILE;
                SET s_value = CONCAT('T',temp_value) ;
        	END;  
        #请求编号
        ELSEIF s_type = 'request_no' THEN
            BEGIN  	
                IF s_date = 'true' THEN 
    				BEGIN
        				SELECT LENGTH(temp_value)+8 INTO temp_num;
        			END ;
                ELSE 
                	SELECT LENGTH(temp_value) INTO temp_num;
    			END IF ;   
                
    			SET v_length = temp_num;
    			WHILE (v_length<s_length) DO 
        			SET temp_value = CONCAT('0', temp_value);
        			SET v_length = v_length + 1;
    			END WHILE;
                
                IF s_date = 'true' THEN
    				BEGIN
        				SET s_value = CONCAT(v_today,temp_value) ;
        			END ;
                ELSE 
                	SET s_value = temp_value ; 
    			END IF ; 
            END ;
        ELSEIF s_type = 'track_id' THEN
            BEGIN  	
                IF s_date = 'true' THEN 
    				BEGIN
        				SELECT LENGTH(temp_value)+8 INTO temp_num;
        			END ;
                ELSE 
                	SELECT LENGTH(temp_value) INTO temp_num;
    			END IF ;   
                
    			SET v_length = temp_num;
    			WHILE (v_length<s_length) DO 
        			SET temp_value = CONCAT('0', temp_value);
        			SET v_length = v_length + 1;
    			END WHILE;
                IF s_date = 'true' THEN
    				BEGIN
        				SET s_value = CONCAT(v_today,temp_value) ;
        			END ;
                ELSE 
                	SET s_value = temp_value ; 
    			END IF ; 
            END ;
        ELSE
            BEGIN
            	SET s_value = templue ; 	
        	END;  
        END IF ;      
    END IF; 
END$$

DELIMITER ;