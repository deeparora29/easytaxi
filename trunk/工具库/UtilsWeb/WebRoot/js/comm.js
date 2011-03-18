function onReady(){    //这个就是传说的ready 
				$("input").each(function(){
					if($(this).attr("type")=="radio" || $(this).attr("type")=="checkbox")
					$(this).addClass("onborder");
				 });
				$('table.table_border1px tr:odd').addClass("bg_00");
				$('table.table_border1px tr').mouseover(function(){     
                                //如果鼠标移到class为stripe的表格的tr上时，执行函数 
                                $(this).addClass("bg_01");}).mouseout(function(){    
                                                                //给这行添加class值为over，并且当鼠标一出该行时执行函数 
                                $(this).removeClass("bg_01");})
				$('table.table_border1px tr:odd').mouseover(function(){     
                                //如果鼠标移到class为stripe的表格的tr上时，执行函数 
                                $(this).removeClass("bg_00");
								$(this).addClass("bg_01");}).mouseout(function(){    
                                                                //给这行添加class值为over，并且当鼠标一出该行时执行函数 
                                $(this).removeClass("bg_01");
								$(this).addClass("bg_00");})
/*                $("input").mouseover(function(){     
                                //如果鼠标移到class为stripe的表格的tr上时，执行函数 
                                $(this).addClass("HeightLight");}).mouseout(function(){    
                                                                //给这行添加class值为over，并且当鼠标一出该行时执行函数 
                                $(this).removeClass("HeightLight");});   //移除该行的class 
                $("textarea").mouseover(function(){     
                                //如果鼠标移到class为stripe的表格的tr上时，执行函数 
                                $(this).addClass("HeightLight");}).mouseout(function(){    
                                                                //给这行添加class值为over，并且当鼠标一出该行时执行函数 
                                $(this).removeClass("HeightLight");});   //移除该行的class 
                $("select").mouseover(function(){     
                                //如果鼠标移到class为stripe的表格的tr上时，执行函数 
                                $(this).addClass("HeightLight");}).mouseout(function(){    
                                                                //给这行添加class值为over，并且当鼠标一出该行时执行函数 
                                $(this).removeClass("HeightLight");})
*/}// JavaScript Document