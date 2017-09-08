package com._520it.wms1.web.action;

import com.opensymphony.xwork2.ActionContext;

import com._520it.wms1.domain.Depot;
import com._520it.wms1.query.DepotQueryObject;
import com._520it.wms1.service.IDepotService;
import com._520it.wms1.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class DepotAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter 
	private IDepotService service;

	@Getter 
	private DepotQueryObject qo=new DepotQueryObject();

	@Getter private Depot depot=new Depot();

	@RequiredPermission("仓库列表")
	public String execute(){
		try {
			ActionContext.getContext().put("result", service.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("仓库编辑")
	public String input() {
		try {
			if (depot.getId() != null) {
                depot = service.get(depot.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("仓库保存/更新")
	public String saveOrUpdate() {
		try {
			if (depot.getId() == null) {
                service.save(depot);
				addActionMessage("保存成功");
            } else {
                service.update(depot);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("仓库删除")
	public String delete()  throws  Exception {
		try {
			if (depot.getId() != null) {
                service.delete(depot.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

}
