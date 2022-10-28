<%-- 
<%@page import="com.itwillbs.member.MemberBean"%>
<%@page import="com.itwillbs.member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
// member/idCheck.jsp
	// 'id':$('.id').val()
	String id = request.getParameter("id");
	
	// MemberDAO 객체 생성
	MemberDAO dao = new MemberDAO();
	
	// getMember(id) 메서드 호출
	MemberBean mb = dao.getMember(id); // 리턴값은 멤버빈에 담겨져 오지요
	String result="";
	
	if(mb.getId()!=null){
		// id가 null이 아님 == 기존 우리 DB에 똑같은 아이디 있음!! 중복임!!! 사용 불가
		result = "아이디 중복.. 다른 아이디를 입력해주세요";
	} else {
		// id가 null이다 == 기존 DB에 이 아이디 없음 == 중복 X == 아이디 사용 가능
		result = "아이디 사용 가능💝💝";
	}
	
%>

<%=result%> 

--%>