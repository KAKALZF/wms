package com._520it.wms1.web.action;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms1.domain.Department;
import com._520it.wms1.domain.Employee;
import com._520it.wms1.domain.RequiredPermission;
import com._520it.wms1.domain.Role;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.EmployeePageQuery;
import com._520it.wms1.query.QueryObject;
import com._520it.wms1.service.IDepartmentService;
import com._520it.wms1.service.IEmployeeService;
import com._520it.wms1.service.IRoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class EmployeeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Setter
	private IEmployeeService service;
	@Setter
	private IDepartmentService deptService;
	@Setter
	private IRoleService roleService;
	@Setter
	@Getter
	private Long[] ids;

	@Getter
	private Employee emp = new Employee();
	@Setter
	@Getter
	private QueryObject qo = new EmployeePageQuery();

	@Override
	@RequiredPermission("员工列表")
	@InputConfig(methodName = "input")
	public String execute() throws Exception {
		try {
			List<Department> depts = deptService.list();
			ActionContext.getContext().put("depts", depts);
			PageResult result = service.pageQuery(qo);
			ActionContext.getContext().put("result", result);
			//System.out.println(1 / 0);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败,请联系管理员");
		}

		return LIST;
	}

	@RequiredPermission("删除员工")
	public String delete() throws Exception {
		try {
			service.delete(emp.getId());
			//addActionMessage("删除成功");
			putMsg("删除成功");

		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;

	}

	@RequiredPermission("编辑员工")
	public String input() throws Exception {
		List<Role> roles = roleService.list();
		ActionContext.getContext().put("roles", roles);
		List<Department> depts = deptService.list();
		ActionContext.getContext().put("depts", depts);
		if (emp.getId() != null) {
			emp = service.get(emp.getId());
		}
		return "input";
	}

	@RequiredPermission("保存和和更新员工")
	public String saveOrUpdate() throws Exception {
		try {
			if (emp.getId() != null) {
				service.update(emp);
				addActionMessage("更新成功");
			} else {
				service.save(emp);
				addActionMessage("保存成功");
			}
			//System.out.println(1 / 0);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存失败,请联系管理员");

		}
		return "success";
	}

	@RequiredPermission("批量删除员工")
	public String batchDelete() throws Exception {
		try {
			service.batchDelete(ids);
			putMsg("批量删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("批量删除失败");
		}

		return NONE;
	}
}
