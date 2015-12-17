package com.example.fwqi.dudu.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.widget.ActionBar;
import com.example.fwqi.dudu.widget.LoadingView;

/**
 * Created by leehong on 2015/10/15.
 *
 * 添加注释
 */
public class ActionBarActivity extends BaseActivity {
    private ActionBar actionBar = null;
    private LoadingView loadingView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(View view) {
        RelativeLayout baseView = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.action_bar_container, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.action_bar);
        baseView.addView(view, layoutParams);
        super.setContentView(baseView);
        initView(baseView);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
    }

    private void initView(View view) {
        actionBar = (ActionBar) view.findViewById(R.id.action_bar);
        loadingView = (LoadingView) view.findViewById(R.id.loading_view);

        ImageView backView = (ImageView) view.findViewById(R.id.back);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTitleBackPressed();
            }
        });
    }

    protected void onTitleBackPressed() {
        this.finish();
    }

    public void setTitleName(String titleName) {
        if (actionBar != null) {
            actionBar.setTitle(titleName);
        }
    }

    public void showActionBar() {
        if (actionBar != null) {
            actionBar.show();
        }
    }

    public void hideActionBar() {
        if (actionBar != null) {
            actionBar.gone();
        }
    }

    public void showLoadingView() {
        if (loadingView != null) {
            loadingView.show();
        }
    }

    public void hideLoadingView()
    {
        if (loadingView != null) {
            loadingView.gone();
        }
    }
}
