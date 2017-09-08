package com._520it.wms1.service.impl;

import com._520it.wms1.domain.ProductStock;
import com._520it.wms1.mapper.ProductStockMapper;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.ProductStockQueryObject;
import com._520it.wms1.service.IProductStockService;
import lombok.Setter;

import java.util.List;

public class ProductStockServiceImpl implements IProductStockService {
	@Setter
	private ProductStockMapper mapper;

	public void save(ProductStock entity) {
		mapper.save(entity);
	}


	public void update(ProductStock entity) {
		mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(ProductStockQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if (count <= 0) {
			return PageResult.emptyResult;
		}
		List<ProductStock> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData, count.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}

	@Override
	public ProductStock get(Long id) {
		return null;
	}
}
