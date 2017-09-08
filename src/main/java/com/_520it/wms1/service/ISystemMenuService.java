package com._520it.wms1.service;

import java.util.List;
import java.util.Map;

import com._520it.wms1.domain.SystemMenu;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.SystemMenuQueryObject;

public interface ISystemMenuService {
	void delete(Long id);

	void save(SystemMenu entity);

	SystemMenu get(Long id);

	List<SystemMenu> list();

	void update(SystemMenu entity);

	PageResult pageQuery(SystemMenuQueryObject qo);

	List<Map<String, Object>> queryMenusByParentsId(String parentSn);

}
