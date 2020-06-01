/**
 * 
 */
package com.imooc.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Administrator
 *
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
	
	private UserDetailsService userDetailsService;

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken)authentication;
		UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
		if (user == null) {
			throw new InternalAuthenticationServiceException("retrieveUser returned null - a violation of the interface contract");
		}
		
		SmsCodeAuthenticationToken authenticationTokenResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
		authenticationTokenResult.setDetails(authentication.getDetails());
		return authenticationTokenResult;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
