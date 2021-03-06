package cn.tzl.shop.framgent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadPoolExecutor;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.cache.MD5FileNameGenerator;

import cn.bmob.v3.AsyncCustomEndpoints;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.CloudCodeListener;
import cn.bmob.v3.listener.FindListener;

import cn.tzl.shop.GoodsShowActivity;
import cn.tzl.shop.HomeActivity;
import cn.tzl.shop.R;
import cn.tzl.shop.R.string;
import cn.tzl.shop.adapter.HomeListAdapter;
import cn.tzl.shop.dao.GoodsSource;
import cn.tzl.shop.dao.MyGoods;
import cn.tzl.shop.dao.GoodsSource.Goods;
import cn.tzl.shop.refresh.ui.PullToRefreshBase;
import cn.tzl.shop.refresh.ui.PullToRefreshBase.OnRefreshListener;
import cn.tzl.shop.refresh.ui.PullToRefreshListView;
import cn.tzl.shop.view.TopImagerViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class HomeFragment extends BaseFragment implements OnPageChangeListener{
	private static final int GETGOODSSUCCESS = 0;
	private View view;
	private ViewPager topImager;
	private PullToRefreshListView ptrlv_test;
	private ListView lvGoods;
	private List<Goods> mGoodsData;
	private List<Goods> mOneGoodsData;
	private Handler mhandler;
	private Timer timer;
	boolean first = true;
	private View headerView;
	private boolean isPullDownRefresh=true;//判断是下拉，还是上拉的标记
	private int mCurrentIndex=0;//当前条目的位置
	private SimpleDateFormat mDateFormat = new SimpleDateFormat("MM-dd HH:mm");//日期格式
	private String[] imageSrc = {
			"assets/image/belle.JPG","assets/image/belle.JPG","assets/image/belle.JPG"
	};
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GETGOODSSUCCESS:
					if(first){
						mGoodsData.addAll(mOneGoodsData);
						setAdapter();
						first=false;
					}else{
						mGoodsData.addAll(mOneGoodsData);
						adapter.notifyDataSetChanged();
						
						ptrlv_test.onPullUpRefreshComplete();
						//把当前是否是有数据的状态传过去
						ptrlv_test.setHasMoreData(hasMoreData);	
					}
					
			
				break;

			default:
				break;
			}
		};
	};

	private HomeListAdapter adapter;
	private int start;
	private boolean hasMoreData;	
	@Override
	public View createSuccessView() {
		mGoodsData = new ArrayList<Goods>();
		mOneGoodsData = new ArrayList<Goods>();
		Log.i("HomeFragment",""+ 111111);
		view = View.inflate(getActivity(), R.layout.home_fragment, null);
		headerView = View.inflate(getActivity(), R.layout.list_heart_view_home, null);
		initWeight();
		//得到真正的listView
		lvGoods = ptrlv_test.getRefreshableView();

		//设置当前上拉加载不可用
		ptrlv_test.setPullLoadEnabled(false);
		//设置下拉刷新可用
		ptrlv_test.setPullRefreshEnabled(true);
		//设置上拉加载可用
		ptrlv_test.setPullLoadEnabled(true);
		//滑到底部是否自动加载数据，这句话一定要加要不然"已经到底啦"显示不出来
		ptrlv_test.setScrollLoadEnabled(true);

		initData();
		setListeners();
		//以头布局的形式加载给listView
		lvGoods.addHeaderView(headerView);
		
		ptrlv_test.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onPullDownToRefresh(
				PullToRefreshBase<ListView> refreshView) {
				isPullDownRefresh=true;
				setChangeAdapter();
			}

			@Override
			public void onPullUpToRefresh(
				PullToRefreshBase<ListView> refreshView) {
				isPullDownRefresh=false;
				setChangeAdapter();
			}
			
		});
		setLastUpdateTime();
		
		
		return view;
	}
	protected void setChangeAdapter() {

		hasMoreData = true;
		//判断是上拉，还是下拉
		if(isPullDownRefresh){
			adapter.notifyDataSetChanged();
			ptrlv_test.onPullDownRefreshComplete();
			
			//把当前是否是有数据的状态传过去
			ptrlv_test.setHasMoreData(hasMoreData);	
		}else{
			start = mCurrentIndex;
			int end=start+10;
			if(end>=mGoodsData.size()){
				end=mGoodsData.size();
				hasMoreData=false;
				if(!hasMoreData){
					//设置上拉加载可用
					ptrlv_test.setPullLoadEnabled(false);
				}
			}
//				
		initData();
//			mCurrentIndex=end;
		}
		
	}
	private void initData() {		
		AsyncCustomEndpoints ace = new AsyncCustomEndpoints();
		//第一个参数是上下文对象，第二个参数是云端代码的方法名称，第三个参数是上传到云端代码的参数列表（JSONObject cloudCodeParams），第四个参数是回调类
		Map<String, String> obj = new HashMap<String, String>();
		int lastPosition = lvGoods.getLastVisiblePosition()-1;
		String count = ""+lastPosition;
		obj.put("number", ""+count);
		JSONObject jobj = new JSONObject(obj);
		ace.callEndpoint(getActivity(), "queryShoppingGoods", jobj, 
				new CloudCodeListener() {
			@Override
			public void onSuccess(Object object) {							
				Log.i("物品数据", object.toString());
				//						Gson gson = new Gson();	
				try {
					JSONObject obj = new JSONObject(object.toString());
					String results = obj.getString("results");
					Log.i("解析物品数据", results);
					Gson gson = new Gson();
					GoodsSource gSource = gson.fromJson(results,GoodsSource.class);
					Log.i("解析物品数据", gSource.toString());
					mOneGoodsData = gSource.results;	
					handler.sendEmptyMessage(GETGOODSSUCCESS);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			@Override
			public void onFailure(int code, String msg) {
				Toast.makeText(getActivity(), "错误："+ msg, Toast.LENGTH_SHORT).show();

			}
		});	
		
		
		
	}
	
	
	
	
	private void initWeight() {
		topImager = (ViewPager) headerView.findViewById(R.id.tivp_home);
		ptrlv_test = (PullToRefreshListView) view.findViewById(R.id.lv_home);
	}

	private void setListeners() {
		topImager.setAdapter(new MyViewPagerAdapter());
		topImager.setCurrentItem(1000*imageSrc.length);
		topImager.setOnPageChangeListener(this);		
		lvGoods.setOnItemClickListener(new MyOnItemClickListener());
	}
	public void setAdapter(){
		adapter = new HomeListAdapter(getActivity(), mGoodsData,lvGoods);
		lvGoods.setAdapter(adapter);
	}
	class MyOnItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			//获得第position商品的标签
			//封装到Intent中
			//跳转到GoodsActivi，GoodsActivity通过商品的标签请求服务器，得到数据
			Intent intent = new Intent(getActivity(), GoodsShowActivity.class);
			intent.putExtra("goodsId",mGoodsData.get(position-1).getObjectId());
			startActivity(intent);

		}

	}
	class MyViewPagerAdapter extends PagerAdapter{
		LinkedList<ImageView> convertView = new LinkedList<ImageView>();
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			int index = position % imageSrc.length;

			ImageView view;
			if(convertView.size() > 0){
				view = convertView.remove(0);
			}else{
				view = new ImageView(getActivity());	
			}

			view.setScaleType(ScaleType.FIT_XY);

			BitmapUtils utils = new BitmapUtils(getActivity());
			utils.display(view, imageSrc[index]);
			container.addView(view);

			//image.setOnTouchListener(new HeartImageTouchListener());

			return view;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			ImageView view = (ImageView) object;
			convertView.add(view);
			container.removeView((View) object);
		}


	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onPageSelected(int arg0) {


	}

	private class AutoPlayViewPagerThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	
	private void setLastUpdateTime() {
		 String text = formatDateTime(System.currentTimeMillis());
	     ptrlv_test.setLastUpdatedLabel(text);
		
	}
	
	private String formatDateTime(long time) {
       if (0 == time) {
           return "";
       }
       
       return mDateFormat.format(new Date(time));
   }

}
