////////////////////////////////////////////////////////////
// Copyright (C) 2005-2008 彩程数字科技.
// All rights reserved.
// 项目名称   	    CCWRank Jquery Plugin
// 版本说明         V1.0.0
// 开发者           刘思远
// 创建时间	        2008.11.24
// 修改记录         
// 文件名称	        jquery.ccwrank.js
// 文件描述         CCWRank是一个jQuery的评分插件
// 其他
////////////////////////////////////////////////////////////

(function( $ ) {
    
    $.fn.ccwrank = function() {
        
        /*******************************
         *  installation of ccwrank
         *******************************/
        if ( arguments.length < 1 || typeof( arguments[ 0 ] ) == "object") {
        
            // override the default arguments
            var settings = $.extend( {}, $.fn.ccwrank.defaults, arguments[ 0 ] );
            
            this.each( function() {
                
                var container = $(this);
                
                // cache the settings
                container.data( "settings", settings );
                
                // remove all children of the container
                container.empty();
                
                // generate the ranking images
                for ( var i = 0; i < settings.imgNumber; i++ ) {
                    var img = $( document.createElement( "img" )).attr({
                        src: settings.nullImg[ i % settings.nullImg.length ],
                        title: (( i + 1 ) / settings.imgNumber * settings.maxValue ).toFixed( 1 ),
                        alt: " 0 "
                    });
                    container.append( img );
                }
                
                // add event handlers if it hasn't been ranked
                if ( !settings.ranked ) {
                    resetRank( container );
                }
                
                // cache the value and 
                var index = Math.round( settings.value / settings.maxValue * settings.imgNumber ) - 1;
                var current = container.find( "> img" ).eq( index );
                refreshRank( container, current, settings );
                container.data( "value", current.attr( "title" ));
            });
            
            
        /*******************************
         *  api of ccwrank
         *******************************/
        } else if ( typeof( arguments[ 0 ] ) == "string") {
            
            if ( arguments[ 0 ] == "getValue" ) {
                
                var values = [];
                this.each( function() {
                    values.push( $( this ).data( "value" ) );
                });
                
                if ( values.length == 1 ) {
                    return values[ 0 ];
                }
                return values;
                
            } else if ( arguments[ 0 ] == "setValue" ) {
                
                var value = arguments[ 1 ];
                
                if ( isNaN( value )) {
                    return;
                }
                
                this.each( function() {
                    var container = $( this );
                    var settings = container.data( "settings" );
                    if ( value > settings.maxValue ) {
                        value = settings.maxValue;
                    } else if ( value < 0 ) {
                        value = 0;
                    }
                    var index = Math.round( value / settings.maxValue * settings.imgNumber ) - 1;
                    var current = container.find( "> img" ).eq( index );
                    refreshRank( container, current, settings );
                    container.data( "value", value);
                });
            } else if ( arguments[ 0 ] == "reset" ) {
                this.each( function() {
                    resetRank( $( this ));
                });
            }
        }
        
        return this;
    };
    
    // private function for refreshing the rank
    function refreshRank( container, currentImg, settings ) {
        container.find( "> img" ).each( function() {
            var i = container.find( "> img" ).index( this );
            $( this ).attr({
                src: settings.nullImg[ i % settings.nullImg.length ],
                alt: " 0 "
            });
        });
        if ( currentImg && currentImg.length > 0 ) {
            currentImg.prevAll().andSelf().each( function() {
                var i = container.find( "> img" ).index( this );
                $( this ).attr({
                    src: settings.rankImg[ i % settings.rankImg.length ],
                    alt: " * "
                });
            });
        }
    }
    
    // private function for reseting the rank
    function resetRank( container ) {
        var settings = container.data( "settings" );
        container.find( "> img" ).click( function() {
            container.find( "> img" ).unbind()
                .css({ cursor: "default" });
            var value = ( $( this ).attr( "title" ) * 1).toFixed( 1 );
            container.data( "value", value );
            if ( settings.rankedCallback ) {
                settings.rankedCallback( value );
            }
        }).mouseover( function() {
            refreshRank( container, $( this ), settings );
            if ( settings.hoverCallback ) {
                settings.hoverCallback( ( $( this ).attr( "title" ) * 1).toFixed( 1 ));
            }
        }).css({ cursor: "pointer" });
    }
    
    // the default arguments of ccwtab
    // which can be overridden outside this plugin
    $.fn.ccwrank.defaults = {
        ranked: false,
        imgNumber: 5,
        rankImg: ["assets/ccwrank-img.gif"],
        nullImg: ["assets/ccwrank-null-img.gif"],
        maxValue: 10,
        value: 5,
        hoverCallback: false,
        rankedCallback: false
    };
    
})( jQuery );