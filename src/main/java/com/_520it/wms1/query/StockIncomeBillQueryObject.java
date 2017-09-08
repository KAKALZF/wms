package com._520it.wms1.query;

import java.util.Date;

import com._520it.wms1.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockIncomeBillQueryObject extends QueryObject {
	private int status = -1;
	private Long depotId = -1L;
	private Date beginDate;
	private Date endDate;

	public Date getEndDate() {
		//先判断,否则初次访问则会空指针,endDate为空;
		return endDate != null ? DateUtil.getEndDate(endDate) : null;
	}
}
