package cn.tzl.shop.dao;

import java.io.Serializable;
import java.net.Socket;

import android.os.Parcel;
import android.os.Parcelable;

public class Goodsdetails implements Parcelable{
	private String shopName;
	private String goodsName;
	private String goodsContent;
	private String url;
	
	public Goodsdetails() {
		super();
	}
	public Goodsdetails(Parcel src) {
		readFromParcel(src);
	}

	private void readFromParcel(Parcel src) {
		this.shopName = src.readString();
		this.goodsName = src.readString();
		this.goodsContent = src.readString();
		this.url = src.readString();
		
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsContent() {
		return goodsContent;
	}
	public void setGoodsContent(String goodsContent) {
		this.goodsContent = goodsContent;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Goodsdetails [shopName=" + shopName + ", goodsName="
				+ goodsName + ", goodsContent=" + goodsContent + ", url=" + url
				+ "]";
	}
	@Override
	public int describeContents() {
		
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(shopName);
		dest.writeString(goodsName);
		dest.writeString(goodsContent);
		dest.writeString(url);
		
	}
	public static final Parcelable.Creator<Goodsdetails> CREATOR = new Parcelable.Creator<Goodsdetails>() {

		@Override
		public Goodsdetails createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Goodsdetails(source);
		}

		@Override
		public Goodsdetails[] newArray(int size) {
			
			return new Goodsdetails[size];
		}
		
	};

	
}
