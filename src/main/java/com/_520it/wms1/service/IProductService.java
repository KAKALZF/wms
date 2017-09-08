package com._520it.wms1.service;
import java.util.List;
import com._520it.wms1.domain.Product;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.ProductQueryObject;

public interface IProductService {
	void delete(Long id);
	
	void save(Product entity);
	
    Product get(Long id);
    
    List<Product> list();
    
	void update(Product entity);
	
	PageResult pageQuery(ProductQueryObject qo);
}
