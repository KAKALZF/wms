package com._520it.wms1.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Department extends BaseDomain {


	private static final long serialVersionUID = 8426742001361421847L;
	private String name;
	private String sn;
}
