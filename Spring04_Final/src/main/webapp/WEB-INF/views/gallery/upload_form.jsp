<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/gallery/upload_form</title>
</head>
<body>
	<div class="container">
	<h3>이미지 업로드 폼 입니다.</h3>
	<form action="upload" method="post" enctype="multipart/form-data">
		<div>
			<label for="title">제목</label>
			<input type="text" name="title" id="title"/>
		</div>
		<div>
			<label for="title">설명</label>
			<input type="text" name="caption" id="caption"/>
		</div>
		<div>
			<label for="im">첨부 이미지</label>
			<input type="file" name="imagePath" id="imagePath"/>
		</div>
		<button type="submit">업로드</button>
		
	</form>
	</div>
</body>
</html>