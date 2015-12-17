package com.example.fwqi.dudu.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.example.fwqi.dudu.R;

/**
 * Created by leehong on 2015/9/28.
 */
public class BaseGridView extends GridView {
    private Paint paint = new Paint();
    private Path path = new Path();
    private boolean isWrapContent = false;

    public BaseGridView(Context context) {
        this(context, null);
    }

    public BaseGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint.setColor(getResources().getColor(R.color.grid_separator_line_color));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
    }

    public void setWrapContent(boolean isWrapContent) {
        this.isWrapContent = isWrapContent;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isWrapContent) {
            int expandSpec = MeasureSpec.makeMeasureSpec(
                    Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        path.reset();
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; ++i) {
            View childView = this.getChildAt(i);
            int top = childView.getTop();
            int left = childView.getLeft();
            int right = childView.getRight();
            int bottom = childView.getBottom();
            path.moveTo(left, top);
            path.lineTo(right, top);
            path.lineTo(right, bottom);
            path.lineTo(left, bottom);
            path.lineTo(left, top);
        }
        canvas.drawPath(path, this.paint);
    }
}
