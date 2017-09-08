package com._520it.wms1.service.impl;

import java.util.List;
import java.util.Map;

import lombok.Setter;

import com._520it.wms1.domain.SystemMenu;
import com._520it.wms1.mapper.SystemMenuMapper;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.SystemMenuQueryObject;
import com._520it.wms1.service.ISystemMenuService;
import com._520it.wms1.util.UserContextUtil;

public class SystemMenuServiceImpl implements ISystemMenuService {
	@Setter
	private SystemMenuMapper mapper;

	public void delete(Long id) {
		mapper.delete(id);
	}

	public void save(SystemMenu entity) {
		mapper.save(entity);
	}

	public SystemMenu get(Long id) {
		return mapper.get(id);
	}

	public List<SystemMenu> list() {
		return mapper.list();
	}

	public void update(SystemMenu entity) {
		mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(SystemMenuQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if (count <= 0) {
			return PageResult.emptyResult;
		}
		List<SystemMenu> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData, count.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}

	@Override
	public List<Map<String, Object>> queryMenusByParentsId(String parentSn) {
		if (UserContextUtil.getCurrentUser().isAdmin()) {
			return mapper.queryMenusByParentsId(parentSn);
		} else {
			return mapper.queryMenusByParentSnAndEmployeeId(parentSn,
					UserContextUtil.getCurrentUser().getId());
		}
	}
}
