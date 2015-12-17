package com.example.fwqi.dudu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.data.CartData;
import com.example.fwqi.dudu.data.Product;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by leehong on 2015/9/27.
 */
public class RecommendGridAdapter extends BaseAdapter {
    private Context context = null;
    private List<Product> productList = null;

    /**
     * the constructor.
     *
     * @param context
     */
    public RecommendGridAdapter(Context context) {
        this.context = context;
    }

    /**
     * Set the product data list.
     *
     * @param productList
     */
    public void setProductList(List<Product> productList) {
        this.productList = productList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (productList == null) {
            return 0;
        }
        Log.d("dudu", "productList.size()=" + productList.size());
        return this.productList.size();
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
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.recommend_grid_item_view, null);
            viewHolder = new ViewHolder();
            viewHolder.productImage = (ImageView) convertView.findViewById(R.id.recommend_product_image);
            viewHolder.productDescription = (TextView) convertView.findViewById(R.id.recommend_product_description);
            viewHolder.productPrice = (TextView) convertView.findViewById(R.id.recommend_product_price);
            viewHolder.saleImage = (ImageView) convertView.findViewById(R.id.recommend_product_status);
            viewHolder.purchaseCount = (TextView)convertView.findViewById(R.id.recommend_purchase_count_txt);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product product = this.productList.get(position);
        viewHolder.productImage.setImageDrawable(null);
        ImageLoader.getInstance().displayImage(product.getUrl(), viewHolder.productImage);
        viewHolder.productDescription.setText(product.getName());
        viewHolder.productPrice.setText("$" + String.valueOf(product.getPrice()));
        viewHolder.saleImage.setVisibility(product.getIsSale() ? View.VISIBLE : View.GONE);
        Product purchaseProduct = CartData.getInstance().getProduct(product.getId());
        if (purchaseProduct != null) {
            viewHolder.purchaseCount.setVisibility(View.VISIBLE);
            viewHolder.purchaseCount.setText(String.valueOf(purchaseProduct.getPurchaseCount()));
        } else {
            viewHolder.purchaseCount.setVisibility(View.GONE);
            viewHolder.purchaseCount.setText("");
        }
        return convertView;
    }

    public class ViewHolder {
        public ImageView productImage = null;
        public TextView productDescription = null;
        public TextView productPrice = null;
        public ImageView saleImage = null;
        public TextView purchaseCount = null;
    }
}
