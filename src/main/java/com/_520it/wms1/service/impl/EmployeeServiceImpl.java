package com._520it.wms1.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Setter;

import com._520it.wms1.domain.Employee;
import com._520it.wms1.domain.Permission;
import com._520it.wms1.domain.Role;
import com._520it.wms1.mapper.EmployeeMapper;
import com._520it.wms1.mapper.PermissionMapper;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.EmployeePageQuery;
import com._520it.wms1.query.QueryObject;
import com._520it.wms1.service.IEmployeeService;
import com._520it.wms1.util.UserContextUtil;

public class EmployeeServiceImpl implements IEmployeeService {
	@Setter
	private EmployeeMapper empMapper;
	@Setter
	private PermissionMapper perMapper;

	@Override
	public void save(Employee emp) {
		//如果没有为员工分配部门,则设置部门为空;
		if (emp.getDept().getId() == -1) {
			//emp.setDept(null);
			emp.getDept().setId(null);
		}
		empMapper.save(emp);
		List<Role> roles = emp.getRoles();
		//思考:角色集合是怎么传递过来的
		for (Role role : roles) {
			empMapper.saveReload(emp.getId(), role.getId());
		}
	}

	@Override
	public void update(Employee emp) {
		if (emp.getDept().getId() == -1) {
			//emp.setDept(null);
			emp.getDept().setId(null);
		}
		empMapper.deleteRelation(emp.getId());
		empMapper.update(emp);
		List<Role> roles = emp.getRoles();
		//思考:角色集合是怎么传递过来的
		for (Role role : roles) {
			empMapper.saveReload(emp.getId(), role.getId());
		}
	}

	@Override
	public void delete(Long id) {
		empMapper.delete(id);
	}

	@Override
	public Employee get(Long id) {
		return empMapper.get(id);
	}

	@Override
	public List<Employee> list() {
		EmployeePageQuery qo = new EmployeePageQuery();
		return empMapper.list(qo);

	}

	@Override
	public PageResult pageQuery(QueryObject qo) {
		Long totalCountLong = empMapper.getTotalCount(qo);
		if (totalCountLong == 0) {
			return new PageResult(Collections.EMPTY_LIST, 0,
					qo.getCurrentPage(), qo.getPageSize());
		}
		List<Employee> listData = empMapper.getListData(qo);
		return new PageResult(listData, totalCountLong.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public Employee checkLogin(String username, String password) {
		Set<String> expressionSet = new HashSet<>();
		Employee emp = empMapper.checkLogin(username, password);
		if (emp != null) {
			List<Role> roles = emp.getRoles();
			for (Role role : roles) {
				List<Permission> permissions = perMapper.getByRoleId(role
						.getId());
				if (permissions != null) {
					for (Permission p : permissions) {
						String exp = p.getExpression();
						expressionSet.add(exp);
					}
				}
			}
		}
		/*ActionContext.getContext().getSession()
				.put("EXPRESSION_IN_SESSION", expressionSet);*/
		UserContextUtil.setUserPermissions(expressionSet);
		return emp;
	}

	@Override
	public void batchDelete(Long[] ids) {
		empMapper.batchDelete(ids);
	}

}
