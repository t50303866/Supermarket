package cn.tzl.shop.view;

import android.content.Context;

import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewpager extends MyViewPager {
	public NoScrollViewpager(Context context) {
		super(context,null);
		// TODO Auto-generated constructor stub
	}

	public NoScrollViewpager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}


}
