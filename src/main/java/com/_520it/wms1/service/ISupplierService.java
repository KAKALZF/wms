package com._520it.wms1.service;
import java.util.List;
import com._520it.wms1.domain.Supplier;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.SupplierQueryObject;

public interface ISupplierService {
	void delete(Long id);
	
	void save(Supplier entity);
	
    Supplier get(Long id);
    
    List<Supplier> list();
    
	void update(Supplier entity);
	
	PageResult pageQuery(SupplierQueryObject qo);
}
