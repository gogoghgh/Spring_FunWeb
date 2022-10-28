package com.itwillbs.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	// 멤버변수 ===================================
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	// MyBatis 객체 주입
	@Autowired
	private SqlSession sqlSession;
	
	// boardMapper 가상 이름 정의!! 계속 쓸거니까~~
	private static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper"; 
	
	// 멤버변수 끝===================================
	
	
	
	
	// 0. 글 번호
	@Override
	public Integer getMaxNo() {
		return sqlSession.selectOne(NAMESPACE+".getMaxNo");
	}
	// 0. 글 번호 끝
	
	
	
	// 1. 글쓰기
	@Override
	public void boardWrite(BoardVO vo) {
		log.info("(♥♥♥♥♥ 1.boardWrite(vo)) 서비스가 호출함");

		log.info("(♥♥♥♥♥ 1.boardWrite(vo)) MyBatis 호출할거 ㅎ");
		sqlSession.insert(NAMESPACE+".boardWrite", vo);
		
	}
	// 1. 글쓰기 끝
	
	
	
	// 2. 글 목록 불러오기 시작
	@Override
	public List<BoardVO> getBoardList(PageVO vo) {
		log.info("(♥♥♥♥♥ 2.getBoardList) 서비스가 호출함");
		
		log.info("(♥♥♥♥♥ 2.getBoardList) boardMapper.xml 가서,,, sql 처리");
		return sqlSession.selectList(NAMESPACE+".getBoardList", vo);
	}
	// 2. 글 목록 불러오기 끝
	
	
	
	// 3. 전체 글 개수 구하기 시작
	@Override
	public int getBoardCount() {
		log.info("(♥♥♥♥♥ 3.getBoardCount) 서비스가 호출함");
		log.info("(♥♥♥♥♥ 3.getBoardCount) mapper.xml로~~~ ");
		return sqlSession.selectOne(NAMESPACE+".getBoardCount");
	}
	// 3. 전체 글 개수 구하기 끝
	
	
	
	// 4. 검색된 글 목록 불러오기 시작
	@Override
	public List<BoardVO> getBoardListSearch(PageVO vo) {
		log.info("(♥♥♥♥♥ 4.getBoardListSearch) 서비스가 호출함");
		log.info("(♥♥♥♥♥ 4.getBoardListSearch) boardMapper.xml 가서,,, sql 처리");
		return sqlSession.selectList(NAMESPACE+".getBoardListSearch", vo);
	}
	// 4. 검색된 글 목록 불러오기 끝
	
	
	
	// 5. 검색된 글 개수 구하기 시작
	@Override
	public int getBoardCountSearch(PageVO vo) {
		log.info("(♥♥♥♥♥ 5.getBoardCountSearch) 서비스가 호출함");
		log.info("(♥♥♥♥♥ 5.getBoardCountSearch) mapper.xml로~~~ ");
		return sqlSession.selectOne(NAMESPACE+".getBoardCountSearch");
	}
	// 5. 검색된 글 개수 구하기 끝
	
	
	
}
