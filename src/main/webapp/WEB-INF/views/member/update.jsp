<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update.jsp</title>
<link href="${pageContext.request.contextPath }/resources/css/default.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/subpage.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/script/jquery-3.6.0.js"></script>
<script type="text/javascript">
/* 	$(document).ready(function(){
		// 필수 입력 대상들에 이벤트 걸어 걸어
		// id=join인 대상에 -> submit 이벤트
		$('#join').submit(function(){
// 			alert("전송 잘 되나 테서터");
			if($('.id').val()==""){
				//class=id인 대상의 -> value값이 -> 공백이랑 같냐?
				alert('아이디를 입력해주세요');
				$('.id').focus(); // id 칸에 커서 깜빡이게
				return false; // submit 하지 마라~~ 막아주고
			} // if
			
			if($('.pw').val()==""){
				alert('비번을 입력해주세요');
				$('.pw').focus();
				return false;
			}
			
			if($('.name').val()==""){
				alert('이름을 입력해주세요');
				$('.name').focus();
				return false;
			}
			
			if($('.email').val()==""){
				alert('이메일을 입력해주세요');
				$('.email').focus();
				return false;
			}
			
		});// submit
		
	});// ready

 */
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
				<li><a href="#">Update us</a></li>
				<li><a href="#">Privacy policy</a></li>
			</ul>
		</nav>
		<!-- 왼쪽메뉴 -->
		<!-- 본문내용 -->
		<article>
			<h1>Join Us</h1>
			<form action="${pageContext.request.contextPath }/member/updatePro" id="join" method="post">
				<fieldset>
					<!-- 필수 사항 -->
					<legend>Basic Info</legend>
					<label>User ID</label> <input type="text" name="userid" class="id" value="${vo.userid }" readonly="readonly"> <br>
					<label>Password</label> <input type="password" name="userpw" class="pw"><br>
					<label>Name</label> <input type="text" name="username" class="name" value="${vo.username }" ><br>
					<label>E-Mail</label> <input type="email" name="useremail" class="email" value="${vo.useremail }" ><br>
				</fieldset>

				<fieldset>
					<!-- 선택 사항 -->
					<legend>Optional</legend>
					<label>Address</label> <input type="text" name="address"><br>
					<label>Phone Number</label> <input type="text" name="phone"><br>
					<label>Mobile Phone Number</label> <input type="text" name="mobile"><br>
				</fieldset>
				<div class="clear"></div>
				<div id="buttons">
					<input type="submit" value="Submit" class="submit"> 
					<input type="reset" value="Cancel" class="cancel">
				</div>
			</form>
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