package com._520it.wms1.mapper;

import java.util.List;

import com._520it.wms1.domain.StockOutcomeBill;
import com._520it.wms1.query.StockOutcomeBillQueryObject;

public interface StockOutcomeBillMapper {
	void save(StockOutcomeBill entity);

	void update(StockOutcomeBill entity);

	void delete(Long id);

	StockOutcomeBill get(Long id);

	Long getTotalCount(StockOutcomeBillQueryObject qo);

	List<StockOutcomeBill> getListData(StockOutcomeBillQueryObject qo);

	void updateAudit(StockOutcomeBill sb);

}