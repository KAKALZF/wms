package com._520it.wms1.web.action;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms1.domain.Department;
import com._520it.wms1.domain.RequiredPermission;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;
import com._520it.wms1.service.IDepartmentService;
import com.opensymphony.xwork2.ActionContext;

public class DepartmentAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IDepartmentService service;

	@Setter
	@Getter
	private Department dept = new Department();
	@Setter
	@Getter
	private QueryObject qo = new QueryObject();

	@Override
	@RequiredPermission("部门列表")
	public String execute() throws Exception {
		System.out.println("rbac");
		System.out.println(service);
		//List<Department> list = service.list();
		PageResult result = service.pageQuery(qo);
		ActionContext.getContext().put("result", result);
		return LIST;
	}

	@RequiredPermission("删除部门")
	public String delete() throws Exception {
		try {
			System.out.println(dept.getId() + "=========================");
			service.delete(dept.getId());
			putMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

	@RequiredPermission("编辑部门")
	public String input() throws Exception {
		if (dept.getId() != null) {
			dept = service.get(dept.getId());
		}
		return "input";
	}

	@RequiredPermission("保存或更新部门")
	public String saveOrUpdate() throws Exception {
		try {
			if (dept.getId() != null) {
				service.update(dept);
				addActionMessage("更新成功");
			} else {
				service.save(dept);
				addActionMessage("保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("保存或更新失败");
		}

		return "success";
	}
}
