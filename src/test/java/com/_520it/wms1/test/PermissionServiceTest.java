package com._520it.wms1.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com._520it.wms1.domain.Department;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;
import com._520it.wms1.service.IDepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PermissionServiceTest {
	@Autowired
	private IDepartmentService service;

	@Test
	public void testSave() {
		Department dept = new Department();
		dept.setName("研发部");
		dept.setSn("dept-001");
		service.save(dept);
	}

	@Test
	public void testUpdate() {
		Department dept = new Department();
		dept.setId(2L);
		//dept.setName("销售部");
		dept.setSn("dept-001");
		service.update(dept);
	}

	@Test
	public void testDelete() {
		service.delete(3L);
	}

	@Test
	public void testGet() {
		Department emp = service.get(1L);
		System.out.println(emp);
	}

	@Test
	public void testList() {
		List<Department> list = service.list();
		for (Department dept : list) {
			System.out.println(dept);
		}
	}

	@Test
	public void testPageQuery() {
		QueryObject qo = new QueryObject();
		PageResult result = service.pageQuery(qo);
		System.out.println(result);
	}

}
