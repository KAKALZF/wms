package com._520it.wms1.service;
import com._520it.wms1.domain.ProductStock;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.ProductStockQueryObject;

public interface IProductStockService {
	
	void save(ProductStock entity);
	
    ProductStock get(Long id);
    

	void update(ProductStock entity);
	
	PageResult pageQuery(ProductStockQueryObject qo);
}
