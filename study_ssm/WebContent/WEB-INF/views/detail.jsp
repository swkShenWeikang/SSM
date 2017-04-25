<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Detail</title>
</head>
<body>
	<table align="center" border="2">
		<tr align="center">
			<th colspan="2">图示详情</th>
		</tr>
		<tr align="center">
			<th>图书ID</th><td>${requestScope.book.bookId }</td>
		</tr>
		<tr align="center">
			<th>图书名称</th><td>${requestScope.book.name }</td>
		</tr>
		<tr align="center">
			<th>馆藏数量</th><td>${requestScope.book.number }</td>
		</tr>
	</table>
</body>
</html>