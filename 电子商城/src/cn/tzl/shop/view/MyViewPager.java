package cn.tzl.shop.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;

public class MyViewPager extends ViewPager {
    private static final int DEFAULT_OFFSCREEN_PAGES = 0;
    private int mOffscreenPageLimit = DEFAULT_OFFSCREEN_PAGES;

	public MyViewPager(Context context) {
		super(context, null);
		// TODO Auto-generated constructor stub
	}
	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	 public void setOffscreenPageLimit(int limit) {
	        if (limit < DEFAULT_OFFSCREEN_PAGES) {
	            Log.w("", "Requested offscreen page limit " + limit + " too small; defaulting to " +
	                    DEFAULT_OFFSCREEN_PAGES);
	            limit = DEFAULT_OFFSCREEN_PAGES;
	        }
	        if (limit != mOffscreenPageLimit) {
	            mOffscreenPageLimit = limit;
	          
	        }
	    }

}
