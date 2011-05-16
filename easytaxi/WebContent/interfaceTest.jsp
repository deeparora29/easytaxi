<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口测试</title>
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript"><!--
	function callService(){
		var data = $("#requestdata").val();
		$.ajax( {
			type :"GET",
			contentType :"application/json;utf-8",
			url :"passenger",
			//url :"taxi",
			cache : false,
			//dataType :"json",
			data : { data : data },
			success : function( msg) {
				//alert(msg.ErrorCode);
				$("#responsedata").val(msg);
			}
		});

	}
</script>
</head>
<body>
	<table>
		<tr>
			<td>request data</td>
			<td><textarea rows="5" cols="100" name="requestdata" id="requestdata"></textarea></td>
		</tr>
		<tr>
			<td>request data</td>
			<td><textarea rows="3" cols="100" name="responsedata" id="responsedata"></textarea></td>
		</tr>
		<tr>
			<td><input type="button" name="t_button" value="CALL_SERVER" onclick="callService()"/></td>
		</tr>
	</table>
		<ul>
			<li>
				<b>P001</b>
				<p>{TransCode:'P001',phone:'13012345678',email:'anne.mian.ren@gmail.com',password:'123',
					nickname:'anne',firstname:'Ren',lastname:'Mian',gender:'female',province:'四川',	city:'成都',
					agreement:'yes',descr:'for test passenger'}
				</p>
			</li>
			<li>
				<b>P002</b>
				<p>{TransCode:'P002',account:'anne.mian.ren@gmail.com',password:'123'}</p>
			</li>
			<li>
				<b>P003</b>
				<p>{TransCode:'P003',userid:'P00001',phone:'13012345678',userGPS:{lat:46.66765, lng: 124.74645, text:'成都市高新区管委会'},
					dstGPS:{lat:45.3553, lng:138.44563, text:'ABC'},number:2,luggage:1,comments:'sponse a call taxi',share:'yes'}
				</p>
			</li>
			<li>
				<b>P004</b>
				<p>{TransCode:'P004',userid:'P00001',requestNo:'201104062201'}</p>
			</li>
			<li>
				<b>P005</b>
				<p>{TransCode:'P005',userid:'P00001',requestNo:'201104062201',comments:'I want to cancel it.'}</p>
			</li>
			<li>
				<b>P006</b>
				<p>{TransCode:'P006',userid:'P00001',requestNo:'201104062201',credit:4.5,comments:'good service'}</p>
			</li>
			<li>
				<b>P007</b>
				<p>{TransCode:'P007',userid:'P00001',cab:'川A123456',number:'5'}</p>
			</li>
			<li>
				<b>P008</b>
				<p>{TransCode:'P008',userid:'P00001',cab:'川A123456'}</p>
			</li>
			<li>
				<b>P009</b>
				<p>{TransCode:'P009',userid:'P00001',userGPS:{lat:45.3424, lng: 153.8984}}</p>
			</li>
			<li>
				<b>P010</b>
				<p>{TransCode:'P010',userid:'P00001',cab:'川A123456'}</p>
			</li>
			<li>
				<b>P011</b><p>{TransCode:'P011',userid:'P00001',phone:'13909876543'}</p>
			</li>
			
			<li>
				<b>T001</b>
				<p>{TransCode:'T001',cab:'川A123456',password:'123456',license:'0123444',company:'Taxi Co.',email:'test@taxi.com',carModel:'Auto Das.',
				chargeModel:'pay cash',drivers:[{name:'A司机', phone:'13912345678'}, {name:'B司机', phone:'13912345611'}],descr:'test taxi'}
				</p>
			</li>
			<li>
				<b>T002</b>
				<p>{TransCode:'T002',account:'anne.mian.ren@gmail.com',password:'123'}</p>
			</li>
			<li>
				<b>T003</b>
				<p>{TransCode:'T003',userid:'T00005',cab:'川A12345',status:0,cabGPS:{ lat:43.1243, lng:58.0902X }}</p>
			</li>
			<li>
				<b>T004</b>
				<p>{TransCode:'T004',userid:'T00005',cabGPS:{lat:32.5767, lng:54.3432},requestNo:'2011040522202'}}</p>
			</li>
			<li>
				<b>T005</b>
				<p>{TransCode:'T005',userid:'T00005',requestNo:'2011040522202',comments:'cancel le'}</p>
			</li>
			<li>
				<b>T006</b>
				<p>{TransCode:'T006',userid:'T00005',requestNo:'201104142020',credit:4.5,comments:'test comments……'}</p>
			</li>
			<li>
				<b>T007</b>
				<p>{TransCode:'T007',userid:'T00005',passengerid:'P00001',number:'5'}</p>
			</li>
			<li>
				<b>T008</b>
				<p>{TransCode:'T008',userid:'T00005',passengerid:'P00001'}</p>
			</li>
			<li>
				<b>T009</b>
				<p>{TransCode:'T009',userid:'T00005',requestNo:'201104062202'}</p>
			</li>
			<li>
				<b>T010</b>
				<p>{TransCode:'T010',userid:'T00005',passengerid:'P00001'}</p>
			</li>
			
			<li>
				<b>T011</b>
				<p>{TransCode:'T011',userid:'T00005',phone:'13911111111'}</p>
			</li>
			<li>
				<b>T012</b>
				<p>{TransCode:'T012',userid:'T00005',status:0 }</p>
			</li>
			
		</ul>
</body>
</html>