package cn.tzl.shop.dao;

import java.util.ArrayList;
import java.util.List;



public class CartGoodsDao {
	public ArrayList<BuyedGoods> results;
	

	public ArrayList<BuyedGoods> getResults() {
		return results;
	}

	public void setResults(ArrayList<BuyedGoods> results) {
		this.results = results;
	}

	

	@Override
	public String toString() {
		return "CartGoodsDao [results=" + results + "]";
	}



	public class BuyedGoods{
		//":[{"BuyedUser":"a",
	//	"GoodsCount":"5",
//		"GoodsDescribe":"���������彡��������Ȼ���ʣ����к�Ԫ�ء��󵨷���Ϊ��ĺ��ӹ��򣡣�����",
//		"GoodsImage":"M02/41/EC/oYYBAFaSYa-Ae56gAAAgYwBnaxw631.jpg",
//		"GoodsName":"�̷�",
//		"GoodsPrice":"130",
//		"GoodsStyle":"��װ",
//		"createdAt":"2016-01-18 19:28:56",
//		"objectId":"bd0aac57b6",
//		"updatedAt":"2016-01-18 19:28:56"}

		
		private String objectId;  //Idֵ
		private String GreatedAt; //����ʱ��
		private String updatedAt; //����ʱ��
		private String GoodsName;  //��Ʒ����
		private String GoodsDescribe;//��Ʒ����
		private String GoodsCount; //��������
		private String GoodsStyles; //��Ʒ�ͺš����
		private String GoodsPrice; //��Ʒ�۸�
		private String BuyedUser;//������
		private String GoodsImage;
		public String getObjectId() {
			return objectId;
		}
		public void setObjectId(String objectId) {
			this.objectId = objectId;
		}
		public String getGreatedAt() {
			return GreatedAt;
		}
		public void setGreatedAt(String greatedAt) {
			GreatedAt = greatedAt;
		}
		public String getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(String updatedAt) {
			this.updatedAt = updatedAt;
		}
		public String getGoodsName() {
			return GoodsName;
		}
		public void setGoodsName(String goodsName) {
			GoodsName = goodsName;
		}
		public String getGoodsDescribe() {
			return GoodsDescribe;
		}
		public void setGoodsDescribe(String goodsDescribe) {
			GoodsDescribe = goodsDescribe;
		}
		public String getGoodsCount() {
			return GoodsCount;
		}
		public void setGoodsCount(String goodsCount) {
			GoodsCount = goodsCount;
		}
		public String getGoodsStyles() {
			return GoodsStyles;
		}
		public void setGoodsStyles(String goodsStyles) {
			GoodsStyles = goodsStyles;
		}
		public String getGoodsPrice() {
			return GoodsPrice;
		}
		public void setGoodsPrice(String goodsPrice) {
			GoodsPrice = goodsPrice;
		}
		public String getBuyedUser() {
			return BuyedUser;
		}
		public void setBuyedUser(String buyedUser) {
			BuyedUser = buyedUser;
		}
		public String getGoodsImage() {
			return GoodsImage;
		}
		public void setGoodsImage(String goodsImage) {
			GoodsImage = goodsImage;
		}
		@Override
		public String toString() {
			return "BuyedGoods [objectId=" + objectId + ", GreatedAt="
					+ GreatedAt + ", updatedAt=" + updatedAt + ", GoodsName="
					+ GoodsName + ", GoodsDescribe=" + GoodsDescribe
					+ ", GoodsCount=" + GoodsCount + ", GoodsStyles="
					+ GoodsStyles + ", GoodsPrice=" + GoodsPrice
					+ ", BuyedUser=" + BuyedUser + ", GoodsImage=" + GoodsImage
					+ "]";
		}
		
	}
}
