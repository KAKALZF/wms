package com._520it.wms1.web.action;

import com.opensymphony.xwork2.ActionContext;

import com._520it.wms1.domain.ProductStock;
import com._520it.wms1.query.ProductStockQueryObject;
import com._520it.wms1.service.IBrandService;
import com._520it.wms1.service.IDepotService;
import com._520it.wms1.service.IProductStockService;
import com._520it.wms1.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class ProductStockAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IProductStockService service;
	@Setter
	private IBrandService brandService;
	@Setter
	private IDepotService depotService;

	@Getter
	private ProductStockQueryObject qo = new ProductStockQueryObject();

	@Getter
	private ProductStock productStock = new ProductStock();

	@RequiredPermission("库存列表")
	public String execute() {
		try {
			put("depots", depotService.list());
			put("brands", brandService.list());
			ActionContext.getContext().put("result", service.pageQuery(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

}
