package com._520it.wms1.service.impl;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Setter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com._520it.wms1.domain.Permission;
import com._520it.wms1.domain.RequiredPermission;
import com._520it.wms1.mapper.PermissionMapper;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.QueryObject;
import com._520it.wms1.service.IPermissionService;
import com._520it.wms1.web.action.BaseAction;

public class PermissionServiceImpl implements IPermissionService,
		ApplicationContextAware {
	@Setter
	private PermissionMapper perMapper;
	private ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
		System.out.println(this.ctx);
	}

	@Override
	public void reload() {
		//获取所有的权限,检查是否已经包含该权限
		List<Permission> list = perMapper.list();
		//定义一个集合存放权限表达式
		Set<String> permissions = new HashSet<>();
		for (Permission permission : list) {
			String expression = permission.getExpression();
			permissions.add(expression);
		}

		Map<String, BaseAction> actionMap = ctx
				.getBeansOfType(BaseAction.class);
		Collection<BaseAction> actions = actionMap.values();
		Iterator<BaseAction> iterator = actions.iterator();
		while (iterator.hasNext()) {
			BaseAction action = iterator.next();
			Method[] methods = action.getClass().getMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(RequiredPermission.class)) {
					RequiredPermission requiredPermission = method
							.getAnnotation(RequiredPermission.class);
					String permissionName = requiredPermission.value();
					String actionName = action.getClass().getName();
					String methodName = method.getName();
					//拼接权限表达式(Action名+方法名)
					String expression = actionName + ":" + methodName;
					if (!permissions.contains(expression)) {
						Permission p = new Permission();
						p.setExpression(expression);
						p.setName(permissionName);
						perMapper.save(p);
					}
				}
			}
		}

	}

	@Override
	public void save(Permission dept) {
		perMapper.save(dept);
		//	System.out.println(1 / 0);
	}

	@Override
	public void delete(Long id) {
		perMapper.delete(id);
	}

	@Override
	public Permission get(Long id) {
		return perMapper.get(id);
	}

	@Override
	public List<Permission> list() {
		return perMapper.list();

	}

	@Override
	public PageResult pageQuery(QueryObject qo) {

		Long totalCountLong = perMapper.getTotalCount(qo);
		if (totalCountLong == 0) {
			return new PageResult(Collections.EMPTY_LIST, 0,
					qo.getCurrentPage(), qo.getPageSize());
		}
		List<Permission> listData = perMapper.getListData(qo);
		return new PageResult(listData, totalCountLong.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public List<Permission> getByRoleId(Long id) {
		return	perMapper.getByRoleId(id);
	}

}
