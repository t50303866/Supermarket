package cn.tzl.shop;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {
	private RelativeLayout rlRoot;
	private ImageView iv;
	private ImageView iv2;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		sp = getSharedPreferences("config", MODE_PRIVATE);
		
		rlRoot = (RelativeLayout) findViewById(R.id.rl_splash);
		iv = (ImageView) findViewById(R.id.iv_splash);
		BitmapUtils  utils = new BitmapUtils(this);
		
		utils.display(iv, "assets/image/belle.JPG");
    	
		startAnim();
	}

	private void startAnim() {

		AnimationSet set = new AnimationSet(false);

		RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(2000);
		rotate.setFillAfter(true);

		ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
		scale.setDuration(2000);
		scale.setFillAfter(true);

		AlphaAnimation alpha = new AlphaAnimation(0, 1);
		alpha.setDuration(2000);
		alpha.setFillAfter(true);

		set.addAnimation(rotate);
		set.addAnimation(scale);
		set.addAnimation(alpha);
		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(sp.getBoolean("automaticLogin", false)){
					if("".equals(sp.getString("name", ""))){	
						startActivity(new Intent(SplashActivity.this, LoginActivity.class));
						finish();
					}else{
						startActivity(new Intent(SplashActivity.this, HomeActivity.class));
						finish();
					}
				}else{
					startActivity(new Intent(SplashActivity.this, LoginActivity.class));
					finish();
				}
				
			}
		});
		rlRoot.startAnimation(set);


	}


}
