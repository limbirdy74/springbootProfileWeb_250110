package com.youngcompany.profile.dao;

import java.util.ArrayList;

import com.youngcompany.profile.dto.BoardDto;

public interface BoardDao {
	
	public void writeDao(String bid, String bname, String btitle, String bcontent); // 글쓰기
	public ArrayList<BoardDto> listDao(int amount, int pageNum); // 모든 글 가져오기. amount 한페이지에 보여줄 글의 갯수, pageNum 유저가 클릭한 페이지 번호
	public BoardDto contentViewDao(String bnum); // 글 번호로 해당 번호글 가죠오기
	public void contentModifyDao(String bnum, String btitle, String bcontent); // 글 수정하기
	public int contentDeleteDao(String bnum); // 글 삭제하기
	public int totalBoardCountDao(); // 게시판 총 글의 개수
	public void updateHitDao(String bnum); //조회수 1 증가

}
