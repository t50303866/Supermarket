package cn.tzl.shop.view;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MyToast{
	/**
	 * 窗体管理者
	 */
	private WindowManager wm;
	Activity mActivity;
	
	
	public MyToast( Activity mActivity) {
		this.mActivity = mActivity;
		
	}


	public void myToast(String address){
	
		TextView tv = new TextView(mActivity);
		tv.setText(address);
		tv.setTextSize(20);
		tv.setTextColor(0xf00);
	}
}
