package com.example.boot07.gallery.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok을 사용해서 Dto 만들기
@Alias("galleryDto")
@Data
@Builder // GalleryDto.builder().num(1).wrtier("lim").caption("title")... .build(); 
								//GallertDtoBuilder type (.build() 전까지)
@NoArgsConstructor
@AllArgsConstructor
public class GalleryDto {

	//table column
	private int num;
	private String writer;
	private String caption;
	private String imagePath;
	private String regdate;
	//페이징 처리
	private int startRowNum;
	private int endRowNum;
	//다음글/이전글
	private int prevNum; //이전 글의 글번호
	private int nextNum; //다음 글의 글번호
	//
	private MultipartFile image; //이미지 파일 업로드 처리를 위한 필드
	
	/*폼 만들때
	인풋 name="caption">
	인풋 name="image"할 거기 떄문에
	*/
	
	
}