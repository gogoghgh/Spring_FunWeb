<%-- 

<%@page import="com.itwillbs.member.db.MemberBean"%>
<%@page import="com.itwillbs.member.db.MemberDAO"%>
<%@page import="com.itwillbs.member.MemberDAO"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="com.itwillbs.member.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// member/joinPro.jsp
	// DB에 insert하러 고고
	
	// join Form에서 넘어오는 애들,, 한글 처리
	request.setCharacterEncoding("UTF-8");
	
	// MemberBean 객체 생성
	MemberBean mb = new MemberBean();
	
	// id, pw, name, email, regdate 매칭
	mb.setId(request.getParameter("id"));
	mb.setPw(request.getParameter("pw"));
	mb.setName(request.getParameter("name"));
	mb.setRegdate(new Timestamp(System.currentTimeMillis()));

	// MemberDAO 객체 생성
	MemberDAO dao = new MemberDAO();
	
	// insertMember(mb) 메서드 호출
	dao.insertMember(mb);
	
	// 회원 가입 완료 후 -> login.jsp로 페이지 이동
	System.out.println("(from joinPro.jsp) 회원 가입 완 -> login.jsp로 이동");
	response.sendRedirect("login.jsp");
%> 


--%>