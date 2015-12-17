package com.example.fwqi.dudu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.fwqi.dudu.data.MyItemData;
import com.example.fwqi.dudu.widget.MeListItemView;

import java.util.List;

/**
 * Created by leehong on 2015/11/24.
 */
public class MySettingAdapter extends BaseAdapter {
    private Context context;
    private List<MyItemData> mySettingItemList;

    public MySettingAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<MyItemData> itemDatas) {
        this.mySettingItemList = itemDatas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mySettingItemList != null) {
            return mySettingItemList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mySettingItemList != null) {
            return mySettingItemList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new MeListItemView(this.context);
        }

        MeListItemView itemView = (MeListItemView) convertView;
        MyItemData itemData = mySettingItemList.get(position);
        if (itemData != null) {
            itemView.setIconResId(itemData.getIconResId());
            itemView.setName(itemData.getName());
            itemView.setRightText(itemData.getRightText());
            if (itemData.isShowRightTextView()) {
                itemView.showRightText();
            }else {
                itemView.hideRightText();
            }

            if (itemData.isShowRightArrowIcon()) {
                itemView.showRightArrow();
            }else {
                itemView.hideRightArrow();
            }

            if (itemData.isShowTopView()) {
                itemView.showTopView();
            }else {
                itemView.hideTopView();
            }

            if (itemData.isShowBottomView()) {
                itemView.showBottomView();
            }else {
                itemView.hideBottomView();
            }

            if (itemData.isShowTopSeparator()) {
                itemView.showTopSeparatorView();
            }else {
                itemView.hideTopSeparatorView();
            }

            if (itemData.isShowBottomSeparator()) {
                itemView.showBottomSeparatorView();
            }else {
                itemView.hideBottomSeparatorView();
            }
        }
        
        return convertView;
    }
}
