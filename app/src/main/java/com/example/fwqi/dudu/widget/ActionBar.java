package com.example.fwqi.dudu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fwqi.dudu.R;

/**
 * Created by leehong on 2015/10/19.
 */
public class ActionBar extends FrameLayout {
    private String titleName = null;

    public ActionBar(Context context) {
        super(context);
        init(context);
    }

    public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ActionBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.action_bar, this, true);
        this.setTitle(this.titleName);
    }

    public void setTitle(String title) {
        this.titleName = title;

        TextView titleView = (TextView) this.findViewById(R.id.title_name);
        if (titleView != null) {
            titleView.setText(titleName);
        }
    }

    public void setTitle(int resId) {
        setTitle(getContext().getString(resId));
    }

    public void show() {
        this.setVisibility(View.VISIBLE);
    }

    public void gone() {
        this.setVisibility(View.GONE);
    }

    public void showBackBtn() {
        ImageView backView = (ImageView) this.findViewById(R.id.back);
        backView.setVisibility(View.VISIBLE);
    }

    public void hideBackBtn() {
        ImageView backView = (ImageView) this.findViewById(R.id.back);
        backView.setVisibility(View.INVISIBLE);
    }
}
