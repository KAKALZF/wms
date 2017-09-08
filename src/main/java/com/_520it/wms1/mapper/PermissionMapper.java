package com._520it.wms1.mapper;

import java.util.List;

import com._520it.wms1.domain.Permission;
import com._520it.wms1.query.QueryObject;

public interface PermissionMapper {
	void save(Permission dept);

	void delete(Long id);

	Permission get(Long id);

	List<Permission> list();

	Long getTotalCount(QueryObject qo);

	List<Permission> getListData(QueryObject qo);

	List<Permission> getByRoleId(Long id);
}
