package com.example.fwqi.dudu.widget;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fwqi.dudu.R;

/**
 * Created by leehong on 2015/11/9.
 */
public class MeListItemView extends LinearLayout {
    private ImageView iconView = null;
    private TextView nameView = null;
    private TextView rightTextView = null;
    private ImageView rightArrowView = null;
    private View itemTopView = null;
    private View itemBottomView = null;
    private View topSeparatorView = null;
    private View bottomSeparatorView = null;

    public MeListItemView(Context context) {
        super(context);
        init(context);
    }

    public MeListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MeListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.me_list_item_view, this);

        iconView = (ImageView)findViewById(R.id.my_item_image);
        nameView = (TextView)findViewById(R.id.my_item_name);
        rightTextView = (TextView)findViewById(R.id.my_right_text);
        rightArrowView = (ImageView)findViewById(R.id.my_right_arrow);
        itemTopView = (View)findViewById(R.id.my_item_top_view);
        itemBottomView = (View)findViewById(R.id.my_item_bottom_view);
        topSeparatorView = (View)findViewById(R.id.my_item_top_separator_view);
        bottomSeparatorView = (View)findViewById(R.id.my_item_bottom_separator_view);
    }

    public void setIconResId(int resId) {
        if (resId > -1) {
            showIcon();
            iconView.setImageResource(resId);
        }else {
            hideIcon();
        }
    }
    public void showIcon() {
        iconView.setVisibility(View.VISIBLE);
    }

    public void hideIcon() {
        iconView.setVisibility(View.GONE);
    }

    public void setName(String name) {
        nameView.setText(name);
    }

    public void setRightText(String rightText) {
        rightTextView.setText(rightText);
    }

    public void showRightText() {
        rightTextView.setVisibility(View.VISIBLE);
    }

    public void hideRightText() {
        rightTextView.setVisibility(View.GONE);
    }

    public void showRightArrow() {
        rightArrowView.setVisibility(View.VISIBLE);
    }

    public void hideRightArrow() {
        rightArrowView.setVisibility(View.GONE);
    }

    public void showTopView() {
        itemTopView.setVisibility(View.VISIBLE);
    }

    public void hideTopView() {
        itemTopView.setVisibility(View.GONE);
    }

    public void showBottomView() {
        itemBottomView.setVisibility(View.VISIBLE);
    }

    public void hideBottomView() {
        itemBottomView.setVisibility(View.GONE);
    }

    public void showTopSeparatorView() {
        topSeparatorView.setVisibility(View.VISIBLE);
    }

    public void hideTopSeparatorView() {
        topSeparatorView.setVisibility(View.GONE);
    }

    public void showBottomSeparatorView() {
        bottomSeparatorView.setVisibility(View.VISIBLE);
    }

    public void hideBottomSeparatorView() {
        bottomSeparatorView.setVisibility(View.GONE);
    }
}
