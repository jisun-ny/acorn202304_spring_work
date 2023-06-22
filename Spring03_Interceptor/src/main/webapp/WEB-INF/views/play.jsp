<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/play.jsp</title>
</head>
<body>
	<div class="container">
		<p>신나게 놀아요</p>
		<p>request 영역에 msg라는 키값으로 저장된 문자열 : <strong>${msg }</strong></p>
		<a href="${pageContext.request.contextPath }/">인덱스로</a>
		
		<!-- 지금 컨트롤러에선 /play요청에 대해서는 그냥 play.jsp페이지 리턴해놨기 떄문에 아무것도 뜨지 않음
			interceptor(preHandle)을 사용하면 요청을 컨트롤러에서 처리하여 jsp페이지를 응답해주기 전에 msg값을 받을 수 있음.
		 -->
	</div>
</body>
</html>