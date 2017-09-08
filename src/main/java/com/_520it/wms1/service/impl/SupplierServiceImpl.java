package com._520it.wms1.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.wms1.domain.Supplier;
import com._520it.wms1.mapper.SupplierMapper;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.SupplierQueryObject;
import com._520it.wms1.service.ISupplierService;
public class SupplierServiceImpl implements ISupplierService {
	@Setter
	private SupplierMapper mapper;
	
	public void  delete(Long id) {
		  mapper.delete(id);
	}

	public void save(Supplier entity) {
		  mapper.save(entity);
	}

	public Supplier get(Long id) {
		return mapper.get(id);
	}

	public List<Supplier> list() {
		return mapper.list();
	}

	public void update(Supplier entity) {
		  mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(SupplierQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if(count<=0){
			return PageResult.emptyResult;
		}
		List<Supplier> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData,count.intValue(),qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}
}
