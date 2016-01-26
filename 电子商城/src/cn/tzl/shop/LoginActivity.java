package cn.tzl.shop;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.tzl.shop.dao.MyUser;

import com.lidroid.xutils.BitmapUtils;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private static final String TAG = "LoginActivity";
	private ImageView iv;
	private Button btLogin;
	private TextView tvRetrievePassword;
	private TextView tvRegister;
	private EditText etName;
	private EditText etPassword;
	private CheckBox cbRememberPassword;
	private CheckBox cbAutomaticLogin;
	private SharedPreferences sp;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Bmob.initialize(this, "acda51ba8786dd2755a801d678e66c02");
		sp = getSharedPreferences("config", MODE_PRIVATE);
		setViews();
		setListeners();
		String name = sp.getString("name", "");
		if(name != null){
			etName.setText(name);
			etPassword.setText(sp.getString("password", ""));
		}
		cbAutomaticLogin.setChecked(sp.getBoolean("automaticLogin", false));
		cbRememberPassword.setChecked(sp.getBoolean("rememberPassword", false));
	}
	
	private void setViews() {
		iv = (ImageView) findViewById(R.id.iv_login);
		btLogin = (Button) findViewById(R.id.bt_login);
		tvRetrievePassword = (TextView) findViewById(R.id.tv_retrieve_password);
		tvRegister = (TextView) findViewById(R.id.tv_register);
		etName = (EditText) findViewById(R.id.et_name);
		etPassword = (EditText) findViewById(R.id.et_password);
		cbRememberPassword = (CheckBox) findViewById(R.id.cb_remember_password);
		cbAutomaticLogin = (CheckBox) findViewById(R.id.cb_automatic_login);
		
		BitmapUtils  utils = new BitmapUtils(this);
		utils.display(iv, "assets/image/belle.JPG");
		
	}
	private void setListeners() {
		tvRetrievePassword.setOnClickListener(new MyListeners());
		tvRegister.setOnClickListener(new MyListeners());
		btLogin.setOnClickListener(new MyListeners());
		
		cbAutomaticLogin.setOnCheckedChangeListener(new MyCheckListener());
		cbRememberPassword.setOnCheckedChangeListener(new MyCheckListener());
	
	}
	
	public class MyCheckListener implements OnCheckedChangeListener{

		private Editor editor = sp.edit();

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			int id = buttonView.getId();
			switch (id) {
			case R.id.cb_automatic_login:
				if(isChecked){		
					editor.putBoolean("automaticLogin", isChecked);
					editor.commit();
					cbRememberPassword.setChecked(true);
				}else{
					editor.putBoolean("automaticLogin", isChecked);
					editor.commit();
				}
				break;
			case R.id.cb_remember_password:
				if(!isChecked){
					editor.putBoolean("rememberPassword", !isChecked);
					editor.commit();
					cbAutomaticLogin.setChecked(false);
				}else{
					editor.putBoolean("rememberPassword", isChecked);
					editor.commit();
				}
			default:
				break;
			}
			
			
		}
		
	}
	
	public class MyListeners implements OnClickListener{

		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.bt_login:
				mLogin();						
				break;
			case R.id.tv_retrieve_password:
				startActivity(new Intent(LoginActivity.this, RetrievePasswordActivity.class));
				break;
			case R.id.tv_register:
					startActivity(new Intent(LoginActivity.this, RegisterActivity.class));		
				
				break;
			default:
				break;
			}
			
		}
		
	}

	public void mLogin() {
		final String name= etName.getText().toString().trim();
		final String password = etPassword.getText().toString().trim();
		BmobUser bu = new BmobUser();
		bu.setUsername(name);
		bu.setPassword(password);
		
		bu.login(this,new SaveListener() {
			
			@Override
			public void onSuccess() {
				startActivity(new Intent(LoginActivity.this, HomeActivity.class));
				finish();
				if(cbRememberPassword.isChecked()){
					Editor editor = sp.edit();
					editor.putString("name", name);
					editor.putString("password",password);
					editor.commit();
			}
			}
			
			@Override
			public void onFailure(int arg0, String msg) {
				Toast.makeText(LoginActivity.this, "用户名或密码错误"+msg, 0).show();
				
			}
		});
		
	}
	
}
