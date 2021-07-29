package com.fk.service;

import com.fk.dao.impl.ServiceDao;
import com.fk.dto.HealthStatus;

public interface IPingService {

	public void ping(String ip, ServiceDao serviceDao) throws Exception;
	public HealthStatus getHealthStatusForInstance(String ip, ServiceDao serviceDao) throws Exception;
	public void register(String ip, ServiceDao serviceDao) throws Exception;
}
