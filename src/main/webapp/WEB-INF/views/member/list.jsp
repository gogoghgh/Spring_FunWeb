<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/list.jsp</title>
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/subpage.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/script/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#btn').click(function(){
			alert('정상 작동 중');
			
			// ajax 써서 가상주소 가서 멤버 리스트 가져와서 출력하기
			$.ajax({
				url: '${pageContext.request.contextPath}/member/listJson',
				dataType: 'json', // 받아올 데이터 타입은? json
				success: function(rData){
					// rData :   json 타입의 배열 데이터!! -> 반복할거임
					$.each(rData, function(index, item){ 
										// item에 받아 와서 --> index 순서,, 0 1 2 ,,,
						// 본문 테이블 형태에 맞춰서 출력할거~~ 
						$('#notice').append('<tr><td>' + item.userid + '</td><td>' + item.username + '</td><td>' + item.useremail + '</td></tr>');
						
					}); // each == 반복문~~
				}
				
			});// ajax
			
			
		});// click
	});// jquery ready

</script>
</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는 곳 (inc/top.jsp 파일로 뗐음!!) -->
		<jsp:include page="../inc/top.jsp"/>
		<!-- 헤더 들어가는 곳 -->

		<!-- 본문들어가는 곳 -->
		<!-- 본문메인이미지 -->
		<div id="sub_img_member"></div>
		<!-- 본문메인이미지 -->
		<!-- 왼쪽메뉴 -->
		<nav id="sub_menu">
			<ul>
				<li><a href="${pageContext.request.contextPath }/member/insert">Join us</a></li>
				<li><a href="#">Privacy policy</a></li>
			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->
		<!-- 본문내용 -->
		<article>
			<h1>Member List</h1>
			
			<input type="button" value="회원 목록" id="btn">
			
			<table id="notice" >
				<tr style="font-weight: bold;">
					<td>userid</td>
					<td>username</td>
					<td>useremail</td>
				</tr>
			</table>
			
			
		</article>
		<!-- 본문내용 -->
		<!-- 본문들어가는 곳 -->

		<div class="clear"></div>
		<!-- 푸터들어가는 곳 (inc/bottom.jsp 파일로 뗐음) -->
		<jsp:include page="../inc/bottom.jsp"/>
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>