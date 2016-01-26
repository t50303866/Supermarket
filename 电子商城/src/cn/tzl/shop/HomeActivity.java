package cn.tzl.shop;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import cn.tzl.shop.framgent.FramentFactory;




import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.drm.ProcessedData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class HomeActivity extends FragmentActivity{
//	private static final int DEFAULT_OFFSCREEN_PAGES = 0;
	private static final String TAG = "HomeActivity";
	private ViewPager vp;
	int lastPosition = 0;//上次显示的Fragment的position，用于显示新Fragment时，把旧的移除
	Object lastItem = null;//上次显示的Fragment对象，用于显示新Fragment时，把旧的移除
	private MainAdapter myAdapter;
	private RadioGroup rg;
	private ImageView ivYuan;
	private MyReceiveBroadCast receive;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		myAdapter = new MainAdapter(getSupportFragmentManager());
		initViews();
		setListeners();	
		receive = new MyReceiveBroadCast();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.tzl.yuan");
		registerReceiver(receive, filter);
	}

	private void initViews() {
		vp = (ViewPager) findViewById(R.id.vp_home);
		rg = (RadioGroup) findViewById(R.id.rg);
		ivYuan = (ImageView) findViewById(R.id.iv_yuan);
		
	}

	private void setListeners() {
		//准备Pager之后设置Viewpager的Adapter
		vp.setOffscreenPageLimit(0);
		vp.setAdapter(new MainAdapter(getSupportFragmentManager()));

		//手动初始化首页数据
		FramentFactory.createFragment(0);
		//mPagerList.get(0).initData();
		rg.check(R.id.rb_home);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				//int position = 0;
				switch (checkedId) {
				case R.id.rb_home:
					//position = 0;
					vp.setCurrentItem(0, false);	
					break;
				case R.id.rb_community:
					//position = 0;
					vp.setCurrentItem(1, false);
					break;
				case R.id.rb_information:
					//position = 0;
					vp.setCurrentItem(2, false);
					break;
				case R.id.rb_shop_vehicle:
					//position = 0;
					ivYuan.setVisibility(View.GONE);
					vp.setCurrentItem(3, false);
					break;
				case R.id.rb_me:
					//position = 0;
					vp.setCurrentItem(4, false);
					break;
				default:
					break;
				}
			}
		});
		
		vp.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	
	
	private class MainAdapter extends FragmentStatePagerAdapter{
		public MainAdapter(FragmentManager fm) {		
			super(fm);
		}
		//每个条目返回的fragment
		@Override
		public Fragment getItem(int position) {
			return FramentFactory.createFragment(position);
		}
		//一共有几个条目
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			super.destroyItem(container, position, object);
		}
		

	}
	
	public class MyReceiveBroadCast extends BroadcastReceiver{

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			ivYuan.setVisibility(View.VISIBLE);
			
		}
		
	}
	
	@Override
		protected void onDestroy() {
			unregisterReceiver(receive);
			super.onDestroy();
			
		}
}
