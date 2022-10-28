<%-- 

<%@page import="com.itwillbs.member.MemberDAO"%>
<%@page import="com.itwillbs.member.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// member/loginPro.jsp

	// 멤버빈 객체 생성
	MemberBean mb = new MemberBean();
	
	// id, pw
	mb.setId(request.getParameter("id"));
	mb.setPw(request.getParameter("pw"));
	
	// DAO 객체 챙성
	MemberDAO dao = new MemberDAO();
	
	// 로그인 체크 메서드 loginMember()
	int result = dao.loginMember(mb);
	
	if(result == 0){
		%>
			<script type="text/javascript">
				alert("비밀번호 오류");
				history.back();
			</script>		
		<%
	} else if(result == -1) {
		%>
			<script type="text/javascript">
				alert("아이디 없음");
				history.back();
			</script>		
		<%
	} else {
		// 아이디 비번 일치 = 로그인 성공
		System.out.println("(from loginPro.jsp) 로그인 성공 result: " + result);
		// 세션값 생성
		session.setAttribute("loginID", mb.getId());
		// main.jsp로 이동
		response.sendRedirect("../main/main.jsp");
		System.out.println("(from loginPro.jsp) 세션값 생성 완 -> main.jsp로 이동할게염");
	}
	
%>

 --%>