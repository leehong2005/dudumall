package com.example.fwqi.dudu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.fwqi.dudu.data.RecommendItemData;
import com.example.fwqi.dudu.widget.RecommendItemView;

import java.util.List;

/**
 * Created by leehong on 2015/9/21.
 */
public class RecommendListAdapter extends BaseAdapter {
    private Context context = null;
    private List<RecommendItemData> recommendItemDataList = null;

    public RecommendListAdapter(Context context) {
        this.context = context;
    }

    /**
     * Set data to RecommendPageAdapter.
     *
     * @param recommendItemDataList the data.
     */
    public void setData(List<RecommendItemData> recommendItemDataList) {
        this.recommendItemDataList = recommendItemDataList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        Log.d("dudu", "recommendItemDataList.size()=" + recommendItemDataList.size());
        return recommendItemDataList.size();

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView) {
            convertView = new RecommendItemView(this.context);
        }

        RecommendItemView itemView = (RecommendItemView) convertView;
        itemView.setTitleVisibility(true);
        itemView.setData(this.recommendItemDataList.get(position));

        return convertView;
    }
}
