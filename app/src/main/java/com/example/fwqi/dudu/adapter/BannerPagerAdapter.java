package com.example.fwqi.dudu.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by leehong on 2015/10/6.
 */
public class BannerPagerAdapter extends PagerAdapter {
    private List<String> urlList = null;
    private Context context = null;

    public BannerPagerAdapter(Context context, List<String> urlList) {
        this.context = context;
        this.urlList = urlList;
    }

    @Override
    public int getCount() {
        if (urlList != null) {
            return urlList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView bannerPage = new ImageView(this.context);
        bannerPage.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.getInstance().displayImage(this.urlList.get(position), bannerPage);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
        container.addView(bannerPage, 0, params);
        return bannerPage;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.v("recommend", "position = " + position);
        View view = (View) object;
        if (view != null) {
            container.removeView(view);
        }
    }
}
