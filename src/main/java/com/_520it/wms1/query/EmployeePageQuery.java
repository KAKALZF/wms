package com._520it.wms1.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeePageQuery extends QueryObject {
	private String keywords;
	private String deptId;
}
