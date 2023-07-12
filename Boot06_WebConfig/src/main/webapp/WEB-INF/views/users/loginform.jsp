<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/loginform.jsp</title>
</head>
<body>
	<div class="container">
		<form action="${pageContext.request.contextPath }/users/login" method ="post">
		<!-- 상대경로 써도 되고, 헷갈리면 절대경로로써주기 ${pageContext.request.contextPath }/users/login -->
			<input type="text" name="id" placeholder="아이디입력..." />
			<!-- 비밀번호 생략 그냥 예시니까.. -->
			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>