package com._520it.wms1.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ObjectProp("系统菜单")
public class SystemMenu extends BaseDomain {
	private static final long serialVersionUID = 1L;
	@ObjectProp("名称")
	private String name;
	@ObjectProp("编码")
	private String sn;
	@ObjectProp("URL")
	private String url;
	//父菜单,多对一
	private SystemMenu parent;
	//所有的子菜单,一对多
	private List<SystemMenu> childs;

	public String getParentName() {
		if (getParent() == null) {
			return "根目录";
		} else {
			return parent.getName();
		}

	}

	@Override
	public String toString() {
		return "SystemMenu{" + "name='" + name + '\'' + ", url='" + url + '\''
				+ ", sn='" + sn + '\'' + '}';
	}
}
