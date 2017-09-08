package com._520it.wms1.web.interceptor;

import java.lang.reflect.Method;
import java.util.Set;

import com._520it.wms1.domain.Employee;
import com._520it.wms1.domain.RequiredPermission;
import com._520it.wms1.util.UserContextUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SecurityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		//		Map<String, Object> session = invocation.getInvocationContext()
		//				.getSession();
		//		Employee emp = (Employee) session.get("EMPLOYEE_IN_SESSOIN");
		//		System.out.println("职工:" + emp);
		//		Set<String> exp = (Set<String>) session.get("EXPRESSION_IN_SESSION");
		Employee emp = UserContextUtil.getCurrentUser();
		Set<String> exp = UserContextUtil.getUserPermissions();
		System.out.println("访问权限:" + exp);
		if (emp == null) {
			return "login";
		}
		//放行超级管理员
		if (emp != null && emp.isAdmin()) {
			return invocation.invoke();
		}

		//获取当前访问的Action对象;
		Object action = invocation.getProxy().getAction();
		String methodName = invocation.getProxy().getMethod();
		Method method = action.getClass().getMethod(methodName);
		//放行没贴标签的方法
		if (!method.isAnnotationPresent(RequiredPermission.class)) {
			return invocation.invoke();
		}
		//放行有权限的访问
		String actionName = action.getClass().getName();
		String expression = actionName + ":" + methodName;
		if (exp != null && !exp.contains(expression)) {
			return "nopermission";
		}
		return invocation.invoke();
	}

}
