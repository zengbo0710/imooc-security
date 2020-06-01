/**
 * 
 */
package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 *
 */
@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	// 如果为空，会导致所有的页面为空页面
	@GetMapping("/me")
	public Authentication me(Authentication authentication) {
		return authentication;
	}

}
