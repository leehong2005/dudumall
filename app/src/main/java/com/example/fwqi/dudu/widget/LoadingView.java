package com.example.fwqi.dudu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.fwqi.dudu.R;

/**
 * Created by leehong on 2015/10/19.
 */
public class LoadingView extends FrameLayout{
    public LoadingView(Context context) {
        super(context);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context contenxt) {
        LayoutInflater.from(contenxt).inflate(R.layout.loading_view, this, true);
    }

    public void show() {
        this.setVisibility(View.VISIBLE);
    }

    public void gone() {
        this.setVisibility(View.GONE);
    }
}
