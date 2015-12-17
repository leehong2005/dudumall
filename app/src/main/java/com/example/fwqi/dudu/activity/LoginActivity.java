package com.example.fwqi.dudu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.Manager.LoginManager;

/**
 * Created by leehong on 2015/11/5.
 */
public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set login layout.
        setContentView(R.layout.login_view);
        this.setTitleName("登录");

        LinearLayout loginByPhoneBtn = (LinearLayout) findViewById(R.id.login_by_phone_btn);
        loginByPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().goToPhoneLogin(LoginActivity.this);
                LoginActivity.this.finish();
            }
        });
    }
}
