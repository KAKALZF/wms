package com._520it.wms1.service.impl;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms1.domain.Permission;
import com._520it.wms1.domain.Role;
import com._520it.wms1.domain.SystemMenu;
import com._520it.wms1.mapper.RoleMapper;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;
import com._520it.wms1.service.IRoleService;

public class RoleServiceImpl implements IRoleService {
	@Setter
	@Getter
	private RoleMapper roleMapper;

	@Override
	public void save(Role role) {
		roleMapper.save(role);
		List<Permission> permissions = role.getPermissions();
		//将角色的权限保存到中间表中
		for (Permission per : permissions) {
			roleMapper.saveRelation(role.getId(), per.getId());
		}
		List<SystemMenu> menus = role.getMenus();
		//将角色的权限保存到中间表中
		for (SystemMenu menu : menus) {
			roleMapper.saveRoleMenuRelation(role.getId(), menu.getId());
		}
		//保存角色到角色表
	}

	@Override
	public void update(Role role) {

		roleMapper.update(role);
		//删除角色和权限的关联
		roleMapper.deleteRelation(role.getId());
		List<Permission> permissions = role.getPermissions();
		//重新将角色的权限保存到中间表中
		for (Permission per : permissions) {
			roleMapper.saveRelation(role.getId(), per.getId());
		}

		//删除角色和权限的关联
		roleMapper.deleteRoleMenuRelation(role.getId());
		List<SystemMenu> menus = role.getMenus();
		//重新将角色的权限保存到中间表中
		for (SystemMenu menu : menus) {
			//roleMapper.saveRelation(role.getId(), menu.getId());
			roleMapper.saveRoleMenuRelation(role.getId(), menu.getId());
		}
	}

	@Override
	public void delete(Long id) {
		roleMapper.delete(id);
	}

	@Override
	public Role get(Long id) {
		return roleMapper.get(id);
	}

	@Override
	public List<Role> list() {
		return roleMapper.list();

	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCountLong = roleMapper.getTotalCount(qo);
		if (totalCountLong == 0) {
			return new PageResult(Collections.EMPTY_LIST, 0,
					qo.getCurrentPage(), qo.getPageSize());
		}
		List<Role> listData = roleMapper.getListData(qo);
		return new PageResult(listData, totalCountLong.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

}
