package cn.tzl.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.AsyncCustomEndpoints;
import cn.bmob.v3.listener.CloudCodeListener;
import cn.tzl.shop.application.MyApplication;
import cn.tzl.shop.dao.GoodsSource;
import cn.tzl.shop.dao.GoodsSource.Goods;
import cn.tzl.shop.globe.Globe;

import com.bmob.btp.e.a.What;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GoodsShowActivity extends Activity {
	private ImageView ivGoods;
	private TextView tvGoodsName;
	private TextView tvGoodsDescribe;
	private Spinner spStyle;
	private TextView tvGoodsNumber;
	private TextView tvSubduction;
	private TextView tvAdd;
	private EditText etNumber;
	List<Goods> mGoodsData;
	protected static final int GETGOODSSUCCESS = 0;
	protected static final int ADDSHOPPINGCARTSSUCCESS = 1;
	private String goodsId;
	private SharedPreferences sp;
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case GETGOODSSUCCESS:
				settingPage(mGoodsData);
				break;
			case ADDSHOPPINGCARTSSUCCESS:
				Intent intent = new Intent();
				intent.setAction("com.tzl.yuan");
				sendBroadcast(intent);
				break;
			default:
				break;
			}
		};
	};
	private DisplayImageOptions options;
	private Goods goods;
	private String username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goods_show);
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_stub)			// ����ͼƬ�����ڼ���ʾ��ͼƬ
		.showImageForEmptyUri(R.drawable.ic_empty)	// ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
		.showImageOnFail(R.drawable.ic_error)		// ����ͼƬ���ػ��������з���������ʾ��ͼƬ	
		.cacheInMemory(true)						// �������ص�ͼƬ�Ƿ񻺴����ڴ���
		.cacheOnDisc(true)							// �������ص�ͼƬ�Ƿ񻺴���SD����
		.displayer(new RoundedBitmapDisplayer(20))	// ���ó�Բ��ͼƬ
		.build();			
		setData();
		setViews();
//		
//		sp = getSharedPreferences("config", MODE_PRIVATE);
//		username = sp.getString("name", "");
	}

	private void setData() {
		Intent intent = getIntent();
		goodsId = intent.getStringExtra("goodsId");	
		AsyncCustomEndpoints ace = new AsyncCustomEndpoints();
		//��һ�������������Ķ��󣬵ڶ����������ƶ˴���ķ������ƣ��������������ϴ����ƶ˴���Ĳ����б�JSONObject cloudCodeParams�������ĸ������ǻص���
		Map<String, String> obj = new HashMap<String, String>();
		obj.put("goodsId", goodsId);
		JSONObject jobj = new JSONObject(obj);
		ace.callEndpoint(this, "queryOneGoods", jobj, 
				new CloudCodeListener() {			
			@Override
			public void onSuccess(Object object) {							
				Log.i("��Ʒ����", object.toString());
				try {
					JSONObject obj = new JSONObject(object.toString());
					String results = obj.getString("results");
					Gson gson = new Gson();
					GoodsSource gSource = gson.fromJson(results,GoodsSource.class);
					Log.i("������Ʒ����", gSource.toString());
					mGoodsData = gSource.results;
					handler.sendEmptyMessage(GETGOODSSUCCESS);	
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
			@Override
			public void onFailure(int code, String msg) {
				Toast.makeText(GoodsShowActivity.this, "����"+ msg, Toast.LENGTH_SHORT).show();			
			}
		});	

	}

	protected void getGoodsData() {

	}

	private void setViews() {
		ivGoods = (ImageView) findViewById(R.id.iv_goods);
		tvGoodsName = (TextView) findViewById(R.id.tv_goods_name);
		tvGoodsDescribe = (TextView) findViewById(R.id.tv_goods_content);
		spStyle = (Spinner) findViewById(R.id.sp_style);
		tvGoodsNumber = (TextView) findViewById(R.id.tv_goods_number);
		tvSubduction = (TextView) findViewById(R.id.tv_subduction);
		tvAdd = (TextView) findViewById(R.id.tv_add);
		etNumber = (EditText) findViewById(R.id.et_number);

	}
	public void settingPage(List<Goods> result){
		goods = result.get(0);
		Log.i("One Goods", result.toString());
		setImage(goods.getGoodsImage());
		tvGoodsName.setText(goods.getGoodsName());
		tvGoodsDescribe.setText("\t"+goods.getGoodsDescribe());
		tvGoodsNumber.setText(goods.getGoodsCount());		
		/**
		 * �ؼ��Ӽ����������õ�֮����ܽ��м���������Adapter
		 */
		//setListeners();
		/**
		 * ����Adapter
		 */
		setAdapters(goods.getGoodsStyles());
	}

	/**
	 * �����������Ӽ��ٵ���¼�����
	 * @param v
	 */
	public void addAndSubduction(View v){
		String string = etNumber.getText().toString();
		int number = Integer.parseInt(string);
		switch (v.getId()) {
		case R.id.tv_subduction:
			number--;
			if(number <= 1){
				number = 1;
			}
			if(number > Integer.parseInt(goods.getGoodsCount())){
				etNumber.setText(goods.getGoodsCount());
			}else {

				etNumber.setText(number+"");
			}
			break;
		case R.id.tv_add:
			number++;
			if(number > Integer.parseInt(goods.getGoodsCount())){
				etNumber.setText(goods.getGoodsCount());
			}else{

				etNumber.setText(number+"");
			}
			break;
		default:
			break;
		}
	}

	/**
	 * ���򡢼��빺�ﳵ��ť����¼�
	 * @param v
	 */
	public void buyGoods(View v){
		switch (v.getId()) {
		case R.id.bt_buy:
			
			break;
		case R.id.bt_add_shop_vehicle:
			Map<String, String> map = new HashMap<String, String>();
			map.put("goodsName", goods.getGoodsName());
			map.put("goodsDescribe", goods.getGoodsDescribe());
			map.put("goodsCount",etNumber.getText().toString());
			map.put("goodsImage", goods.getGoodsImage().get(0));
			map.put("goodsStyle",spStyle.getSelectedItem().toString());
			map.put("buyedUser", MyApplication.username);
			map.put("goodsPrice", goods.getGoodsPrice());
			JSONObject obj = new JSONObject(map);
			AsyncCustomEndpoints ace = new AsyncCustomEndpoints();
			ace.callEndpoint(this, "insertShoppingCart", obj, 
					new CloudCodeListener() {			
				@Override
				public void onSuccess(Object object) {								
					Toast.makeText(GoodsShowActivity.this, object.toString(), Toast.LENGTH_SHORT).show();			
					handler.sendEmptyMessage(ADDSHOPPINGCARTSSUCCESS);
				}
				@Override
				public void onFailure(int code, String msg) {
					Toast.makeText(GoodsShowActivity.this, "����"+ msg, Toast.LENGTH_SHORT).show();			
				}
			});		
			break;
		default:
			break;
		}
	}

	private void setAdapters(List<String> list) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spStyle.setAdapter(adapter);
	}

	/**
	 * ����ͼƬ
	 * @param goodsImage ͼƬ·������
	 */
	private void setImage(List<String> goodsImage) {
		final String uri = Globe.BASICURL+goodsImage.get(0);

		ImageLoader.getInstance().loadImage(uri,options, new SimpleImageLoadingListener(){
			@Override
			public void onLoadingComplete(String imageUri, View view,
					Bitmap loadedImage) {
				ivGoods.setImageBitmap(loadedImage);
			}
		});
		ImageLoader.getInstance().displayImage(uri , ivGoods);

	}

}
