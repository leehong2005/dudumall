package com.example.fwqi.dudu.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.adapter.BannerPagerAdapter;

import java.util.List;

/**
 * Created by leehong on 2015/10/6.
 */
public class SlideBannerView {
    private List<String> urlList = null;
    private Context context = null;
    private View bannerView = null;
    private ViewPager viewPager = null;
    private BannerPointView bannerPoint = null;
    private int currentIndex = 0; // 当前图片的索引号
    private Handler handler = new Handler();
    private ScrollTask scrollTask = new ScrollTask();

    /**
     * The constructor.
     *
     * @param context
     */
    public SlideBannerView(Context context) {
        this.context = context;
        onCreateView();
        startAd();
    }

    private View onCreateView() {
        bannerView = LayoutInflater.from(this.context).inflate(R.layout.recommend_banner_view, null);
        viewPager = (ViewPager)bannerView.findViewById(R.id.banner_view_pager);
        viewPager.addOnPageChangeListener(new BannerPageChangeListener());
        bannerPoint = (BannerPointView)bannerView.findViewById(R.id.banner_point_view);
        return bannerView;
    }

    public void setData(List<String> urlList) {
        this.urlList = urlList;
        BannerPagerAdapter pagerAdapter = new BannerPagerAdapter(this.context, urlList);
        viewPager.setAdapter(pagerAdapter);
        bannerPoint.setPointNum(urlList.size());
    }

    public View getBannerView() {
        return bannerView;
    }

    private void startAd() {
        handler.postDelayed(scrollTask, 5000);
    }

    private class ScrollTask implements Runnable {
        @Override
        public void run() {
            synchronized (viewPager) {
                currentIndex = (currentIndex + 1) % urlList.size();
                viewPager.setCurrentItem(currentIndex);
                handler.postDelayed(scrollTask, 5000);
            }
        }
    }

    /**
     * The class implements ViewPager's OnPageChangeListener.
     */
    public class BannerPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            bannerPoint.setCurPoint(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
