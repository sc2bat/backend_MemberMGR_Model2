package com.ezen.board.util;

public class Paging {
	private int page = 1; // 현재 화면에 표시할 페이지 번호
	private int totalCount; // 게시물 전체의 갯수 - 125개
	
	private int displayRow = 10; // 한 페이지에 몇 개의 게시물을 표시할 것인가
	private int displayPage = 10; // 이전(◀)과 다음(▶) 버튼 사이에 몇 개의 페이지를 표시
	
	private int beginPage; // 표시될 시작페이지 번호 1 or 11 or 21 or 31 ...
	private int endPage; // 표시될 끝페이지 번호 10 or 20 or 30 or 40 ...
	
	private boolean prev; // prev 버튼이 보일건지 안보일건지 (true/ false)
	private boolean next; // next 버튼이 보일건지 안보일건지 (true/ false)
	
	private int startNum; // 현재 페이지에 표시될 게시물 번호의 시작 번호
	private int endNum; // 현재 페이지에 표시될 게시물 번호의 끝 번호
	
	// 페이지 표시 예
	// 1 2 3 4 [5] 6 7 8 9 10 [next] - 1페이지부터 표시 prev 없음
	// [prev] 11 12 13 14 [15] 16 17 18 19 20 [next] - 게시물 갯수가 200 개 이상인경우
	// [prev] 21 22 23 24 [25] 26 27 28 29 30 [next] - 게시물 갯수가 300 개 이상인경우
	// [prev] 11 12 13 - next 없음, 게시물 갯수가 121~129 개 인경우
	// 본 클래스는 하나의 게시판에 객체 하나를 할당해서 전체 게시물 수에 따라 계산된 페이지의 각 요소들을 
	// 각 멤버 변수에 저장하고 화면에 표시할 내용을 사용하는 클래스입니다.
	
	private void paging() {
		// 각 멤버변수 값을 세팅합니다
		
		// 1. beginPage 와 endPage 계산
		double temp = page / (double)displayPage;
		// 1/10 -> 0.1		15/10 -> 1.5		25/10 -> 2.5
		
		temp = Math.ceil(temp); // 소수점 첫자리 올림 연산
		// 0.1 -> 1.0		0.9 -> 1.0 		1.5 -> 2.0 		2.5 -> 3.0
		
		endPage = (int)(temp * displayPage); // 소수점 절사 후 화면에 표시할 페이지 숫자를 곱셈
		// 1.0 -> 10 		1.0 -> 10		2.0 -> 20		3.0 -> 30
		endPage = ((int)(Math.ceil( page/(double)displayPage)))*displayPage;
		
		beginPage = endPage - displayPage + 1;
		// endPage 가 10 이면 beginPage가 1
		// endPage 가 20 이면 beginPage가 11
		// endPage 가 30 이면 beginPage가 21
		
		int totalPage = (int)Math.ceil(totalCount / (double)displayRow);
		// 총 게시물 수(totalCount)를를 한화면에 표시할 게시물 수(displayRow)로 나눠서 "총 페이지 갯수"를 계산
		// 108 /10 -> 10.8 -> 11.0 -> 11
		// 75 /10 -> 7.5 -> 8
		
		// 2. totalPage, next, prev 계산
		// 총페이지가 화면에 표시할 끝페이지보다 작다면
		if(totalPage < endPage) {
			endPage = totalPage; // endPage 를 총페이지 수로 대체하고
			next = false; // 다음 버튼은 표시하지 않는 것으로 설정
		}else { // endPage 뒤로 페이지가 더 있으니, next 를 표시하는 것으로 설정
			next = true;
		}
		prev = (beginPage==1)?false:true; // 시작페이지가 1인경우만 표시안함
		
		// 3. startNum endNum
		startNum = (page-1)*displayRow +1;
		// 현재 화면의 시작 게시물 번호 1 page : 1, 2page : 11, 3 page : 21, 4 page : 31
		endNum = page * displayRow;
		// 현재 화면의 끝 게시물 번호 10, 20, 30, 40
	}
	
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		paging(); // 멤버함수 호출 -> 각 멤버변수 구성요소를 계산해주는 메서드
	}
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
}
