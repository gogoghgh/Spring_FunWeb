package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	// 멤버변수 ===================================
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	// DB 써야 하니까 DAO 객체 생성 이 아니라~ 주입
	@Autowired
	private BoardDAO boardDAO;
	
	// 멤버변수 끝===================================
	

	
	// 1. 글쓰기 메서드 boardWrite 시작
	@Override
	public void boardWrite(BoardVO vo) {
		log.info("(♥♥♥♥♥ 1.boardWrite(vo)) 하이룽 컨트롤러가 호출함");
		
		// pass name subject content
		// bno, readcount, re_ref, re_lev, re_seq, date, ip
		
		vo.setReadcount(0);
		vo.setRe_lev(0);
		vo.setRe_seq(0);
		vo.setDate(new Timestamp(System.currentTimeMillis())); // vo에서 date로 했으면 datd로, timestamp로 했으면 timestamp로^^
		
		// bno
		if (boardDAO.getMaxNo() == null ) {
			// 게시판 글 없는 경우, bno는 1!!
			vo.setBno(1);
			vo.setRe_ref(1);
		} else {
			// 게시판 글 있는 경우 최대 번호 + 1
			vo.setBno(boardDAO.getMaxNo() + 1);
			vo.setRe_ref(boardDAO.getMaxNo() + 1);
		}
		
		log.info("(♥♥♥♥♥ 1.boardWrite(vo)) 데이터 처리할 거 다 했고,, dao 메서드 호출할거임");
		boardDAO.boardWrite(vo);
		
	}
	// 1. 글쓰기 메서드 boardWrite 끝
	
	
	
	// 2. 글 목록 불러오기 getBoardList 시작
	@Override
	public List<BoardVO> getBoardList(PageVO vo) {
		log.info("(♥♥♥♥♥ 2.getBoardList) 하이룽 컨트롤러가 호출함");
		
		// startRow, endRow 구하기
		int startRow = (vo.getCurrentPage()-1) * vo.getPageSize() + 1;
		int endRow = vo.getCurrentPage() * vo.getPageSize();
		// sql 단(mapper.xml)에서는 이런 연산 못 하니까,, 서비스 단에서 -1 미리!! 해줄게염
		vo.setStartRow(startRow-1);
		vo.setEndRow(endRow);
		
		log.info("(♥♥♥♥♥ 2.getBoardList) dao 호출할 거");
		return boardDAO.getBoardList(vo);
	}
	// 2. 글 목록 불러오기 getBoardList 끝
	
	
	
	// 3. 전체 글 개수 구하기
	@Override
	public int getBoardCount() {
		log.info("(♥♥♥♥♥ 3.getBoardCount) 컨트롤러가 호출함");
		log.info("(♥♥♥♥♥ 3.getBoardCount) dao 호출할거");
		
		return boardDAO.getBoardCount();
	}
	// 3. 전체 글 개수 구하기 끝
	
	
	
	// 4. 검색된 글 목록
	@Override
	public List<BoardVO> getBoardListSearch(PageVO vo) {
		log.info("(♥♥♥♥♥ 4.getBoardListSearch) 컨트롤러가 호출함");
		
		log.info("(♥♥♥♥♥ 4.getBoardListSearch) 페이징 처리 할 거");
		
		// startRow, endRow 구하기
		int startRow = (vo.getCurrentPage()-1) * vo.getPageSize() + 1;
		int endRow = vo.getCurrentPage() * vo.getPageSize();
		// sql 단(mapper.xml)에서는 이런 연산 못 하니까,, 서비스 단에서 -1 미리!! 해줄게염
		vo.setStartRow(startRow-1);
		vo.setEndRow(endRow);
		
		log.info("(♥♥♥♥♥ 4.getBoardListSearch) dao 호출할 거");
		return boardDAO.getBoardListSearch(vo);
		
	}
	// 4. 검색된 글 목록 끝
	
	
	
	// 5. 검색된 글 개수
	@Override
	public int getBoardCountSearch(PageVO vo) {
		log.info("(♥♥♥♥♥ 3.getBoardCount) 컨트롤러가 호출함");
		log.info("(♥♥♥♥♥ 3.getBoardCount) dao 호출할거");
		return boardDAO.getBoardCountSearch(vo);
	}
	// 5. 검색된 글 개수 끝
	
	
	
	
} // BoardServiceImpl class
