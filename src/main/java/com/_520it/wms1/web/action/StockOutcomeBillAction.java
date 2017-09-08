package com._520it.wms1.web.action;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

import com._520it.wms1.domain.Depot;
import com._520it.wms1.domain.RequiredPermission;
import com._520it.wms1.domain.StockIncomeBill;
import com._520it.wms1.domain.StockOutcomeBill;
import com._520it.wms1.query.StockIncomeBillQueryObject;
import com._520it.wms1.query.StockOutcomeBillQueryObject;
import com._520it.wms1.service.IClientService;
import com._520it.wms1.service.IDepotService;
import com._520it.wms1.service.IStockIncomeBillService;
import com._520it.wms1.service.IStockOutcomeBillService;
import com.opensymphony.xwork2.ActionContext;

public class StockOutcomeBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IStockOutcomeBillService service;
	@Setter
	private IDepotService depotService;
	@Setter
	private IClientService clientService;

	@Getter
	private StockOutcomeBillQueryObject qo = new StockOutcomeBillQueryObject();

	@Getter
	private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();

	@RequiredPermission("出货单列表")
	public String execute() {
		try {
			List<Depot> depots = depotService.list();
			put("depots", depots);
			put("clients", clientService.list());
			ActionContext.getContext().put("result", service.pageQuery(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("出货订单编辑")
	public String input() {
		try {
			put("depots", depotService.list());
			put("clients", clientService.list());
			if (stockOutcomeBill.getId() != null) {
				stockOutcomeBill = service.get(stockOutcomeBill.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("出货订单保存/更新")
	public String saveOrUpdate() {
		try {
			if (stockOutcomeBill.getId() == null) {
				service.save(stockOutcomeBill);
				addActionMessage("保存成功");
			} else {
				service.update(stockOutcomeBill);
				addActionMessage("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("出货订单删除")
	public String delete() throws Exception {
		try {
			if (stockOutcomeBill.getId() != null) {
				service.delete(stockOutcomeBill.getId());
				putMsg("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

	@RequiredPermission("入库审核")
	public String audit() {
		try {
			if (stockOutcomeBill.getId() != null) {
				service.audit(stockOutcomeBill.getId());
				putMsg("审核成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());

		}

		return NONE;
	}

	@RequiredPermission("入库单查看")
	public String show() {
		List<Depot> depots = depotService.list();
		put("depots", depots);
		put("clients", clientService.list());
		stockOutcomeBill = service.get(stockOutcomeBill.getId());
		return "show";
	}

}
