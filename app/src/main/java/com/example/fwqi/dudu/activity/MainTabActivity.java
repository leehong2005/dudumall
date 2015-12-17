package com.example.fwqi.dudu.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.Manager.LoginManager;
import com.example.fwqi.dudu.data.CartData;
import com.example.fwqi.dudu.page.CartFragmentPage;
import com.example.fwqi.dudu.page.MeFragmentPage;
import com.example.fwqi.dudu.page.OrderFragmentPage;
import com.example.fwqi.dudu.page.RecommendFragmentPage;
import com.example.fwqi.dudu.utils.FileUtils;

public class MainTabActivity extends FragmentActivity {
    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {RecommendFragmentPage.class, CartFragmentPage.class,
            OrderFragmentPage.class, MeFragmentPage.class};

    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_recommend_selector, R.drawable.tab_cart_selector,
            R.drawable.tab_order_selector, R.drawable.tab_me_selector};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"推荐", "购物车", "订单", "我的"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        initView();

        //FileUtils.getCartDataFromFile(this, "DuduFile");
    }

    /**
     * 初始化组件
     */
    private void initView() {
        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        //得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }

    @Override
    protected void onStop() {
        FileUtils.saveCartDataToFile(this, "DuduFile");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        CartData.releaseInstance();
        LoginManager.releaseInstance();
        super.onDestroy();
    }

}
