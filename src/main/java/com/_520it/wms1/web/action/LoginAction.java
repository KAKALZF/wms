package com._520it.wms1.web.action;

import lombok.Setter;

import com._520it.wms1.domain.Employee;
import com._520it.wms1.service.IEmployeeService;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Setter
	private String username;
	@Setter
	private String password;
	@Setter
	private IEmployeeService service;

	@Override
	public String execute() throws Exception {
		Employee emp = service.checkLogin(username, password);
		if (emp == null) {
			addActionError("账户或密码错误");
			return "login";
		} else {
			System.out.println(emp);
			ActionContext.getContext().getSession()
					.put("EMPLOYEE_IN_SESSOIN", emp);
			return "main";
		}
	}
}
