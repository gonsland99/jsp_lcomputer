package com.mvc.vo;

//import com.mvc.service.UserService;

public class Pagination {
	int count; 
	int page;
	int pageNum;		// page/count
	int startPage;		// 1,6,11
	int endPage;		// 5,10,15
	int lastPage;
	int prevPage;		// 0,5,10
	int nextPage;		// 6,11,16
	public static final int pageUnit=5;	// 불러올 페이지 수
	public static final int perPage=3;	// 불러올 유저수
//	UserService userService = null;
	public Pagination() {
	}
	public Pagination(int page) {
		this.page = page;
//		userService = UserService.getInstance();
//		count = userService.getUsersCount();
		startPage = ((page-1)/pageUnit)*pageUnit +1;
		lastPage = (int)Math.ceil(count / (float)perPage);
		endPage = startPage + pageUnit -1;
		endPage = endPage < lastPage ? endPage : lastPage;
		prevPage = (endPage-pageUnit);
		nextPage = (startPage+pageUnit);
		System.out.println(this.page);
		System.out.println(startPage);
		System.out.println(lastPage);
		System.out.println(endPage);
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
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
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
}
