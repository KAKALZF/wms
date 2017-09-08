package com._520it.wms1.service.impl;

import java.util.List;

import lombok.Setter;

import com._520it.wms1.domain.Client;
import com._520it.wms1.mapper.ClientMapper;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.ClientQueryObject;
import com._520it.wms1.service.IClientService;
public class ClientServiceImpl implements IClientService {
	@Setter
	private ClientMapper mapper;
	
	public void  delete(Long id) {
		  mapper.delete(id);
	}

	public void save(Client entity) {
		  mapper.save(entity);
	}

	public Client get(Long id) {
		return mapper.get(id);
	}

	public List<Client> list() {
		return mapper.list();
	}

	public void update(Client entity) {
		  mapper.update(entity);
	}

	@Override
	public PageResult pageQuery(ClientQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if(count<=0){
			return PageResult.emptyResult;
		}
		List<Client> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData,count.intValue(),qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}
}
