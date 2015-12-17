package com.example.fwqi.dudu.page;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.widget.ActionBar;
import com.example.fwqi.dudu.widget.LoadingView;

/**
 * Created by leehong on 2015/10/18.
 */
public class ActionBarFragment extends Fragment {
    private ViewGroup actionBarLayout = null;
    private ActionBar actionBar = null;
    private LoadingView loadingView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (actionBarLayout == null) {
            actionBarLayout = (ViewGroup) inflater.inflate(R.layout.action_bar_container, container, false);
            initView(actionBarLayout);

            // Get the content view which will be implemented by derived class.
            View contentView = getContentView(inflater, actionBarLayout);
            if (contentView != null) {
                Context context = inflater.getContext();
                contentView.setBackgroundColor(context.getResources().getColor(R.color.background_color));
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.addRule(RelativeLayout.BELOW, R.id.action_bar);
                actionBarLayout.addView(contentView, params);
            }
        } else {
            refreshContentView();
        }

        return actionBarLayout;
    }

    /**
     * Get the content view which is overridden by derived class.
     * @param inflater
     * @param container
     * @return The content view which will be added in the action bar layout.
     */
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    /**
     * Refresh the content view.
     */
    protected void refreshContentView() {
        return;
    }
    /**
     * Initialize the action bar layout.
     * @param view
     */
    private void initView(View view) {
        actionBar = (ActionBar) view.findViewById(R.id.action_bar);
        loadingView = (LoadingView) view.findViewById(R.id.loading_view);
        actionBar.hideBackBtn();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
