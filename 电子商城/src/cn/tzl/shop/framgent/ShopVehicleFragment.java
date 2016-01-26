package cn.tzl.shop.framgent;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobRealTimeData;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.ValueEventListener;

import cn.tzl.shop.R;
import cn.tzl.shop.adapter.HomeListAdapter;
import cn.tzl.shop.adapter.ShopGoodsListAdapter;
import cn.tzl.shop.dao.ShopVehicleGoods;
import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShopVehicleFragment extends BaseFragment {

	private ListView lvShopGoods;
	private List<ShopVehicleGoods> mListShopGoods;
	private ShopGoodsListAdapter shopGoodsAdapter;
	@Override
	public View createSuccessView() {
		Log.i("HomeFragment",""+ 44444);
		mListShopGoods = new ArrayList<ShopVehicleGoods>();
		View view = View.inflate(getActivity(), R.layout.shop_vehicle_fragment, null);
		lvShopGoods = (ListView) view.findViewById(R.id.lv_shop_vehicle);
		shopGoodsAdapter = new ShopGoodsListAdapter(getActivity(), mListShopGoods);
		lvShopGoods.setAdapter(shopGoodsAdapter);
		initData();
		listenerData();
		return view;
	}


private void initData() {
	/*List<Music> musicList = md.getMusicList();
	musics.clear();
	musics.addAll(musicList);
	musicAdapter.notifyDataSetChanged();*/
		BmobQuery<ShopVehicleGoods> query = new BmobQuery<ShopVehicleGoods>();
		query.findObjects(getActivity(), new FindListener<ShopVehicleGoods>() {
			
			@Override
			public void onSuccess(List<ShopVehicleGoods> arg0) {
				for (ShopVehicleGoods shopVehicleGoods : arg0) {
					mListShopGoods.add(shopVehicleGoods);
				}
				
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		shopGoodsAdapter.notifyDataSetChanged();	
	}

private void listenerData() {
	BmobRealTimeData rtd = new BmobRealTimeData();
	rtd.start(getActivity(), new ValueEventListener() {
		@Override
		public void onDataChange(JSONObject arg0) {
			BmobQuery<ShopVehicleGoods> query = new BmobQuery<ShopVehicleGoods>();
			query.findObjects(getActivity(), new FindListener<ShopVehicleGoods>() {
				
				@Override
				public void onSuccess(List<ShopVehicleGoods> arg0) {
					for (ShopVehicleGoods shopVehicleGoods : arg0) {
						mListShopGoods.add(shopVehicleGoods);
					}
					
				}
				
				@Override
				public void onError(int arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}
			});
			shopGoodsAdapter.notifyDataSetChanged();	
			
		}
		
		@Override
		public void onConnectCompleted() {
			// TODO Auto-generated method stub
			
		}
	});
	if(rtd.isConnected()){
		rtd.subTableUpdate("ShopVehicleGoods");
		
	}
}

	


}
