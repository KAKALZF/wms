package com._520it.wms1.service.impl;

import java.util.Collections;
import java.util.List;

import lombok.Setter;

import com._520it.wms1.domain.Department;
import com._520it.wms1.mapper.DeptMapper;
import com._520it.wms1.mapper.EmployeeMapper;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;
import com._520it.wms1.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService {
	@Setter
	private DeptMapper deptMapper;
	@Setter
	private EmployeeMapper empMapper;

	@Override
	public void save(Department dept) {
		deptMapper.save(dept);
		//	System.out.println(1 / 0);
	}

	@Override
	public void update(Department dept) {
		deptMapper.update(dept);
	}

	@Override
	public void delete(Long id) {
		//删除部门之前把该部门的员工表中的dept_id设置为空
		empMapper.deleteEmp_DeptRelation(id);
		deptMapper.delete(id);
	}

	@Override
	public Department get(Long id) {
		return deptMapper.get(id);
	}

	@Override
	public List<Department> list() {
		return deptMapper.list();

	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCountLong = deptMapper.getTotalCount(qo);
		if (totalCountLong == 0) {
			return new PageResult(Collections.EMPTY_LIST, 0,
					qo.getCurrentPage(), qo.getPageSize());
		}
		List<Department> listData = deptMapper.getListData(qo);
		return new PageResult(listData, totalCountLong.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

}
