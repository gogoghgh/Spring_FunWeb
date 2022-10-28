<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/front.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/script/jquery-3.6.0.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('.brown').click(function(){
			alert('정상동작합니다^^');
		 // $.ajax(); // ajax함수 호출할거~
			$.ajax({
				url:'${pageContext.request.contextPath}/board/listJson',
				dataType:'json', // json 데이터 타입의 글^^ // 게시판 글 5개 들고올 거
				success:function(rData){
					// 성공적으로 json 데이터 들고 오면~ 그걸 rData에 담아서 5번 반복 시키기 -> 테이블에 뿌려줄 거~~
					$.each(rData, function(index, item){
						// item.date 숫자로 보여서,, 이렇게 1666061215000
						//  --> 문자로 변경해서 출력
						var dt = new Date(item.date);
						var d = dt.getFullYear() + ". " + (dt.getMonth()+1) + ". " + (dt.getDate()) + "." ;
								// 연도 뽑고             월 뽑고(0부터 시작이라 1 더함)     일 뽑고
						
						
						$('table').append( // 줄바꿈 안되네 ㄱ-
						'<tr><td class="contxt"><a href="#">' + item.subject + '</a></td><td>'+d+'</td></tr>');
					});// each
				}// success 
			});// ajax
			$(this).unbind(); // ajax 한번만 불러오고 끝낼 수 있도록 ^^
			
		});// click
		
		
		
	}); //jQuery ready
</script>
</head>
<body>
	<div id="wrap">
		<!-- 헤더 들어가는 곳 (inc/top.jsp 파일로 뗐음!!) -->
		<jsp:include page="../inc/top.jsp"/>
		<!-- 헤더 들어가는 곳 -->
		<!-- 메인이미지 들어가는곳 -->
		<div class="clear"></div>
		<div id="main_img">
			<img src="${pageContext.request.contextPath }/resources/images/main_img.jpg" width="971" height="282">
		</div>
		<!-- 메인이미지 들어가는곳 -->
		<!-- 메인 콘텐츠 들어가는 곳 -->
		<article id="front">
			<div id="solution">
				<div id="hosting">
					<h3>Web Hosting Solution</h3>
					<p>Lorem impsun Lorem impsunLorem impsunLorem impsunLorem
						impsunLorem impsunLorem impsunLorem impsunLorem impsunLorem
						impsun....</p>
				</div>
				<div id="security">
					<h3>Web Security Solution</h3>
					<p>Lorem impsun Lorem impsunLorem impsunLorem impsunLorem
						impsunLorem impsunLorem impsunLorem impsunLorem impsunLorem
						impsun....</p>
				</div>
				<div id="payment">
					<h3>Web Payment Solution</h3>
					<p>Lorem impsun Lorem impsunLorem impsunLorem impsunLorem
						impsunLorem impsunLorem impsunLorem impsunLorem impsunLorem
						impsun....</p>
				</div>
			</div>
			<div class="clear"></div>
			<div id="sec_news">
				<h3>
					<span class="orange">Security</span> News
				</h3>
				<dl>
					<dt>Vivamus id ligula....</dt>
					<dd>Proin quis ante Proin quis anteProin quis anteProin quis
						anteProin quis anteProin quis ante......</dd>
				</dl>
				<dl>
					<dt>Vivamus id ligula....</dt>
					<dd>Proin quis ante Proin quis anteProin quis anteProin quis
						anteProin quis anteProin quis ante......</dd>
				</dl>
			</div>
			<div id="news_notice">
				<h3 class="brown">News &amp; Notice</h3>
				<table>
<!--				<tr>
						<td class="contxt"><a href="#">Vivans....</a></td>
						<td>2012.11.02</td>
					</tr>
					<tr>
						<td class="contxt"><a href="#">Vivans....</a></td>
						<td>2012.11.02</td>
					</tr>
					<tr>
						<td class="contxt"><a href="#">Vivans....</a></td>
						<td>2012.11.02</td>
					</tr>
					<tr>
						<td class="contxt"><a href="#">Vivans....</a></td>
						<td>2012.11.02</td>
					</tr>
					<tr>
						<td class="contxt"><a href="#">Vivans....</a></td>
						<td>2012.11.02</td>
					</tr> -->
				</table>
			</div>
		</article>
		
		
		<!-- 메인 콘텐츠 들어가는 곳 -->
		<div class="clear"></div>
		
		
		<!-- 푸터들어가는 곳 (inc/bottom.jsp 파일로 뗐음) -->
		<jsp:include page="../inc/bottom.jsp"/>
		<!-- 푸터들어가는 곳 -->
	</div>
</body>
</html>