package com._520it.wms1.web.action;

import com.opensymphony.xwork2.ActionContext;

import com._520it.wms1.domain.Brand;
import com._520it.wms1.query.BrandQueryObject;
import com._520it.wms1.service.IBrandService;
import com._520it.wms1.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class BrandAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter 
	private IBrandService service;

	@Getter 
	private BrandQueryObject qo=new BrandQueryObject();

	@Getter private Brand brand=new Brand();

	@RequiredPermission("品牌列表")
	public String execute(){
		try {
			System.out.println("=====12233=======");
			ActionContext.getContext().put("result", service.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("品牌编辑")
	public String input() {
		try {
			if (brand.getId() != null) {
                brand = service.get(brand.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("品牌保存/更新")
	public String saveOrUpdate() {
		try {
			if (brand.getId() == null) {
                service.save(brand);
				addActionMessage("保存成功");
            } else {
                service.update(brand);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("品牌删除")
	public String delete()  throws  Exception {
		try {
			if (brand.getId() != null) {
                service.delete(brand.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

}
