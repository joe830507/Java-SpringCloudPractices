package com.lin.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	private DiscoveryClient client;

	@GetMapping(value = "/hello")
	public String index() {
		ServiceInstance instance = client.getInstances(client.getServices().get(0)).get(0);
		String logContent = String.format("/hello,host:%s,serviceId:%s.", instance.getHost(), instance.getServiceId());
		logger.info(logContent);
		return "Hello World";
	}

}
