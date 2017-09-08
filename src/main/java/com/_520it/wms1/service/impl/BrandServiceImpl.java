package com._520it.wms1.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.wms1.domain.Brand;
import com._520it.wms1.mapper.BrandMapper;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.BrandQueryObject;
import com._520it.wms1.service.IBrandService;
public class BrandServiceImpl implements IBrandService {
	@Setter
	private BrandMapper mapper;
	
	public void  delete(Long id) {
		  mapper.delete(id);
	}

	public void save(Brand entity) {
		  mapper.save(entity);
	}

	public Brand get(Long id) {
		return mapper.get(id);
	}

	public List<Brand> list() {
		return mapper.list();
	}

	public void update(Brand entity) {
		  mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(BrandQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if(count<=0){
			return PageResult.emptyResult;
		}
		List<Brand> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData,count.intValue(),qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}
}
