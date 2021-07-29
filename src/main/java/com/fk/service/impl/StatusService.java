package com.fk.service.impl;

import java.util.List;

import com.fk.dao.impl.ServiceDao;
import com.fk.dto.HealthStatus;
import com.fk.dto.Instance;
import com.fk.dto.Service;
import com.fk.dto.ServiceStatus;
import com.fk.service.IPingService;
import com.fk.service.IRegisterService;
import com.fk.service.IStatusService;

public class StatusService implements IStatusService{

	private static final Double HEALTHTHRESHOLD = 0.5;
	
	public ServiceStatus getStatus(String serviceName, IPingService iPingService, ServiceDao serviceDao ) throws Exception {
		Service service = serviceDao.getServiceMap().get(serviceName);
		List<Instance> list = service.getInstances();
		int healthyCount = 0;
		int unhealthyCount = 0;
		for(Instance instance : list) {
			HealthStatus healthStatus = iPingService.getHealthStatusForInstance(instance.getIp(),serviceDao);
			System.out.println("health count for instance "+ instance.getIp() + " :" +healthStatus);
			if(healthStatus.equals(HealthStatus.HEALTHY)) {
				healthyCount++;
			}else {
				unhealthyCount++;
			}
		}
		if(unhealthyCount==0) {
			return ServiceStatus.GREEN;
		}else {
			if(healthyCount/(healthyCount+unhealthyCount)>=HEALTHTHRESHOLD) {
				return ServiceStatus.ORANGE;
			}else {
				return ServiceStatus.RED;
			}
		}
	}
	
	
}
