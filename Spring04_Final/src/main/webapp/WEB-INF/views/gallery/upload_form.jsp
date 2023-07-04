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
	<form action="${pageContext.request.contextPath }/gallery/upload" method="post" 
	enctype="multipart/form-data">
		<div>
			<label for="caption">설명</label>
			<input type="text" name="caption" id="caption"/>
		</div>
		<div>
			<label for="image">첨부 이미지</label>
			<input type="file" name="image" id="image"
			accept=".jpg, .jpeg, .png, .JPG, .JPEG" />
		</div>
		<button type="submit">업로드</button>	
		<br />
		<img src="" alt="이미지 미리보기" id="preview" />
	</form>
	</div>
	<script>
		document.querySelector("#image").addEventListener("change", (e) => {
			//선택된 파일 배열 객체를 얻어낸다.(files는 배열이다)
			const files = e.target.files;
			console.log(files);
			//만일 파일 데이터가 존재한다면
			if(files.length > 0){
				//파일로부터 데이터를 읽어들일 객체 생성
				const reader = new FileReader();
				//로딩이 완료 되었을 때(파일 데이터를 모두 읽었을 때) 실행할 함수 등록
				reader.onload = (event) => {
					//읽은 파일 데이터 얻어내기
					const data = event.target.result;
					//console.log(data);
					//이미지 요소에 data를 src 속성의 value로 넣어보세요
					document.querySelector("#preview").setAttribute("src", data);
				};
				//파일 DataURL 형식의 문자열로 읽어들이기
				reader.readAsDataURL(files[0]);		
				//dataURL: 이미지 파일을 문자열로 나타내고 나중에 디코딩을 해주면 다시 이미지를 불러올 수 있음
			}
		});
	</script>
</body>
</html>