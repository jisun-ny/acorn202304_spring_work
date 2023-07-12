<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/info.jsp</title>
</head>
<body>
	<div class="container">
		<h1>개인정보 페이지</h1>
		<table>
			<tr>
				<th>아이디</th>
				<td>${sessionScope.id }</td>
				<!-- session에 담은건느 아무리 페이지를 옮겨다녀도 웹브라우절르 닫지 않는 이상 사용 가능
				로그인 컨트롤러에서 session.setAttribute("id", id);했기 떄문에 사용 가능 
				로그인요청을 할때 세션에 담았지만 info페이지에서도 사용 가능-->
			</tr>
			<tr>
				<th>주소</th>
				<td>${requestScope.address }</td>
				<!-- controller에서 Model model에 addAttribute해줬기 때문에 .
				그리고 view페이지로 forword 이동함. //테헤란로 14분 들어보자아
				응답하기 전까지 사용가능! (request, model, modelandview..등은 일회성 데이터 )
				-->
			</tr>
		</table>
	</div>
</body>
</html>