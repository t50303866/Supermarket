package cn.tzl.shop.dao;

import android.graphics.Bitmap;
import cn.bmob.v3.BmobObject;

public class MyBitmap extends BmobObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Bitmap photo;
	public Bitmap getPhoto() {
		return photo;
	}
	public void setPhoto(Bitmap photo) {
		this.photo = photo;
	}
	
	
}
