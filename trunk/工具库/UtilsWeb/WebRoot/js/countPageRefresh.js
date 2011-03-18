// code for clock count down
c1=new Image(); c1.src="../images/c1.gif"
c2=new Image(); c2.src="../images/c2.gif"
c3=new Image(); c3.src="../images/c3.gif"
c4=new Image(); c4.src="../images/c4.gif"
c5=new Image(); c5.src="../images/c5.gif"
c6=new Image(); c6.src="../images/c6.gif"
c7=new Image(); c7.src="../images/c7.gif"
c8=new Image(); c8.src="../images/c8.gif"
c9=new Image(); c9.src="../images/c9.gif"
c0=new Image(); c0.src="../images/c0.gif"
cb=new Image(); cb.src="../images/cb.gif"

//var Counter = 30;
function showCnt( ) {
	if (!document.images)
		return false;

	if (document.getElementById("AutoFlash").value == 0)
		return false;

	Counter--;
	if (Counter < 0)
		return false;

	if (Counter == 0)
		document.forms[0].submit();  //页面刷新方式


	var strCount = String(Counter);
	var len = strCount.length;

	if (len == 3) {
		document.images["a"].src = eval("c" + strCount.charAt(0) + ".src")
		document.images["b"].src = eval("c" + strCount.charAt(1) + ".src")
		document.images["c"].src = eval("c" + strCount.charAt(2) + ".src")
	} else if (len == 2) {
		document.images["a"].src = eval("cb.src")
		document.images["b"].src = eval("c" + strCount.charAt(0) + ".src")
		document.images["c"].src = eval("c" + strCount.charAt(1) + ".src")
	} else if (len == 1) {
		document.images["a"].src = eval("cb.src")
		document.images["b"].src = eval("cb.src")
		document.images["c"].src = eval("c" + strCount.charAt(0) + ".src")
	}

	setTimeout("showCnt()", 1000)
}

function contral1( v ) {
		if (v == false) {
			document.getElementById("AutoFlash").value = 0;

		} else {
			document.getElementById("AutoFlash").value = 1;
			showCnt();
		}
}
