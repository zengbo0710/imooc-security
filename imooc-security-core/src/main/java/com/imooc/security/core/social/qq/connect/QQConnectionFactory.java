/**
 * 
 */
package com.imooc.security.core.social.qq.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import com.imooc.security.core.social.qq.api.QQ;

/**
 * @author Administrator
 *
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	/**
	 * @param providerId
	 * @param serviceProvider
	 * @param apiAdapter
	 */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
