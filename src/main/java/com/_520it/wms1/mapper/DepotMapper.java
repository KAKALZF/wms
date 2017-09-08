package com._520it.wms1.mapper;

import com._520it.wms1.domain.Depot;
import com._520it.wms1.query.DepotQueryObject;
import java.util.List;

public interface DepotMapper {
	void save(Depot entity);
	
	void update(Depot entity);
	
	void delete(Long id);
	
    Depot get(Long id);
    
	List<Depot> list();
	
    Long getTotalCount(DepotQueryObject qo);
    
    List<Depot> getListData(DepotQueryObject qo);
}