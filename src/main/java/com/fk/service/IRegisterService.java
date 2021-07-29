package com.fk.service;

import java.util.List;

import com.fk.dao.impl.ServiceDao;
import com.fk.dto.Instance;
import com.fk.dto.Service;

public interface IRegisterService {

	public void registerService(String serviceName, List<Instance> instances, IPingService pingService, ServiceDao serviceDao) throws Exception;
	public Service getService(String name, ServiceDao serviceDao);
}
