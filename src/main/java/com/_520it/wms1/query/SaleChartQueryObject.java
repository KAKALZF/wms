package com._520it.wms1.query;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com._520it.wms1.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleChartQueryObject extends QueryObject {
	private Date beginDate;
	private Date endDate;
	private String keyword;
	private Long brandId = -1L;
	private Long clientId = -1L;
	private String groupByType = "e.name";

	public final static Map<String, String> GROUP_BY_TYPE = new LinkedHashMap<>();
	static {
		GROUP_BY_TYPE.put("e.name", "销售人员");
		GROUP_BY_TYPE.put("c.name", "客户");
		GROUP_BY_TYPE.put("p.name", "产品名称");
		GROUP_BY_TYPE.put("b.name", "品牌");
		GROUP_BY_TYPE.put("date_format(sa.vdate,'%Y-%m')", "销售日期(月)");
		GROUP_BY_TYPE.put("date_format(sa.vdate,'%Y-%m-%d')", "销售日期(日)");
	}

	public Date getEndDate() {
		return endDate == null ? null : DateUtil.getEndDate(endDate);
	}

}
