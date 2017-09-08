package com._520it.wms1.test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMakerTest {
	@Test
	public void testname() throws Exception {
		//建立数据模型
		Map<String, Object> map = new HashMap<>();
		map.put("name", "kaka");
		map.put("age", 22);
		List<String> girls = new ArrayList<>();
		girls.add("Lily");
		girls.add("Rose");
		map.put("gilrs", girls);
		//使用FreeMarker生成合并文件
		//创建配置对象
		Configuration cfg = new Configuration();
		//设置模板文件所在的目录
		cfg.setDirectoryForTemplateLoading(new File("template"));
		//获取指定的模板文件
		Template tmp = cfg.getTemplate("hello.ftl");
		//解析合并,输出合并后的文件
		tmp.process(map, new FileWriter(new File("hello.txt")));

	}
}
