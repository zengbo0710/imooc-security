/**
 * 
 */
package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Administrator
 *
 */
public interface ValidateCodeGenerator {

//	ImageCode generate(ServletWebRequest request);
	ValidateCode generate(ServletWebRequest request);
	
}
