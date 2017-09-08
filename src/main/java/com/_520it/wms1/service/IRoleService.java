package com._520it.wms1.service;

import java.util.List;

import com._520it.wms1.domain.Role;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;

public interface IRoleService {
	void save(Role role);

	void update(Role role);

	void delete(Long id);

	Role get(Long id);

	List<Role> list();

	PageResult pageQuery(QueryObject qo);
	
	
}