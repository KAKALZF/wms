package com._520it.wms1.mapper;

import com._520it.wms1.domain.SystemMenu;
import com._520it.wms1.query.SystemMenuQueryObject;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SystemMenuMapper {
	void save(SystemMenu entity);

	void update(SystemMenu entity);

	void delete(Long id);

	SystemMenu get(Long id);

	List<SystemMenu> list();

	Long getTotalCount(SystemMenuQueryObject qo);

	List<SystemMenu> getListData(SystemMenuQueryObject qo);

	List<Map<String, Object>> queryMenusByParentsId(String parentSn);

	List<Map<String, Object>> queryMenusByParentSnAndEmployeeId(
			@Param(value = "parentSn") String parentSn,
			@Param(value = "EmployeeId") Long id);
}