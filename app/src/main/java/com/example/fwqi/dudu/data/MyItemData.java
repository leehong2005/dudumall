package com.example.fwqi.dudu.data;

import com.example.fwqi.dudu.common.AppConstants;

/**
 * Created by leehong on 2015/11/9.
 */
public class MyItemData {
    private int id = AppConstants.MY_UNSPECIFIED_ID;
    private int iconResId = -1;
    private String name = null;
    private String rightText = null;
    private boolean showRightTextView = false;
    private boolean showRightArrowIcon = false;
    private boolean showTopView = false;
    private boolean showBottomView = false;
    private boolean showTopSeparator = false;
    private boolean showBottomSeparator = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public boolean isShowRightTextView() {
        return showRightTextView;
    }

    public void setShowRightTextView(boolean showRightTextView) {
        this.showRightTextView = showRightTextView;
    }

    public boolean isShowRightArrowIcon() {
        return showRightArrowIcon;
    }

    public void setShowRightArrowIcon(boolean showRightArrowIcon) {
        this.showRightArrowIcon = showRightArrowIcon;
    }

    public boolean isShowTopView() {
        return showTopView;
    }

    public void setShowTopView(boolean showTopView) {
        this.showTopView = showTopView;
    }

    public boolean isShowBottomView() {
        return showBottomView;
    }

    public void setShowBottomView(boolean showBottomView) {
        this.showBottomView = showBottomView;
    }

    public boolean isShowTopSeparator() {
        return showTopSeparator;
    }

    public void setShowTopSeparator(boolean showTopSeparator) {
        this.showTopSeparator = showTopSeparator;
    }

    public boolean isShowBottomSeparator() {
        return showBottomSeparator;
    }

    public void setShowBottomSeparator(boolean showBottomSeparator) {
        this.showBottomSeparator = showBottomSeparator;
    }
}
