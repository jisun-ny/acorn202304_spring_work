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
		<h1>인덱스 페이지 입니다</h1>
		<ul>
			<li><a href="member/list">회원목록보기</a></li>		
			<li><a href="guest/list">방명록 보기</a></li>
		</ul>
		<!--  <img src="${pageContext.request.contextPath }/images/kim1.png"/> -->
		<!-- context경로/ -> webapp폴더 -->
		<!-- 이 요청도 DispatcherServlet로 감. 이미지를 처리하는 controller가 없움. 이런 애들은 resources 폴더에 넣어주자.
		images폴더를 resources에 넣어주기 -->
		<img src="${pageContext.request.contextPath }/resources/images/kim1.png"/>
		<h2>공지사항</h2>
		<ul>
			<c:forEach var="tmp" items = "${requestScope.noticeList }">
				<li>${tmp }</li>
			</c:forEach>
		</ul>
	</div>
	
	<!-- 멤버컨트롤러에 메소드 추가, views에 페이지 추가하면서 추가 삭제 버튼 만들어보기 -->
</body>
</html>