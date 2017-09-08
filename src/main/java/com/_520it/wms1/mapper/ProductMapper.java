package com._520it.wms1.mapper;

import com._520it.wms1.domain.Product;
import com._520it.wms1.query.ProductQueryObject;
import java.util.List;

public interface ProductMapper {
	void save(Product entity);
	
	void update(Product entity);
	
	void delete(Long id);
	
    Product get(Long id);
    
	List<Product> list();
	
    Long getTotalCount(ProductQueryObject qo);
    
    List<Product> getListData(ProductQueryObject qo);
}