package com.example.fwqi.dudu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.data.Product;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by leehong on 2015/10/26.
 */
public class CartListItemView extends LinearLayout {
    private RelativeLayout shopInfoLayout = null;
    private TextView shopNameTextView = null;
    private ImageView productImageView = null;
    private TextView productNameTextView = null;
    private TextView productPriceTextView = null;
    private TextView productCountTextView = null;

    public CartListItemView(Context context) {
        super(context);
        init(context);
    }

    public CartListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CartListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.cart_list_item_view, this);
        shopInfoLayout = (RelativeLayout)findViewById(R.id.cart_list_item_shop_info);
        shopNameTextView = (TextView) findViewById(R.id.cart_shop_name);
        productNameTextView = (TextView)findViewById(R.id.cart_list_item_product_name);
        productImageView = (ImageView)findViewById(R.id.cart_list_item_product_pic);
        productPriceTextView = (TextView)findViewById(R.id.cart_list_item_product_price);
        productCountTextView = (TextView)findViewById(R.id.cart_list_item_count_txt);
    }

    public void setData(Product product) {
        if (product != null) {
            shopNameTextView.setText(product.getShopName());
            ImageLoader.getInstance().displayImage(product.getUrl(), productImageView);
            productNameTextView.setText(product.getName());
            productPriceTextView.setText(product.getPrice());
            productCountTextView.setText(String.valueOf(product.getPurchaseCount()));
        }
    }

    public void showShopInfo() {
        shopInfoLayout.setVisibility(View.VISIBLE);
    }

    public void hideShopInfo() {
        shopInfoLayout.setVisibility(View.GONE);
    }

}
