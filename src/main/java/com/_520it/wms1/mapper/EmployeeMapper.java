package com._520it.wms1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com._520it.wms1.domain.Employee;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;

public interface EmployeeMapper {
	void save(Employee deptemp);

	void update(Employee dept);

	void delete(Long id);

	Employee get(Long id);

	List<Employee> list(QueryObject qo);

	PageResult pageQuery(QueryObject qo);

	Long getTotalCount(QueryObject qo);

	List<Employee> getListData(QueryObject qo);

	void saveReload(@Param(value = "empId") Long empId,
			@Param(value = "roleId") Long roleId);
	/**
	 * 删除员工和角色的联系
	 * @param id
	 */
	void deleteRelation(Long id);

	/**
	 * 删除员工和部门的联系
	 * @param id
	 */
	void deleteEmp_DeptRelation(Long id);

	Employee checkLogin(@Param(value = "username") String username,
			@Param(value = "password") String password);

	void batchDelete(Long[] ids);

}
