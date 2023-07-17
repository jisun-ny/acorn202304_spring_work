package com.example.boot07.gallery.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot07.gallery.dao.GalleryDao;
import com.example.boot07.gallery.dto.GalleryDto;




@Service
public class GalleryServiceImpl implements GalleryService{

	@Autowired
	private GalleryDao dao;
	
	//파일 저장 경로
	@Value("${file.location}")
	private String fileLocation;

	@Override
	public void getList(HttpServletRequest request) {
		  //한 페이지에 몇개씩 표시할 것인지
	      final int PAGE_ROW_COUNT=8;
	      //하단 페이지를 몇개씩 표시할 것인지
	      final int PAGE_DISPLAY_COUNT=5;
	      
	      //보여줄 페이지의 번호를 일단 1이라고 초기값 지정
	      int pageNum=1;
	      //페이지 번호가 파라미터로 전달되는지 읽어와 본다.
	      String strPageNum = request.getParameter("pageNum");
	      //만일 페이지 번호가 파라미터로 넘어 온다면
	      if(strPageNum != null){
	         //숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
	         pageNum=Integer.parseInt(strPageNum);
	      }
	      
	      //보여줄 페이지의 시작 ROWNUM
	      int startRowNum = 1 + (pageNum-1) * PAGE_ROW_COUNT;
	      //보여줄 페이지의 끝 ROWNUM
	      int endRowNum = pageNum * PAGE_ROW_COUNT;
	      
	      //startRowNum 과 endRowNum  을 GalleryDto 객체에 담고
	      GalleryDto dto = new GalleryDto();
	      dto.setStartRowNum(startRowNum);
	      dto.setEndRowNum(endRowNum);
	      
	      //GalleryDao 객체를 이용해서 회원 목록을 얻어온다.
	      List<GalleryDto> list = dao.getList(dto);
	      
	      //하단 시작 페이지 번호 
	      int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
	      //하단 끝 페이지 번호
	      int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
	      
	      //전체 row 의 갯수
	      int totalRow = dao.getCount();
	      //전체 페이지의 갯수 구하기
	      int totalPageCount = (int)Math.ceil(totalRow / (double)PAGE_ROW_COUNT);
	      //끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
	      if(endPageNum > totalPageCount){
	         endPageNum = totalPageCount; //보정해 준다. 
	      }
	      
	      //request 영역에 담아주기
	      //ModelAndView에 담으나  request.setAttribute에 담으나 비슷함.
	      request.setAttribute("list", list);   //gallery list
	      request.setAttribute("startPageNum", startPageNum);   //시작 페이지 번호
	      request.setAttribute("endPageNum", endPageNum);   //끝 페이지 번호
	      request.setAttribute("pageNum", pageNum);   //현재 페이지 번호
	      request.setAttribute("totalPageCount", totalPageCount);   //모든 페이지 count

		
	}

	@Override
	public void saveImage(GalleryDto dto, HttpServletRequest request) {
	      //업로드된 파일의 정보를 가지고 있는 MultipartFile 객체의 참조값을 얻어오기
	      MultipartFile image = dto.getImage();
	      //원본 파일명 -> 저장할 파일 이름 만들기위해서 사용됨
	      String orgFileName = image.getOriginalFilename();
	      //파일 크기 -> 다운로드가 없으므로, 여기서는 필요 없다.
	      long fileSize = image.getSize();
	      
	    
	      //저장할 파일명 UUID문자열 + 원본 파일명
	      String saveFileName = UUID.randomUUID().toString() + orgFileName;
	      
	      
	      //db 에 저장할 저장할 파일의 상세 경로
	      String filePath = fileLocation + File.separator + saveFileName;
	      //이미지는 .png, .jpg...등 파일명이 다양하기 때문에 최대한 그걸 유지시키려고 뒤에 saveFileName을 붙여줌
	      //디렉토리를 만들 파일 객체 생성
	      File upload = new File(fileLocation);
	      if(!upload.exists()) {
	         //만약 디렉토리가 존재하지X
	         upload.mkdir();//폴더 생성
	      }
	     
	      try {
	         //upload 폴더에 파일을 저장한다.
	         image.transferTo(new File(filePath));
	         System.out.println();   //임시 출력
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      //dto 에 업로드된 파일의 정보를 담는다.
	      //-> parameter 로 넘어온 dto 에는 caption, image 가 들어 있었다.
	      //-> 추가할 것 : writer(id), imagePath 만 추가로 담아주면 된다.
	      //-> num, regdate : db 에 추가하면서 자동으로 들어감
	      String id = (String)request.getSession().getAttribute("id");
	      dto.setWriter(id);
	      //gallery 는 사진 다운 기능이 없다. -> orgFileName, saveFileName, fileSize 저장할 필요X
	      //imagePath 만 저장해주면 됨
	      //dto.setImagePath("/gallery/images/" + saveFileName);
	      
	      //DB에는 saveFileName만 저장하고 출력할 때 자세한 경로를 출력해준다.
	      dto.setImagePath(saveFileName);
	      
	      //gallery/list.jsp에서 src="${pageContext.request.contextPath }/gallery/images${tmp.imagePath}" /> 이걸 수정해줘야함.
	      //detail에도!!
	      
	      
	      //GalleryDao를 잉ㅇ해서 DB에 저장하기
	      dao.insert(dto);
	      /*
	       * 이미지는 사실 C:/acorn202304/upload에 저장되어있지만
	       * 이렇게 적으면 안됨! 그러면 본인의 컴푸터에서 찾을거아니여..
	       * <img src ="/gallery/images/abcd1234.png">
	       * 뒤에 파일 이름만 바꿔서 db에 저장하자
	       * 디비에 이걸 통채로 저장하기 위해서 dto에다가 넣어준것...  
	       * users에선 통채로 저장해봤으니까 여기선 파일명만 저장해보자
	       * 
	       */
	      
	      
	      
	      //GalleryDao 를 이용해서 DB 에 저장하기
	      dao.insert(dto);
		
	}

	@Override
	public void getDetail(ModelAndView mView, int num) {
		//dao로 해당 게시글 num에 해당하는 데이터(dto)를 가져온다.
		GalleryDto dto = dao.getData(num);
		//ModelAndView에 가져온 GalleryDto를 담는다.
		mView.addObject("dto", dto); //7개의 dto정보
		
	}


}
