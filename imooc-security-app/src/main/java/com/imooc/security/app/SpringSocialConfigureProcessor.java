/**
 * 
 */
package com.imooc.security.app;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.social.ImoocSpringSocialConfigurer;

/**
 * @author Administrator
 *
 */
@Component
public class SpringSocialConfigureProcessor implements BeanPostProcessor {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (StringUtils.equals(beanName, "imoocSocialSecurityConfig")) {
			ImoocSpringSocialConfigurer configurer = (ImoocSpringSocialConfigurer) bean;
			configurer.signupUrl(SecurityConstants.DEFAULT_SOCIAL_SIGNUP_URL);
			return configurer;
		}
		return bean;
	}

}
