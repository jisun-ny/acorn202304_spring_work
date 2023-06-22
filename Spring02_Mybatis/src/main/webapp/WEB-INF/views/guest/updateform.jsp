<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/updateform.jsp</title>
</head>
<body>
	<div class="container">새 방명록 작성 폼</div>
	<form action="${pageContext.request.contextPath }/guest/update" method= "post">
		<div>
				<label for="num">번호</label>
				<input type="text" name="num" id="num" value="${dto.num}" readonly/>
													<!-- requestScope.dto.num 줄여서 표현-->
													<!--  dto라는 key값으로 guestDto type을 담았으니까 이런 코딩 가능 -->
		</div>
		
		<div>
			<label for="writer">작성자</label>
			<input type="text" name="writer" id="writer" readonly value="${dto.writer }"/>
		</div>
		
		<div>
			<label for="content">내용</label>
			<textarea name="content" id="content" row="10" value="${dto.content }"></textarea>
		</div>
		<div>
			<label for="pwd">비밀번호</label>
			<input type="password" name="pwd" id="pwd"/>
		</div>
		<button type="submit">수정</button>
	</form>
</body>
</html>