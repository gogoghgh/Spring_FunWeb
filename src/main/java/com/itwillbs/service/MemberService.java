package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.MemberVO;

public interface MemberService {
	
	// 1. 회원가입 동작
	public void insertMember(MemberVO vo);
	
	// 2. 로그인 동작
	public MemberVO loginMember(MemberVO vo); // vo라고 적었지만 id, pw 2개만 들고 갈거임^^ 마이바티스 지가 알아서 매칭시키겠지
	
	// 2-1. 로그인 동작2
	public MemberVO loginMember(String userid, String userpw); // 메서드 오버로오딩
	
	// 3. 회원 정보 조회
//	public MemberVO getMember(String userid);
	public MemberVO getMember(String id);

	// 4. 회원 정보 수정
	public Integer updateMember(MemberVO uvo);
	
	// 5. 회원 정보 삭제
	public Integer deleteMember(MemberVO dvo);
	
	// 6. 전체 회원 목록 조회
	public List<MemberVO> getMemberList();
}
