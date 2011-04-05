<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>location service</title>
<link href="http://code.google.com/apis/maps/documentation/javascript/examples/standard.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript">

  var geocoder;
  var map;
  var infowindow ;
  var address ;
  function initialize() {
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(30.658602,104.06486);
    var myOptions = {
      zoom: 11,
      center: latlng,
      mapTypeId: 'roadmap'
    }
    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	
	//google.maps.event.addDomListener(map, "click", getAddress);
	//google.maps.event.addListener(map, 'click', function(event) {
	//	getAddress(event.latLng);
	//});

	//显示latlng为中心的圆
    DrawCircle(latlng );

	//加载数据
	loadData();
  }

  function DrawCircle(latlng,radius)
  {
	  var myOptions = {
	      center: latlng,
	      fillColor : "#00AAFF",
	      //map : map ,
	      radius : 2000,
	      strokeColor : "#FFAA00",
	      strokeWeight : 1 
      }
	  var circle = new google.maps.Circle( myOptions );
	  circle.setMap( map );
  }


  	//初始化加载数据
	function loadData( ) {
		$.ajax( {
			type :"POST",
			contentType :"application/json;utf-8",
			url :"LocationSeviceServlet",
			cache :false,
			dataType :"json",
			data:{},
			success : function( msg) {
				for( var i = 0 ; i < msg.length ; i++ ){
					initTaxiInfo( msg[i] , i ) ;
				}
			}
		});
	 
	}

	var markers = new Array();
	function initTaxiInfo( obj , i ){
	  var _latlng = obj.latLng ;
	  latlng = new google.maps.LatLng(_latlng.lat,_latlng.lng);
	  map.setZoom(13);
	  alert()
	  var image = 'image/taxi_icon_cn_32.png';
	  //注意：需要使用数组的形式才能够实现，点击某个marker显示某个marker的信息
	  markers[i] = new google.maps.Marker({
		  position: latlng, 
		  map: map ,
		  icon: image
	  }); 
	  //只创建一个infowindow，否则点击第二个的时候第一个infowindow不会消失
	  infowindow = new google.maps.InfoWindow();
	  //增加一个监听，当点击taxi图标时显示出租车信息
	  google.maps.event.addListener(markers[i], 'click', function(event) {
		  //定义显示
		  var _html  = 	'<b>taxiNo       :</b>' + obj.taxiNo +  '<br/>' +
						'<b>driverNo     :</b>' + obj.driverNo + '<br/>' +
						'<b>Taxi Status  :</b>' + obj.taxiStatus +'<br/>' + 
						'<b>Taxi Address :</b>' + obj.taxiAddress ;
		  
		  infowindow.setContent( _html );
		  infowindow.open(map, markers[i]);
	  });
	} 

</script>
</head>

<body onload="initialize()">
<div id="map_canvas" style="width: 1000px; height: 800px; border: 1px solid black;"></div>
</body>
</html>