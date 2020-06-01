/**
 * 
 */
package com.imooc.security.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.functors.AndPredicate;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.imooc.security.core.properties.OAuth2ClientProperties;
import com.imooc.security.core.properties.SecurityProperties;

/**
 * @author Administrator
 *
 */
@Configuration
@EnableAuthorizationServer
public class ImoocAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired(required = false)
	private TokenEnhancer imoocJwtTokenEnhancer;
	
	@Autowired(required = false)
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		.tokenStore(tokenStore)
		.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
		if (jwtAccessTokenConverter != null && imoocJwtTokenEnhancer != null) {
			TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
			List<TokenEnhancer> enhancerList = new ArrayList<>();
			enhancerList.add(imoocJwtTokenEnhancer);
			enhancerList.add(jwtAccessTokenConverter);
			enhancerChain.setTokenEnhancers(enhancerList);
			
			endpoints
			.tokenEnhancer(enhancerChain)
			.accessTokenConverter(jwtAccessTokenConverter);
		}
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
		if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
			for (OAuth2ClientProperties config : securityProperties.getOauth2().getClients()) {
				builder.withClient(config.getClientId())
				.secret(config.getClientSecret())
				.accessTokenValiditySeconds(config.getAccessTokenValidateSeconds())
				.authorizedGrantTypes("refresh_token", "password", "authorization_code")
				.scopes("all", "read", "write")
				.refreshTokenValiditySeconds(60 * 60 * 24 * 30);
			}
		}
		
		
//		clients
//		.inMemory()
//		.withClient("imooc")
//		.secret("imoocsecret")
//		.accessTokenValiditySeconds(7200)
//		.authorizedGrantTypes("refresh_token", "password")
//		.scopes("all", "read", "write");
		
//		clients
//		.inMemory()
//		.withClient("imooc")
//		.secret("imoocsecret")
//		.accessTokenValiditySeconds(7200)
//		.authorizedGrantTypes("refresh_token", "password")
//		.scopes("all", "read", "write")
//		.and()
//		.withClient("xxx");
	}
}
