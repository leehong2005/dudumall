package com.example.fwqi.dudu.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leehong on 2015/10/28.
 */
public class CartData {
    private static CartData instance = null;
    private Map<String, Product> purchaseProductMap = null;

    private CartData(){

    }

    public synchronized static CartData getInstance(){
        if (instance == null) {
            instance = new CartData();
        }

        return instance;
    }

    public Map<String, Product> getPurchaseProductMap() {
        return purchaseProductMap;
    }

    public void addProduct(Product product) {
        if (purchaseProductMap == null) {
            purchaseProductMap = new HashMap<>();
        }
        purchaseProductMap.put(product.getId(), product);
    }

    public boolean hasProduct(Product product) {
        if (purchaseProductMap != null && purchaseProductMap.containsKey(product.getId())) {
            return true;
        }

        return false;
    }

    public Product getProduct(String productId) {
        if (purchaseProductMap == null) {
            return null;
        }
        return purchaseProductMap.get(productId);
    }

    public static void releaseInstance() {
        instance = null;
    }
}
