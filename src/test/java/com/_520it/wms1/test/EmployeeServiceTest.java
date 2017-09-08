package com._520it.wms1.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com._520it.wms1.domain.Department;
import com._520it.wms1.domain.Employee;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.EmployeePageQuery;
import com._520it.wms1.service.IDepartmentService;
import com._520it.wms1.service.IEmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceTest {
	@Autowired
	private IEmployeeService service;

	private IDepartmentService deptService;

	@Test
	public void testSave() {
		Department dept = new Department();
		dept.setId(2L);
		dept.setName("研发部");
		Employee emp = new Employee();
		emp.setAge(22);
		emp.setPassword("1234");
		emp.setEmail("123@qq.com");
		emp.setAdmin(true);
		for (int i = 0; i < 50; i++) {
			emp.setName("kaka" + i);
			service.save(emp);
		}
	}

	@Test
	public void testUpdate() {
		Employee emp = new Employee();
		emp.setId(2L);
		emp.setName("kaka2");
		emp.setAge(22);
		emp.setEmail("123453@qq.com");
		emp.setAdmin(true);
		service.update(emp);
	}

	@Test
	public void testDelete() {
		service.delete(3L);
	}

	@Test
	public void testGet() {
		Employee emp = service.get(26L);
		String roleNames = emp.getRoleNames();
		System.out.println(roleNames);
		System.out.println(emp);
	}

	@Test
	public void testList() {
		List<Employee> list = service.list();
		for (Employee emp : list) {
			System.out.println(emp);
		}
	}

	@Test
	public void testPageQuery() {
		EmployeePageQuery qo = new EmployeePageQuery();
		//qo.setKeywords("k");
		PageResult result = service.pageQuery(qo);
		System.out.println(result);
	}

}
