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
	<div align="center">
		<h1>日志列表页面</h1>
		<h1><%=new Date()%></h1>
		<table width="100px" border="1" cellpadding="3" cellspacing="0">
			<thead>
				<tr>
					<th>id</th>
					<th>username</th>
					<th>operation</th>
				</tr>
			</thead>
			<tbody id="tbodyId">
				<tr>
					<td colspan="3">正在加载...</td>
				</tr>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
		window.onload = function(){
			/* doGetObjects(); */
			doPostObjects();
		}
		
		function doPostObjects(){
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4 && xhr.status==200){
					console.log(xhr.responseText);
					doHandleResponseResult(xhr.responseText);
				}
			}
			var url="doFindPageObjects.do";
			xhr.open("POST", url, true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.send("pageCurrent=1");
		}
		
		function doGetObjects(){
			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange=function(){
				if(xhr.readyState==4 && xhr.status==200){
					console.log(xhr.responseText);
					doHandleResponseResult(xhr.responseText);
				}
			}
			var url="doFindPageObjects.do?pageCurrent=1";
			xhr.open("GET", url, true);
			xhr.send(null);
		}
		
		function doHandleResponseResult(text){
			var result = JSON.parse(text)
			if(result.state==1){
				doSetTableBodyRows(result.data.records);
			}else{
				alert(result.message);
			}
		}
		
		function doSetTableBodyRows(records){
			var tBody = document.getElementById("tbodyId");
			tBody.innerHTML = "";
			for(var i=0;i<records.length;i++){
				var tr = document.createElement("tr");
				var td = document.createElement("td");
				td.innerText = records[i].id;
				tr.appendChild(td);
				
				td = document.createElement("td");
				td.innerText = records[i].username;
				tr.appendChild(td);
				
				td = document.createElement("td");
				td.innerText = records[i].operation;
				tr.appendChild(td);
				
				tBody.appendChild(tr);
			}
			
		}
	</script>
</body>
</html>