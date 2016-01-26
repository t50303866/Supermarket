package cn.tzl.shop.adapter;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.BitmapUtils;

import cn.tzl.shop.R;
import cn.tzl.shop.dao.Goodsdetails;
import cn.tzl.shop.dao.ShopVehicleGoods;

import android.app.Activity;
import android.app.ListActivity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ShopGoodsListAdapter extends BaseAdapter{
	private static final String TAG = "HomeListAdapter";
	private Activity mActivity;
	private List<ShopVehicleGoods> data;

	public ShopGoodsListAdapter(Activity mActivity, List<ShopVehicleGoods> data) {
		super();
		this.mActivity = mActivity;
		setData(data);
	}

	public void setData(List<ShopVehicleGoods> data) {
		if(data == null){
			data = new ArrayList<ShopVehicleGoods>();
		}
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(mActivity, R.layout.home_list, null);
			
			holder.goodsName = (TextView) convertView.findViewById(R.id.tv_goods_name);
			holder.goodsDescribe = (TextView) convertView.findViewById(R.id.tv_goods_content);
			holder.goodsImage = (ImageView) convertView.findViewById(R.id.iv_goods);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		Log.i(TAG,"postion:"+ position);
		ShopVehicleGoods detials = data.get(position);
		
		holder.goodsName.setText(detials.getGoodsName());
		holder.goodsDescribe.setText(detials.getGoodsDescribe());
		//TODO �ֽڱ�ΪͼƬ
		BitmapUtils utils = new BitmapUtils(mActivity);

		return convertView;
	}
	private static class ViewHolder{
//		private TextView shopName;
		private TextView goodsName;
		private TextView goodsDescribe;
		private ImageView goodsImage;
		
	}
}
