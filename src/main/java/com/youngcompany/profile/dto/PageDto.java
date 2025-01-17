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
	private int realEndPage; // 실제마지막페이지
	
	private Criteria criteria; // Criteria 내의 변수를 불러오기 위한 객체

	public PageDto(int total, Criteria criteria) {
		super();
		this.total = total;
		this.criteria = criteria;
		
		this.endPage = (int) ((Math.ceil(criteria.getPageNum() / 10.0)) * 10) ;   // Math.ceil 올림
		// 사용자가 클릭한 페이지번호/10.0(열개씩보여주기로했으니까) 의 결과를 올림한 후 10을 곱하면 원하는 endpage 값 출력
		this.startPage = this.endPage - 9;
		
		this.realEndPage = (int) Math.ceil(total*1.0/criteria.getAmount());  // 실수로 만들기 위해 1.0 을 곱함
		// 무조건 마지막 페이지 번호가 10,20,30 으로 끝나는 경우가 아니라 35처럼 끝나는 경우 
		
		if (this.realEndPage < this.endPage) {  // 실재페이지의 갯수가 계산된 endpage 수보다 작을 때 교체
			this.endPage = this.realEndPage;
		}
		
		this.prev = this.startPage > 1;
		//startPage 값이 1만 아니면 이전으로 돌아가는 화살표가 존재해야 함으로 prev -> true
		this.next = this.realEndPage > this.endPage;
		//현재 보여지고 있는 페이지 번호의 마지막 페이지값이 총 글의 갯수로 계산한 실제 마지막 페이지보다 작으면 next -> true
		
	}
	
}
