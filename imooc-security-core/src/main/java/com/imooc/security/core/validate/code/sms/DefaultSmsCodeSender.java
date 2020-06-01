/**
 * 
 */
package com.imooc.security.core.validate.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/* (non-Javadoc)
	 * @see com.imooc.security.core.validate.code.sms.SmsCodeSender#send(java.lang.String, java.lang.String)
	 */
	public void send(String moblie, String code) {
		logger.info("向手机：" + moblie + " 发送验证码：" + code);
	}

}
