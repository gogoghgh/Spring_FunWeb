<%-- <%@page import="com.itwillbs.member.db.BoardDTO"%> --%>
<%@page import="java.util.List"%>
<%-- <%@page import="com.itwillbs.member.db.BoardDAO"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>noticeSearch.jsp</title>
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/subpage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<!-- 헤더들어가는 곳 (inc/top.jsp 파일로 뗐음!!) -->
		<jsp:include page="../inc/top.jsp"/>
		<!-- 헤더들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 메인이미지 -->
		<div id="sub_img_center"></div>
		<!-- 메인이미지 -->

		<!-- 왼쪽메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="#">Notice</a></li>
				<li><a href="#">Public News</a></li>
				<li><a href="#">Driver Download</a></li>
				<li><a href="#">Service Policy</a></li>
			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->
		
		
		


		<!-- 게시판 -->
		<article>
			<h1>Notice Search</h1>
			<table id="notice">
				<tr>
					<th class="tno">No.</th>
					<th class="ttitle">Title</th>
					<th class="twrite">Writer</th>
					<th class="tdate">Date</th>
					<th class="tread">Read</th>
				</tr>
				
				<c:forEach var="dto" items="${boardList }">
					<tr>
						<td>${dto.bno }</td>
						<td class="left"><a href="${pageContext.request.contextPath }/board/content?bno=${dto.bno }"> 
										${dto.subject } &nbsp; [   ] </a></td>
								 <c:set var="bno" value="${dto.bno }"/> 
								 
								
								<%
									// 제목 옆에 댓글 수 출력하기
									// bno를 매개변수로 넣어서 getCommentCount 메서드 호출해야 함!!!! 그걸 int cnt에 저장해야 함,,
									// bno는 어디에 있남? 
									// request영역에 있는 -> boardList 안에,, -> dto 안에,, -> bno
									
// 									BoardDAO dao = new BoardDAO();
// 									dao.getCommentCount(bno);
									
									// 일단 request 영역에서 bno 빼내기
// 									int bno = Integer.parseInt(request.getParameter("bno"));
// 									System.out.println("request에서 꺼내온 bno: " + bno);
									
						 			// getCommentCount(bno) <<<< 얘를 어케 가져오냐,, bno값을 딱 넣어서 메서드를 호출해야 하는디.....
						 			//
						 					
						// 			List<BoardDTO> boardList = request.getParameter("boardList"); // BoardListAction에서 request 영역에 얘를 저장해놨네!! 근데 안 되네ㅠ
// 									int cnt = dao.getCommentCount(${bno});
								
								%>
						<td>${dto.name }</td>
						<td><fmt:formatDate value="${dto.date }" pattern="yyyy.MM.dd"/> </td>
						<td>${dto.readcount }</td>
					</tr>					
				</c:forEach>
				
			</table>
			
			<div id="table_search">
				<c:if test="${!(empty sessionScope.loginID) }">
							<!-- 세션값이 비어있지 않으면~ == 로그인 했으면 ~~ 글쓰기 버튼 보이게 -->
					<input type="button" value="글쓰기" class="btn" onclick="location.href='${pageContext.request.contextPath }/board/write';">
				</c:if>
			</div>
			
			<div id="table_search">
				<form action="${pageContext.request.contextPath }/board/listSearch" name="fr" method="get">
							<!-- 검색어 입력하고 submit 누르면? 검색어값 가지고 BoardListSearch.bo로 간다~~ -->
					<input type="text" name="search" class="input_box"> 
					<input type="submit" value="search" class="btn">
				</form>
			</div>
			
			<!---------------------------------- 페이징 처리 ----------------------------------------->
			<div class="clear"></div>
			<div id="page_control">
				
				<c:if test="${vo.startPage gt vo.pageBlock }">
							<!-- startPage가 10보다 크면?! Prev 글자 보이게-->
					<a href="${pageContext.request.contextPath }/board/listSearch?pageNum=${vo.startPage - vo.pageBlock }&search=${vo.search}">Prev</a> 
				</c:if>
				
				
				<c:forEach var="i" begin="${vo.startPage }" end="${vo.endPage }" step="1">
					<a href="${pageContext.request.contextPath }/board/listSearch?pageNum=${i }&search=${vo.search}">${i }</a>
				</c:forEach>

				 
				<c:if test="${vo.endPage lt vo.pageCount }">
							<!-- endPage < pageCount보다 더 크면? ㄴㄴ less than,, 
							     다음에 넘어갈 페이지가 있다~ == Next 보여야 함 -->
					<a href="${pageContext.request.contextPath }/board/listSearch?pageNum=${vo.startPage + vo.pageBlock }&search=${vo.search}">Next</a> 
				</c:if>
			</div>
			
			
		</article>
		<!-- 게시판 -->
		<!-- 본문들어가는 곳 -->
		<div class="clear"></div>
		
		<!-- 푸터들어가는 곳 (inc/bottom.jsp 파일로 뗐음) -->
		<jsp:include page="../inc/bottom.jsp"/>
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>