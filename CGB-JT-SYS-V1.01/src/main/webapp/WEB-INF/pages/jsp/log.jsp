<%@page import="java.util.Date"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>日志列表页面</h1>
	<h1><%=new Date()%></h1>
	<div>
		<table width="100px" border="1" cellpadding="3" cellspacing="0">
			<thead>
				<tr>
					<th>id</th>
					<th>username</th>
					<th>operation</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ pageObject.records }" var="item">
					<tr>
						<td>${ item.id }</td>
						<td>${ item.username }</td>
						<td>${ item.operation }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>