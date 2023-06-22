<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guest/insertform.jsp</title>
</head>
<body>
	<div class="container">새 방명록 작성 폼</div>
	<form action="${pageContext.request.contextPath }/guest/insert" method= "post">
		
		<div>
			<label for="writer">작성자</label>
			<input type="text" name="writer" id="writer"/>
		</div>
		
		<div>
			<label for="content">내용</label>
			<textarea name="content" id="content" cols="30" rows="10"></textarea>
		</div>
		
		<div>
			<label for="pwd">비밀번호</label>
			<input type="password" name="pwd" id="pwd"/>
		</div>
		<button type="submit">추가</button>
	</form>
	
</body>
</html>