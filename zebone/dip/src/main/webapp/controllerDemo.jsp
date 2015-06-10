<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Spring Controller 测试</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <form action="demo/requestBodyDemo.zb" method="post" target="result">
    	<input name="param" value="参数"/>
    	<input type="submit" value="@RequestBody与@ResponseBody注解测试"/>
    </form>
    
    <form action="demo/httpEntityDemo.zb" method="post" target="result">
    	<input name="param" value="参数"/>
    	<input type="submit" value="httpEntity测试"/>
    </form>
    
    <br/><br/><br/>
    <iframe id="result" name="result"></iframe>
  </body>
</html>
