CREATE DEFINER = 'root'@'localhost' PROCEDURE `get_serial_num`(
        IN s_type VARCHAR(40),
        IN s_length INTEGER,
        IN s_date VARCHAR(8),
        OUT s_value VARCHAR(40)
    )
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN
	declare stmt varchar(2000);
    declare sqlstr varchar(2000);  
    declare v_recnum int;   
    declare v_today varchar(8);     
    declare v_oldday varchar(8);      
    declare field_value varchar(40);
    declare v_length int;      
    declare temp_num int;  
    declare temp_value varchar(1000);   
    declare s_where varchar(100);
	select count(1) into v_recnum from et_sys_var where field_name=s_type ;
    
    if v_recnum = 0 then  
        begin
    		set s_value = '-1';
  		end;   
    ELSE    
    	begin
        	select date_format( now(),'%Y%m%d') into v_today;  
    		select field_date into v_oldday from et_sys_var where field_name=s_type ; 
            set @v_today = v_today ;     
            set @s_type = s_type ; 
    	    if v_oldday <> v_today then    
            	begin      
                	#set s_where = concat('where field_name = ', s_type);
                	set @sqlstr = 'update et_sys_var set field_date = ? ,seri_num = 1 where field_name=?';
                    #concat('update et_sys_var set field_date = ', v_today,',seri_num = 1 ', s_where);
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
    end if; 
    select CONCAT(seri_num,'') into temp_value from et_sys_var ;
    select length(temp_value)+8 into temp_num;
    set v_length = temp_num;
    
    loop1:while (v_length<s_length) do 
        #insert into et_sys_var values('','','',5) ;
        set temp_value = CONCAT('0', temp_value);
        set v_length = v_length + 1;
    end WHILE  loop1;
    set s_value = concat(v_today,temp_value) ;
END;