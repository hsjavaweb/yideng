<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="register" method="post">
	用户名：<input type="text" name="username"/><br/>
	密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"/><br/>
	邮&nbsp;&nbsp;&nbsp;箱：<input type="text" name="email"/><br/>
	性&nbsp;&nbsp;&nbsp;别：<input type="radio" name="sex" value="男"/>男
		<input type="radio" name="sex" value="女"/>女<br/>
	爱&nbsp;&nbsp;&nbsp;好：<input type="checkbox" name="hobby" value="排球"/>排球
		<input type="checkbox" name="hobby" value="足球"/>足球
		<input type="checkbox" name="hobby" value="篮球"/>篮球<br/>
	<input type="submit" value="注册"/>
</form>
</body>
</html>