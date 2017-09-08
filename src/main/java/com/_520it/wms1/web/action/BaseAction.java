package com._520it.wms1.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public static final String LIST = "list";

	public void put(String name, Object obj) {
		ActionContext.getContext().put(name, obj);
	}

	public void putMsg(String msg)  {
		ServletActionContext.getResponse().setContentType(
				"html/text;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void putAjax(Object obj) {
		try {
			ServletActionContext.getResponse().setContentType(
					"text/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter()
					.print(com.alibaba.fastjson.JSON.toJSON(obj));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
