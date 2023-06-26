<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/loginform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>로그인 폼 입니다.</h1>
		<form action="${pageContext.request.contextPath}/users/login" method="post">
			<c:choose>
				<c:when test="${ empty param.url }">
					<input type="hidden" name="url" value="${pageContext.request.contextPath}/"/> <!-- url정보가 없다면 최상위 경로로 -->
				</c:when>
				<c:otherwise>
					<input type="hidden" name="url" value="${param.url }"/> <!-- url정보가 있다면 가져가자!! //로그인 성공이냐 아니냐? -->
				</c:otherwise>
			</c:choose>
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id"/>
			</div>
			<div>
				<label for="pwd">비밀번호</label>
				<input type="password" name="pwd" id="pwd"/>
			</div>
			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>