package com.example.fwqi.dudu.page;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fwqi.dudu.activity.MySettingActivity;
import com.example.fwqi.dudu.common.AppConstants;
import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.adapter.MeListAdapter;
import com.example.fwqi.dudu.Manager.LoginManager;
import com.example.fwqi.dudu.Manager.MeManager;
import com.example.fwqi.dudu.data.LoginData;
import com.example.fwqi.dudu.data.MyItemData;
import com.example.fwqi.dudu.data.ServiceData;
import com.example.fwqi.dudu.utils.HttpUtils;

import java.util.List;

/**
 * Created by leehong on 2015/10/18.
 */
public class MeFragmentPage extends ActionBarFragment {
    private LinearLayout meView = null;
    private ListView meListView = null;

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        if (meView == null) {
            meView = (LinearLayout) inflater.inflate(R.layout.me_fragment_page, container, false);
            init(meView);

            this.setTitleName("我");
            this.hideLoadingView();
        }
        return meView;
    }

    private void init(LinearLayout parentView) {
        meListView = (ListView) parentView.findViewById(R.id.me_list_view);

        List<MyItemData> myItemDataList = MeManager.getMyItemDataList();
        LoginData loginData = LoginManager.getInstance().getLoginData();
        if (loginData != null) {
            myItemDataList.get(0).setRightText(loginData.getUserName());
        }

        MeListAdapter meListAdapter = new MeListAdapter(parentView.getContext());
        meListAdapter.setMyItemDataList(myItemDataList);
        meListView.setAdapter(meListAdapter);
        meListView.setOnItemClickListener(new MyItemClickListener());
    }

    class MyItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // if is not login, go to login.
            if (!LoginManager.getInstance().isLogin()) {
                LoginManager.getInstance().login(MeFragmentPage.this.getActivity());
                LoginManager.getInstance().addLoginChangedListener(new MyOnLoginChangedListener());
            }else{
                // Show the info of the specified item.
                MyItemData itemData = (MyItemData) parent.getAdapter().getItem(position);
                MyRequestThread myRequestThread = new MyRequestThread(new MyUIHandler(), itemData.getId());
                myRequestThread.start();
            }
        }
    }

    class MyOnLoginChangedListener implements LoginManager.OnLoginChangedListener {
        @Override
        public void onLoginChanged(int loginStatus) {
            MyItemData myselfItemData = (MyItemData) meListView.getAdapter().getItem(0);
            LoginData loginData = LoginManager.getInstance().getLoginData();
            if (loginData != null) {
                myselfItemData.setRightText(loginData.getUserName());
            }else {
                myselfItemData.setRightText("未登录");
            }
            ((MeListAdapter)meListView.getAdapter()).notifyDataSetChanged();
        }
    }

    class MyUIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            ServiceData myData = (ServiceData) msg.obj;

            switch (msg.what) {
                case AppConstants.MY_MYSELF_ID:
                    if (myData != null) {
                        if (myData.getErrNo() == AppConstants.ERROR_CODE_SUCCESS) {
                            // Todo...
                        }else {
                            Toast.makeText(MeFragmentPage.this.getActivity(),
                                    myData.getErrMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case AppConstants.MY_SETTING_ID:
                    MySettingActivity.startActivity(MeFragmentPage.this.getActivity());
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    class MyRequestThread extends Thread {
        private Handler uiHandler = null;
        private int itemIndex = AppConstants.MY_UNSPECIFIED_ID;

        public MyRequestThread(Handler uiHandler, int myItemIndex) {
            this.uiHandler = uiHandler;
            this.itemIndex = myItemIndex;
        }

        @Override
        public void run() {
            super.run();

            ServiceData myData = null;
            switch (itemIndex){
                case AppConstants.MY_MYSELF_ID:
                    // Get myself info from server.
                    myData = HttpUtils.getMyselfInfo();
                    break;
                case AppConstants.MY_MONEY_ID:
                    break;
                case AppConstants.MY_RED_ID:
                    break;
                case AppConstants.MY_ADDRESS_ID:
                    break;
                case AppConstants.MY_RECHARGE_ID:
                    break;
                case AppConstants.MY_MESSAGE_ID:
                    break;
                case AppConstants.MY_EARNING_ID:
                    break;
                case AppConstants.MY_SETTING_ID:
                    break;
                case AppConstants.MY_SERVICE_ID:
                    break;
                case AppConstants.MY_HELP_ID:
                    break;
                default:
                    break;
            }

            // Send message to uihandler.
            uiHandler.obtainMessage(this.itemIndex, myData).sendToTarget();
        }
    }
}
