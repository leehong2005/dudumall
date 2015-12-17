package com.example.fwqi.dudu.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fwqi.dudu.R;

/**
 * Created by leehong on 2015/10/18.
 */
public class OrderFragmentPage extends ActionBarFragment{
    private View orderView = null;

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        if (orderView == null) {
            orderView = inflater.inflate(R.layout.cart_fragment_page, container, false);

            this.setTitleName("订单");
            this.hideLoadingView();
        }
        return orderView;
    }
}
