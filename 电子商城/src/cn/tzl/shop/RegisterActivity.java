package cn.tzl.shop;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;
import cn.tzl.shop.dao.MyUser;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private EditText etName;
	private EditText etPassword;
	private EditText etAgeinPassword;
	private Button btRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		Bmob.initialize(this, "acda51ba8786dd2755a801d678e66c02");
		initVews();
		setListeners();
		
	}


	private void initVews() {
		etName= (EditText) findViewById(R.id.et_name);
		etPassword= (EditText) findViewById(R.id.et_password);
		etAgeinPassword= (EditText) findViewById(R.id.et_agein_password);
		btRegister = (Button) findViewById(R.id.bt_register);		
	}

	private void setListeners() {
		btRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				testSignUp();
				
			}
		});
		
	}

	
	/**
	 * ×¢²á
	 */
	protected void testSignUp() {
		MyUser myUser = new MyUser();
		final String name = etName.getText().toString().trim();
		String password = etPassword.getText().toString().trim();
		String ageinPassword = etAgeinPassword.getText().toString().trim();
		
		
		if(password.equals(ageinPassword)){
			myUser.setUsername(name);
			myUser.setPassword(password);
			myUser.signUp(RegisterActivity.this, new SaveListener() {
				
				@Override
				public void onSuccess() {
					Toast.makeText(RegisterActivity.this, "×¢²á³É¹¦"+name, 0).show();				
					startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
					finish();
				}
				
				@Override
				public void onFailure(int arg0, String arg1) {
					Toast.makeText(RegisterActivity.this, "×¢²áÊ§°Ü", 0).show();					
				}
			});
		}else{
			Toast.makeText(RegisterActivity.this, "ÃÜÂë²»Ò»ÖÂ", 0).show();
		}
		
	}
}
