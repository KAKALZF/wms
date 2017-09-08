package com._520it.wms1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com._520it.wms1.domain.Role;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;

public interface RoleMapper {
	void save(Role role);

	void update(Role role);

	void delete(Long id);

	Role get(Long id);

	List<Role> list();

	PageResult pageQuery(QueryObject qo);

	Long getTotalCount(QueryObject qo);

	List<Role> getListData(QueryObject qo);

	void saveRelation(@Param(value = "rid") Long id1,
			@Param(value = "pid") Long id2);

	void deleteRelation(Long id);

	void saveRoleMenuRelation(@Param(value = "roleId") Long roleId,
			@Param(value = "menuId") Long menuId);

	void deleteRoleMenuRelation(Long id);
}
