package com._520it.wms1.domain;

import generator.ObjectProp;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("库存")
public class ProductStock extends BaseDomain {
	private static final long serialVersionUID = 1L;
	@ObjectProp("货品")
	private Product product;//存放的商品
	@ObjectProp("库存价格")
	private BigDecimal price;//采用移动加权平均
	@ObjectProp("库存量")
	private BigDecimal storeNumber;//库存数量
	@ObjectProp("库存总金额")
	private BigDecimal amount;//库存总额
	@ObjectProp("仓库")
	private Depot depot;//所在仓库

	@Override
	public String toString() {
		return "ProductStock [product=" + product + ", price=" + price
				+ ", storeNumber=" + storeNumber + ", amount=" + amount + "]";
	}

}
