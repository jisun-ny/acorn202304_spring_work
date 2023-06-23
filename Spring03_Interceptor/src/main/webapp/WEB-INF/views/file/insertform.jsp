<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/file/insertform.jsp</title>
<style>
	#profileForm{
		display:none;
	}
	#profileLink img{
		width:200px;
		height: 200px;
		border: 1px solid red;
		border-radius: 50%;
	}
</style>
</head>
<body>
	<div class="container">
	<h1>파일 업로드 폼 1</h1>
	<form action="${pageContext.request.contextPath }/file/upload" method="post" enctype="multipart/form-data"> <!-- file 전송 방식은 여기가 좀 다름~ 알고있기~-->		
		제목<input type="text" name="title"/> <br />
		첨부파일<input type="file" name="myFile"/> <br /> <!-- file 전송 방식 -->
		<button type="submit">업로드</button>
	</form>
	
	<h1>파일 업로드 폼 2</h1>
	<form action="${pageContext.request.contextPath }/file/upload2" method="post" enctype="multipart/form-data"> <!-- file 전송 방식은 여기가 좀 다름~ 알고있기~-->
		제목<input type="text" name="title"/> <br />
		첨부파일<input type="file" name="myFile"/> <br /> <!-- file 전송 방식 -->
		<button type="submit">업로드</button>
	</form>
	
	<!-- 
		이미지를 선택해서 업로드 버튼을 누르면 페이지 전환 없이 이미지를 업로드 하고
		업로드 된 파일의 정보를 응답(json) 받아서
		id가 imageWrapper인 div의 자식 요소에 img요소를 추가해서 업로드된 이미지가 바로 보이도록 프로그래밍 해보세요
		
		-webapp/resources/upload 폴더에 이미지를 저장하세요
		-gura_util.js를 webapp/resource/js 폴더에 넣어놓고 로딩해서 쓰면 됩니다.
		-/image/upload 요청 처리는 FileController에서 하면 됩니다.
	 -->
	<h1>이미지 업로드 폼</h1>
	<form action="${pageContext.request.contextPath }/image/upload" method= "post" enctype="multipart/form-data" id="uploadForm"> <!-- file 전송 방식은 여기가 좀 다름~ 알고있기~-->
		이미지<input type="file" name="image" accept=".jpg, .jpeg, .JPG, .JPEG, .gif, .png, .PNG"/> <br /> 
		<button type="submit">업로드</button>
	</form>
	<br />
	<div id="imageWrapper"></div>
	<div>
		<a id="profileLink" href="javascript:">
			프로필
		</a>
	</div>
	
	<form action="${pageContext.request.contextPath }/image/upload" 
	method= "post" enctype="multipart/form-data" id="profileForm"> <!-- file 전송 방식은 여기가 좀 다름~ 알고있기~-->
		이미지<input id="file" type="file" name="image" accept=".jpg, .jpeg, .JPG, .JPEG, .gif, .png, .PNG"/> <br /> 
	</form>
	
	<div>
		<img id="profileImage" src=""/>
	</div>
	<script src="${pageContext.request.contextPath }/resources/js/gura_util.js"></script>
	<script>
		//프로필 링크를 눌렀을 때 실행할 함수 등록
		document.querySelector("#profileLink").addEventListener("click", () => {
			document.querySelector("#file").click(); 
		});
		//링크를 누르면 input을 강제로 js로 클릭하는거! 
		//이렇게 설정하고 css러 #profileForm을 숨겨버리면 감쪽같이 숨길 수 있따
		
		//실제로 파일을 선택했을때 (change이벤트가 발생) 실행할 함수 등록
		document.quertSelector("#file").addEventListener("change", () => {
			 // 폼에 입력한 (선택한파일) 내용을 ajax 로 제출하기
            const form=document.querySelector("#profileForm");
            ajaxFormPromise(form)
            .then(res=>res.json())
            .then(data=>{
               const imgString=`<img src="${pageContext.request.contextPath }\${data.imagePath}">`;
               document.querySelector("#profileLink").innerHTML=imgString;
            });
         });
	
		document.querySelector("#uploadForm").addEventListener("submit", (e) => {
			//폼 전송 막기
			e.preventDefault();
			//gura_util.js에 있는 함수를 호출하면서 폼의 참조값 전달
			/*
			ajaxFormPromise(e.target) //구라 유틸이 호출되어있어야지만 이 함수를 쓸 수 있다.
			.then(res=>res.json())
			.then(data => {
				
			});
			*/
			//만일 gura_util을 사용하지 않는다면
			//서버에 전송할 data를 구성한다
			let data = new FormData(e.target);
			//fetch() 함수가 리턴하는 Promise 객체를
			fetch("${pageContext.request.contextPath }/image/upload", {
				method: "post",
				body: data
			})
			.then(res => res.json())
			.then(data => {
				console.log(data);
				//data는 {imagePath:"/resources/upload/xxx"} 형식의 object이다.
				const imgString = `<img src = "${pageContext.request.contextPath}\${data.imagePath}">`; // \를 해서 jsp가 data.imagePath를 그래도 출력하도록 해줌
				document.querySelector("#imageWrapper").innerHTML =imgString;
			});
		});
	</script>
	</div>
</body>
</html>