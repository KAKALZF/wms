package com._520it.wms1.mapper;

import com._520it.wms1.domain.OrderBill;
import com._520it.wms1.query.OrderBillQueryObject;
import java.util.List;

public interface OrderBillMapper {
	void save(OrderBill entity);
	
	void update(OrderBill entity);
	
	void delete(Long id);
	
    OrderBill get(Long id);
    
	List<OrderBill> list();
	
    Long getTotalCount(OrderBillQueryObject qo);
    
    List<OrderBill> getListData(OrderBillQueryObject qo);

	void updateAudit(OrderBill bill);
    
    
}