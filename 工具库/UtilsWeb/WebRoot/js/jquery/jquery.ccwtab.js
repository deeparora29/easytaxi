////////////////////////////////////////////////////////////
// Copyright (C) 2005-2008 彩程数字科技.
// All rights reserved.
// 项目名称   	    CCWTab Jquery Plugin
// 版本说明         V1.0.0
// 开发者           刘思远
// 创建时间	        2008.11.23
// 修改记录         
// 文件名称	        jquery.ccwtab.js
// 文件描述         CCWTab是一个轻量级的Jquery插件，在语义化
//                  标签的基础上生成基于JS的动态Tab组件，支持
//                  多种事件触发
// 其他
////////////////////////////////////////////////////////////

(function( $ ) {
    
    $.fn.ccwtab = function() {
        
        /*******************************
         *  installation of ccwtab
         *******************************/
        if ( arguments.length < 1 || typeof( arguments[ 0 ] ) == "object") {
        
            // override the default arguments
            var settings = $.extend( {}, $.fn.ccwtab.defaults, arguments[ 0 ] );
            
            this.each( function() {
                var container = $( this );
                var tabList = container.find( "> ul" );
                
                // cache the settings
                container.data( "settings", settings );
                
                // add the click listener for each tab
                tabList.find( "> li" ).bind( settings.behavior, false, function( e ) {
                    e.preventDefault();
                    var tabs = tabList.find( "> li" );
                    tabs.removeClass( settings.selectedClass );
                    container.find( "> div" ).hide();
                    var selected = $( this )
                        .addClass( settings.selectedClass );
                    var panel = container.find( selected.find( "> a" ).attr( "href" )).show();
                    container.trigger( "onTabActivate", [tabs.index( selected ), panel] );
                });
                
                // hide the head titles of panel
                container.find( "> div > h3:first-child" ).hide();
                
                // show the selected panel and hide other panels
                container.find( "> div" ).hide();
                
                // select the tab
                var selected = tabList.find( "> li" )
                    .eq( settings.selectedIndex )
                    .addClass( settings.selectedClass );
                
                container.find( selected.find( "> a" ).attr( "href" )).show();
            });
        
        
        /*******************************
         *  api of ccwtab
         *******************************/
        } else if ( typeof( arguments[ 0 ] ) == "string") {
            
            if ( arguments[ 0 ] == "selectedIndex" ) {
                
                var index = arguments[ 1 ];
                
                if ( index ) {
                    // set the selectedIndex
                    this.each( function() {
                        var container = $( this );
                        var settings = container.data( "settings" );
                        var selected = container.find( "> ul > li" )
                            .removeClass( settings.selectedClass )
                            .eq( index )
                            .addClass( settings.selectedClass );
                        container.find( "> div" ).hide();
                        container.find( selected.find( "> a" ).attr( "href" )).show();
                    });
                } else {
                    // get the selectedIndex of the first matched element
                    var container = $( this[0] );
                    var settings = container.data( "settings" );
                    container.find( "> ul > li" ).each( function ( i ){
                        if ( $( this ).hasClass( settings.selectedClass )) {
                            index = i
                        }
                    });
                    return index;
                }
            }
        }
    };
    
    
    // the default arguments of ccwtab
    // which can be overridden outside this plugin
    $.fn.ccwtab.defaults = {
        selectedIndex: 0,
        selectedClass: "ccwtab_active",
        behavior: "click"
    };
    
})( jQuery );