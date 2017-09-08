package com._520it.wms1.service;

import java.util.List;
import java.util.Map;

import com._520it.wms1.query.OrderBillChartQueryObject;
import com._520it.wms1.query.SaleChartQueryObject;

public interface IChartService {
	List<Map<String, Object>> queryOrderBillChart(OrderBillChartQueryObject qo);

	List<Map<String, Object>> querySaleBillChart(SaleChartQueryObject sqo);
}
