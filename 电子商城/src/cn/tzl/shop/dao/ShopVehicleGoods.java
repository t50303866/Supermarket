package cn.tzl.shop.dao;

import cn.bmob.v3.BmobObject;

public class ShopVehicleGoods extends BmobObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String goodsName;
	private String goodsDescribe;
	private byte picture;
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsDescribe() {
		return goodsDescribe;
	}
	public void setGoodsDescribe(String goodsDescribe) {
		this.goodsDescribe = goodsDescribe;
	}
	public byte getPicture() {
		return picture;
	}
	public void setPicture(byte picture) {
		this.picture = picture;
	}
	
}
