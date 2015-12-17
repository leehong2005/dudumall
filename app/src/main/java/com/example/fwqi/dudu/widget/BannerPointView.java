package com.example.fwqi.dudu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.utils.Utils;

/**
 * Created by leehong on 2015/10/5.
 */
public class BannerPointView extends LinearLayout {
    private int pointNum = 0;
    private int curPoint = 0;

    public BannerPointView(Context context) {
        this(context, null);
    }

    public BannerPointView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public int getPointNum() {
        return pointNum;
    }

    public void setPointNum(int pointNum) {
        if (this.pointNum != pointNum) {
            this.pointNum = pointNum;
            refreshView();
        }
    }

    public int getCurPoint() {
        return curPoint;
    }

    public void setCurPoint(int curPoint) {
        if (this.curPoint != curPoint) {
            View lastView = this.getChildAt(this.curPoint);
            if (lastView != null) {
                ((ImageView) lastView).setImageResource(R.drawable.banner_point);
            }

            View curView = this.getChildAt(curPoint);
            if (curView != null) {
                ((ImageView) curView).setImageResource(R.drawable.banner_point_on);
            }
            this.curPoint = curPoint;
        }
    }

    private void initView() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    private void refreshView() {
        removeAllViews();
        for (int i = 0; i < pointNum; ++i) {
            ImageView pointView = new ImageView(this.getContext());
            if (i == curPoint) {
                pointView.setImageResource(R.drawable.banner_point_on);
            } else {
                pointView.setImageResource(R.drawable.banner_point);
            }

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    Utils.dip2px(getContext(), 10), Utils.dip2px(getContext(), 10));
            layoutParams.leftMargin = Utils.dip2px(getContext(), 5);
            layoutParams.rightMargin = Utils.dip2px(getContext(), 5);
            this.addView(pointView, layoutParams);
        }
    }
}
