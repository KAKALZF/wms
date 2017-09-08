package com._520it.wms1.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms1.domain.Permission;
import com._520it.wms1.domain.RequiredPermission;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;
import com._520it.wms1.service.IPermissionService;
import com.opensymphony.xwork2.ActionContext;

public class PermissionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Setter
	private IPermissionService service;

	@Setter
	@Getter
	private Permission per = new Permission();
	@Setter
	@Getter
	private QueryObject qo = new QueryObject();

	@Override
	@RequiredPermission("权限列表")
	public String execute() throws Exception {

		//List<Permission> list = service.list();
		PageResult result = service.pageQuery(qo);
		ActionContext.getContext().put("result", result);
		return LIST;
	}

	@RequiredPermission("删除权限")
	public String delete() throws Exception {
		try {
			service.delete(per.getId());
			putMsg("删除权限成功");
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除权限失败");
		}
		return NONE;
	}

	public String input() throws Exception {
		if (per.getId() != null) {
			per = service.get(per.getId());
		}
		return "input";
	}

	@RequiredPermission("加载权限")
	public String reload() throws Exception {
		try {
			service.reload();
			putMsg("加载成功");
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("加载失败");
		}
		return NONE;
	}

}
