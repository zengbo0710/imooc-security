///**
// * 
// */
//package com.imooc.code;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.ServletWebRequest;
//
//import com.imooc.security.core.validate.code.ImageCode;
//import com.imooc.security.core.validate.code.ValidateCodeGenerator;
//
///**
// * @author Administrator
// *
// */
//@Component("imageCodeGenerator")
//public class DemoCodeImageGenerator implements ValidateCodeGenerator {
//
//	/* (non-Javadoc)
//	 * @see com.imooc.security.core.validate.code.ValidateCodeGenerator#generate(org.springframework.web.context.request.ServletWebRequest)
//	 */
//	@Override
//	public ImageCode generate(ServletWebRequest request) {
//		System.out.println("自定义的图片验证码");
//		return null;
//	}
//
//}
