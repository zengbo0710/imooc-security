/**
 * 
 */
package com.imooc.security.core.social.weixin.api;

/**
 * @author Administrator
 *
 */
public interface Weixin {

	WeixinUserInfo getUserInfo(String openId);
	
}
