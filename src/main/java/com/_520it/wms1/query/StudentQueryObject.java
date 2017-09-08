package com._520it.wms1.query;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentQueryObject extends QueryObject {
	private int status = -1;
	private Long depot_id = -1L;
	private Date beginDate;
	private Date endDate;
}
