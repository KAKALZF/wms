package com._520it.wms1.query;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SystemMenuQueryObject extends QueryObject {
	private Long parentId;
	private String parentSn;
}
