package com.itwillbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;
import com.itwillbs.service.MemberService;
import com.itwillbs.service.MemberServiceImpl;

@RestController
public class AjaxController {
	// 멤버 변수 ===============================================
	
	// 객체 생성!!!!!!!!
	// 기존 방법
//	MemberService memberService = new MemberServiceImpl();
	
	@Autowired
	private MemberService memberService; // 자동으로 객체 생성해서 받아올 수 있게,, DI 의존 관계 주입 
	
	@Autowired
	private BoardService boardService;

	private static final Logger log = LoggerFactory.getLogger(AjaxController.class);
	// 멤버 변수 끝 ===============================================
	
	
	
	// http://localhost:8080/FunWeb/member/idCheck
	// http://localhost:8080/FunWeb/member/idCheck?id=admin     --> idDup 이죠~~ DB에 이미 있는 아이디니까
	// http://localhost:8080/FunWeb/member/idCheck?id=kim       --> idOk
		// 이 주소로 들어가면, 서비스 한 그 결과만 띡 나옴,, 
		// 어디로 이동하는 게 아니라, 여기 idCheck 메소드에서 처리한 결과를 볼 수 있음!!!
	@RequestMapping(value = "/member/idCheck", method = RequestMethod.GET)
	public ResponseEntity<String> idCheck(HttpServletRequest request) {
		// 주소 리턴할 때가 String이고,,
		// 여기선 결과 데이터를 리턴해야 하니까(ajax 잖아욤),, 결과값 응답=리턴하겠ㄷㅏ!! 는 의미에서 
		// 리턴 타입은 ResponseEntity!!!! << 여기에 응답할 데이터 담아서 데이터를 리턴할거임
		
		System.out.println("(AjaxController_1.idCheck) idCheck 메서드 호출됨");
		
		// 1. join.jsp에서 파라메타로 id 값 받아와서
		String id = request.getParameter("id");

		// 2. DB 작업 해야 함~ getMember 메서드(리턴타입 vo) 호출해서 변수에 담고 
		MemberVO memberVO = memberService.getMember(id);
		
		String result = "";
		
		if(memberVO != null){
			// id가 null이 아님 == 기존 우리 DB에 똑같은 아이디 있음!! 중복임!!! 사용 불가
			result = "idDup";
			
		} else {
			// id가 null이다 == 기존 DB에 이 아이디 없음 == 중복 X == 아이디 사용 가능
			result = "idOk";
		}
		
		ResponseEntity<String> entity = new ResponseEntity<String>(result, HttpStatus.OK);
															// 리턴할 변수,  상태는? OK
		
		System.out.println("(AjaxController_1.idCheck) 아이디 중복 체크 결과 result: " + result);
		
//		return "결과 데이터 들어갈 자리!! 이동할 주소가 아니라!!";
		return entity;
	}
	// 1. idCheck 끝
	
	
	
	// 2. list 시작
	@RequestMapping(value = "/member/listJson", method = RequestMethod.GET)
	public ResponseEntity<List<MemberVO>> list(HttpServletRequest request) {
		log.info("(♥♥♥♥♥ 2.list(request)) 호출됨 ");
		
		List<MemberVO> list = memberService.getMemberList();
		
		ResponseEntity<List<MemberVO>> entity 
			= new ResponseEntity<List<MemberVO>>(list, HttpStatus.OK);
											// 리턴할 변수,  상태는? OK
		
		// List<MemberVO> 얘를 자동으로 json 데이터로 변경해주는 프로그램(jackson-databind) 설치!!!!
		// 와씨 개이득이다,,, ㄷㄷㄷㄷㄷㄷㄷㄷㄷ 
		
		log.info("(♥♥♥♥♥ 2.list(request)) 리턴할 데이터 list.size: " + list.size());
		
//		return "결과 데이터 들어갈 자리!! 이동할 주소가 아니라!!";
		return entity;
	}
	// 2. list 끝
	
	
	
	// 3. boardList 시작
	@RequestMapping(value = "/board/listJson", method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> boardList(HttpServletRequest request) {
		log.info("(♥♥♥♥♥ 3.boardList) 호출됨 ");
		
		// 최근 글 다섯 개만 가져오기 위해,, 페이징 이렇게 셋팅
		PageVO vo = new PageVO();
		vo.setPageNum("1");
		vo.setPageSize(5);
		vo.setCurrentPage(1);
		
		List<BoardVO> list = boardService.getBoardList(vo);
		
		ResponseEntity<List<BoardVO>> entity
			= new ResponseEntity<List<BoardVO>>(list, HttpStatus.OK);
											// 리턴할 변수,  상태는? OK
		
		log.info("(♥♥♥♥♥ 3.boardList) 리턴할 데이터 list.size: " + list.size());
		
//		return "결과 데이터 들어갈 자리!! 이동할 주소가 아니라!!";
		return entity;
	}
	// 3. boardList 끝
	
	
	
	
}
