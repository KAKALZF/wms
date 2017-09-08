package com._520it.wms1.service;
import java.util.List;
import com._520it.wms1.domain.Brand;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.BrandQueryObject;

public interface IBrandService {
	void delete(Long id);
	
	void save(Brand entity);
	
    Brand get(Long id);
    
    List<Brand> list();
    
	void update(Brand entity);
	
	PageResult pageQuery(BrandQueryObject qo);
}
