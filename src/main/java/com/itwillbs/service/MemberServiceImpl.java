package com.itwillbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	// 객체 생성,,,,, 멤버변수로 선언해놓고, 각 메서드별로 또 생성할 필요 없이 걍 이거 쓰면 되도록
	// (기존)
	// MemberDAO memberDAO = new MemberDAOImpl();
	
	// (의존성 주입 버전)
	@Autowired
	private MemberDAO memberDAO;
	
	
	// 1. insertMember
	@Override
	public void insertMember(MemberVO vo) {
		System.out.println("(MemberServiceImpl_1.insertMember) 호출됨");
		
		// service -> dao에 메서드 호출
		System.out.println("(MemberServiceImpl_1.insertMember) --> DAO_insertMember 메서드 호출할거");
		memberDAO.insertMember(vo);
		
	}// 1. insertMember 끝

	
	
	// 2. loginMember
	@Override
	public MemberVO loginMember(MemberVO vo) {
		System.out.println("(MemberServiceImpl_2.loginMember) 호출됨");
		
		// service -> dao 호출
		System.out.println("(MemberServiceImpl_2.loginMember) --> DAO_loginMember 메서드 호출할거");
		return memberDAO.loginMember(vo);
		
	}// 2. loginMember 끝
	
	

	@Override
	public MemberVO loginMember(String userid, String userpw) {
		return null;
	}

	@Override
	public MemberVO getMember(String id) {
		
		// DAO에 있는 해당 메서드 호출
		return memberDAO.getMember(id);
	}

	
	// updateMember
	@Override
	public Integer updateMember(MemberVO uvo) {
		
		return memberDAO.updateMember(uvo);
	}

	@Override
	public Integer deleteMember(MemberVO dvo) {
		return null;
	}

	@Override
	public List<MemberVO> getMemberList() {
		
		return memberDAO.getMemberList();
	}
	
}
