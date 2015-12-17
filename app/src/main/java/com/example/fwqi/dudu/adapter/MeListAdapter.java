package com.example.fwqi.dudu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.fwqi.dudu.data.MyItemData;
import com.example.fwqi.dudu.widget.MeListItemView;

import java.util.List;

/**
 * Created by leehong on 2015/11/9.
 */
public class MeListAdapter extends BaseAdapter {
    private Context context = null;
    private List<MyItemData> myItemDataList = null;

    public MeListAdapter(Context context) {
        this.context = context;
    }

    public void setMyItemDataList(List<MyItemData> myItemDataList) {
        this.myItemDataList = myItemDataList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (myItemDataList != null) {
            return myItemDataList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (myItemDataList != null) {
            return myItemDataList.get(position);
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

        MeListItemView meListItemView = (MeListItemView) convertView;
        MyItemData myItemData = myItemDataList.get(position);
        if (myItemData != null) {
            meListItemView.setIconResId(myItemData.getIconResId());
            meListItemView.setName(myItemData.getName());
            meListItemView.setRightText(myItemData.getRightText());
            if (myItemData.isShowRightTextView()) {
                meListItemView.showRightText();
            }else {
                meListItemView.hideRightText();
            }

            if (myItemData.isShowRightArrowIcon()) {
                meListItemView.showRightArrow();
            }else {
                meListItemView.hideRightArrow();
            }

            if (myItemData.isShowTopView()) {
                meListItemView.showTopView();
            }else {
                meListItemView.hideTopView();
            }

            if (myItemData.isShowBottomView()) {
                meListItemView.showBottomView();
            }else {
                meListItemView.hideBottomView();
            }

            if (myItemData.isShowTopSeparator()) {
                meListItemView.showTopSeparatorView();
            }else {
                meListItemView.hideTopSeparatorView();
            }

            if (myItemData.isShowBottomSeparator()) {
                meListItemView.showBottomSeparatorView();
            }else {
                meListItemView.hideBottomSeparatorView();
            }
        }

        return convertView;
    }
}
