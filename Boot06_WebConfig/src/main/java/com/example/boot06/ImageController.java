package com.example.boot06;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


//이미지를 응답한다고 웹브라우저한테 알려준다고 응답..? 
//웹브라우저에게 전달하는 바이트가 응답하기전에 이건 이미지에 대한 바이트야~ 라는 정보를 주면서 바이트를 전달해주면 스프링프레임워크가 알아서 작업을 해줘서 웹브라우저가 사진을 응답 받을 수 있도록 해줌..?
@Controller
public class ImageController {
	//application.properties 파일에 있는 정보 얻어내기 --> 파일 로케이션 읽어내기
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping(
			value = "/aaa/{imageName}",
			produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE,
					MediaType.IMAGE_GIF_VALUE}
			) //경로변수 //이미지 이름은 그때그때 달라질 텐데 이걸 {ImageName}으로 받겠다.(pathvaluable)
	@ResponseBody
	public byte[] getImage(@PathVariable("imageName") String imageName) throws IOException { // @PathVariable: 업로드된 사진 이름 {imagename}을 String imagename으로 받아 온다.
		//imageName에는 응답해줄 이미지의 이름이 들어있다.
			
		//읽어들일 파일 경로
		// C:/acorn202304/upload/kim1.png 형식의 경로 (/는 역슬래쉬가 오류나서 대신 /쓴거임 원래는 역슬래쉬)
		String absolutePath = fileLocation + File.separator + imageName;
		//					C:/acorn202304/upload   	/					/kim1.png	
		
		//파일에서 읽어들일 InputStream
		InputStream is = new FileInputStream(absolutePath); //FileInputStream에서 빨대를 꽂은다음?? 
		
		//fileLocation 필드에는 파일에 저장되어있는 서버의 파일 시스템상에서의 위치가 들어있다.
		return IOUtils.toByteArray(is);
		//IOUtils.toByteArray해서 (인풋된 인풋스트림을 바이트 데이터로 리턴)
		//return IOUtils.toByteArray(is); 리턴된 바이트데이터를 리턴
		
	}
}
