package com.itwillbs.domain;

public class PageVO {
	private String pageNum;  //
	private int pageSize;    // 한 페이지에 글 몇 개 출력?
	private int currentPage; // 현재 페이지
	private int startRow;    // 
	private int endRow;      // 
	
	private int cnt;         // 총 글 개수
	private int pageCount;   // 목록 하단에,,  이전 1 2 3 ..  9 10 이후
	private int pageBlock;   //        ㄴ 현재 몇번째 페이지 블럭인가
	private int startPage;   // 현재 블럭 기준 젤 대가리
	private int endPage;     // 현재 블럭 기준 젤 꼬리
	
	private String search;   // 글 검색할 때,, 검색어 담아서 가도록
	
	
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageBlock() {
		return pageBlock;
	}
	public void setPageBlock(int pageBlock) {
		this.pageBlock = pageBlock;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	
	@Override
	public String toString() {
		return "PageVO [pageNum=" + pageNum + ", pageSize=" + pageSize + ", currentPage=" + currentPage + ", startRow="
				+ startRow + ", endRow=" + endRow + ", cnt=" + cnt + ", pageCount=" + pageCount + ", pageBlock="
				+ pageBlock + ", startPage=" + startPage + ", endPage=" + endPage + ", search=" + search + "]";
	}
	
}
