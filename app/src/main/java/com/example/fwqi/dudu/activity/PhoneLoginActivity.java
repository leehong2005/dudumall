package com.example.fwqi.dudu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fwqi.dudu.common.AppConstants;
import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.event.LoginEvent;
import com.example.fwqi.dudu.data.LoginData;
import com.example.fwqi.dudu.data.ServiceData;
import com.example.fwqi.dudu.utils.HttpUtils;

import de.greenrobot.event.EventBus;

/**
 * Created by leehong on 2015/11/14.
 */
public class PhoneLoginActivity extends ActionBarActivity {
    private final int SMS_CERTIFY_NUMBER_TASK = 0;
    private final int LOGIN_TASK = 1;
    // 当前的倒计时时间。
    private int curTime = 0;
    private Handler handler = new Handler();
    private CountDownTask countDownTask = new CountDownTask();
    private Button getCertifyBtn = null;
    private PhoneLoginHandler uiHandler = new PhoneLoginHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_phone_view);
        this.setTitleName("手机快速登录");

        init();
    }

    private void init() {
        // Get the phone number.
        final EditText editText = (EditText)findViewById(R.id.phone_number);

        // The sms certify button.
        getCertifyBtn = (Button)findViewById(R.id.get_phone_certify_btn);
        getCertifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get phone certify number from server.
                final String phoneNumber = editText.getText().toString();
                RequestSMSCertifyNumberTask requestSMSCertifyNumberTask =
                        new RequestSMSCertifyNumberTask(uiHandler, phoneNumber);
                requestSMSCertifyNumberTask.start();

                // Start count down.
                startCountDown();
            }
        });

        // The login button.
        final EditText certifyEdit = (EditText)findViewById(R.id.phone_certify);
        Button loginBtn = (Button)findViewById(R.id.phone_login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneNumber = editText.getText().toString();
                final String smsCertifyNumber = certifyEdit.getText().toString();

                LoginTask loginTask = new LoginTask(uiHandler, phoneNumber, smsCertifyNumber);
                loginTask.start();
            }
        });
    }

    // 倒计时开始
    private void startCountDown() {
        curTime = 60;
        handler.postDelayed(countDownTask, 1000);
    }

    // The countdown runnable.
    class CountDownTask implements Runnable{
        @Override
        public void run() {
            curTime -= 1;
            if (curTime > 0) {
                getCertifyBtn.setText("重新发送" + curTime + "秒");
                getCertifyBtn.setEnabled(false);
                handler.postDelayed(countDownTask, 1000);
            }else {
                getCertifyBtn.setText("重新发送");
                getCertifyBtn.setEnabled(true);
            }
        }
    }

    class PhoneLoginHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            ServiceData serviceData = null;
            if (msg != null) {
                serviceData = (ServiceData) msg.obj;
                if (serviceData != null && serviceData.getErrNo() < 0){
                    Toast.makeText(PhoneLoginActivity.this, serviceData.getErrMsg(), Toast.LENGTH_SHORT).show();
                }

                switch (msg.what){
                    case SMS_CERTIFY_NUMBER_TASK:
                        break;
                    case LOGIN_TASK:
                        // Post the login result to subscriber.
                        int loginStatus = serviceData.getErrNo() == 0 ?
                                AppConstants.LOGIN_SUCCESS : AppConstants.LOGIN_FAIL;
                        LoginData loginData = null;
                        if (loginStatus == AppConstants.LOGIN_SUCCESS){
                            loginData = (LoginData) serviceData.getData("login");
                        }
                        LoginEvent loginEvent = new LoginEvent();
                        loginEvent.setLoginStatus(loginStatus);
                        loginEvent.setLoginData(loginData);
                        EventBus.getDefault().post(loginEvent);
                        PhoneLoginActivity.this.finish();

                        break;
                    default:
                        break;
                }
            }

        }
    }

    /**
     * The task is used to get sms certify from server.
     */
    class RequestSMSCertifyNumberTask extends Thread {
        private Handler uiHandler = null;
        private String phoneNumber = null;

        public RequestSMSCertifyNumberTask(Handler uiHandler, String phoneNumber) {
            this.uiHandler = uiHandler;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public void run() {
            ServiceData serviceData = HttpUtils.getSMSCertifyNumber(phoneNumber);
            if (uiHandler != null) {
                Message message = uiHandler.obtainMessage(SMS_CERTIFY_NUMBER_TASK, serviceData);
                message.sendToTarget();
            }
        }
    }

    /**
     * The task is used to login.
     */
    class LoginTask extends Thread {
        private Handler uiHandler = null;
        private String phoneNumber = null;
        private String smsCertify = null;

        public LoginTask(Handler handler, String phoneNumber, String smsCertify){
            this.uiHandler = handler;
            this.phoneNumber = phoneNumber;
            this.smsCertify = smsCertify;
        }

        @Override
        public void run() {
            ServiceData loginData = HttpUtils.getLoginInfo(phoneNumber, smsCertify);
            if (uiHandler != null) {
                uiHandler.obtainMessage(LOGIN_TASK, loginData).sendToTarget();
            }
        }
    }
}
