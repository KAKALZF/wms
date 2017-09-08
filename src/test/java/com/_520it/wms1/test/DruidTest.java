package com._520it.wms1.test;

import org.junit.Test;

import com.alibaba.druid.filter.config.ConfigTools;

public class DruidTest {
	@Test
	public void testname() throws Exception {
		System.out.println(ConfigTools.encrypt("admin"));
	}
}
