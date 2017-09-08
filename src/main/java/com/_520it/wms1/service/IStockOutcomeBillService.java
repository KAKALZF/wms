package com._520it.wms1.service;

import com._520it.wms1.domain.StockOutcomeBill;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.StockOutcomeBillQueryObject;

public interface IStockOutcomeBillService {
	void delete(Long id);

	void save(StockOutcomeBill entity);

	StockOutcomeBill get(Long id);


	void update(StockOutcomeBill entity);

	PageResult pageQuery(StockOutcomeBillQueryObject qo);

	void audit(Long id);
}
