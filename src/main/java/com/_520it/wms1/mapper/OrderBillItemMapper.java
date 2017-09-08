package com._520it.wms1.mapper;

import com._520it.wms1.domain.OrderBillItem;

public interface OrderBillItemMapper {
	void save(OrderBillItem entity);

	void deleteByBillId(Long id);

}