package com._520it.wms1.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@ObjectProp("仓库")
public class Depot extends BaseDomain {

	private static final long serialVersionUID = 8426742001361421847L;
	@ObjectProp("仓库名称")
	private String name;
	@ObjectProp("仓库地址")
	private String location;
}
