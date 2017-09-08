package com._520it.wms1.domain;

import generator.ObjectProp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ObjectProp("出库订单")
public class StockOutcomeBill extends BaseDomain {
	public final static int STATUS_NORMAL = 0;
	public final static int STATUS_AUDIT = 1;
	private static final long serialVersionUID = 1L;
	@ObjectProp("出库单编码")
	private String sn;
	@ObjectProp("业务时间")
	private Date vdate;
	@ObjectProp("审核状态")
	private int status = STATUS_NORMAL;
	@ObjectProp("入库金额")
	private BigDecimal totalAmount;
	@ObjectProp("入库总数")
	private BigDecimal totalNumber;
	@ObjectProp("审核时间")
	private Date auditTime;
	@ObjectProp("录入时间")
	private Date inputTime;
	@ObjectProp("录入人")
	private Employee inputUser;
	@ObjectProp("审核人")
	private Employee auditor;
	@ObjectProp("所属仓库")
	private Depot depot = new Depot();
	@ObjectProp("所属客户")
	private Client client = new Client();
	//出库明细
	private List<StockOutcomeBillItem> items = new ArrayList<>();

	@Override
	public String toString() {
		return "StockIncomeBill [sn=" + sn + ", vdate=" + vdate + ", status="
				+ status + ", totalAmount=" + totalAmount + ", totalNumber="
				+ totalNumber + ", auditTime=" + auditTime + ", inputTime="
				+ inputTime + "]";
	}

}
