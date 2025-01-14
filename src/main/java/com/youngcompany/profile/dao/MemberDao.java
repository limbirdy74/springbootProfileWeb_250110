package com.youngcompany.profile.dao;

import com.youngcompany.profile.dto.MemberDto;

public interface MemberDao {
	
	public int joinMemberDao(String mid, String mpw, String mname, String memail); // 회원가입
	public int idCheckDao(String mid); // 아이디 존재 여부 체크
	public MemberDto memberInfoDao(String ㅡid);  //회원아이디로 회원정보 조회
	public int loginDao(String mid, String mpw); // 회원로그인
	public int memberModify(String mid, String mpw, String mname, String memail); // 회원정보수정
	
}
