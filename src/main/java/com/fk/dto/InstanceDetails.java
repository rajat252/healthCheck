package com.fk.dto;

import java.util.List;

public class InstanceDetails {

	private String ip;
	private List<Long> timestamps;
	private HealthStatus healthStatus;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public List<Long> getTimestamps() {
		return timestamps;
	}
	public void setTimestamps(List<Long> timestamps) {
		this.timestamps = timestamps;
	}
	public HealthStatus getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(HealthStatus healthStatus) {
		this.healthStatus = healthStatus;
	}
	
	
}
