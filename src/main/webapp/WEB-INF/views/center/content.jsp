<%@page import="java.util.List"%>
<%-- <%@page import="com.itwillbs.member.db.BoardDAO"%> --%>
<%-- <%@page import="com.itwillbs.member.db.CommentDTO"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write.jsp</title>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>

<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/subpage.css" rel="stylesheet" type="text/css">

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
			<h2>파일 업로드 하실 줄 아시는 분 구함!@@@@2 <br> 돈 드려요 ㅋ </h2>
<!-- 			<form action="./BoardWriteAction.bo" method="get"> -->
<!-- 										<input type="hidden" name="pass" value="1234"> -->
				<table id="notice">
					<tr>
						<td>글쓴이</td>
						<td>${dto.name } </td>
					</tr>
					<tr>
						<td>글 쓴 날짜</td>
						<td>${dto.date } </td>
					</tr>
					<tr>
						<td>조회수</td>
						<td>${dto.readcount } </td>
					</tr>
					<tr>
						<td>제목</td>
						<td>${dto.subject } </td>
					</tr>
					<tr>
						<td>내용</td>
						<td height="100px">${dto.content } </td>
					</tr>
					<tr>
						<td>파일💾🎉</td>
						
						<td>
							<c:if test="${dto.file ne null }" >  
								<a href="./upload/${dto.file }" download> 
								<img src="./upload/${dto.file }"> </a>
							</c:if>
						</td>
						
						
					</tr>

				</table>
				
				<div id = "table_search">
					<c:if test="${sessionScope.loginID eq dto.name }">
						<!-- 로그인 한 사람이랑 글쓴이가 일치하면~ ★★★★★★  -->
						<input type="button" value="글 수정" class="btn" 
								onclick="location.href='./BoardUpdate.bo?bno=${dto.bno}';">
						
						<input type="button" value="글 수정(파일)" class="btn" 
								onclick="location.href='./FileBoardUpdate.bo?bno=${dto.bno}';">
												
						<input type="button" value="글 삭제" class="btn" 
								onclick="location.href='./BoardDelete.bo?bno=${dto.bno}';">
					</c:if>
						<!-- 자기 글이든 아니든 글 목록 버튼은 다 보이게^^ -->
						<input type="button" value="글 목록" class="btn" onclick="location.href='./BoardList.bo';">
				</div>
				<br>
				<br>
				
				<!-- ----------------------- 댓글 작성 구간^^ 시작 --------------------------------- -->
				
				<!-- 댓글 수정, 삭제를 위하여 기존 값을 form 태그로 넘김 -->
<!-- 				<script type="text/javascript">
					function displaySet(exe, bno, name, cmtContent, cmd){
						// js: displaySet의 exe, bno, name, cmtContent, cmd 값을 받아 form태그로 넘긴다
						obj = document.frm;
						obj.bno.value = bno;
						obj.exe.value = exe; // exe: 2(댓글 수정), 3(댓글 삭제)
						obj.name.value = name;
						
						while(cmtContent.indexOf("<br>") != -1){
							cmtContent = cmtContent.replace("<br>", "\n");
						}
						obj.cmtContent.value = cmtContent;
						obj.cmd.value = cmd;
					}
				
				</script> -->

				<form action="./CommentWrite.bo" method="post" name="frm" >
					<!-- 댓글 수정, 삭제를 위한,, 파라메타.... -->
<!-- 					<input type="hidden" name="bno" value="1"> 댓글 수정, 삭제할 때 js 함수에서 value 변경,, -->
<!-- 					<input type="hidden" name="exe" value="1"> exe:1(댓글 추가) -->
					<input type="hidden" name="pageNum" value="${pageNum }"> <!-- 굳이 필요한감? -->
					<input type="hidden" name="bno" value="${dto.bno }">  <!-- bno : 메인 글의 bno!! (BoardDTO의 bno!!!!) 여기가 중요 ★★★-->
					
					<table>
						<tr>
							<th colspan="2"> 댓글</th>
						</tr>
						<tr>
							<td width="100"> 이름 </td>
							<td width="150"> <input type="text" name="name"> </td>
						</tr>
						<tr>
							<td> 내용 </td>
							<td> <textarea rows="5" cols="60" name="content"></textarea> </td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" value="댓글 작성" name="cmd">
								<input type="reset" value="리셋">
							</td>
						</tr>
					</table>
				</form>
				<!-- ----------------------- 댓글 작성 구간 끝^^ --------------------------------- -->
				
				<br>
				<hr>
				
				
				<!-- ----------------------- 댓글 리스트 구간 --------------------------------- -->
				<%
// 					BoardDAO dao = new BoardDAO();
// 					int bno = Integer.parseInt(request.getParameter("bno"));
// 					List<CommentDTO> cmtList = dao.getCommentList(bno);
// 					request.setAttribute("cmtList", cmtList);
				%>
				
						<input type="hidden" name="c_bno" value="${cdto.c_bno }">
				<c:forEach var="cdto" items="${cmtList }">
					<table width="60%" style="border: 1px solid gray">
						<tr>
							<td> name: ${cdto.name } </td>
							<td align="right"> <fmt:formatDate value="${cdto.date }" pattern="yyyy.MM.dd hh:mm"/>
						</tr>
						<tr height="60px">
							<td colspan="2"> content: <br> ${cdto.content } </td>
						</tr>
					</table>
<%-- 						<c:set var="cmtContent" value="${cdto.content }"/> --%>
<%-- 						<c:set var="cmtContent" value="${fn:replace(cmtContent,rn,'<br>') }" /> --%>
<!-- 						<span class="update"> -->
<%-- 							<a href="javascript:displaySet(2, '${cdto.bno }', '${cdto.name }', '${cmtContent }', '수정')">수정</a> --%>
<%-- 							<a href="javascript:displaySet(3, '${cdto.bno }', '${cdto.name }', '${cmtContent }', '삭제')">삭제</a> --%>
<!-- 						</span> -->
<!-- 					</div> -->
<!-- 					<div style="padding-top: 10px;"> -->
<%-- 						${cmtContent }  <!-- request.getAttribute("cmtList"); --> --%>
<!-- 					</div> -->
<%-- 						<span class="content">content: ${cdto.content }</span> --%>
<%-- 						<span class="date">date: ${cdto.date }</span> --%>
				</c:forEach>
						<input type="button" value="수정" onclick="location.href='./CommentUpdate.bo?c_bno=${cdto.c_bno}';">
						<input type="button" value="삭제" onclick="location.href='#';">
				
				<!-- ----------------------- 댓글 리스트 구간 끝^^ --------------------------------- -->
				
				
				
			<div class="clear"></div>
			<div id="page_control"></div>
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