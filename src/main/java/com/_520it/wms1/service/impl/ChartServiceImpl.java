package com._520it.wms1.service.impl;

import java.util.List;
import java.util.Map;

import lombok.Setter;

import com._520it.wms1.mapper.ChartMapper;
import com._520it.wms1.query.OrderBillChartQueryObject;
import com._520it.wms1.query.SaleChartQueryObject;
import com._520it.wms1.service.IChartService;

public class ChartServiceImpl implements IChartService {
	@Setter
	private ChartMapper chartMapper;

	@Override
	public List<Map<String, Object>> queryOrderBillChart(
			OrderBillChartQueryObject qo) {
		return chartMapper.queryOrderBillChart(qo);
	}

	@Override
	public List<Map<String, Object>> querySaleBillChart(SaleChartQueryObject sqo) {
		return chartMapper.querySaleBillChart(sqo);
	}

}
