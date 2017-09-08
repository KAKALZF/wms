package com._520it.wms1.web.action;

import com.opensymphony.xwork2.ActionContext;

import com._520it.wms1.domain.Supplier;
import com._520it.wms1.query.SupplierQueryObject;
import com._520it.wms1.service.ISupplierService;
import com._520it.wms1.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class SupplierAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter 
	private ISupplierService service;

	@Getter 
	private SupplierQueryObject qo=new SupplierQueryObject();

	@Getter private Supplier supplier=new Supplier();

	@RequiredPermission("供应商列表")
	public String execute(){
		try {
			ActionContext.getContext().put("result", service.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("供应商编辑")
	public String input() {
		try {
			if (supplier.getId() != null) {
                supplier = service.get(supplier.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("供应商保存/更新")
	public String saveOrUpdate() {
		try {
			if (supplier.getId() == null) {
                service.save(supplier);
				addActionMessage("保存成功");
            } else {
                service.update(supplier);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("供应商删除")
	public String delete()  throws  Exception {
		try {
			if (supplier.getId() != null) {
                service.delete(supplier.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

}
