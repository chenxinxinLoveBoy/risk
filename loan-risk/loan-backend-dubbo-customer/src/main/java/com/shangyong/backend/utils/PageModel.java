package com.shangyong.backend.utils;

/**
 * 分页
 */
public class PageModel<T> {

	static int pageSize = 10;

	static int beginPage = 0;

	static int endPage = pageSize;

	public static void getManagePage(int findAllCount, int pageNumber) {
		if (pageNumber * pageSize < findAllCount) {
			endPage = pageNumber * pageSize;
			beginPage = endPage - pageSize;
			setBeginPage(beginPage);
			setEndPage(pageSize);
		} else {
			beginPage = pageSize * (pageNumber - 1);
			setBeginPage(beginPage);
			setEndPage(pageSize);
		}
	}

	public static int getPageSize() {
		return pageSize;
	}

	public static void setPageSize(int pageSize) {
		PageModel.pageSize = pageSize;
	}

	public static int getBeginPage() {
		return beginPage;
	}

	public static void setBeginPage(int beginPage) {
		PageModel.beginPage = beginPage;
	}

	public static int getEndPage() {
		return endPage;
	}

	public static void setEndPage(int endPage) {
		PageModel.endPage = endPage;
	}

}