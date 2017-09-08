package com._520it.wms1.web.interceptor;

import com._520it.wms1.domain.Employee;
import com._520it.wms1.util.UserContextUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckLoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//		Map<String, Object> session = invocation.getInvocationContext()
		//				.getSession();
		//		Object obj = session.get("EMPLOYEE_IN_SESSOIN");
		Employee obj = UserContextUtil.getCurrentUser();
		System.out.println("员工对象:" + obj);
		if (obj == null) {
			return Action.LOGIN;
		}
		//放行
		return invocation.invoke();
	}

}
