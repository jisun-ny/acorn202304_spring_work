<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>공지사항</h1>
		<ul>
			<c:forEach var="tmp" items = "${requestScope.noticeList }">
				<li>${tmp }</li>
				
			</c:forEach>
		</ul>
</body>
</html>
