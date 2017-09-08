package com._520it.wms1.service;

import java.util.List;

import com._520it.wms1.domain.Employee;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;

public interface IEmployeeService {
	void save(Employee dept);

	void update(Employee dept);

	void delete(Long id);

	Employee get(Long id);

	List<Employee> list();

	PageResult pageQuery(QueryObject qo);

	Employee checkLogin(String username, String password);

	void batchDelete(Long[] ids);
}