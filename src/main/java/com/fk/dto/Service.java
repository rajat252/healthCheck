package com.fk.dto;

import java.util.List;

public class Service {

	private String name;
	private List<Instance> instances;
	
	public Service(String name, List<Instance> instances) {
		this.name = name;
		this.instances = instances;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Instance> getInstances() {
		return instances;
	}
	public void setInstances(List<Instance> instances) {
		this.instances = instances;
	}
	
	
}
