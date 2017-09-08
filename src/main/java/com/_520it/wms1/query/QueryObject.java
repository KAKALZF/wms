package com._520it.wms1.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryObject {
	private Integer pageSize = 5;
	private Integer currentPage = 1;

	public Integer getBeginIndex() {
		return (currentPage - 1) * pageSize;
	}
	
}
