package com._520it.wms1.service;
import java.util.List;
import com._520it.wms1.domain.OrderBill;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.OrderBillQueryObject;

public interface IOrderBillService {
	void delete(Long id);
	
	void save(OrderBill entity);
	
    OrderBill get(Long id);
    
    List<OrderBill> list();
    
	void update(OrderBill entity);
	
	PageResult pageQuery(OrderBillQueryObject qo);
	
	void audit(Long id);
}
