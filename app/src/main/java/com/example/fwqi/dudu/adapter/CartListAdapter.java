package com.example.fwqi.dudu.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.fwqi.dudu.data.CartData;
import com.example.fwqi.dudu.data.Product;
import com.example.fwqi.dudu.widget.CartListItemView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by leehong on 2015/10/25.
 */
public class CartListAdapter extends BaseAdapter {
    private Context context = null;
    private List<Product> purchaseProduct = null;

    public CartListAdapter(Context context) {
        this.context = context;
    }

    public void setDataList(CartData cartData) {
        if (cartData == null) {
            this.purchaseProduct = null;
        }else {
            purchaseProduct = new ArrayList<>();
            Iterator iter = cartData.getPurchaseProductMap().entrySet().iterator();
            while (iter.hasNext()){
                Map.Entry entry = (Map.Entry) iter.next();
                Product product = (Product) entry.getValue();
                purchaseProduct.add(product);
            }
        }

        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (purchaseProduct != null) {
            return purchaseProduct.size();
        }
        return 0;
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
        if (convertView == null) {
            convertView = new CartListItemView(context);
        }

        CartListItemView cartListItemView = (CartListItemView) convertView;
        cartListItemView.setData(purchaseProduct.get(position));
        if (0 == position) {
            cartListItemView.showShopInfo();
        }else {
            cartListItemView.hideShopInfo();
        }
        return convertView;
    }
}
