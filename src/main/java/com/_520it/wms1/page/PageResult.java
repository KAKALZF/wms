package com._520it.wms1.page;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PageResult {
	private List<?> listData;
	private Integer totalCount;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPage;
	private Integer prePage;
	private Integer nextPage;
	public static final PageResult emptyResult = new PageResult(
			Collections.EMPTY_LIST, 0, 1, 5);

	public PageResult(List<?> listData, Integer totalCount,
			Integer currentPage, Integer sizePage) {
		super();
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = sizePage;

		this.totalPage = this.totalCount % this.pageSize == 0 ? this.totalCount
				/ this.pageSize : (this.totalCount / this.pageSize) + 1;
		this.prePage = this.currentPage - 1 >= 1 ? this.currentPage - 1 : 1;
		this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1
				: this.totalPage;
	}

}
