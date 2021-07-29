package com.fk.service;

import com.fk.dao.impl.ServiceDao;
import com.fk.dto.ServiceStatus;
import com.fk.service.impl.PingService;

public interface IStatusService {

	public ServiceStatus getStatus(String serviceName, IPingService iPingService, ServiceDao serviceDao ) throws Exception;
}
