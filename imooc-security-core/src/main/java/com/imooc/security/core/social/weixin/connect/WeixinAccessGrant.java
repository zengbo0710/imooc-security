/**
 * 
 */
package com.imooc.security.core.social.weixin.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author Administrator
 *
 */
public class WeixinAccessGrant extends AccessGrant {

	/**
	 * @param accessToken
	 * @param scope
	 * @param refreshToken
	 * @param expiresIn
	 */
	public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
		super(accessToken, scope, refreshToken, expiresIn);
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 8529084489038651387L;
	
	private String openId;
	
	public WeixinAccessGrant() {
		super("");
	}

}
