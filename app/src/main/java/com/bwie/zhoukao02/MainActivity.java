package com.bwie.zhoukao02;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.zhoukao02.bean.User;
import com.bwie.zhoukao02.presenter.LoginPresenter;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginPresenter.UserLoginPresenter {

    private CheckBox cbRemember;
    private Button btnLogin;
    private EditText edPassword;
    private EditText edUsername;
    private User user;
    private String username;
    private String password;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loginPresenter = new LoginPresenter(this);
    }

    private void initView() {
        edUsername = findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
        btnLogin = findViewById(R.id.btn_login);
        cbRemember = findViewById(R.id.cb_remrember);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
           switch (v.getId()){
               case R.id.btn_login:

                  String username = edUsername.getText().toString().trim();
                   String password = edPassword.getText().toString().trim();

                   User user = new User();

                   user.username = username;
                   user.password = password;

                   //判断是否为空null,业务层(C)
                   boolean userInfo = loginPresenter.submit(user);

                   if (userInfo){
                       loginPresenter.Login(user);

                   }else{
                       Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();

                   }
                   break;
           }
           }

    @Override
    public void success() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void failed() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "信息输入有误,请重新输入", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

