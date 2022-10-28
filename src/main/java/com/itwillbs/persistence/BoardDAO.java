package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;

public interface BoardDAO {
	// 추상 메서드 쭉 적어주세욘,, 인터페이스니까
	
	// 1. 글쓰기 메서드
	public Integer getMaxNo();
	public void boardWrite(BoardVO vo);
	
	// 2. 글 목록 불러오기 메서드
	public List<BoardVO> getBoardList(PageVO vo);
	
	// 3. 전체 글 개수 구하기
	public int getBoardCount();
	
	// 4. 검색된 글 목록
	public List<BoardVO> getBoardListSearch(PageVO vo);
	
	// 5. 검색된 글 개수
	public int getBoardCountSearch(PageVO vo);
	
	
	
	
}
