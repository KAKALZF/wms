package com._520it.wms1.util;

import java.util.Set;

import com._520it.wms1.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

public class UserContextUtil {

	public final static String EXPRESSION_IN_SESSION = "EXPRESSION_IN_SESSION";
	public final static String EMPLOYEE_IN_SESSOIN = "EMPLOYEE_IN_SESSOIN";

	private UserContextUtil() {
	}

	//保存用户登录信息
	public static void setCurrentUser(Employee currentUser) {
		if (currentUser == null) {
			//注销用户信息
			ActionContext.getContext().getSession().remove(EMPLOYEE_IN_SESSOIN);
		} else {
			//保存用户登录信息
			ActionContext.getContext().getSession()
					.put(EMPLOYEE_IN_SESSOIN, currentUser);
		}
	}

	//保存权限信息
	public static void setUserPermissions(Set<String> expressionSet) {
		if (expressionSet == null) {
			ActionContext.getContext().getSession()
					.remove(EXPRESSION_IN_SESSION);
		} else {
			ActionContext.getContext().getSession()
					.put(EXPRESSION_IN_SESSION, expressionSet);
		}
	}

	//获取当前用户信息
	public static Employee getCurrentUser() {
		return (Employee) ActionContext.getContext().getSession()
				.get(EMPLOYEE_IN_SESSOIN);
	}

	//获取权限信息
	@SuppressWarnings("unchecked")
	public static Set<String> getUserPermissions() {
		return (Set<String>) ActionContext.getContext().getSession()
				.get(EXPRESSION_IN_SESSION);
	}

}
