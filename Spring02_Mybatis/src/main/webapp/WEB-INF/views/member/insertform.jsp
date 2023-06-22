<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/member/insertform.jsp</title>
</head>
<body>
	<div class="container">회원추가 폼 입니다</div>
	<form action="${pageContext.request.contextPath }/member/insert" method="post">
		<div>
			<label for="name">이름</label>
			<input type="text" name="name" id="name" />
		</div>
		
		<div>
			<label for="addr">주소</label>
			<input type="text" name="addr" id="addr" />
		</div>
		<button type="submit">추가</button>
	</form>
</body>
</html>