<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/list.jsp</title>
</head>
<body>
	<a href="${pageContext.request.contextPath }/guest/insertform">방명록 추가</a>
	<h1>방명록 입니다</h1>
	<table>
		<thead>
			<tr>
				<th>글번호</th>
	            <th>작성자</th>
	            <th>내용</th>
	            <th>등록일</th>
	            <th>수정</th>
				<th>삭제</th>
	           
			</tr>
		</thead>
		<tbody>
		<!-- 출력할 데이터를 jsp페이지가 안가져와도 됨! 이미 request영역에 담겨있음!! modelandview가 자동으로 넣었자나..  -->
			<c:forEach var="tmp" items="${list }">
									<!-- requestScope.list 생략 표현 -->
									<!--${list } 는 List<GuestDto>이다. 즉 tmp는  List<GuestDto> type이라는 것을 바로 알 수 있다.-->
				<tr>
					<td>${tmp.num }</td>
					<td>${tmp.writer }</td>					
					<td><textarea rows="5">${tmp.content }</textarea></td>					
					<td>${tmp.regdate }</td>
					<td><a href="updateform?num=${tmp.num }">수정</a></td>
                  	<td>
	                    <form action="delete" method="post">
	                       <input type="hidden" name="num" value="${tmp.num }"/>
	                       <input type="password" name="pwd" placeholder="비밀번호..."/>
	                       <button type="submit">삭제</button>
	                    </form>
                  	</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>