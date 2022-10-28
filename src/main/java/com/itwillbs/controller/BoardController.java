package com.itwillbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;

@Controller
public class BoardController {
	// 멤버변수 =================================================
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	// 서비스 객체 생성이 아니라 주입
	@Autowired
	private BoardService boardService;
	
	// 멤버변수 끝 =================================================
	
	
	
	// 1. write 시작
	// 가상주소  	http://localhost:8080/FunWeb/board/write
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write() {
		log.info("(♥♥♥♥♥ 1.write) 호출됨");
		log.info("(♥♥♥♥♥ 1.write) return값 center/write.jsp로 이동할거");
	
		return "center/write";
	}
	// 1. write 끝
	
	
	
	// 2. writePro 시작
	// 가상주소  	http://localhost:8080/FunWeb/board/writePro
	@RequestMapping(value = "/board/writePro", method = RequestMethod.POST)
	public String writePro(BoardVO vo) {
		log.info("(♥♥♥♥♥ 2.writePro) 호출됨");

		log.info("(♥♥♥♥♥ 2.writePro) 글쓴이 누고 vo.getName: " + vo.getName());
		
//		BoardController
//		-> BoardService/BoardServiceImpl
//		-> BoardDAO/BoardDAOImpl
//		-> MyBatis  boardMapper.xml   + BoardVO도 필요~~
//		-> DB
		
		log.info("(♥♥♥♥♥ 2.writePro) service 호출할거^^");
		boardService.boardWrite(vo);
		
		// 글쓰기 작업 완료하면 -> 글 목록으로 이동
		log.info("(♥♥♥♥♥ 2.writePro) redirect:/board/list로 이동할거");
		return "redirect:/board/list"; // 주소줄 변화 O + 페이지 이동 O
		
	}
	// 2. writePro 끝
	
	
	
	// 3. list 시작
	// 가상주소  	http://localhost:8080/FunWeb/board/list
	// 가상주소  	http://localhost:8080/FunWeb/board/list?pageNum=3
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		log.info("(♥♥♥♥♥ 3.list) 호출됨");
		
		// 글 목록 데이터 들고~~ 가상주소 변경 없이 jsp로 이동 (입력받은 주소랑 == 주소창에 뜨는 주소랑 똑같음)
		// pageNum 셋팅
		String pageNum = request.getParameter("pageNum");
		
		if (pageNum == null) {
			pageNum = "1";
		}
		
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		
		// pageVO에 페이징 관련 정보를 담아서!! 넘어감
		PageVO vo = new PageVO();
		vo.setPageNum(pageNum);
		vo.setPageSize(pageSize);
		vo.setCurrentPage(currentPage);
		
		// 글 목록 불러오기
		log.info("(♥♥♥♥♥ 3.list) service 호출할거^^");
		List<BoardVO> boardList = boardService.getBoardList(vo);
								// ServiceImpl -> DAOImpl -> mapper.xml 가서 sql (select) 날리고
								// 그 결과로 BoardVO 타입의 데이터(리스트)가 생기고
								// 그 결과 데이터를 DAOImpl에서 ServiceImpl로 리턴(리턴타입 list)
								// -> ServiceImpl에서 Controller로 또 리턴 (리턴타입 list)
								// -> 그걸 여기서(Controller) boardList 변수에 담은 거임!!!! 휴 먼길 왔네
		
		// 전체 글 개수 구하기~~ service, dao에 메서드 만들고 오기
		int cnt = boardService.getBoardCount();
		
		// 페이징 처리
		int pageCount = cnt / pageSize + (cnt % pageSize == 0? 0 : 1);
		int pageBlock = 2;
		int startPage = ((currentPage -1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock -1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
		// 계산 다 한 거 PageVO에 담기~
		vo.setCnt(cnt);
		vo.setPageCount(pageCount);
		vo.setPageBlock(pageBlock);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		
		// model 상자에 boardList 결과 데이터 받은 거, 페이징 처리 다 끝낸 PageVO vo 담기^^
		model.addAttribute("boardList", boardList);
		model.addAttribute("vo", vo); 
		log.info("(♥♥♥♥♥ 3.list) PageVO: " + vo);
		
		
		log.info("(♥♥♥♥♥ 3.list) center/notice로 이동할거");
		return "center/notice";
	}
	// 3. list 끝
	
	
	
	// 4. listSearch 시작
	@RequestMapping (value = "/board/listSearch", method = RequestMethod.GET)
	public String listSearch(HttpServletRequest request, Model model) {
		log.info("(♥♥♥♥♥ 4.listSearch) 호출됨");

		// 검색어 가져오기
		String search = request.getParameter("search");
		String search2 = "%"+search+"%";
		
		// 글 목록 데이터 들고~~ 가상주소 변경 없이 jsp로 이동 (입력받은 주소랑 == 주소창에 뜨는 주소랑 똑같음)
		// pageNum 셋팅
		String pageNum = request.getParameter("pageNum");
		
		
		if (pageNum == null) {
			pageNum = "1";
		}
		
		int pageSize = 5;
		int currentPage = Integer.parseInt(pageNum);
		
		// pageVO에 페이징 관련 정보를 담아서!! 넘어감
		PageVO vo = new PageVO();
		vo.setPageNum(pageNum);
		vo.setPageSize(pageSize);
		vo.setCurrentPage(currentPage);
		// 검색어(% 포함하는!!)도 담기!!  <-- DB 작업할 때는 % 필요하니까!! mapper.xml에서 %search%로 검색되게끔
		vo.setSearch(search2);
		
		// 글 목록 불러오기
		log.info("(♥♥♥♥♥ 4.listSearch) service 호출할거^^");
		List<BoardVO> boardList = boardService.getBoardListSearch(vo);
								// ServiceImpl -> DAOImpl -> mapper.xml 가서 sql (select) 날리고
								// 그 결과로 BoardVO 타입의 데이터(리스트)가 생기고
								// 그 결과 데이터를 DAOImpl에서 ServiceImpl로 리턴(리턴타입 list)
								// -> ServiceImpl에서 Controller로 또 리턴 (리턴타입 list)
								// -> 그걸 여기서(Controller) boardList 변수에 담은 거임!!!! 휴 먼길 왔네
		
		// 검색된 글 개수 구하기~~ service, dao에 메서드 만들고 오기
		int cnt = boardService.getBoardCountSearch(vo);
		
		// 페이징 처리
		int pageCount = cnt / pageSize + (cnt % pageSize == 0? 0 : 1);
		int pageBlock = 2;
		int startPage = ((currentPage -1) / pageBlock) * pageBlock + 1;
		int endPage = startPage + pageBlock -1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
		// 계산 다 한 거 PageVO에 담기~
		vo.setCnt(cnt);
		vo.setPageCount(pageCount);
		vo.setPageBlock(pageBlock);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		// % 없는 검색어  <-- 화면상에는 % 안 보여도 되니까
		vo.setSearch(search);
		
		// model 상자에 boardList 결과 데이터 받은 거, 페이징 처리 다 끝낸 PageVO vo 담기^^
		model.addAttribute("boardList", boardList);
		model.addAttribute("vo", vo); 
		log.info("(♥♥♥♥♥ 4.listSearch) PageVO: " + vo);
		
		log.info("(♥♥♥♥♥ 4.listSearch) center/noticeSearch로 이동할거");
		return "center/noticeSearch";
		
	}
	// 4. listSearch 끝
	
	
	
	
}
