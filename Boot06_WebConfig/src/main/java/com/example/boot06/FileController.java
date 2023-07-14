package com.example.boot06;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

	@Value("${file.location}")
	private String fileLocation;

	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(String orgFileName, String saveFileName, long fileSize)
																	// 필요한 정보 매개변수로 받아와줌
			throws UnsupportedEncodingException, FileNotFoundException {

		// 원래는 DB 에서 읽어와야 하지만 지금은 다운로드해줄 파일의 정보가 요청 파라미터로 전달된다.

		// 다운로드 시켜줄 원본 파일명
		String encodedName = URLEncoder.encode(orgFileName, "utf-8");
		// 파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
		encodedName = encodedName.replaceAll("\\+", " ");
		// 응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
		HttpHeaders headers = new HttpHeaders();
		// 파일을 다운로드 시켜 주겠다는 정보
		headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
		// 파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encodedName);
																			//파일의 원래 이름을 전달해준다 -> 웹브라우저가 이 이름을 참고해서 다운로드할때 원본이름으로 다운로드 시켜준다.
		// 파일의 크기 정보도 담아준다.
		headers.setContentLength(fileSize);

		// 읽어들일 파일의 경로 구성
		String filePath = fileLocation + File.separator + saveFileName;
		// 파일에서 읽어들일 스트림 객체
		InputStream is = new FileInputStream(filePath);
		// InputStreamResource 객체의 참조값 얻어내기
		InputStreamResource isr = new InputStreamResource(is);

		// ResponseEntity 객체의 참조값 얻어내기 //파일다운할떄는 ResponseEntity 객체 사용
		ResponseEntity<InputStreamResource> resEn = ResponseEntity.ok().headers(headers)
				.header("Content-Transfer-Encoding", "binary").body(isr);

		return resEn;

		// 바디에 넣어주는 타입이 ResponseEntity객체의 제너릭 --> 즉, 리턴 타입 (인풋스트림리소스
		// 스프링에서는 파일다운로드 뷰에다가 다운시켜줬지만 부트에서는 그냥 ResponseEntity를 사용하면 된다.
		//스프링 프레임워크가 ResponseEntity를 받아서 알아서 헤더를 요청하고 어쩌꼬저쩌고 해서 자동으로 알아서 다운로드 받아줌
	}

	@GetMapping("/file/uploadform")
	public String uploadform() {
		return "file/uploadform";
	}

	@PostMapping("/file/upload")
	public String upload(FileDto dto, Model model) { // 업로드 된 정보가 여기 들어옴
		/*
		 * dto에 있는 정보를 이용해서 C:/acorn202304/upload 폴더에 업로드 된 파일을 저장해보세요.
		 * 
		 * 저장되는 파일명은 UUID+원본파일명으로 만들어주세요
		 */
		// 업로드 된 파일의 정보를 가지고 있는 MultipartFile객체의 참조값 얻어오기
		MultipartFile myFile = dto.getMyFile();// dto는 업로드된 파일 정보를 가지고있따
		// 원본파일명
		String orgFileName = myFile.getOriginalFilename();
		// 파일의 크기
		long fileSize = myFile.getSize();
		// 저장할 파일명 구성하기
		String saveFileName = UUID.randomUUID().toString(); // 여기선 원본 파일명 더해줄 필요없다

		String filePath = fileLocation + File.separator + saveFileName;
		// UUID로 만들어서 저장해놓은 파일 이름
		try {
			// 원하는 경로에 파일 저장하기
			myFile.transferTo(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 원래는 DB에 저장해야하지만 테스트를 위해 view 페이지에 전달하기
		model.addAttribute("orgFileName", orgFileName);
		model.addAttribute("saveFileName", saveFileName);
		model.addAttribute("fileSize", fileSize);

		return "file/upload";
	}
}
