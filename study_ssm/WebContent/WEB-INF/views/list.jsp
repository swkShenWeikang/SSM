<%@page import="com.swk.model.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>library</title>
</head>
<body>
	<table border="2" align="center">
		<tr align="center">
			<th>图书ID</th>
			<th>图书名称</th>
			<th colspan="2">操作</th>
		</tr>
		<%
			List<Book> list = (List<Book>)request.getAttribute("list");
				
			for(Book book : list){
		%>
		<tr align="center">
			<td><%= book.getBookId() %></td>
			<td><%= book.getName() %></td>
			<td><a href="detail/<%=book.getBookId() %>">查看详情</a></td>
			<td>预约</td>
			<%-- <td><a href="appoint/12345678910/<%=book.getBookId() %>">预约</a></td> --%>
		</tr>

		<%
			}
		%>
	</table>
</body>
</html>