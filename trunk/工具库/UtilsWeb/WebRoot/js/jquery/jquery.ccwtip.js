////////////////////////////////////////////////////////////
// Copyright (C) 2005-2008 彩程数字科技.
// All rights reserved.
// 项目名称   	    CCWTip Jquery Plugin
// 版本说明         V1.0.0
// 开发者           刘思远
// 创建时间	        2008.11.23
// 修改记录         
// 文件名称	        jquery.ccwtip.js
// 文件描述         
// 其他
////////////////////////////////////////////////////////////

(function( $ ) {
    
    $.fn.ccwtip = function() {
        
        /*******************************
         *  installation of ccwtip
         *******************************/
        if ( arguments.length < 1 || typeof( arguments[ 0 ] ) == "object") {
        
            // override the default arguments
            var settings = $.extend( {}, $.fn.ccwtip.defaults, arguments[ 0 ] );
            
            this.each( function() {
                var target = $( this );
                var container;
                if ( settings.content && typeof( settings.content ) == "object" ) {
                    container = $( settings.content )
                        .addClass( settings.containerClass )
                        .hide()
                        .css({ position: "absolute" });
                    ;
                } else {
                    container = $( document.createElement( "div" ))
                        .addClass( settings.containerClass )
                        .appendTo( document.body )
                        .hide()
                        .css({ position: "absolute" });
                }
                
                if ( typeof( settings.content ) == "string" ) {
                    container.html( settings.content );
                }
                
                target.mousemove( function( e ) {
                    container.css({
                        top: e.pageY + settings.containerOffset.top,
                        left: e.pageX + settings.containerOffset.left
                    });
                });
                
                if ( settings.showEvent == settings.hideEvent ) {
                    target.bind( settings.showEvent, false, function( e ) {
                        e.preventDefault();
                        container.toggle();
                        if ( container.css( "display" ) != "none" ) {
                            target.trigger( "onTipShow", [container] );
                        } else if ( container.css( "display" ) == "none" ) {
                            target.trigger( "onTipHide", [container] );
                        }
                    });
                } else {
                    target.bind( settings.showEvent, false, function( e ) {
                        e.preventDefault();
                        container.show();
                        target.trigger( "onTipShow", [container] );
                    });
                    
                    target.bind( settings.hideEvent, false, function( e ) {
                        e.preventDefault();
                        container.hide();
                        target.trigger( "onTipHide", [container] );
                    });
                }
                
                target.data( "tooltip", container );
                target.data( "settings", settings );
            });
        
        
        /*******************************
         *  api of ccwtip
         *******************************/
        } else if ( typeof( arguments[ 0 ] ) == "string" ) {
            
            if ( arguments[0] == "content" ) {
                var content = arguments[1];
                
                this.each( function() {
                    var target = $( this );
                    var container = target.data( "tooltip" );
                    var settings = target.data( "settings" );
                    if ( typeof( content ) == "string" ) {
                        container.html( content );
                    } else {
                        container = $( content )
                            .addClass( settings.containerClass )
                            .hide()
                            .css({ position: "absolute" });
                    }
                    target.data( "tooltip", container );
                });
            }
        }
        
        return this;
    };
    
    
    // the default arguments of ccwtip
    // which can be overridden outside this plugin
    $.fn.ccwtip.defaults = {
        showEvent: "mouseover",
        hideEvent: "mouseout",
        content: "test tip",
        containerClass: "ccwtip_container",
        containerOffset: { top: 23, left: 5 }
    };
    
})( jQuery );