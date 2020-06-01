package com.imooc.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/order")
public class AsyncController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	

	public DeferredResult<String> order() throws Exception {
		logger.info("main start");
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		DeferredResult<String> result = new DeferredResult<>(); 
		deferredResultHolder.getMap().put(orderNumber, result);
		
//		Callable<String> result = new Callable<String>() {
//
//			@Override
//			public String call() throws Exception {
//				logger.info("callable start");
//				Thread.sleep(1000);
//				logger.info("callable stop");
//				return "success";
//			}
//		};
		
		logger.info("main stop");
		return result;
	}
	


}
