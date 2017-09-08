package com._520it.wms1.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Permission extends BaseDomain {

	private static final long serialVersionUID = 1L;
	private String name;
	private String expression;
}
