package com._520it.wms1.mapper;

import com._520it.wms1.domain.StockIncomeBillItem;

public interface StockIncomeBillItemMapper {
	void save(StockIncomeBillItem entity);

	void deleteItemsByBillId(Long id);

}