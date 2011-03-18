此目录页面用于做工具库-报表处理

用到了jasperreport第三方开源组件
该功能的核心类为com.btit.common.jasperreport.JasperReportHelper：实现了生成HTML、PDF、EXCEL格式的报表功能。
使用说明：
1.设计报表布局模板，可通过Ireport设计或编程实现；
2.构造数据源，有三种方式：JDBC的connection连接、数组、集合
3.构造报表需要的参数Map
4.构造JasperReportHelper实例
5.输出指定格式(HTML、PDF、EXCEL)的报表

其中演示例子：
　jasperjcondemo--报表布局模板通过Ireport设计，数据源使用JDBC的connection连接，包括三种类型的报表（图形报表、交叉报表、分组报表）
  subreportjcondemo--报表布局模板通过Ireport设计，数据源使用JDBC的connection连接的子报表
  jaspercolldemo--报表布局模板通过Ireport设计，数据源使用集合的例子
  jdesignjcondemo--报表布局模板通过编程实现，数据源使用JDBC的connection连接的例子

更多情况请查阅<<报表处理_工具文档说明.docx>>