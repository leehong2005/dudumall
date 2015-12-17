package com.example.fwqi.dudu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fwqi.dudu.R;

/**
 * Created by leehong on 2015/10/25.
 */
public class CartListHeaderView extends LinearLayout {
    private RelativeLayout freeDeliverMsgView = null;

    public CartListHeaderView(Context context) {
        super(context);
        init(context);
    }

    public CartListHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CartListHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.cart_list_header_view, this);

        freeDeliverMsgView = (RelativeLayout)findViewById(R.id.free_deliver_layout);
    }

    public void showFreeDeliverMsg() {
        if (freeDeliverMsgView != null) {
            freeDeliverMsgView.setVisibility(View.VISIBLE);
        }
    }

    public void hideFreeDeliverMsg() {
        if (freeDeliverMsgView != null) {
            freeDeliverMsgView.setVisibility(View.GONE);
        }
    }
}
