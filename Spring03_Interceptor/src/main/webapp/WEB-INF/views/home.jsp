<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/home.jsp</title>
</head>
<body>
	<div class="container">
		<c:if test="${not empty sessionScope.id}" >
			<p>
				<strong>${id }</strong>님 로그인 중...
			</p>
		</c:if>
		<ul>
			<li><a href="${pageContext.request.contextPath }/play">놀러가기</a></li>
			<li><a href="${pageContext.request.contextPath }/users/loginform">로그인</a></li>
			<li><a href="${pageContext.request.contextPath }/users/logout">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath }/users/info">개인정보(로그인이 필요)</a></li>
			<li><a href="${pageContext.request.contextPath }/file/insertform">파일 업로드 테스트</a></li>
		</ul>
		<!-- hello라는 패키지에 있는 fortune이라는 서블릿을 호출하는 것.
		jsp파일을 만들면 servlet으로 변환 -->
		<h2>공지사항</h2>
		<ul>
			<c:forEach var="tmp" items = "${requestScope.noticeList }">
				<li>${tmp }</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>