package com.example.fwqi.dudu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fwqi.dudu.common.AppConstants;
import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.event.CartEvent;
import com.example.fwqi.dudu.data.CartData;
import com.example.fwqi.dudu.data.Product;
import com.example.fwqi.dudu.data.ServiceData;
import com.example.fwqi.dudu.utils.HttpUtils;
import com.example.fwqi.dudu.widget.ProductImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.greenrobot.event.EventBus;

/**
 * Created by leehong on 2015/10/17.
 */
public class DetailProductActivity extends ActionBarActivity {
    // The product id.
    private String productID = null;
    // The detail product data.
    private ServiceData detailProductData = null;
    // The product data.
    private Product detailProduct = null;
    // The detail product ui handler.
    private DetailProductUIHandler uiHandler = new DetailProductUIHandler();

    public static void startActivity(Context context, Product product) {
        Intent intent = new Intent();
        intent.setClass(context, DetailProductActivity.class);
        intent.putExtra("productID", product.getId());
        ((Activity)context).startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get data from intent.
        Intent intent = this.getIntent();
        productID = intent.getStringExtra("productID");

        // Set detail product layout.
        setContentView(R.layout.product_detail_view);
        this.hideActionBar();
        initView();

        // Start work thread to request the detail product data from server.
        DetailProductRequestThread workThread = new DetailProductRequestThread(uiHandler);
        workThread.start();
        this.showLoadingView();
    }

    /**
     * Initialize the detail product view.
     */
    private void initView() {
        // Set the product image view size.
        ProductImageView productImage = (ProductImageView)findViewById(R.id.detail_product_pic);
        productImage.setRatio(674/720);

        // Implement the onClick event of the back button.
        ImageView backView = (ImageView)findViewById(R.id.detail_back_btn);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailProductActivity.this.finish();
            }
        });

        // Implement the onClick event of the subtract button.
        final TextView orderCountView = (TextView)findViewById(R.id.purchase_count_txt);
        ImageView decreaseBtn = (ImageView)findViewById(R.id.decrease_order_btn);
        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOrderCount = (String) orderCountView.getText();
                if (strOrderCount != null) {
                    int nOrderCount = Integer.valueOf(strOrderCount);

                    if (nOrderCount > 1) {
                        nOrderCount -= 1;
                        orderCountView.setText(String.valueOf(nOrderCount));
                    }
                }
            }
        });

        // Implement the onClick event of the add button.
        ImageView addBtn = (ImageView)findViewById(R.id.add_order_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOrderCount = (String) orderCountView.getText();
                if (strOrderCount != null) {
                    int nOrderCount = Integer.valueOf(strOrderCount);
                    nOrderCount += 1;
                    orderCountView.setText(String.valueOf(nOrderCount));
                }
            }
        });

        // Implement the onClick event of the add cart button.
        Button addCartBtn = (Button)findViewById(R.id.add_cart_btn);
        addCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (detailProduct != null) {
                    Log.i("DetailProductActivity", "add to cart, product id = " + detailProduct.getId());
                    CartData.getInstance().addProduct(detailProduct);
                    detailProduct.setPurchaseCount(Integer.valueOf((String) orderCountView.getText()));
                    EventBus.getDefault().post(new CartEvent());
                    DetailProductActivity.this.finish();
                }
            }
        });
    }

    /**
     * Refresh the detail product view with the data.
     * @param product The detail product data.
     */
    private void setData(Product product) {
        ProductImageView productImage = (ProductImageView)findViewById(R.id.detail_product_pic);
        TextView productNameView = (TextView) findViewById(R.id.detail_product_name);
        TextView productPriceView = (TextView) findViewById(R.id.detail_product_price);
        TextView shopView = (TextView) findViewById(R.id.detail_product_shop);
        TextView productDetailView = (TextView) findViewById(R.id.detail_product_detail);

        ImageLoader.getInstance().displayImage(product.getUrl(), productImage);
        productNameView.setText(product.getName());
        productPriceView.setText("$" + product.getPrice());
        shopView.setText(product.getShopName());
        productDetailView.setText(product.getDetailDesc());
    }

    class DetailProductUIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            // Refresh the detail product view.
            if (detailProductData != null && detailProductData.getErrNo() == AppConstants.ERROR_CODE_SUCCESS) {
                detailProduct = (Product) detailProductData.getData("detail");
                DetailProductActivity.this.setData(detailProduct);
                DetailProductActivity.this.hideLoadingView();
            }
        }
    }

    class DetailProductRequestThread extends Thread {
        private Handler uiHandler = null;

        public DetailProductRequestThread(Handler uiHandler) {
            this.uiHandler = uiHandler;
        }

        @Override
        public void run() {
            super.run();

            // Get the detail product data from server.
            detailProductData = HttpUtils.getDetailProductData(productID);

            // Request the detail product data from server.
            uiHandler.obtainMessage().sendToTarget();
        }
    }
}
