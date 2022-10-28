package com.itwillbs.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@Controller
public class MemberController {
	
	// 멤버변수 ===========================================
	
	// Service 객체 생성 (DI.. 의존성 주입...)
	// 멤버변수로 해놓고~ 여기만 선언하고, 메서드마다 다 쓸 수 있게끔,,
	@Autowired
	private MemberService memberService;

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	// 멤버변수 껏 ===========================================
	
	
	
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	// 가상주소 http://localhost:8080/FunWeb/insert     가 들어오면
	public String insert() {
//		/WEB-INF/views/insert.jsp  여기로 보내는 거임
		// 어떻게??? servlet-context.xml 에서 접두사~~ 접미사~~~ 거기서 이렇게 설정해놓은 거임
		return "insert";
	}
	
	
	
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET) // 기본 get 방식
	// 가상주소 http://localhost:8080/FunWeb/member/insert   가 들어오면  
	public String insert2() {
		
		System.out.print("(MemberController_1.insert2) 가상주소 변경 없이!! 뷰 페이지로 이동");
		System.out.println("    --> member/join");
		// forward 방식으로 이동했던 그거^^ 
		//  sendRedirect --> false!!!!
														// 뷰 페이지로 가니까 get 방식 ㅇㅋㅇㅋ
		return "member/join";
//		/WEB-INF/views/member/join.jsp        // 욜로 연결을 시켜주네~~~~ 매핑해주네~~~ 아하
//	    --접두사 -----             --접미사--
		// 회원가입 입력 폼 페이지로~~~~~~~~ 가는 거임
	} // 1. insert() 끝
	
	
	
	// 2. insertPro() 시작
	@RequestMapping(value = "/member/insertPro", method = RequestMethod.POST)  // 폼이니까 post 방식
	// 가상주소 http://localhost:8080/FunWeb/member/insertPro   가 들어오면
	public String insertPro(MemberVO memberVO) {
//		private static final Logger log = LoggerFactory.getLogger(MemberController.class);
		
		System.out.print("(MemberController_2.insertPro) 횐 가입 DB 처리 -> 가상주소 변경하면서 이동");
		System.out.println("    --> redirect:/member/login");
		// DB에 회원 가입 처리하고 로그인 페이지로 이동할건데~~~~~
		// 가상주소 변경되면서!!! 이동할 수 있게!!
		// response.sendRedirect --> true!!!!!
			// 걍 이렇게 하니까,,   http://localhost:8080/FunWeb/member/insertPro 이렇게 주소창에 뜨네,,, 안되는데~~
		System.out.println("(MemberController_2.insertPro) memberVO: " + memberVO);
		
		// 회원가입 DB 처리하기 위해서
		memberService.insertMember(memberVO);
		
		return "redirect:/member/login";
		// 회원 가입 버턴 눌렀을 시 DB 처리하고 -> 로그인 페이지로 갈거니까
		// 가상 주소 insertPro에서 -> login으로 변경 작업
	} // 2. insertPro() 끝

	
	
	// 3. login() 시작
	@RequestMapping(value = "/member/login", method = RequestMethod.GET) // 기본 get방식
	// 가상주소 http://localhost:8080/FunWeb/member/login   가 들어오면
	public String login() {
		System.out.print("(MemberController_3.login) 가상주소 변경 없이 뷰 페이지로 이동");
														// 뷰 페이지로 가니까 get 방식 ㅇㅋㅇㅋ
		System.out.println("    --> member/login");
		
		return "member/login";
		// 로그인 뷰 페이지로 이동
	} // 3. login() 끝
	
	
	
	// 4. loginPro() 시작
	@RequestMapping(value = "/member/loginPro", method = RequestMethod.POST) // 폼이니까 post 방식
	// 가상주소 http://localhost:8080/FunWeb/member/loginPro   가 들어오면
	public String loginPro(HttpSession session, MemberVO vo) {
		System.out.print("(MemberController_4.loginPro) 로그인 DB 처리 -> 가상주소 변경하면서 이동");
		System.out.println("   --> redirect:/main/main");
		
		// 로그인 처리
		MemberVO vo2 = memberService.loginMember(vo);
		
		if (vo2 != null) {
			// null이 아니다 == 아이디 - 비밀번호가 일치한다
			System.out.println("(MemberController_4.loginPro) 아이디 비번 일치");

			// 세션값 생성
			session.setAttribute("loginID", vo.getUserid());
			
			// 메인으로 이동
			return "redirect:/main/main";
			
		} else {
			// null이다 ==  아이디 - 비밀번호 틀림
			System.out.println("(MemberController_4.loginPro) 아이디 비번 틀림");
			
			// WEB-INF/views/member/msg.jsp 로 보내기^^ 
			return "member/msg";
			
		}
		// 로그인 버턴 눌렀을 시 DB 처리하고 -> 가상주소 loginPro에서 main으로 변경 작업
	} // 4. loginPro() 끝 
	
	
	
	// 5. main() 시작
	@RequestMapping(value = "/main/main", method = RequestMethod.GET) // 기본 get방식
	// 가상주소 http://localhost:8080/FunWeb/main/main   가 들어오면
	public String main() {
		System.out.print("(MemberController_5.main) 가상주소 변경 없이 뷰 페이지로 이동");
		System.out.println("   --> main/main");
		
		return "main/main";
		// 메인 뷰페이지로 이동
	} // 5. main() 끝
	
	
	
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET) // 기본 get방식
	// 가상주소 http://localhost:8080/FunWeb/member/logout   가 들어오면
	public String logout(HttpSession session) {
//	public String logout(세션값 받아오기!!) {   
		// jsp 시절엔 HttpSession session = request.getSession 했는데~~~  ,,,,
		// 스프링에선 나 이거(세션값) 쓰고싶다~ 여기다가 선언만 하면 자동으로 받아와짐!!! 개이득 ㄷㄷㄷㄷ
		
		// 받아온 세션값 (loginID) 초기화
		session.invalidate();
		
		System.out.print("(MemberContrller_6.logout) 로그아웃 처리 -> 가상주소 변경하면서 이동");
		System.out.println("   --> redirect:/main/main");
		
		return "redirect:/main/main";
		// 로그아웃 처리하고 -> 가상주소 logout에서 main으로 변경 작업
	} // 6. logout() 끝
	
	
	
	@RequestMapping(value = "/member/update", method = RequestMethod.GET) // 기본 get방식
	// 가상주소 http://localhost:8080/FunWeb/member/update   가 들어오면
	public String update(HttpSession session, Model model) {
		System.out.print("(MemberController_7.update) 가상주소 변경 없이 뷰 페이지로 이동");
		System.out.println("  --> member/update");
		
		// 세션값 가져오기
		String id = (String)session.getAttribute("loginID");
				
		// 수정할 정보 가지고 가기~~
		MemberVO vo = memberService.getMember(id);
		
//		request.setAttribute("vo", vo); 구!!! 
		model.addAttribute("vo", vo); // 신!!! made by SPRING~~~ man~~
		
		return "member/update";
		// 수정할 정보 가지고!! 횐 정보 수정 폼 뷰페이지로 이동
	} // 7. update() 끝
	
	
	
	@RequestMapping(value = "/member/updatePro", method = RequestMethod.POST)  // 폼이니까 post 방식
	// 가상주소 http://localhost:8080/FunWeb/member/updatePro   가 들어오면
	public String updatePro(MemberVO vo) {
		System.out.print("(MemberController_8.updatePro) 횐 정보 업뎃 DB 처리 -> 가상주소 변경하면서 이동");
		System.out.println("  --> redirect:/main/main");
		
		// loginMember 비밀번호 일치 여부 확인
		MemberVO vo2 = memberService.loginMember(vo);
		
		if (vo2 != null) {
			// 아이디, 비번 일치하면? 
			//  수정 고고 -> main으로 이동
			int result = memberService.updateMember(vo);
			
			return "redirect:/main/main"; // 주소줄 변화 O + 페이지 이동 O
			
		} else {
		// 틀리면? msg.jsp로 보내서 history.back
		return "member/msg";
		
		}
		
		// 횐 정보 업뎃 DB 처리하고 -> 가상주소 updatePro에서 main으로 변경 작업
	} // 8. updatePro() 끝
	
	
	
	// 9. getMemberList
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	// 가상주소 http://localhost:8080/FunWeb/member/list   가 들어오면
	public void getMemberList(MemberVO vo) {
		log.info("(♥♥♥♥♥ 9.getMemberList(vo)) 호출됨 ");
		log.info("(♥♥♥♥♥ 9.getMemberList(vo)) 리턴타입 void라서 /member/list.jsp로 이동할거 ");
		
	}
	// 9. getMemberList 끝
	
	
}
