package com._520it.wms1.service;

import java.util.List;
import com._520it.wms1.domain.StockIncomeBill;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.StockIncomeBillQueryObject;

public interface IStockIncomeBillService {
	void delete(Long id);

	void save(StockIncomeBill entity);

	StockIncomeBill get(Long id);

	List<StockIncomeBill> list();

	void update(StockIncomeBill entity);

	PageResult pageQuery(StockIncomeBillQueryObject qo);

	void audit(Long id);
}
