package com.imooc.security.core.validate.code.sms;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;

@Component
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * @return the securityProperties
	 */
	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	/**
	 * @param securityProperties the securityProperties to set
	 */
	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

	/* (non-Javadoc)
	 * @see com.imooc.security.core.validate.code.ValidateCodeGenerator#generate(org.springframework.web.context.request.ServletWebRequest)
	 */
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
	
}