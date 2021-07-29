package com.fk.service.impl;

import java.util.List;
import java.util.Map;

import com.fk.dao.impl.ServiceDao;
import com.fk.dto.Instance;
import com.fk.dto.Service;
import com.fk.service.IPingService;
import com.fk.service.IRegisterService;

public class RegisterService implements IRegisterService{

	
	
	public void registerService(String serviceName, List<Instance> instances, IPingService pingService, ServiceDao serviceDao) throws Exception {
		Service service = new Service(serviceName, instances);
		Map<String, Service> serviceMap = serviceDao.getServiceMap();
		Map<String, String> instanceServiceMap = serviceDao.getInstanceServiceMap();
		if(serviceMap.get(serviceName)!=null){
			throw new Exception("Service is already registered");
		}
		serviceMap.put(serviceName,service);
		
		for(Instance instance : instances) {
			instanceServiceMap.put(instance.getIp(), service.getName());
			pingService.register(instance.getIp(),serviceDao);
		}
	}
	
	public Service getService(String name, ServiceDao serviceDao) {
		return serviceDao.getServiceMap().get(name);
	}
	
	
}
