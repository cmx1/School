package com.example.cmx.school;


import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

//import com.wtu.school.activity.BaseActivity;
//import com.wtu.school.activity.MyApp;

public class LoginActivity extends BaseActivity {

	private EditText etAccount ;//�˺��������
	private EditText etPassword ;//���������
	private Button btnLogin ; //���밴ť
	private ImageView ivDeleteAccount ;//����˺������
	private ImageView ivDeletePassword  ;//������������
	private CheckBox cbRemember ; //��ס����
	private static boolean isRemembering = false ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login) ;
		Log.i("oncreate ��������", "oncreate!!") ;
		
		 
		findView();
		initView();
		 setOnClick();
	}

	@Override
	protected void findView() {
		// TODO �Զ����ɵķ������
				etAccount =  (EditText) findViewById(R.id.login_et_account) ;
				etPassword  = (EditText) findViewById(R.id.login_et_password) ;
				btnLogin = (Button) findViewById(R.id.login_btn_login);
				cbRemember = (CheckBox) findViewById(R.id.login_cb_rememberpw);
				ivDeleteAccount = (ImageView) findViewById(R.id.login_iv_account_delete);
				ivDeletePassword = (ImageView) findViewById(R.id.login_iv_password_delete);
	}

	@Override
	protected void initView() {
		// TODO �Զ����ɵķ������
		if (TextUtils.isEmpty(MyApp.sp.getString("account", "")))
		 {
			 //Toast.makeText(getApplicationContext(), "û�б������룡", 0).show(); 
		 }else {
			 //Toast.makeText(getApplicationContext(), "���������룡��", 0).show() ;
			 etAccount.setText(MyApp.sp.getString("account", null)) ;
			 etPassword.setText(MyApp.sp.getString("password", null)) ;
		 }
	}

	@Override
	protected void setOnClick() {
		// TODO �Զ����ɵķ������
		// TODO Auto-generated method stub
		//�Ե��밴ť���¼�����
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String account = etAccount.getText().toString().trim() ;
				String password = etPassword.getText().toString().trim();
				
				if (TextUtils.isEmpty(account)||TextUtils.isEmpty(password)){
					
					Toast.makeText(getApplicationContext(), "�˺Ż����벻��Ϊ�գ�", Toast.LENGTH_SHORT).show();
				}else if ("admin".equals(account)&&"admin".equals(password))
				{
					
					Toast.makeText(getApplicationContext(), "����ɹ���", Toast.LENGTH_SHORT).show();
					if (isRemembering)
					{
						Editor editor =  MyApp.sp.edit();
						editor.putString("account", account) ;
						editor.putString("password", password) ;
						editor.commit();
					}
					else {
						Editor editor =  MyApp.sp.edit();
						editor.putString("account", null) ;
						editor.putString("password", null) ;
						editor.commit();
					}
					//startActivity(new Intent(LoginActivity.this,MainActivity.class)) ;
					//getApplicationContext()
					//finish() ;
					
					
				}
				else {
					Toast.makeText(getApplicationContext(), "�����˺Ż����벻��ȷ�����������룡", Toast.LENGTH_SHORT).show();
				}
			}
		});
		///���˺�������������¼���������
		etAccount.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(etAccount.getText().toString()))
				{
					ivDeleteAccount.setVisibility(View.VISIBLE) ;
					}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(etAccount.getText().toString()))
				{
					ivDeleteAccount.setVisibility(View.INVISIBLE) ;
					}
			}
		});
		////������������
       etPassword.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(etPassword.getText().toString()))
				{
					ivDeletePassword.setVisibility(View.VISIBLE) ;
					}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (TextUtils.isEmpty(etPassword.getText().toString()))
				{
					ivDeletePassword.setVisibility(View.INVISIBLE) ;
					}
			}
		});
       
       ivDeleteAccount.setOnTouchListener(new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			etAccount.setText("");
			return false;
		}
	});
       //delete Password
       
       ivDeletePassword.setOnTouchListener(new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			etPassword.setText("") ;
			return false;
		}
	});
       
       
     ///�� ��ס���밴ť���¼�����
   	cbRemember.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (isChecked){
				isRemembering = isChecked ;
			}
			else {
				isRemembering =isChecked ;
			}
		}
	});
	}
	

	
	
}
