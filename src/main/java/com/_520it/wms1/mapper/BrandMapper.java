package com._520it.wms1.mapper;

import com._520it.wms1.domain.Brand;
import com._520it.wms1.query.BrandQueryObject;
import java.util.List;

public interface BrandMapper {
	void save(Brand entity);
	
	void update(Brand entity);
	
	void delete(Long id);
	
    Brand get(Long id);
    
	List<Brand> list();
	
    Long getTotalCount(BrandQueryObject qo);
    
    List<Brand> getListData(BrandQueryObject qo);
}