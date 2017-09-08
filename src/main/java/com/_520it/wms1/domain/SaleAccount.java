package com._520it.wms1.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SaleAccount extends BaseDomain {
	private Date vdate;
	private BigDecimal number;
	private BigDecimal costPrice;
	private BigDecimal costAmount;
	private BigDecimal salePrice;
	private BigDecimal saleAmount;
	private Product product;
	private Employee saleman;
	private Client client;

}
