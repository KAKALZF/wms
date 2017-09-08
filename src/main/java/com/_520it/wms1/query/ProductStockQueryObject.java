package com._520it.wms1.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductStockQueryObject extends QueryObject {
	private String keyword;
	private Long depotId = -1L;
	private Long brandId = -1L;
	private Integer limitNumber;
}
