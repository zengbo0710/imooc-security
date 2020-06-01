package com.imooc.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import com.imooc.security.browser.logout.ImoocLogoutSuccessHandler;
import com.imooc.security.browser.session.ImoocExpiredSessionStrategy;
import com.imooc.security.core.authentication.AbstractChannelSecurityConfig;
import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.authorize.AuthorizeConfigManager;
import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeProcessorHolder;
import com.imooc.security.core.validate.code.ValidateCodeSecurityConfig;
import com.imooc.security.core.validate.code.filter.SmsCodeFilter;
import com.imooc.security.core.validate.code.filter.ValidateCodeFilter;

@Configuration
//public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
//	@Autowired
//	private AuthenticationSuccessHandler imoocAuthenticationSucessHandler;
//	
//	@Autowired
//	private AuthenticationFailureHandler imoocAuthenticationFailureHandler;
	
	@Autowired
	private DataSource dataSource;
	
//	@Autowired
//	private ValidateCodeProcessorHolder validateCodeProcessorHolder;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;
	
	@Autowired
	private SpringSocialConfigurer imoocSocialSecurityConfig;
	
	@Autowired
	private InvalidSessionStrategy invalidSessionStrategy;
	
	@Autowired
	private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
	
	@Autowired
	private LogoutSuccessHandler imoocLogoutSuccessHandler;
	
	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		tokenRepository.setCreateTableOnStartup(false);
		return tokenRepository;
	};
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.httpBasic().and().authorizeRequests().anyRequest().authenticated();

//		http.formLogin().loginPage("/imooc-signIn.html")
//		.loginProcessingUrl("/user/login")
//		.and().authorizeRequests()
//		.antMatchers("/imooc-signIn.html").permitAll()
//		.anyRequest().authenticated()
//		.and().csrf().disable();
		
		applyPasswordAuthenticationConfig(http);
		
//	 	ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//		validateCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
//		validateCodeFilter.setSecurityProperties(securityProperties);
//		validateCodeFilter.setValidateCodeProcessorHolder(validateCodeProcessorHolder);
//		validateCodeFilter.afterPropertiesSet();
		
//		SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
//		smsCodeFilter.setAuthenticationFailureHandler(imoocAuthenticationFailureHandler);
//		smsCodeFilter.setSecurityProperties(securityProperties);
//		smsCodeFilter.setValidateCodeProcessorHolder(validateCodeProcessorHolder);
//		smsCodeFilter.afterPropertiesSet();
		
		http
		.apply(validateCodeSecurityConfig)
		.and()
		.apply(smsCodeAuthenticationSecurityConfig)
		.and()
		.apply(imoocSocialSecurityConfig)
		.and()
//		.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
//		.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
//		.formLogin()		
//		.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
//		.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
//		.successHandler(imoocAuthenticationSucessHandler)
//		.failureHandler(imoocAuthenticationFailureHandler)
		
//		.and()
//		.rememberMe()
//		.tokenRepository(persistentTokenRepository())
//		.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
//		.userDetailsService(userDetailsService)
//		.and()
		.sessionManagement()
		.invalidSessionStrategy(invalidSessionStrategy)
//		.invalidSessionUrl(SecurityConstants.DEFAULT_SESSION_INVALID_URL)
		.maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
		.maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
		.expiredSessionStrategy(sessionInformationExpiredStrategy)
		.and()
		.and()
		.logout()
//		.logoutUrl("/signOut")
		.logoutSuccessHandler(imoocLogoutSuccessHandler)
//		.deleteCookies("JSESSIONID")
//		.logoutSuccessUrl("imooc-logout.html")
		.and()
//		.authorizeRequests()
//		.antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
//				SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
//				securityProperties.getBrowser().getLoginPage(),
//				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
//				securityProperties.getBrowser().getSignUpUrl(),
//				SecurityConstants.DEFAULT_SESSION_INVALID_URL,
//				"/user/regist")
//		.permitAll()
//		.antMatchers(HttpMethod.GET, "/user/*")
////		.access("hasRole('ADMIN') and hasIpAddress('xxxx')")
//		.hasRole("ADMIN")
//		.anyRequest().authenticated()
//		.and()
		.csrf()
		.disable();
		authorizeConfigManager.config(http.authorizeRequests());
	}

}
