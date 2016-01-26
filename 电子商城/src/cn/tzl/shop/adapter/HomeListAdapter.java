package cn.tzl.shop.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.lidroid.xutils.BitmapUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import cn.tzl.shop.R;

import cn.tzl.shop.dao.GoodsSource;
import cn.tzl.shop.dao.GoodsSource.Goods;
import cn.tzl.shop.dao.Goodsdetails;
import cn.tzl.shop.globe.Globe;


import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeListAdapter extends BaseAdapter{
	private static final String TAG = "HomeListAdapter";
	protected static final int HANDLER_IMAGE_LOADER_SUCCESS = 0;
	private Activity mActivity;
	private List<Goods> data;
	private ListView listView;
	DisplayImageOptions options;
	
//private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
//	private Handler handler = new Handler(){
//		public void handleMessage(android.os.Message msg) {
//			switch (msg.what) {
//			case HANDLER_IMAGE_LOADER_SUCCESS:
//				//����ImageView
//				
//				int position = (Integer) msg.obj;
//				Log.i("log", "��������");
//				ImageView iv = (ImageView) listView.findViewWithTag(position);
//				List<String> imagePath = data.get(position).getGoodsImage();
//				String uri = Globe.BASICURL+imagePath.get(0);
//		    	ImageLoader.getInstance().displayImage(uri , iv);
//				break;
//
//			default:
//				break;
//			}
//		};
//	};
	
	
	public HomeListAdapter(Activity mActivity, List<Goods> data,ListView listView) {
		super();
		this.mActivity = mActivity;
		this.listView = listView;
		setData(data);
		// ʹ��DisplayImageOptions.Builder()����DisplayImageOptions
				options = new DisplayImageOptions.Builder()
					.showStubImage(R.drawable.ic_stub)			// ����ͼƬ�����ڼ���ʾ��ͼƬ
					.showImageForEmptyUri(R.drawable.ic_empty)	// ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
					.showImageOnFail(R.drawable.ic_error)		// ����ͼƬ���ػ��������з���������ʾ��ͼƬ	
					.cacheInMemory(true)						// �������ص�ͼƬ�Ƿ񻺴����ڴ���
					.cacheOnDisc(true)							// �������ص�ͼƬ�Ƿ񻺴���SD����
					.displayer(new RoundedBitmapDisplayer(20))	// ���ó�Բ��ͼƬ
					.build();									// �������ù���DisplayImageOption����
	}

	public void setData(List<Goods> data) {
		if(data == null){
			data = new ArrayList<Goods>();
		}
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Goods getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(mActivity, R.layout.home_list, null);			
			holder.goodsName = (TextView) convertView.findViewById(R.id.tv_goods_name);
			holder.goodsDescribe = (TextView) convertView.findViewById(R.id.tv_goods_content);
			holder.goodsImage = (ImageView) convertView.findViewById(R.id.iv_goods);
			holder.goodsPrice = (TextView) convertView.findViewById(R.id.tv_goods_price);
			holder.playmentCount = (TextView) convertView.findViewById(R.id.tv_playment_count);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		Log.i(TAG,"postion:"+ position);			
		Goods goods = data.get(position); 
		holder.goodsName.setText(goods.getGoodsName());
		holder.goodsDescribe.setText(goods.getGoodsDescribe());
		holder.goodsPrice.setText("��"+goods.getGoodsPrice());
		holder.playmentCount.setText("�Ѿ��۳�"+goods.getPlaymentCount());
		List<String> imagePath = goods.getGoodsImage();
		final String uri = Globe.BASICURL+imagePath.get(0);
		holder.goodsImage.setTag(uri);
		
		if(uri !=null && !"".equals(uri)){
			ImageLoader.getInstance().loadImage(uri,options, new SimpleImageLoadingListener(){
				@Override
				public void onLoadingComplete(String imageUri, View view,
						Bitmap loadedImage) {
					if(holder.goodsImage.getTag() !=null && holder.goodsImage.getTag().equals(uri)){
						holder.goodsImage.setImageBitmap(loadedImage);
					}
				}
			});
		}
		
    	ImageLoader.getInstance().displayImage(uri , holder.goodsImage);
//		Message msg = handler.obtainMessage();
//		msg.obj = position;
//		msg.what = HANDLER_IMAGE_LOADER_SUCCESS;
//		handler.sendMessage(msg);
		return convertView;
	}
	private static class ViewHolder{
//		private TextView shopName;
		private TextView goodsName;
		private TextView goodsDescribe;
		private ImageView goodsImage;
		private TextView  goodsPrice;
		private TextView  playmentCount;
	}
//	/**
//	 * ͼƬ���ص�һ����ʾ������
//	 * @author Administrator
//	 *
//	 */
//	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
//		
//		static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
//
//		@Override
//		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//			if (loadedImage != null) {
//				ImageView imageView = (ImageView) view;
//				// �Ƿ��һ����ʾ
//				boolean firstDisplay = !displayedImages.contains(imageUri);
//				if (firstDisplay) {
//					// ͼƬ����Ч��
//					FadeInBitmapDisplayer.animate(imageView, 500);
//					displayedImages.add(imageUri);
//				}
//			}
//		}
//	}
}
