package com.example.fwqi.dudu.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fwqi.dudu.Manager.LoginManager;
import com.example.fwqi.dudu.Manager.MeManager;
import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.adapter.MySettingAdapter;
import com.example.fwqi.dudu.common.AppConstants;
import com.example.fwqi.dudu.data.MyItemData;

import java.util.List;

/**
 * Created by leehong on 2015/11/24.
 */
public class MySettingActivity extends ActionBarActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MySettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_setting_view);
        init();
    }

    private void init(){
        this.setTitleName("设置");
        ListView mySettingListView = (ListView) findViewById(R.id.me_setting_list_view);
        List<MyItemData> mySettingDatas = MeManager.getMySettingDataList();
        MySettingAdapter mySettingAdapter = new MySettingAdapter(this);
        mySettingAdapter.setData(mySettingDatas);
        mySettingListView.setAdapter(mySettingAdapter);

        // Set the item click listener of the list view.
        mySettingListView.setOnItemClickListener(new OnSettingItemClickListener());
    }

    class OnSettingItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MySettingAdapter settingAdapter = (MySettingAdapter) parent.getAdapter();
            MyItemData myItemData = (MyItemData) settingAdapter.getItem(position);
            if (myItemData != null) {
                switch (myItemData.getId()) {
                    case AppConstants.MY_LOGOUT_ID:
                        showLogoutDialog();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("退出登录"); //设置标题
        String message = "确认要退出"
                + LoginManager.getInstance().getLoginData().getUserName() +"账号吗？";
        builder.setMessage(message); //设置内容
        //builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); //关闭dialog
                MySettingActivity.this.finish();
                LoginManager.getInstance().logout();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //参数都设置完成了，创建并显示出来
        builder.create().show();

    }
}
