package com._520it.wms1.mapper;

import java.util.List;

import com._520it.wms1.domain.Department;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;

public interface DeptMapper {
	void save(Department dept);

	void update(Department dept);

	void delete(Long id);

	Department get(Long id);

	List<Department> list();
	
	 PageResult pageQuery(QueryObject qo);
	 
	 Long getTotalCount(QueryObject qo);
	 
	 List<Department> getListData(QueryObject qo);
	 
}
