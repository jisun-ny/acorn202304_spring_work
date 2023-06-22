<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/insert</title>
</head>
<body>
	<script>
		alert("${param.writer}님이 작성한 방명록이 저장되었습니다.");
		location.href = "${pageContext.request.contextPath }/guest/list";
		//insertform에서 입력한 parameter를 controller를 거쳤더라도 응답하기 전까지 view페이지에서 el로 불러와 사용할 수 있따.
	</script>
	
</body>
</html>