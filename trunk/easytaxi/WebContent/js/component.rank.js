////////////////////////////////////////////////////////////
// 文件名称	        component.rank.js
// 文件描述         公共的评分组件
// 其他
////////////////////////////////////////////////////////////

( function( $ ) {

    $( document ).ready( function() {
        
        // 默认显示的建议
        var defaultSuggestion = "请输入意见和建议";
        
        // 初始化评分组件
    	$( "div.star" ).ccwrank({
            ranked: true,
    		imgNumber: 5,
            maxValue: 5,
    		rankImg: [ "../image/img-star.gif" ],
    		nullImg: [ "../image/img-star-null.gif" ],
            value: 0
    	});
		$( "div.bigstar" ).ccwrank({
			imgNumber: 5,
            maxValue: 5,
			rankImg: [ "../image/img-bigstar.gif" ],
			nullImg: [ "../image/img-bigstar-null.gif" ],
            value: 0,
            hoverCallback: function( value ) {
                $( ".rankWindow .grade" ).text( value );
            }
		});
        $( ".rankWindow #suggestion" ).focus( function() {
            var textarea = $( this );
            if ( textarea.text() == defaultSuggestion ) {
                textarea.text( "" );
            }
        }).blur( function() {
            var textarea = $( this );
            if ( textarea.text() == "" ) {
                textarea.text( defaultSuggestion );
            }
        });
        
        // 开始评分的点击事件
        $( ".box .rank" ).click( function() {
            var rank = $( this );
            var module = rank.parents( "div.box:first" ).attr( "id" );
            var value = rank.find( "> b" ).text();
            var rankWindow = $( ".rankWindow" ).data( "module", module );
            rankWindow.find( ".grade" ).text( value );
            rankWindow.find( ".bigstar" )
                .ccwrank( "setValue", value )
                .ccwrank( "reset" );
            rankWindow.find( "#suggestion" ).text( defaultSuggestion );
            rankWindow.css({
                position: "absolute",
                top: rank.offset().top + rank.outerHeight() + 10,
                left: rank.offset().left + rank.outerWidth() - rankWindow.outerWidth() - 5
            });
            rankWindow.show();
        });
        
        // 完成/取消评分的点击事件
        $( ".rankWindow #commitRank" ).click( function() {
            var rankWindow = $( ".rankWindow" );
            var moduleName = rankWindow.data( "module" );
            var suggestion = rankWindow.find( "#suggestion" ).text();
            var value = rankWindow.find( ".bigstar" ).ccwrank( "getValue" );
            if ( suggestion == defaultSuggestion ) {
                suggestion = "";
            }
            rankWindow.hide();
            
            //////////////////////////////////////////////////////////////
            // TODO: 把评分的数据(modulName, suggestion, value)传到服务器
            //////////////////////////////////////////////////////////////
            var module = $( "#" + moduleName );
            var rankedNumber = module.find( ".rank" ).data( "rankedNumber" );
            var oldValue = module.find( ".star" ).ccwrank( "getValue" );
            var newValue = ((( oldValue * rankedNumber ) + value ) / 
                ( rankedNumber + 1 )).toFixed( 1 );
            module.find( ".star" ).ccwrank( "setValue", newValue );
            module.find( ".rank > b" ).text( newValue );
            module.find( ".rank" ).unbind()
                .css({ cursor: "default" });
        });
        $( ".rankWindow #cancelRank" ).click( function() {
            $( ".rankWindow" ).hide();
        });
        
    });

})( jQuery )