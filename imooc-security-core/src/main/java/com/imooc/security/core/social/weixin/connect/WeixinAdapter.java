/**
 * 
 */
package com.imooc.security.core.social.weixin.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.imooc.security.core.social.weixin.api.Weixin;
import com.imooc.security.core.social.weixin.api.WeixinUserInfo;

/**
 * @author Administrator
 *
 */
public class WeixinAdapter implements ApiAdapter<Weixin> {

	private String openId;
	
	public WeixinAdapter() {}
	
	public WeixinAdapter(String openId){
		this.openId = openId;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ApiAdapter#test(java.lang.Object)
	 */
	@Override
	public boolean test(Weixin api) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ApiAdapter#setConnectionValues(java.lang.Object, org.springframework.social.connect.ConnectionValues)
	 */
	@Override
	public void setConnectionValues(Weixin api, ConnectionValues values) {
		WeixinUserInfo profile = api.getUserInfo(openId);
		values.setProviderUserId(profile.getOpenid());
		values.setDisplayName(profile.getNickname());
		values.setImageUrl(profile.getHeadimgurl());	
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ApiAdapter#fetchUserProfile(java.lang.Object)
	 */
	@Override
	public UserProfile fetchUserProfile(Weixin api) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.ApiAdapter#updateStatus(java.lang.Object, java.lang.String)
	 */
	@Override
	public void updateStatus(Weixin api, String message) {	
	}

}
