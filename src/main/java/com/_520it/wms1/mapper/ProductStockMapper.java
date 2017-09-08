package com._520it.wms1.mapper;

import com._520it.wms1.domain.ProductStock;
import com._520it.wms1.query.ProductStockQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {
	void save(ProductStock ps);

	void update(ProductStock ps);


	ProductStock selectStockByProductIdAndDepotId(
			@Param(value = "productId") Long productId,
			@Param(value = "depotId") Long depotId);

	Long getTotalCount(ProductStockQueryObject qo);

	List<ProductStock> getListData(ProductStockQueryObject qo);

}
