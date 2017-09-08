package com._520it.wms1.mapper;

import com._520it.wms1.domain.StockIncomeBill;
import com._520it.wms1.query.StockIncomeBillQueryObject;
import java.util.List;

public interface StockIncomeBillMapper {
	void save(StockIncomeBill entity);
	
	void update(StockIncomeBill entity);
	
	void delete(Long id);
	
    StockIncomeBill get(Long id);
    
	List<StockIncomeBill> list();
	
    Long getTotalCount(StockIncomeBillQueryObject qo);
    
    List<StockIncomeBill> getListData(StockIncomeBillQueryObject qo);

	void updateAudit(StockIncomeBill sb);
	
}