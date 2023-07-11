<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/fortune.jsp</title>
</head>
<body>
	<p>
		오늘의 운세: ${fortuneToday }
		<!-- ${requestScope.fortuneTday }가 생략되어있는것. -->
	</p>
	
<!-- 만일 스프링을 사용하지 않고 그냥 jsp로 똑같이 표현한다면 -->
	<%
		String fortuneToday = (String)request.getAttribute("fortuneToday");
	%>	
	<p>
		오늘의 운세: <%=fortuneToday %>
	</p>
</body>
</html>