package com.fk.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.fk.dto.InstanceDetails;
import com.fk.dto.Service;

public class ServiceDao{

	private Map<String,InstanceDetails> instanceHealthMap = new HashMap<String,InstanceDetails>();
	private Map<String, Service> serviceMap = new HashMap<String, Service>();
	private Map<String, String> instanceServiceMap =  new HashMap<String, String>();

	public Map<String, InstanceDetails> getInstanceHealthMap() {
		return instanceHealthMap;
	}

	public void setInstanceHealthMap(Map<String, InstanceDetails> instanceHealthMap) {
		this.instanceHealthMap = instanceHealthMap;
	}

	public Map<String, Service> getServiceMap() {
		return serviceMap;
	}

	public void setServiceMap(Map<String, Service> serviceMap) {
		this.serviceMap = serviceMap;
	}

	public Map<String, String> getInstanceServiceMap() {
		return instanceServiceMap;
	}

	public void setInstanceServiceMap(Map<String, String> instanceServiceMap) {
		this.instanceServiceMap = instanceServiceMap;
	}
	
	
	
}
