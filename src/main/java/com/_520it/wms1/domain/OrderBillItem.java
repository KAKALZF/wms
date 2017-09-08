package com._520it.wms1.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBillItem extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private BigDecimal costPrice=BigDecimal.ZERO;
	private BigDecimal number=BigDecimal.ZERO;
	private BigDecimal amount=BigDecimal.ZERO;
	private String remark;
	private Product product;
	private Long billId;
	@Override
	public String toString() {
		return "OrderBillItem [status=" + ", costPrice=" + costPrice
				+ ", number=" + number + ", amount=" + amount + ", remark="
				+ remark + ", product=" + product + ", billId=" + billId + "]";
	}

}
