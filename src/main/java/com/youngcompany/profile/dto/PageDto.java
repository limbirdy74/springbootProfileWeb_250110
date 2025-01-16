package com.youngcompany.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageDto {
	
	private int total; // 게시판 글의 총 갯수
	private int startPage; // 화면에 보여질 하단 페이지 번호 중 시작 페이지 번호
	private int endPage; // 화면에 보여질 하단 페이지 번호 중 마지막 페이지 번호
	private boolean next; // 현재 보여지고 있는 페이지 번호 이상으로 페이지가 더 있는지의 여부
	private boolean prev; // 현재 보여지고 있는 페이지 번호 이하로 페이지가 더 있는지의 여부
	
	private Criteria criteria; // Criteria 내의 변수를 불러오기 위한 객체

	public PageDto(int total, Criteria criteria) {
		super();
		this.total = total;
		this.criteria = criteria;
		
		this.endPage = (int) ((Math.ceil(criteria.getPageNum() / 10.0)) * 10) ;   // Math.ceil 올림
		this.startPage = this.endPage - 9;
		
		int realEndPage = (int) Math.ceil(total*1.0/criteria.getAmount());  // 실수로 만들기 위해 1.0 을 곱함
		// 무조건 마지막 페이지 번호가 10,20,30 으로 끝나는 경우가 아니라 35처럼 끝나는 경우 
		
		if (realEndPage < this.endPage) {  // 실재페이지의 갯수가 계산된 endpage 수보다 작을 때 교체
			this.endPage = realEndPage;
		}
	}
	
}
