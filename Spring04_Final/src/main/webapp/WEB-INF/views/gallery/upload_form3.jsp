<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/gallery/upload_form</title>
</head>
<style>
#dropZone {
	width: 400px;
	height: 400px;
	border: 2px dashed red;
	border-radius: 20px;
	/*아래는 자식 contents를 상하 좌우로 가운데 정렬하기 위한 css*/
	display: flex;
	justify-content: center;
	align-items: center;
}

#preview {
	display: none; /* 일단 숨겨놓고 나중에 바꾸자 */
	object-fit: cover;
	width: 100%;
	height: 100%;
	border-radius: 20px;
}

#image {
	display: none;
}
</style>
<body>
	<div class="container">
		<h3>이미지 업로드 폼 입니다.</h3>
		<form action="${pageContext.request.contextPath }/gallery/upload"
			method="post" enctype="multipart/form-data">
			<div>
				<label for="caption">설명</label> <input type="text" name="caption"
					id="caption" />
			</div>
			<div>
				<label for="image">첨부 이미지</label> <input type="file" name="image"
					id="image" accept=".jpg, .jpeg, .png, .JPG, .JPEG" />
			</div>

		</form>
		<!-- drag and drop을 할 div -->
		<a href="javascript:" id="dropZoneLink" title="업로드 할 이미지 선택">
			<div id="dropZone">
				<p>이미지를 drag해서 drop 또는 여기를 클릭하세요.</p>
				<img src="" id="preview" />
			</div>
		</a>
		<button id="submitBtn">업로드</button>
		<!-- 버튼을 디자인을 위해 form 바깥으로 빠져나왔다면 아무리 놀러도 submit되지 않음. 이럴때는 자바스크립트를 활용해서 눌렀을 때 form을 강제로 제출 시킬 수 있다. -->
	</div>
	<script>
		//업로드 버튼을 눌렀을 때
		 document.querySelector("#submitBtn").addEventListener("click", ()=>{
			 //입력 내용 검증
			 //입력한 caption울 읽어온다
			 const caption = document.querySelector("#caption").value;
			 const files = document.querySelector("#image").files;
			 //만일 caption을 3글자 미만으로 입력하거나 선택한 파일이 없다면
			 if( caption.length < 3 || files.length == 0) {
				 alert("caption을 3글자 이상 입력하고 업로드할 이미지를 선택해 주세요")
				 return; //함수를 여기서 종료
			 }
			 
		//ajax 전송할 데이터를 FormData() 객체에 담는다.
		const data = new FormData();
		//caption 이라는 파라미터 명으로 input 요소에 입력한 문자열을 담는다. 
         data.append("caption", document.querySelector("#caption").value);
         //input 요소에 선택된 파일 데이터 얻어오기 
         const file=document.querySelector("#image").files[0];
         //image 라는 파라미터 명으로  현재 선택한 파일을 객체를 담는다.
         data.append("image", file);
         /*
            fetch() 함수를 호출하면서 method:"post", body:FormData 객체를 전달하면
            <form  method="post" enctype="multipart/form-data"> 폼을 전송한것과 같다
         */
         fetch("${pageContext.request.contextPath }/gallery/ajax_upload", {
            method:"post",
            body:data
         })
         .then(res=>res.json())
         .then(data=>{
            console.log(data);
            //추가
           	if(data.isSuccess){  		
           		alert(file.name+" 이미지를 성공적으로 업로드 했습니다.")
           		//UI를 초기 상태로 되돌리기
           		document.querySelector("#preview").style.display = none;
           		document.querySelector("#dropZone p").style.display = "block";
           		//input type = "file"을 reset 하는 방법 -> 빈 value ""를 넣어준다
           		document.querySelector("#image").value=""; //처음 상태로 만들기
           		document.querySelector("#caption").value="";
           	}
         });
      });

			
			//폼데이타 객체에 키벨루 키 밸류해서 데이터들을 담고 data를 바디에 담아서 패치에 전달 
			//응답할 때 (then) 제이슨으로 응답할 예정이면 res.json해준다
			//ajax_upload 요청에 대한 컨트롤러수정
		
	
		document.querySelector("#submitBtn").addEventListener("click", () => {
			document.querySelector("form").submit();
		});
		//dropzone을 클릭해도 파일창을 띄우도록 한다.
		//a를 클릭했을때 input id= "image"를 강제 클릭
		//이후 css를 이용해서 숨겨버린다.
		document.querySelector("#dropZoneLink").addEventListener("click", () => {
			document.querySelector("#image").click();
		});
		
		
		//이미지를 끌고 드랍존안에 가져오면 dragover
		//이미지를 딱 놓으면 drop --드랍만 등록할 수 없다. dragover와 drop은 세트! 
		//drop을 하면 파일을 열어주는게 디폴트기때문에 preventdefault도 필요하다.
		
		//dropZone div의 참조값 얻어오기
		const dropZone = document.querySelector("#dropZone");
		//이벤트 리스너 함수 등록하기
		dropZone.addEventListener("dragover", (e) => {
			e.preventDefault();			
		});
		dropZone.addEventListener("drop", (e) => {
			e.preventDefault();
			//어떤 파일이던간에 드랍된 파일의 정보는 e(이벤트 오브젝트)에 담겨있따. (e.dataTransger.files 가 들어있다.)--> 이걸 어떻게 읽어와서 활용할까?
			//drop 된 파일의 정볼르 얻어오기
			const files = e.dataTransfer.files;
			console.log(files[0]);
			//drop된 파일의 정보를 조사해서 이미지 파일이 아니라면 함수를 여기서 종료 시키기
			const type = files[0].type;
			if( type != "image/png" && type != "image/jpg" && type != "image/gif") {
				alert("이미지 파일을 drop하세요!");
				return;
			}
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
					document.querySelector("#preview").style.display="block";
					document.querySelector("#dropZone p").style.display="none";
				};
				//파일 DataURL 형식의 문자열로 읽어들이기
				reader.readAsDataURL(files[0]);		
				//dataURL: 이미지 파일을 문자열로 나타내고 나중에 디코딩을 해주면 다시 이미지를 불러올 수 있음
				
				//선택된 파일의 정보를 input type = "file" 요소에 넣어주기
				document.querySelector("#image").files = files;
			}
	
		});
		
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
					document.querySelector("#preview").style.display="block";
					document.querySelector("#dropZone p").style.display="none";
				};
				//파일 DataURL 형식의 문자열로 읽어들이기
				reader.readAsDataURL(files[0]);		
				//dataURL: 이미지 파일을 문자열로 나타내고 나중에 디코딩을 해주면 다시 이미지를 불러올 수 있음
				
				
				
			}
		});
	</script>
</body>
</html>