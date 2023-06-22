<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/file/insertform.jsp</title>
</head>
<body>
	<div class="container">
	<form action="${pageContext.request.contextPath }/file/upload" method="post" enctype="multipart/form-data"> <!-- file 전송 방식은 여기가 좀 다름~ 알고있기~-->
		제목<input type="text" name="title"/> <br />
		첨부파일<input type="file" name="myFile"/> <br /> <!-- file 전송 방식 -->
		<button type="submit">업로드</button>
	</form>
	</div>
</body>
</html>