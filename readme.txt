<Resource
	name="yc"
	type="javax.sql.DataSource"
	auth="Container"
	driverClassName="oracle.jdbc.driver.OracleDriver"
	url="jdbc:oracle:thin:@127.0.0.1:1521:orcl"
	username="scott"
	password="a"
	autoReconnect="true"
	maxActive="120"
	maxIdle="30" />
	
	
--按用户方式导出数据
exp system/a@orcl file=d:\scott_back.dmp  owner=scott 

--按用户方式导入数据
imp system/a@orcl file=d:\scott_back.dmp ignore=y full=y   --ignore忽略创建错误。比如库中已存在表，但无数据，加上此参数即可导入数据而不会报错