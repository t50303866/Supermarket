package cn.tzl.shop.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsSource {
	public ArrayList<Goods> results;

	public ArrayList<Goods> getGoodslist() {
		return results;
	}

	public void setGoodslist(ArrayList<Goods> goodslist) {
		this.results = goodslist;
	}

	@Override
	public String toString() {
		return "GoodsSource [goodslist=" + results + "]";
	}

	public class Goods{
		
//		{"results":[{"createdAt":"2016-01-10 23:19:51",
//		"goodsCount":"632","goodsDescribe":"���ܡ�������ʡ�ռ�",
//		"goodsImage":["M02/42/1A/oYYBAFaSdoGAV3XSAADdScpIomU834.jpg"],
//		"goodsName":"�յ�","goodsStye":"����յ�","objectId":"SHv0IIIy",
//		"updatedAt":"2016-01-10 23:20:51"}]}
//	}

		
		private String objectId;  //Idֵ
		private String createdAt; //����ʱ��
		private String updatedAt; //����ʱ��
		private String goodsName;  //��Ʒ����
		private String goodsDescribe;//��Ʒ����
		private String goodsCount; //��Ʒ�������
		private List<String> goodsStyles; //��Ʒ�ͺš����
		private String goodsPrice; //��Ʒ�۸�
		private String playmentCount;//�Ѿ��۳�����

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
		
		public List<String> getGoodsStyles() {
			return goodsStyles;
		}
		public void setGoodsStyles(List<String> goodsStyles) {
			this.goodsStyles = goodsStyles;
		}
		public String getObjectId() {
			return objectId;
		}
		public void setObjectId(String objectId) {
			this.objectId = objectId;
		}
		
		public String getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}
		public String getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(String updatedAt) {
			this.updatedAt = updatedAt;
		}
		public List<String> getGoodsImage() {
			return goodsImage;
		}
		public void setGoodsImage(List<String> goodsImage) {
			this.goodsImage = goodsImage;
		}
	
		
		public String getGoodsPrice() {
			return goodsPrice;
		}
		public void setGoodsPrice(String goodsPrice) {
			this.goodsPrice = goodsPrice;
		}
		public String getPlaymentCount() {
			return playmentCount;
		}
		public void setPlaymentCount(String playmentCount) {
			this.playmentCount = playmentCount;
		}
		@Override
		public String toString() {
			return "Goods [objectId=" + objectId + ", createdAt=" + createdAt
					+ ", updatedAt=" + updatedAt + ", goodsName=" + goodsName
					+ ", goodsDescribe=" + goodsDescribe + ", goodsCount="
					+ goodsCount + ", goodsStye=" + goodsStyles + ", goodsPrice="
					+ goodsPrice + ", playmentCount=" + playmentCount
					+ ", goodsImage=" + goodsImage + "]";
		}
	
		
		
	}

}
