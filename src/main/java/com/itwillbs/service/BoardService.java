package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;

public interface BoardService {
	// 추상 메서드
	
	// 1. 글쓰기
	public void boardWrite(BoardVO vo);

	// 2. 전체 글 목록
	public List<BoardVO> getBoardList(PageVO vo);

	// 3. 전체 글 개수
	public int getBoardCount();

	// 4. 검색된 글 목록
	public List<BoardVO> getBoardListSearch(PageVO vo);

	// 5. 검색된 글 개수
	public int getBoardCountSearch(PageVO vo);
}
