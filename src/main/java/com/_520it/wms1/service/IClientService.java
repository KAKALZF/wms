package com._520it.wms1.service;
import java.util.List;
import com._520it.wms1.domain.Client;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.ClientQueryObject;

public interface IClientService {
	void delete(Long id);
	
	void save(Client entity);
	
    Client get(Long id);
    
    List<Client> list();
    
	void update(Client entity);
	
	PageResult pageQuery(ClientQueryObject qo);
}
