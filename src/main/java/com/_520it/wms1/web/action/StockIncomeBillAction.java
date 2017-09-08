package com._520it.wms1.web.action;

import com._520it.wms1.domain.Depot;
import com._520it.wms1.domain.RequiredPermission;
import com._520it.wms1.domain.StockIncomeBill;
import com._520it.wms1.query.StockIncomeBillQueryObject;
import com._520it.wms1.service.IDepotService;
import com._520it.wms1.service.IStockIncomeBillService;
import com.opensymphony.xwork2.ActionContext;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class StockIncomeBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IStockIncomeBillService service;
	@Setter
	private IDepotService depotService;

	@Getter
	private StockIncomeBillQueryObject qo = new StockIncomeBillQueryObject();

	@Getter
	private StockIncomeBill stockIncomeBill = new StockIncomeBill();

	@RequiredPermission("采购订单列表")
	public String execute() {
		try {
			List<Depot> depots = depotService.list();
			put("depots", depots);
			ActionContext.getContext().put("result", service.pageQuery(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("采购订单编辑")
	public String input() {
		try {
			List<Depot> depots = depotService.list();
			put("depots", depots);
			if (stockIncomeBill.getId() != null) {
				stockIncomeBill = service.get(stockIncomeBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("采购订单保存/更新")
	public String saveOrUpdate() {
		try {
			if (stockIncomeBill.getId() == null) {
				service.save(stockIncomeBill);
				addActionMessage("保存成功");
			} else {
				service.update(stockIncomeBill);
				addActionMessage("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("采购订单删除")
	public String delete() throws Exception {
		try {
			System.out.println("===========入库删除==============");
			if (stockIncomeBill.getId() != null) {
				service.delete(stockIncomeBill.getId());
				putMsg("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		//return NONE;
		return SUCCESS;
	}

	@RequiredPermission("入库审核")
	public String audit() throws Exception {
		try {
			if (stockIncomeBill.getId() != null) {
				service.audit(stockIncomeBill.getId());
				putMsg("审核成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("审核失败");
		}

		return NONE;
	}

	@RequiredPermission("入库单查看")
	public String show() {
		List<Depot> depots = depotService.list();
		put("depots", depots);
		stockIncomeBill = service.get(stockIncomeBill.getId());
		return "show";
	}

}
