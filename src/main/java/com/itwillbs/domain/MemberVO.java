package com.itwillbs.domain;

import java.sql.Date;

public class MemberVO {
	// VO: Value Object:  값을 저장하는 객체
	// == JavaBean,  DTO(Data Transfer Object)와 개념 동일함
	
	// 도메인(domain): 개발(프로젝트) 진행 시, 가장 중요한 기능(역할)을 하는 단어(명사)
	//   ex. 영화 프로젝트의 도메인 => 영화, 회원, 영화관, / 차종, 예약, 결제,..
	//  ==> 물리적으로 분리가 가능한 단위의 개념 (== DB table 분리해야 하는 것)
	
	
	// tbl_member 테이블의 정보를 저장, 전달
	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	private Date regdate;
	private Date updatedate;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", useremail="
				+ useremail + ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}
	
}
