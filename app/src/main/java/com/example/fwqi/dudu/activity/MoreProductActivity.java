package com.example.fwqi.dudu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.fwqi.dudu.common.AppConstants;
import com.example.fwqi.dudu.data.RecommendItemData;
import com.example.fwqi.dudu.data.Product;
import com.example.fwqi.dudu.data.ServiceData;
import com.example.fwqi.dudu.utils.HttpUtils;
import com.example.fwqi.dudu.widget.RecommendItemView;

import java.util.List;

/**
 * Created by leehong on 2015/10/11.
 */
public class MoreProductActivity extends ActionBarActivity{
    private MoreProductUIHandler handler = new MoreProductUIHandler();
    private ServiceData moreProductData = null;
    private String cateId = null;
    private RecommendItemView moreProductView = null;

    /**
     * Start the activity.
     *  @param from
     * @param cateName
     * @param cateId
     */
    public static void startActivity(Context from, CharSequence cateName, String cateId) {
        Activity fromActivity = (Activity) from;
        Intent intent = new Intent();
        intent.putExtra("cateName", cateName);
        intent.putExtra("cateId", cateId);
        intent.setClass(fromActivity, MoreProductActivity.class);
        fromActivity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String titleName = intent.getStringExtra("cateName");
        cateId = intent.getStringExtra("cateId");

        // Create the grid view to show more product.The grid view has no data now.
        moreProductView = new RecommendItemView(this);
        moreProductView.setTitleVisibility(false);
        moreProductView.setColumn(3);
        setContentView(moreProductView);
        this.setTitleName(titleName);

        // Start the worker thread to request data from server.
        MoreProductRequestThread thread = new MoreProductRequestThread(handler);
        thread.start();
        this.showLoadingView();
    }

    class MoreProductUIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (moreProductData != null && moreProductData.getErrNo() == AppConstants.ERROR_CODE_SUCCESS) {
                List<Product> productList = (List<Product>) moreProductData.getData("list");
                RecommendItemData data = new RecommendItemData();
                data.setItemData(productList);
                moreProductView.setData(data);
                MoreProductActivity.this.hideLoadingView();
            }
        }
    }

    class MoreProductRequestThread extends Thread {
        private Handler uiHandler = null;

        public MoreProductRequestThread(Handler uiHandler) {
            this.uiHandler = uiHandler;
        }

        @Override
        public void run() {
            moreProductData = HttpUtils.getMoreProductData(cateId, 0, 30);

            if (uiHandler != null) {
                uiHandler.obtainMessage().sendToTarget();
            }
        }
    }
}
