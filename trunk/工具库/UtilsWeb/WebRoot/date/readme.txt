此目录页面用于做工具库-日期/日期时间编辑框

calendar.js提供了两种方式的日期选择。
calendar('yyyy-mm-dd',false);  //日期编辑框
calendar('yyyy-mm-dd',true);   //日期时间编辑框


----------------------------------------------
calendar_all.js
本日历选择控件由tiannet根据前人经验完善而得。大部分代码来自meizz的日历控件。
tiannet添加了时间选择功能、select,object标签隐藏功能，还有其它小功能。
使用方法：
 (1)只选择日期     <input type="text" name="date"     readOnly onClick="setDay(this);">
 (2)选择日期和小时    <input type="text" name="dateh"    readOnly onClick="setDayH(this);">
 (3)选择日期和小时及分钟 <input type="text" name="datehm" readOnly onClick="setDayHM(this);">
设置参数的方法
 (1)设置日期分隔符      setDateSplit(strSplit);默认为"-"
 (2)设置日期与时间之间的分隔符    setDateTimeSplit(strSplit);默认为" "
 (3)设置时间分隔符      setTimeSplit(strSplit);默认为":"
 (4)设置(1),(2),(3)中的分隔符    setSplit(strDateSplit,strDateTimeSplit,strTimeSplit);
 (5)设置开始和结束年份      setYearPeriod(intDateBeg,intDateEnd)
说明：
 默认返回的日期时间格式如同：2005-02-02 08:08
