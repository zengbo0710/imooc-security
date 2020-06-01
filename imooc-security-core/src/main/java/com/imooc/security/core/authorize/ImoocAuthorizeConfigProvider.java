/**
 * 
 */
package com.imooc.security.core.authorize;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;

/**
 * @author Administrator
 *
 */
@Component
@Order(Integer.MIN_VALUE)
public class ImoocAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Autowired
	private SecurityProperties securityProperties;
	
	/* (non-Javadoc)
	 * @see com.imooc.security.core.authorize.AuthorizeConfigProvider#config(org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry)
	 */
	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config
		.antMatchers(
				SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
				SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
				SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
				securityProperties.getBrowser().getLoginPage(),
				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
				securityProperties.getBrowser().getSignUpUrl(),
				securityProperties.getBrowser().getSession().getSessionInvalidUrl()
				)
		.permitAll();
		
		if (StringUtils.isNotBlank(securityProperties.getBrowser().getSignOutUrl())) {
			config.antMatchers(securityProperties.getBrowser().getSignOutUrl()).permitAll();
		}
		
		return false;
	}

}
