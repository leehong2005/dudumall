package com.example.fwqi.dudu.Manager;

import com.example.fwqi.dudu.common.AppConstants;
import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.data.MyItemData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leehong on 2015/11/9.
 */
public class MeManager {
    private static final List<MyItemData> myItemDataList = new ArrayList<>();
    private static final List<MyItemData> mySettingDataList = new ArrayList<>();

    static {
        MyItemData myInfo = new MyItemData();
        myInfo.setId(AppConstants.MY_MYSELF_ID);
        myInfo.setIconResId(R.drawable.my_myself_icon);
        myInfo.setName("个人信息");
        myInfo.setShowRightTextView(true);
        myInfo.setRightText("未登录");
        myInfo.setShowTopView(true);
        myInfo.setShowTopSeparator(true);
        myItemDataList.add(myInfo);

        MyItemData myMoney = new MyItemData();
        myMoney.setId(AppConstants.MY_MONEY_ID);
        myMoney.setIconResId(R.drawable.my_money_icon);
        myMoney.setName("我的余额");
        myMoney.setShowRightArrowIcon(true);
        myMoney.setShowTopSeparator(true);
        myItemDataList.add(myMoney);

        MyItemData myRed = new MyItemData();
        myRed.setId(AppConstants.MY_RED_ID);
        myRed.setIconResId(R.drawable.my_red_icon);
        myRed.setName("我的红包");
        myRed.setShowRightArrowIcon(true);
        myRed.setShowTopSeparator(true);
        myItemDataList.add(myRed);

        MyItemData myAddress = new MyItemData();
        myAddress.setId(AppConstants.MY_ADDRESS_ID);
        myAddress.setIconResId(R.drawable.my_address_icon);
        myAddress.setName("地址管理");
        myAddress.setShowRightArrowIcon(true);
        myAddress.setShowTopSeparator(true);
        myItemDataList.add(myAddress);

        MyItemData myRecharge = new MyItemData();
        myRecharge.setId(AppConstants.MY_RECHARGE_ID);
        myRecharge.setIconResId(R.drawable.my_recharge_icon);
        myRecharge.setName("我的充值");
        myRecharge.setShowRightArrowIcon(true);
        myRecharge.setShowTopSeparator(true);
        myItemDataList.add(myRecharge);

        MyItemData myMessage = new MyItemData();
        myMessage.setId(AppConstants.MY_MESSAGE_ID);
        myMessage.setIconResId(R.drawable.my_message_icon);
        myMessage.setName("我的消息");
        myMessage.setShowRightArrowIcon(true);
        myMessage.setShowTopSeparator(true);
        myItemDataList.add(myMessage);

        MyItemData myEarning = new MyItemData();
        myEarning.setId(AppConstants.MY_EARNING_ID);
        myEarning.setIconResId(R.drawable.my_earnings_icon);
        myEarning.setName("我的收益");
        myEarning.setShowRightArrowIcon(true);
        myEarning.setShowTopSeparator(true);
        myEarning.setShowBottomSeparator(true);
        myItemDataList.add(myEarning);

        MyItemData mySetting = new MyItemData();
        mySetting.setId(AppConstants.MY_SETTING_ID);
        mySetting.setIconResId(R.drawable.my_setting_icon);
        mySetting.setName("设置");
        mySetting.setShowRightArrowIcon(true);
        mySetting.setShowTopView(true);
        mySetting.setShowBottomView(true);
        mySetting.setShowTopSeparator(true);
        mySetting.setShowBottomSeparator(true);
        myItemDataList.add(mySetting);

        MyItemData myService = new MyItemData();
        myService.setId(AppConstants.MY_SERVICE_ID);
        myService.setIconResId(R.drawable.my_service_icon);
        myService.setName("客服");
        myService.setShowRightTextView(true);
        myService.setRightText("400-004-6655");
        myService.setShowTopSeparator(true);
        myItemDataList.add(myService);

        MyItemData myHelp = new MyItemData();
        myHelp.setId(AppConstants.MY_HELP_ID);
        myHelp.setIconResId(R.drawable.my_help_icon);
        myHelp.setName("帮助");
        myHelp.setShowRightArrowIcon(true);
        myHelp.setShowBottomView(true);
        myHelp.setShowTopSeparator(true);
        myHelp.setShowBottomSeparator(true);
        myItemDataList.add(myHelp);
    }

    static {
        MyItemData myAbout = new MyItemData();
        myAbout.setId(AppConstants.MY_ABOUT_ID);
        myAbout.setIconResId(R.drawable.my_aboutus_icon);
        myAbout.setName("关于");
        myAbout.setShowRightArrowIcon(true);
        myAbout.setShowTopView(true);
        myAbout.setShowTopSeparator(true);
        mySettingDataList.add(myAbout);

        MyItemData myFeedback = new MyItemData();
        myFeedback.setId(AppConstants.MY_FEEDBACK_ID);
        myFeedback.setIconResId(R.drawable.my_feedback_icon);
        myFeedback.setName("意见反馈");
        myFeedback.setShowRightArrowIcon(true);
        myFeedback.setShowTopSeparator(true);
        mySettingDataList.add(myFeedback);

        MyItemData myCleanup = new MyItemData();
        myCleanup.setId(AppConstants.MY_CLEAR_CACHE_ID);
        myCleanup.setIconResId(R.drawable.my_cleanup_icon);
        myCleanup.setName("清除缓存");
        myCleanup.setShowRightTextView(true);
        myCleanup.setRightText("");
        myCleanup.setShowRightArrowIcon(true);
        myCleanup.setShowTopSeparator(true);
        mySettingDataList.add(myCleanup);

        MyItemData myVersionUpdate = new MyItemData();
        myVersionUpdate.setId(AppConstants.MY_VERSION_UPDATE_ID);
        myVersionUpdate.setIconResId(R.drawable.my_update_icon);
        myVersionUpdate.setName("版本更新");
        myVersionUpdate.setShowRightTextView(true);
        myVersionUpdate.setRightText("检查新版本");
        myVersionUpdate.setShowTopSeparator(true);
        myVersionUpdate.setShowBottomSeparator(true);
        myVersionUpdate.setShowBottomView(true);
        mySettingDataList.add(myVersionUpdate);

        MyItemData myLogout = new MyItemData();
        myLogout.setId(AppConstants.MY_LOGOUT_ID);
        myLogout.setName("退出登录");
        myLogout.setShowTopSeparator(true);
        myLogout.setShowBottomSeparator(true);
        mySettingDataList.add(myLogout);
    }

    public static List<MyItemData> getMyItemDataList() {
        return myItemDataList;
    }

    public static List<MyItemData> getMySettingDataList() {
        return mySettingDataList;
    }
}
