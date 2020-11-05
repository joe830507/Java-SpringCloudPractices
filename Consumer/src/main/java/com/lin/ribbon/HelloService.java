package com.lin.ribbon;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {

	private Logger logger = Logger.getLogger(getClass().getName());

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "helloFallback", commandKey = "helloKey")
	public String helloService() {
		long start = System.currentTimeMillis();
		String result = restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
		long end = System.currentTimeMillis();
		logger.info("It takes : " + (end - start) + " ms");
		return result;
	}

	public String helloFallback() {
		return "error";
	}
}
