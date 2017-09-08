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
@ObjectProp("采购订单")
public class OrderBill extends BaseDomain {
	public final static int STATUS_NORMAL = 0;
	public final static int STATUS_AUDIT = 1;
	private static final long serialVersionUID = 1L;
	@ObjectProp("订单编码")
	private String sn;
	@ObjectProp("业务时间")
	private Date vdate;
	@ObjectProp("审核状态")
	private int status = STATUS_NORMAL;
	@ObjectProp("采购金额")
	private BigDecimal totalAmount;
	@ObjectProp("采购总数")
	private BigDecimal totalNumber;
	@ObjectProp("审核时间")
	private Date auditTime;
	@ObjectProp("制单时间")
	private Date inputTime;
	@ObjectProp("制单人")
	private Employee inputUser;
	@ObjectProp("审核人")
	private Employee auditor;
	@ObjectProp("供应商")
	private Supplier supplier;
	
	private List<OrderBillItem> items = new ArrayList<>();

	@Override
	public String toString() {
		return "OrderBill [sn=" + sn + ", vdate=" + vdate + ", status="
				+ status + ", totalAmount=" + totalAmount + ", totalNumber="
				+ totalNumber + ", auditTime=" + auditTime + ", inputTime="
				+ inputTime + ", items=" + items + "]";
	}
	
}
