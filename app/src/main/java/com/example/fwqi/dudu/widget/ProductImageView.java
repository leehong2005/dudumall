package com.example.fwqi.dudu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by leehong on 2015/10/6.
 */
public class ProductImageView extends ImageView{

    private float mRatio = 1.0f;

    public ProductImageView(Context context) {
        super(context);
    }

    public ProductImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProductImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setRatio(float ratio) {
        this.mRatio = ratio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = (int) (widthMeasureSpec * mRatio);
        super.onMeasure(widthMeasureSpec, height);
    }

}
