package com._520it.wms1.web.action;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms1.domain.Permission;
import com._520it.wms1.domain.RequiredPermission;
import com._520it.wms1.domain.Role;
import com._520it.wms1.domain.SystemMenu;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;
import com._520it.wms1.service.IPermissionService;
import com._520it.wms1.service.IRoleService;
import com._520it.wms1.service.ISystemMenuService;
import com.opensymphony.xwork2.ActionContext;

public class RoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IRoleService service;
	@Setter
	private IPermissionService perService;
	@Setter
	private ISystemMenuService systemMenuService;
	@Getter
	private Role role = new Role();
	@Setter
	@Getter
	private QueryObject qo = new QueryObject();

	@Override
	@RequiredPermission("角色列表")
	public String execute() throws Exception {
		PageResult result = service.pageQuery(qo);
		ActionContext.getContext().put("result", result);
		return LIST;
	}

	@RequiredPermission("删除角色")
	public String delete() throws Exception {
		try {
			service.delete(role.getId());
			putMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

	@RequiredPermission("编辑角色")
	public String input() throws Exception {
		List<Permission> permissions = perService.list();
		ActionContext.getContext().put("permissions", permissions);
		List<SystemMenu> menus = systemMenuService.list();
		ActionContext.getContext().put("menus", menus);
		if (role.getId() != null) {
			role = service.get(role.getId());
		}
		return "input";
	}

	@RequiredPermission("保存或更新角色")
	public String saveOrUpdate() throws Exception {
		if (role.getId() != null) {
			service.update(role);
			addActionMessage("更新成功");
		} else {
			service.save(role);
			addActionMessage("保存成功");
		}
		return "success";
	}
}
