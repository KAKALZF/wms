package com._520it.wms1.query;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com._520it.wms1.util.DateUtil;
@Setter
@Getter
@ToString
public class OrderBillChartQueryObject extends QueryObject {
	private String keyword;
	private Long supplierId = -1L;
	private Long brandId = -1L;
	private Date beginDate;
	private Date endDate;
	private String groupByType = "p.name";
	public static final Map<String,String> GROUP_BY_TYPE = new LinkedHashMap<>();
	static {
		GROUP_BY_TYPE.put("iu.name", "订货人员");
		GROUP_BY_TYPE.put("p.name", "货品名称");
		GROUP_BY_TYPE.put("su.name", "供应商");
		GROUP_BY_TYPE.put("b.name", "货品品牌");
		GROUP_BY_TYPE.put("date_format(bill.vdate,'%Y-%m')", "订货日期(月)");
		GROUP_BY_TYPE.put("date_format(bill.vdate,'%Y-%m-%d')", "订货日期(日)");
	}

	public Date getEndDate() {
		//先判断,否则初次访问则会空指针,endDate为空;
		return endDate != null ? DateUtil.getEndDate(endDate) : null;
	}
}
