package com._520it.wms1.service;
import java.util.List;
import com._520it.wms1.domain.Depot;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.DepotQueryObject;

public interface IDepotService {
	void delete(Long id);
	
	void save(Depot entity);
	
    Depot get(Long id);
    
    List<Depot> list();
    
	void update(Depot entity);
	
	PageResult pageQuery(DepotQueryObject qo);
}
