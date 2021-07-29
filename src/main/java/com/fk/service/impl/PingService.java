package com.fk.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fk.dao.impl.ServiceDao;
import com.fk.dto.HealthStatus;
import com.fk.dto.InstanceDetails;
import com.fk.service.IPingService;

public class PingService implements IPingService{

	
	private static final Integer PING_COUNT = 2;
	private static final Integer TIME_COUNT_IN_MINS = 1;
	
	public void register(String ip, ServiceDao serviceDao) throws Exception{
		Map<String,InstanceDetails> instanceHealthMap = serviceDao.getInstanceHealthMap();
		InstanceDetails instanceDetails = instanceHealthMap.get(ip);
		if(instanceDetails!=null) {
			throw new Exception("Instance is already registered");
		}
		instanceDetails = new InstanceDetails();
		instanceDetails.setHealthStatus(HealthStatus.UNHEALTHY);
		instanceDetails.setIp(ip);
		instanceDetails.setTimestamps(new ArrayList<Long>());
		instanceHealthMap.put(ip, instanceDetails);
		serviceDao.setInstanceHealthMap(instanceHealthMap);
	}
	
	public void ping(String ip,ServiceDao serviceDao) throws Exception {
		Map<String,InstanceDetails> instanceHealthMap = serviceDao.getInstanceHealthMap();
		InstanceDetails instanceDetails = instanceHealthMap.get(ip);
		if(instanceDetails==null) {
			throw new Exception("Instance is not registered");
		}
		populateHealthStatus(instanceDetails,ip,true,serviceDao);
	}
	
	private InstanceDetails populateHealthStatus(InstanceDetails instanceDetails,String ip, boolean isPing, ServiceDao serviceDao) {
		List<Long> timestamps = instanceDetails.getTimestamps();
		int totalValidPing = PING_COUNT * TIME_COUNT_IN_MINS;
		Date date = new Date();
		Long now = date.getTime();
		Long timeStampsToDelete = now-60*1000*TIME_COUNT_IN_MINS;
		List<Long> timeStampsToDeleteList = new ArrayList<Long>();
		
		for(Long timestamp : timestamps) {
			if(timestamp<timeStampsToDelete) {
				timeStampsToDeleteList.add(timestamp);
			}
		}
		timestamps.removeAll(timeStampsToDeleteList);
		if(isPing) {
			timestamps.add(now);
		}
		instanceDetails.setTimestamps(timestamps);
		if(timestamps.size()>=totalValidPing) {
			instanceDetails.setHealthStatus(HealthStatus.HEALTHY);
		}else {
			instanceDetails.setHealthStatus(HealthStatus.UNHEALTHY);
		}
		Map<String,InstanceDetails> instanceHealthMap = serviceDao.getInstanceHealthMap();
		instanceHealthMap.put(ip, instanceDetails);
		
		serviceDao.setInstanceHealthMap(instanceHealthMap);
		
		return instanceDetails;
	}
	
	public HealthStatus getHealthStatusForInstance(String ip, ServiceDao serviceDao) throws Exception{
		Map<String,InstanceDetails> instanceHealthMap = serviceDao.getInstanceHealthMap();
		InstanceDetails instanceDetails = instanceHealthMap.get(ip);
		if(instanceDetails==null) {
			throw new Exception("Instance is not registered");
		}
		
		instanceDetails = populateHealthStatus(instanceDetails,ip,false,serviceDao);
		return instanceDetails.getHealthStatus();
	}
}
