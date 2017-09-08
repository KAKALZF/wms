package com._520it.wms1.service;

import java.util.List;

import com._520it.wms1.domain.Department;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;

public interface IDepartmentService {
	void save(Department dept);

	void update(Department dept);

	void delete(Long id);

	Department get(Long id);

	List<Department> list();
	
	 PageResult pageQuery(QueryObject qo);
}