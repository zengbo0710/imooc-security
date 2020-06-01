package com.imooc.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

	public String greeting(String name) {
		return "hello " + name;
	}
}
