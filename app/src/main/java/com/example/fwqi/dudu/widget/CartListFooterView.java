package com.example.fwqi.dudu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.fwqi.dudu.R;

/**
 * Created by leehong on 2015/10/31.
 */
public class CartListFooterView extends LinearLayout {
    public CartListFooterView(Context context) {
        super(context);
        init(context);
    }

    public CartListFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CartListFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.cart_list_footer_view, this);
    }
}
