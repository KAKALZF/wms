package com._520it.wms1.mapper;

import com._520it.wms1.domain.StockOutcomeBillItem;

public interface StockOutcomeBillItemMapper {
	void save(StockOutcomeBillItem entity);

	void deleteItemsByBillId(Long id);

}