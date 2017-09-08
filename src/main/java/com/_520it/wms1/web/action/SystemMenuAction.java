package com._520it.wms1.web.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms1.domain.RequiredPermission;
import com._520it.wms1.domain.SystemMenu;
import com._520it.wms1.query.SystemMenuQueryObject;
import com._520it.wms1.service.ISystemMenuService;
import com._520it.wms1.vo.SystemMenuVO;
import com.opensymphony.xwork2.ActionContext;

public class SystemMenuAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private ISystemMenuService service;

	@Getter
	private SystemMenuQueryObject qo = new SystemMenuQueryObject();

	@Getter
	private SystemMenu systemMenu = new SystemMenu();

	@RequiredPermission("系统菜单列表")
	public String execute() {
		try {
			SystemMenu parent = service.get(qo.getParentId());
			List<SystemMenuVO> parents = new ArrayList<>();
			while (parent != null) {
				SystemMenuVO vo = new SystemMenuVO();
				vo.setId(parent.getId());
				vo.setName(parent.getName());
				parents.add(vo);
				parent = parent.getParent();
			}
			Collections.reverse(parents);
			ActionContext.getContext().put("parents", parents);
			ActionContext.getContext().put("result", service.pageQuery(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("系统菜单编辑")
	public String input() {
		try {
			SystemMenu parent = service.get(qo.getParentId());
			System.out.println(parent);
			if (parent == null) {
				ActionContext.getContext().put("parentName", "根目录");
			} else {
				ActionContext.getContext().put("parentId", parent.getId());
				ActionContext.getContext().put("parentName", parent.getName());

			}
			if (systemMenu.getId() != null) {
				systemMenu = service.get(systemMenu.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("系统菜单保存/更新")
	public String saveOrUpdate() {
		try {
			if (systemMenu.getId() == null) {
				service.save(systemMenu);
				addActionMessage("保存成功");
			} else {
				service.update(systemMenu);
				addActionMessage("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("系统菜单删除")
	public String delete() throws Exception {
		try {
			if (systemMenu.getId() != null) {
				service.delete(systemMenu.getId());
				putMsg("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

	public String getParentByParentSn() throws Exception {
		List<Map<String, Object>> list = service.queryMenusByParentsId(qo
				.getParentSn());
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
		putAjax(list);
		return NONE;
	}

}
