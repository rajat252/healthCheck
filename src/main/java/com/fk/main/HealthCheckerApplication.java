package com.fk.main;

import java.util.ArrayList;
import java.util.List;

import com.fk.dao.impl.ServiceDao;
import com.fk.dto.Instance;
import com.fk.service.IPingService;
import com.fk.service.IRegisterService;
import com.fk.service.IStatusService;
import com.fk.service.impl.PingService;
import com.fk.service.impl.RegisterService;
import com.fk.service.impl.StatusService;

public class HealthCheckerApplication {

	public static void main(String args[]) {
		IPingService pingService = new PingService();
		IRegisterService registerService = new RegisterService();
		IStatusService statusService = new StatusService();
		ServiceDao serviceDao =new ServiceDao();
		
		Instance instance = new Instance();
		instance.setIp("ip1");
		Instance instance1 = new Instance();
		instance1.setIp("ip2");
		
		List<Instance> list = new ArrayList<Instance>();
		list.add(instance1);
		list.add(instance);
		
		Instance instance3 = new Instance();
		instance3.setIp("ip3");
		Instance instance4 = new Instance();
		instance4.setIp("ip4");
		
		List<Instance> list2 = new ArrayList<Instance>();
		list2.add(instance3);
		list2.add(instance4);
		try {
			registerService.registerService("service", list, pingService, serviceDao);
			registerService.registerService("service2", list2, pingService, serviceDao);
			pingService.ping("ip1", serviceDao);
			pingService.ping("ip1", serviceDao);
			pingService.ping("ip2", serviceDao);
			//pingService.ping("ip2", serviceDao);
			System.out.println(statusService.getStatus("service", pingService, serviceDao));
			System.out.println(statusService.getStatus("service2", pingService, serviceDao));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
