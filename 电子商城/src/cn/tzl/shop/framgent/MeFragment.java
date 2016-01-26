package cn.tzl.shop.framgent;

import java.io.File;
import java.util.List;

import com.lidroid.xutils.BitmapUtils;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import cn.tzl.shop.LoginActivity;
import cn.tzl.shop.R;
import cn.tzl.shop.SettingActivity;
import cn.tzl.shop.dao.MyBitmap;
import cn.tzl.shop.viewUtils.ViewUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.BoringLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MeFragment extends BaseFragment implements OnClickListener {
	private TextView tvSetting;
	private View view;
	private ImageView ivHeadPortrait;


	public View createSuccessView() {
		Log.i("HomeFragment",""+ 5555);
		view = View.inflate(getActivity(), R.layout.me_pager, null);
		initsWight();
		setListsteners();
		return view;
	}

	private void initsWight() {
		tvSetting = (TextView) view.findViewById(R.id.tv_setting);
		ivHeadPortrait = (ImageView) view.findViewById(R.id.iv_head_portrait);
		btAddGoods = (Button) view.findViewById(R.id.bt_add_goods_me);
		
		ivHeadPortrait.setImageResource(R.drawable.default_head);
	}

	private void setListsteners() {
		tvSetting.setOnClickListener(this);
		ivHeadPortrait.setOnClickListener(this);
		btAddGoods.setOnClickListener(this);
	}
	private Button btPhotoPlbum;
	private Button btPidicon;
	private AlertDialog dialog;
	private Button btAddGoods;
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_setting:
			startActivity(new Intent(getActivity(), SettingActivity.class));
			break;
		case R.id.iv_head_portrait:
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			View view = View.inflate(getActivity(), R.layout.my_dialog_heart_portrait, null);
			btPhotoPlbum = (Button) view.findViewById(R.id.bt_photo_album);
			btPidicon = (Button) view.findViewById(R.id.bt_vidicon);
			
			btPhotoPlbum.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Log.i("MeFragment", "相册");
					Intent intent = new Intent(Intent.ACTION_PICK,null);
					intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
					startActivityForResult(intent, 1);
					dialog.dismiss();
				}
			});
			btPidicon.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Log.i("MeFragment", "拍摄");
				
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(
							new File(Environment.getExternalStorageDirectory(),"xiaoma.jpg")));
				startActivityForResult(intent, 2);
				dialog.dismiss();
				}
			});
			//builder.setView(view);
			
			//builder.setCancelable(false);
			dialog = builder.create();
			//builder.show();
			dialog.setView(view, 0, 0, 0, 0);
			dialog.show();
			break;
		
		default:
			break;
		}
		
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if(data != null){
					startPhotoZoom(data.getData());		
			}else{
				return ;
			}
			break;
		case 2:

			File temp = new File(Environment.getExternalStorageDirectory()+"/xiaoma.jpg");
			startPhotoZoom(Uri.fromFile(temp));
			
			break;
		case 3:
			if(data != null){
				setPicToView(data);
			}else{
				return ;
			}
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void startPhotoZoom(Uri uri){
		if(uri != null){
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		
		//宽高比列
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		
		//裁剪图片的宽高
		intent.putExtra("outputX", 80);
		intent.putExtra("outputY", 80);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);
		}else{
			return ;
		}
	}
	
	private void setPicToView(Intent picdata){
		Bundle extras = picdata.getExtras();
		if(extras != null){
			MyBitmap mBitmap = new MyBitmap();
			Bitmap photo = extras.getParcelable("data");
			mBitmap.setPhoto(photo);
			mBitmap.save(getActivity(), new SaveListener() {
				
				@Override
				public void onSuccess() {
					Toast.makeText(getActivity(), "头像上传成功", 0).show();
					
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					// TODO Auto-generated method stub
					
				}
			});
			Drawable drawable = new BitmapDrawable(photo);
			ivHeadPortrait.setImageDrawable(drawable);
		}else{
			return ;
		}
	}
}
