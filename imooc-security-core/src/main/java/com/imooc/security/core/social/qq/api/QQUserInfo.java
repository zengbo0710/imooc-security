/**
 * 
 */
package com.imooc.security.core.social.qq.api;

/**
 * @author Administrator
 *
 */
public class QQUserInfo {
	/**
	 * 	返回码
	 */
	private String ret;
	/**
	 * 如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。
	 */
	private String msg;
	/**
	 * 
	 */
	private String openId;
	/**
	 * 不知道什么东西，文档上没写，但是实际api返回里有。
	 */
	private String is_lost;
	/**
	 * 省(直辖市)
	 */
	private String province;
	/**
	 * 市(直辖市区)
	 */
	private String city;
	/**
	 * 出生年月
	 */
	private String year;
	/**
	 * 	用户在QQ空间的昵称。
	 */
	private String nickname;
	/**
	 * 	大小为30×30像素的QQ空间头像URL。
	 */
	private String figureurl;
	/**
	 * 	大小为50×50像素的QQ空间头像URL。
	 */
	private String figureurl_1;
	/**
	 * 	大小为100×100像素的QQ空间头像URL。
	 */
	private String figureurl_2;
	/**
	 * 	大小为40×40像素的QQ头像URL。
	 */
	private String figureurl_qq_1;
	/**
	 * 	大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100×100的头像，但40×40像素则是一定会有。
	 */
	private String figureurl_qq_2;
	/**
	 * 	性别。 如果获取不到则默认返回”男”
	 */
	private String gender;
	/**
	 * 	标识用户是否为黄钻用户（0：不是；1：是）。
	 */
	private String is_yellow_vip;
	/**
	 * 	标识用户是否为黄钻用户（0：不是；1：是）
	 */
	private String vip;
	/**
	 * 	黄钻等级
	 */
	private String yellow_vip_level;
	/**
	 * 	黄钻等级
	 */
	private String level;
	/**
	 * 标识是否为年费黄钻用户（0：不是； 1：是）
	 */
	private String is_yellow_year_vip;
	/**
	 * @return the ret
	 */
	public String getRet() {
		return ret;
	}
	/**
	 * @param ret the ret to set
	 */
	public void setRet(String ret) {
		this.ret = ret;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/**
	 * @return the is_lost
	 */
	public String getIs_lost() {
		return is_lost;
	}
	/**
	 * @param is_lost the is_lost to set
	 */
	public void setIs_lost(String is_lost) {
		this.is_lost = is_lost;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the figureurl
	 */
	public String getFigureurl() {
		return figureurl;
	}
	/**
	 * @param figureurl the figureurl to set
	 */
	public void setFigureurl(String figureurl) {
		this.figureurl = figureurl;
	}
	/**
	 * @return the figureurl_1
	 */
	public String getFigureurl_1() {
		return figureurl_1;
	}
	/**
	 * @param figureurl_1 the figureurl_1 to set
	 */
	public void setFigureurl_1(String figureurl_1) {
		this.figureurl_1 = figureurl_1;
	}
	/**
	 * @return the figureurl_2
	 */
	public String getFigureurl_2() {
		return figureurl_2;
	}
	/**
	 * @param figureurl_2 the figureurl_2 to set
	 */
	public void setFigureurl_2(String figureurl_2) {
		this.figureurl_2 = figureurl_2;
	}
	/**
	 * @return the figureurl_qq_1
	 */
	public String getFigureurl_qq_1() {
		return figureurl_qq_1;
	}
	/**
	 * @param figureurl_qq_1 the figureurl_qq_1 to set
	 */
	public void setFigureurl_qq_1(String figureurl_qq_1) {
		this.figureurl_qq_1 = figureurl_qq_1;
	}
	/**
	 * @return the figureurl_qq_2
	 */
	public String getFigureurl_qq_2() {
		return figureurl_qq_2;
	}
	/**
	 * @param figureurl_qq_2 the figureurl_qq_2 to set
	 */
	public void setFigureurl_qq_2(String figureurl_qq_2) {
		this.figureurl_qq_2 = figureurl_qq_2;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the is_yellow_vip
	 */
	public String getIs_yellow_vip() {
		return is_yellow_vip;
	}
	/**
	 * @param is_yellow_vip the is_yellow_vip to set
	 */
	public void setIs_yellow_vip(String is_yellow_vip) {
		this.is_yellow_vip = is_yellow_vip;
	}
	/**
	 * @return the vip
	 */
	public String getVip() {
		return vip;
	}
	/**
	 * @param vip the vip to set
	 */
	public void setVip(String vip) {
		this.vip = vip;
	}
	/**
	 * @return the yellow_vip_level
	 */
	public String getYellow_vip_level() {
		return yellow_vip_level;
	}
	/**
	 * @param yellow_vip_level the yellow_vip_level to set
	 */
	public void setYellow_vip_level(String yellow_vip_level) {
		this.yellow_vip_level = yellow_vip_level;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * @return the is_yellow_year_vip
	 */
	public String getIs_yellow_year_vip() {
		return is_yellow_year_vip;
	}
	/**
	 * @param is_yellow_year_vip the is_yellow_year_vip to set
	 */
	public void setIs_yellow_year_vip(String is_yellow_year_vip) {
		this.is_yellow_year_vip = is_yellow_year_vip;
	}
	
}
