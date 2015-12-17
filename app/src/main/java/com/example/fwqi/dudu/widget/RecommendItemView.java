package com.example.fwqi.dudu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fwqi.dudu.activity.DetailProductActivity;
import com.example.fwqi.dudu.activity.MoreProductActivity;
import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.adapter.RecommendGridAdapter;
import com.example.fwqi.dudu.data.RecommendItemData;
import com.example.fwqi.dudu.data.Product;

/**
 * Created by leehong on 2015/9/26.
 */
public class RecommendItemView extends LinearLayout{
    private TextView titleView = null;
    private BaseGridView dataView = null;
    private RelativeLayout titleController = null;
    private RecommendGridAdapter gridAdapter = null;
    private RecommendItemData itemData = null;
    /**
     * the constructor.
     *
     * @param context
     */
    public RecommendItemView(Context context) {
        this(context, null);
    }

    /**
     * the constructor.
     *
     * @param context
     * @param attrs
     */
    public RecommendItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * the constructor.
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public RecommendItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
        gridAdapter = new RecommendGridAdapter(this.getContext());
        dataView.setAdapter(gridAdapter);
    }

    public void setData(RecommendItemData data) {
        if (data != null) {
            itemData = data;
            titleView.setText(itemData.getCateName());
            gridAdapter.setProductList(itemData.getItemData());
        }

        invalidate();
    }
    /**
     * Initialize the recommend item view.
     *
     * @param context
     */
    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.recommend_list_item_view, this);

        titleController = (RelativeLayout)findViewById(R.id.recommend_item_title_controller);
        titleView = (TextView)findViewById(R.id.recommend_item_title_txtView);
        dataView = (BaseGridView)findViewById(R.id.recommend_item_content_controller);
        dataView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = itemData.getItemData().get(position);
                DetailProductActivity.startActivity(view.getContext(), product);
            }
        });

        TextView textView = (TextView) findViewById(R.id.recommend_item_more);
        textView.setOnClickListener(new MoreProductShow());
    }

    public void setTitleVisibility(boolean show) {
        if (show) {
            // From recommend page.
            titleController.setVisibility(View.VISIBLE);
            dataView.setWrapContent(true);
        }else {
            // From more product page.
            titleController.setVisibility(View.GONE);
            dataView.setWrapContent(false);
        }
    }

    public void setColumn(int num) {
        dataView.setNumColumns(num);
    }

    /**
     * The MoreProductShow listener is used to show more recommend product.
     */
    class MoreProductShow implements OnClickListener {
        @Override
        public void onClick(View v) {
            if (itemData != null) {
                MoreProductActivity.startActivity(v.getContext(), itemData.getCateName(), itemData.getCateId());
            }
        }
    }
}