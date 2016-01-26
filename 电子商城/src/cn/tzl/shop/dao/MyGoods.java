package cn.tzl.shop.dao;

import java.util.Date;
import java.util.List;

public class MyGoods{
	
//	{"results":[{"createdAt":"2016-01-10 23:19:51",
//	"goodsCount":"632","goodsDescribe":"智能、环保、省空间",
//	"goodsImage":["M02/42/1A/oYYBAFaSdoGAV3XSAADdScpIomU834.jpg"],
//	"goodsName":"空调","goodsStye":"中央空调","objectId":"SHv0IIIy",
//	"updatedAt":"2016-01-10 23:20:51"}]}
//}

	
	private String objectId;
	private Date createdAt;
	private Date updatedAt;
	private String goodsName;
	private String goodsDescribe;
	private String goodsCount;
	private String goodsStye;
	private List<String> goodsImage;
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
	public String getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(String goodsCount) {
		this.goodsCount = goodsCount;
	}
	public String getGoodsStye() {
		return goodsStye;
	}
	public void setGoodsStyle(String goodsStyle) {
		this.goodsStye = goodsStyle;
	}	


	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdateAt() {
		return updatedAt;
	}
	public void setUpdateAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<String> getGoodsImage() {
		return goodsImage;
	}
	public void setGoodsImage(List<String> goodsImage) {
		this.goodsImage = goodsImage;
	}
	public void setGoodsStye(String goodsStye) {
		this.goodsStye = goodsStye;
	}
	@Override
	public String toString() {
		return "Goods [goodsName=" + goodsName + ", goodsDescribe="
				+ goodsDescribe + ", goodsCount=" + goodsCount + ", goodsStye="
				+ goodsStye + ", goodsImage=" + goodsImage + 
				 "]";
	}
	
	
}
