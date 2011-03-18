此目录页面用于做工具库-按参数自动页面刷新功能

提供了:页面呈现技术器，按form表单提交方式进行刷新。
对应js：countPageRefresh.js。
修改说明：若是刷新方式修改，修改showCnt()方法即可。
定义刷新时间在countPageRefrsh.html的javascript段中。




若不考虑页面隐藏的查询或是其他条件，可以采用以下是几种手动页面刷新方法（可根据项目需要进行选择）：
<input type=button value=刷新 onclick="location.assign(location)">
<input type=button value=刷新 onclick="history.go(0)">
<input type=button value=刷新 onclick="location.reload()">
<input type=button value=刷新 onclick="location=location">
<input type=button value=刷新 onclick="location.assign(location)">
<input type=button value=刷新 onclick="document.execCommand('Refresh')"> 
<input type=button value=刷新 onclick="window.navigate(location)"> 
<input type=button value=刷新 onclick="location.replace(location)"> 
<input type=button value=刷新 onclick="window.open('自身的文件','_self')"> 


