package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;


//  @Repository: 스프링(root-context.xml)에서 이 파일을 DAO 파일로 인식하도록 설정^^ 너가 DAO야~~ 명찰 달아주는 거
				// --> 스프링이 관리하는 애면,, 파일 옆에 S 떠야하는 거 아님???? 
				// servlet-context.xml 젤 밑에 있는 코드 <context:component-scan base-package="com.itwillbs.web" /> 복사해서
				// root-context.xml 젤 밑에 붙여넣기,, 후 web -> persistance로 변경!!! 그러면 오~~ s 뜨네~~ 
				// ㄴ 이 과정이 결국....  MemberDAO dao = new MemberDAOImpl(); 이 말임!!!!!!!!!!!!!!!! 
@Repository
public class MemberDAOImpl implements MemberDAO {
	// DB에 관련된 동작을 수행
	
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	

	
	// DB 연결 + MyBatis 설정 + 자원해제까지!!! ㄷㄷㄷ 한방에 하는 올인원 객체 
	// 1.
	@Autowired
	private SqlSession sqlSession; // 직접 생성 안 하고 주입하겠다^^ root-context.xml에 만들어 놨으니까
	
	
	// mapper(sql 쿼리 적어놓은 곳)의 주소(이름)... 매번 쓸 거니까 걍 상수화해놓자!!
	private static final String NAMESPACE
	= "com.itwillbs.mapper.MemberMapper";
	
	
	// DB 연결 정보 필요 => 의존 관계 있네,, DB 연결하는 애랑,, DAOImpl이랑 의존하네,,,, 수군 수군,,
	//  ㄴroot-context.xml에 있지요? 네넵 
	//    근데~~~ myBatis 쓸 거니까 sqlSession 객체를 쓰자~~
	// sqlSessionFactory 객체 필요함(생성 X 주입해봅시다 OOO)
			//	private SqlSessionFactory factory = new SqlSessionFactory();..... 이렇ㄱ ㅔ했어야 할 걸~~
	// 이미 생성된 객체 (root-context.xml 에 sqlSessionFactory 객체(Bean)) 있으니까!! 걍 이거 쓰면 됨
	// 2. 
	@Inject
	private SqlSessionFactory factory;
	
	
	@Override
	public String getTime(){ // 상속받은 MemberDAO 추상메서드 오버라이딩!! getTime 호출하면 이게 실행돼야 함
		// 1. 2. DB 연결   --> sqlSession 만들 때 다 했고~
		
		// 3. sql 작성   --> mapper 에서 할거고~
		
		// 4. sql 실행
		
		// 5. 데이터 처리
		SqlSession sqlSession = factory.openSession();
		
					// 쿼리 실행 시켜서 데이터 하나만 갖고 와라~,,,
		String now // String으로 자동으로 형변환 해라,,,ㅋ
		= sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
				// 이런 패키지는,, 없어요!!! 실제 경로는 아님!! 
				// 걍 외부에서 들어갈 때 구분하기 쉽게 하려고,, 이렇게 적은 것,,,, 띠용?
				// 얘를 보고 memberMapper.xml까지 찾아갈 수 있게 해줌		
		
		log.info("(♥♥♥♥♥  getTime) 메서드 호출 완");
		log.info("(♥♥♥♥♥  getTime) now: " + now);
		
		return now; // null이었넴^^;;;;
	}// getTime 끝
	
	
	// 1. 회원 가입 메서드 insertMember
	@Override
	public void insertMember(MemberVO vo) {
		System.out.println("( 1.insertMember) 호출됨");
		
		log.info("(♥♥♥♥♥  1.insertMember) ↑↑↑↑↑ 테스트가 insertMember(vo) 호출함 ↑↑↑↑↑");
		log.info("(♥♥♥♥♥  1.insertMember) 1+2. DB 연결:  sqlSession(DI 객체) 얘가 함");
		
		log.info("(♥♥♥♥♥  1.insertMember) 3. sql 쿼리 작성:  memberMapper.xml 얘가 함"); // 만들러 고고 // 네네 만들고 왔고요
		log.info("(♥♥♥♥♥  1.insertMember) 3. pstmt 객체 생성: sqlSession(DI객체) 얘가 함 ");
			// pstmt: 쿼리 저장, 쿼리 안에 물음표 넣고, 쿼리 실행해주는 역할 했는데,,
		
		log.info("(♥♥♥♥♥  1.insertMember) 4. SQL 실행: sqlSession(DI객체).insert ");
			// sqlSession.insert(statement, parameter); 
			//                    sql구문,  가져갈 거
			sqlSession.insert(NAMESPACE+".insertMember", vo); 
							// com.itwillbs.mapper.MemberMapper.insertMember
								// 멤버 회원 가입 정보가 담긴 vo,, 가져갈거니까!! 가져가서 DB에 넣을거니까~~
								// dto.getID, dto.getPW..... 이런거 안 해도 됨!! 난 vo만 주면 mybatis가 알아서 착착 매칭^^ 
								// 그러니까~~~ DB table 컬럼명이랑 == dto(vo) 이름 똑같이 적으세요~!!!!!!!!!!!! 개편하니까 
		log.info("(♥♥♥♥♥  1.insertMember) vo 생긴거 궁금하넴: " + vo);
		log.info("(♥♥♥♥♥  1.insertMember) 전달하는 vo 객체는 / 따봉 mybatis가 mapper에서 자동으로 매핑 후 / 정보 전달함");
		log.info("(♥♥♥♥♥  1.insertMember) --> mapper 이동 --> MySQL DB까지 이동");
		
		log.info("(♥♥♥♥♥  1.insertMember) 6. 자원 해제: sqlSession(DI객체) 얘가 함 ");
			
	}
	// 1. 회원 가입 메서드 insertMember 끝
	
	
	
	// 2. 로그인 체크 메서드 loginMember
	@Override
	public MemberVO loginMember(MemberVO vo) {
		System.out.println("( 2.loginMember) 호출됨");
		log.info("(♥♥♥♥♥  2.loginMember) ↑↑↑↑↑ 테스트가 loginMember(vo) 메서드 호출함 ↑↑↑↑↑");
		
		MemberVO resultVO
		 = sqlSession.selectOne(NAMESPACE+".loginMember", vo); 
			// 매개변수 vo를 mapper에 전달시키고 
			// --> 그 넘어간 vo 안의 id, pw값이 sql 쿼리 안에 들어가서 채워지고 
			// --> DB 가서 조건에 맞는 애 가져오는거~~
		
			// mapper에서 쿼리 실행 결과 저장해서 리턴
		
		return resultVO;
	}
	// 2. 로그인 체크 메서드 loginMember 끝
	
	
	// 2-1. 로그인 체크 메서드 loginMember
	@Override
	public MemberVO loginMember(String userid, String userpw) {
		log.info("(♥♥♥♥♥  2-1.loginMember) ↑↑↑↑↑ 테스트가 loginMember(userid, userpw) 메서드 호출함 ↑↑↑↑↑");
		
		// 쿼리는 위에서 만들었던 거 그대로 쓸건데,, 전달할 데이터에 userid, userpw라고 쓰면? 에러가 나지요,, 
		// 왜? 이 메서드는 오버로딩이 2개 뿐~ (인자 1개, 2개) 
//		 = sqlSession.selectOne(NAMESPACE+".loginMember", userid, userpw); XXX 인자 3개짜리는 못 받음
		
			// 전달된 정보(id, pw)를 하나의! 도메인 객체(VO)에 저장 후 처리
			MemberVO vo = new MemberVO();
			vo.setUserid(userid);
			vo.setUserpw(userpw);
			
//		MemberVO resultVO
//		= sqlSession.selectOne(NAMESPACE+".loginMember", vo); // 쌉가능
		// 얘네는 한 VO 안에 있는 애들이니까 ㄱㅊㄱㅊ
		
		
		// 그런데 만약에,, 받아온 애들이 각각 다른 VO에서 온 애들이라면? 조인한 경우라면,,?!!?! 
		// 회원 정보 + 게시판 정보 => 하나의 도메인(MemberVO)에 저장 불가!! 게시판 정보를 memberVO에 저장하긴 좀~~~ 그렇죠~~ 
		// ===> 컬렉션을 사용 (연관 없는 데이터(=DB table이 다른 애들)들을 한번에 저장 가능)!!!!!!
		Map<String, Object> paramMap = new HashMap<String, Object>();
			// key    value
//		paramMap.put("key=컬럼명", "value=데이터 값"); // map 객체에 넘어온 정보들 저장~
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		log.info("(♥♥♥♥♥  2-1.loginMember) paramMap: " + paramMap);
		
		// resultVO같은 변수에 안 담고 바로 리턴해도 됨~~~ 이렇게 더 많이 쓴다
		return sqlSession.selectOne(NAMESPACE+".loginMember", paramMap);
	}
	// 2-1. 로그인 체크 메서드 loginMember 끝
	
	
	
	// 3. 회원 정보 조회 메서드 getMember
	@Override
	public MemberVO getMember(String id) {
		
		log.info("(♥♥♥♥♥  3.getMember) ↑↑↑↑↑ 테스트가 getMember(id) 호출함  ↑↑↑↑↑");
		log.info("(♥♥♥♥♥  3.getMember) memberMapper.xml에서 sql 구문 호출 동작");
		
		String userid = id;
		
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE+".getMember", userid); 
								// 전달받은 id 함께 보내서~~
								// mapper에 있는 해당 sql 구문(com.itwillbs.mapper.MemberMapper.getMember)에 집어넣기~~
		// mapper -> dao -> db -> select 날린 결과값 돌려받음!! 
		// 리턴타입 MemberVO니까 MemberVO 타입 resultVO에 담아줬고
		log.info("(♥♥♥♥♥  3.getMember) resultVO: " + resultVO);
		log.info("(♥♥♥♥♥  3.getMember) Test 파일로 이동할게요");
		
		return resultVO;
	}
	// 3. 회원 정보 조회 메서드 getMember 끝
	
	
	
	// 4. 회원 정보 수정 메서드 updateMember
	@Override
	public Integer updateMember(MemberVO uvo) {
		// 아이디, 비밀번호 받아와서 -> 해당 유저 찾아서(select) -> 수정할 정보 입력받고 -> 그 정보 들고가서 -> update 시키기
		log.info("(♥♥♥♥♥  4.updateMember) ↑↑↑↑↑ 테스트가 updateMember(uvo) 호출함 ↑↑↑↑↑");
		log.info("(♥♥♥♥♥  4.updateMember) memberMapper.xml에서 sql 구문 호출 동작 ");
		
		int result
		 = sqlSession.update(NAMESPACE+".updateMember", uvo); // 전달받은 uvo 갖고 가서 sql 쿼리에 집어넣어서 update 하고 온나~~
			// update 메서드: 
			//   리턴타입 int!!!!!  Execute an update statement. The number of rows affected will be returned.
			//   pstmt.executeUpdate 했을 때랑 같네~!~!~!~!~!~!~!~!~!~!~!~!!!!!!!! 
			// insert 메서드도!!
			// selectOne 메서드 리턴타입,,, Mapped object 임,, 
			//                               ㄴ이게 곧 ResultSet이다?? 우리가 dto에 set하고,, 그랬던 걸 mybatis가 대신 해주는거다???
		
		log.info("(♥♥♥♥♥  4.updateMember) result: " + result + " / 수정할 회원 정보 uvo: " + uvo);
		// result ==> 무족권 0  ||   1
		//               수정 X    수정 O
		
		log.info("(♥♥♥♥♥  4.updateMember) Test 파일로 이동할게요 ");

		return result;
	}
	// 4. 회원 정보 수정 메서드 updateMember 끝 
	
	
	
	// 5. 회원 정보 삭제 메서드  deleteMember
	@Override
	public Integer deleteMember(MemberVO dvo) {
		log.info("(♥♥♥♥♥  5.deleteMember) ↑↑↑↑↑ 테스트가 deleteMember(dvo) 호출함 ↑↑↑↑↑");
		log.info("(♥♥♥♥♥  5.deleteMember) memberMapper.xml에서 sql 구문 호출 동작 ");
		
		int result = sqlSession.delete(NAMESPACE+".deleteMember", dvo);
		
		log.info("(♥♥♥♥♥  5.deleteMember) result: " +  result + " / 삭제할 dvo: " + dvo);
		log.info("(♥♥♥♥♥  5.deleteMember) Test 파일로 이동할게요 ");
		
		return result;
	}
	// 5. 회원 정보 삭제 메서드 deleteMember 끝
	
	
	
	// 6. 전체 회원 목록 조회 메서드 getMemberList 
	@Override
	public List<MemberVO> getMemberList() {
		log.info("(♥♥♥♥♥  6.getMemberList) ↑↑↑↑↑ 테스트가 getMemberList 호출함 ↑↑↑↑↑");
		log.info("(♥♥♥♥♥  6.getMemberList) memberMapper.xml에서 sql 구문 호출 동작 ");
		
		// DB에서 VO 형태의 객체가 전달되면 
		//  -> 이 데이터들을 자동으로 List에 담아주는 메서드!!!!!!! 개좋네
		List<MemberVO> memberList = sqlSession.selectList(NAMESPACE+".getMemberList");
		
		log.info("(♥♥♥♥♥  6.getMemberList) List에 횐 정보 저장 완");
		log.info("(♥♥♥♥♥  6.getMemberList) Test 파일로 이동할게요 ");

		return memberList;
		
		// return sqlSession.selectList(NAMESPACE+".getMemberList"); 
		// 이렇게 적어도 됨~ 추가적으로 가져갈 거 없이 DB에 있는거 그대~~로 토스만 할거면~ 넘겨주기만 할거면~~ 
		// 뭐 더 작업할 거 있으면 위에처럼 적으시고^^
		
	}
	// 6. 전체 회원 목록 조회 메서드 getMemberList 끝
	
}
