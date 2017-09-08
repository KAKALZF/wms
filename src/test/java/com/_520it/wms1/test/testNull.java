package com._520it.wms1.test;

import org.junit.Test;

import com._520it.wms1.util.FileUploadUtil;

public class testNull {

	@Test
	public void test() {
		FileUploadUtil.deleteFile("");
	}

}
