package com._520it.wms1.service;

import java.util.List;

import com._520it.wms1.domain.Permission;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;

public interface IPermissionService {
	void save(Permission per);

	void delete(Long id);

	Permission get(Long id);

	void reload();

	List<Permission> list();
	
	PageResult pageQuery(QueryObject qo);
	
	List<Permission> getByRoleId(Long id);
}