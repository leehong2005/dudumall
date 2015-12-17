package com.example.fwqi.dudu.page;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fwqi.dudu.common.AppConstants;
import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.adapter.RecommendListAdapter;
import com.example.fwqi.dudu.event.CartEvent;
import com.example.fwqi.dudu.data.RecommendItemData;
import com.example.fwqi.dudu.data.ServiceData;
import com.example.fwqi.dudu.utils.HttpUtils;
import com.example.fwqi.dudu.widget.SlideBannerView;

import java.util.List;

import de.greenrobot.event.EventBus;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class RecommendFragmentPage extends ActionBarFragment {
    private View recommendView = null;
    private RecommendListAdapter recommendListAdapter = null;
    private RecommendUIHandler handler = new RecommendUIHandler();
    private ListView recommendListView = null;
    private ServiceData recommendData = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        if (recommendView == null) {
            recommendView = inflater.inflate(R.layout.recommend_fragment_page, container, false);

            // Get the list view.
            recommendListView = (ListView) recommendView.findViewById(R.id.recommend_list_controller);

            // Start the thread to request recommend data from server.
            RecommendRequestThread requestThread = new RecommendRequestThread(handler);
            requestThread.start();
            this.showLoadingView();
            this.setTitleName("推荐");
        }
        return recommendView;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEvent(CartEvent cartEvent){
        recommendListAdapter.notifyDataSetChanged();
    }

    /**
     * The RecommendUIHandler class is used to hand the recommend request data.
     */
    class RecommendUIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (recommendData != null && recommendData.getErrNo() == AppConstants.ERROR_CODE_SUCCESS) {
                // Banner data
                List<String> bannerList = (List<String>) recommendData.getData("banner");
                // List data.
                List<RecommendItemData> recommendItemDataList = (List<RecommendItemData>) recommendData.getData("list");

                // Create banner view and add this view as the head view of the list view.
                SlideBannerView bannerView = new SlideBannerView(getActivity());
                bannerView.setData(bannerList);
                recommendListView.addHeaderView(bannerView.getBannerView());

                // Create the adapter of the list view.
                recommendListAdapter = new RecommendListAdapter(getActivity());
                recommendListAdapter.setData(recommendItemDataList);
                recommendListView.setAdapter(recommendListAdapter);

                // Hide the loading view.
                RecommendFragmentPage.this.hideLoadingView();
            }
        }
    }

    /**
     * The RecommendRequestThread class is used to request recommend data from server.
     */
    class RecommendRequestThread extends Thread {
        // The UI handler.
        private Handler uiHandler = null;

        /**
         * The constructor.
         *
         * @param handler The UI handler.
         */
        public RecommendRequestThread(Handler handler) {
            this.uiHandler = handler;
        }

        @Override
        public void run() {
            // Get recommend data from server.
            recommendData = HttpUtils.getRecommendData();
            if (uiHandler != null) {
                uiHandler.obtainMessage().sendToTarget();
            }
        }
    }
}
