package com._520it.wms1.domain;

import generator.ObjectProp;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseDomain  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ObjectProp("编号")
	protected Long id;
}
