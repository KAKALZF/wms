package com._520it.wms1.domain;

import generator.ObjectProp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@ObjectProp("学生")
public class Student {
	@ObjectProp("编号")
	private Long id;
	@ObjectProp("姓名")
	private String name;
	@ObjectProp("年龄")
	private Integer age;
}
