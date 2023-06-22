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
			<li><a href="${pageContext.request.contextPath }/member/insertform">파라미터 추출 테스트</a></li>
			<li><a href="${pageContext.request.contextPath }/fortune">오늘의 운세보기1</a></li>
			<li><a href="${pageContext.request.contextPath }/fortune2">오늘의 운세보기2</a></li>
			<li><a href="${pageContext.request.contextPath }/fortune3">오늘의 운세보기3</a></li>
			<li><a href="${pageContext.request.contextPath }/member/delete?num=1">작업 후 redirect 응답받기1</a></li>
			<li><a href="${pageContext.request.contextPath }/member/delete?num=2">작업 후 redirect 응답받기2</a></li>
																	<!--  get 방식 파라미터 num이 전송 됨. -->
			<li><a href="${pageContext.request.contextPath }/test/json1">JSON 응답 받기</a></li>
			<li><a href="${pageContext.request.contextPath }/test/json2">JSON 응답 받기2</a></li>
			<li><a href="${pageContext.request.contextPath }/test/json3">JSON 응답 받기3</a></li>			
			<li><a href="${pageContext.request.contextPath }/test/json4">JSON 응답 받기4</a></li>
			<li><a href="${pageContext.request.contextPath }/test/json5">JSON 응답 받기5</a></li>
			<li><a href="test/json6">JSON 응답 받기6</a></li>		
						<!-- 상대경로 -->															
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