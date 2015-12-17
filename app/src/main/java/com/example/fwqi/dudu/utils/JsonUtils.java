package com.example.fwqi.dudu.utils;

import com.example.fwqi.dudu.common.AppConstants;
import com.example.fwqi.dudu.R;
import com.example.fwqi.dudu.data.CartCheckData;
import com.example.fwqi.dudu.data.CartData;
import com.example.fwqi.dudu.data.LoginData;
import com.example.fwqi.dudu.data.MyselfData;
import com.example.fwqi.dudu.data.PageInfo;
import com.example.fwqi.dudu.data.RecommendItemData;
import com.example.fwqi.dudu.data.Product;
import com.example.fwqi.dudu.data.ServiceData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by leehong on 2015/10/12.
 */
public class JsonUtils {
    /**
     * Parse the recommend json data to recommend data.
     *
     * @param recommendJson The recommend json.
     * @return The recommend data.
     */
    public static ServiceData parseToRecommendData(String recommendJson) {
        if (recommendJson == null) {
            return null;
        }

        // Parse recommend json to recommend data.
        JSONObject jsonObject = null;
        ServiceData recommendData = new ServiceData();
        try {
            jsonObject = new JSONObject(recommendJson);
            int errCode = jsonObject.optInt("errno");
            recommendData.setErrNo(errCode);
            recommendData.setErrMsg(jsonObject.optString("errmsg"));
            if (errCode == AppConstants.ERROR_CODE_SUCCESS) {
                recommendData.putData("list", parseToRecommendList(jsonObject));
                recommendData.putData("banner", parseToRecommendBanner(jsonObject));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recommendData;
    }

    /**
     * Parse the more product json data to more product data.
     *
     * @param moreProductJson The more product data json.
     * @return The more product data.
     */
    public static ServiceData parseToMoreProductData(String moreProductJson) {
        if (moreProductJson == null) {
            return null;
        }

        // Parse more product json to more product data.
        ServiceData moreProductData = new ServiceData();
        try {
            JSONObject jsonObject = new JSONObject(moreProductJson);
            int errCode = jsonObject.optInt("errno");
            moreProductData.setErrNo(errCode);
            moreProductData.setErrMsg(jsonObject.optString("errmsg"));
            if (errCode == AppConstants.ERROR_CODE_SUCCESS) {
                moreProductData.putData("pageInfo", parseToPageInfoList(jsonObject));
                moreProductData.putData("list", parseToMoreProductList(jsonObject));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return moreProductData;
    }

    public static ServiceData parseToDetailProductData(String detailProductJson) {
        if (detailProductJson == null) {
            return null;
        }

        // Parse detail product json to detail product data.
        ServiceData detailProductData = new ServiceData();
        try {
            JSONObject jsonObject = new JSONObject(detailProductJson);
            int errCode = jsonObject.optInt("errno");
            detailProductData.setErrNo(errCode);
            detailProductData.setErrMsg(jsonObject.optString("errmsg"));
            if (errCode == AppConstants.ERROR_CODE_SUCCESS) {
                detailProductData.putData("detail", parseToDetailProduct(jsonObject));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return detailProductData;
    }

    /**
     * Parse the CartData object to json.
     * @param cartData The CartData object.
     * @return CartData json.
     */
    public static String parseToJson(CartData cartData){
        if (cartData == null) {
            return null;
        }

        JSONObject jsonObjectParent = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        Map<String, Product> productMap = cartData.getPurchaseProductMap();
        if (productMap != null) {
            Iterator iter = productMap.entrySet().iterator();
            while (iter.hasNext()){
                Map.Entry entry = (Map.Entry) iter.next();
                Product product = (Product) entry.getValue();
                try {
                    JSONObject jsonObjectChild = new JSONObject();
                    jsonObjectChild.put("id", product.getId());
                    jsonObjectChild.put("name", product.getName());
                    jsonObjectChild.put("pic", product.getUrl());
                    jsonObjectChild.put("price", product.getPrice());
                    jsonObjectChild.put("image", product.getImage());
                    jsonObjectChild.put("sale", product.getIsSale());
                    jsonObjectChild.put("shop", product.getShopName());
                    jsonObjectChild.put("desc", product.getDetailDesc());
                    jsonObjectChild.put("count", product.getPurchaseCount());

                    jsonArray.put(jsonObjectChild);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            try {
                jsonObjectParent.put("cart", jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return jsonObjectParent.toString();
        }
        return null;
    }

    /**
     * Parse the local cart json to CartData object.
     *
     * @param cartJson The cart json from local memory.
     * @return
     */
    public static CartData parseToCartData(String cartJson){
        if (cartJson == null) {
            return null;
        }

        try {
            JSONObject jsonObjectParent = new JSONObject(cartJson);
            JSONArray jsonArray = jsonObjectParent.optJSONArray("cart");

            if (jsonArray != null) {
                CartData cartData = CartData.getInstance();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObjectChild = (JSONObject) jsonArray.opt(i);
                    Product product = new Product();
                    product.setId(jsonObjectChild.optString("id"));
                    product.setName(jsonObjectChild.optString("name"));
                    product.setUrl(jsonObjectChild.optString("pic"));
                    product.setPrice(jsonObjectChild.optString("price"));
                    product.setImage(jsonObjectChild.optInt("image"));
                    product.setShopName(jsonObjectChild.optString("shop"));
                    product.setIsSale(jsonObjectChild.optBoolean("sale"));
                    product.setDetailDesc(jsonObjectChild.optString("desc"));
                    product.setPurchaseCount(jsonObjectChild.optInt("count"));

                    cartData.addProduct(product);
                }

                return cartData;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parse the CartData object to json for getting the cart check data from server.
     * @param cartData The CartData object.
     * @return CartData json.
     */
    public static String parseToJsonForCheck(CartData cartData){
        if (cartData == null) {
            return null;
        }

        JSONObject jsonObjectParent = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        Map<String, Product> productMap = cartData.getPurchaseProductMap();
        if (productMap != null) {
            Iterator iter = productMap.entrySet().iterator();
            while (iter.hasNext()){
                Map.Entry entry = (Map.Entry) iter.next();
                Product product = (Product) entry.getValue();
                try {
                    JSONObject jsonObjectChild = new JSONObject();
                    jsonObjectChild.put("id", product.getId());
                    jsonObjectChild.put("price", product.getPrice());
                    jsonObjectChild.put("amount", String.valueOf(product.getPurchaseCount()));

                    jsonArray.put(jsonObjectChild);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            try {
                jsonObjectParent.put("products", jsonArray);
                jsonObjectParent.put("address_type", "0");
                jsonObjectParent.put("address", "3222");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return jsonObjectParent.toString();
        }
        return null;
    }

    /**
     * Parse the cart check json to CartCheckData object.
     *
     * @param cartCheckJson The recommend json.
     * @return The CartCheckData object..
     */
    public static ServiceData parseToCartCheckData(String cartCheckJson) {
        if (cartCheckJson == null) {
            return null;
        }

        // Parse cart check json to CartCheckData object.
        JSONObject jsonObject = null;
        ServiceData recommendData = new ServiceData();
        try {
            jsonObject = new JSONObject(cartCheckJson);
            int errCode = jsonObject.optInt("errno");
            recommendData.setErrNo(errCode);
            recommendData.setErrMsg(jsonObject.optString("errmsg"));
            if (errCode == AppConstants.ERROR_CODE_SUCCESS) {
                recommendData.putData("cart_check", parseToCartCheck(jsonObject));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recommendData;
    }

    /**
     * Parse the my info json to my info data.
     * @param myInfoJson
     * @return
     */
    public static ServiceData parseToMyInfoData(String myInfoJson) {
        if (myInfoJson == null) {
            return null;
        }

        // Parse cart check json to CartCheckData object.
        JSONObject jsonObject = null;
        ServiceData myInfoData = new ServiceData();
        try {
            jsonObject = new JSONObject(myInfoJson);
            int errCode = jsonObject.optInt("errno");
            myInfoData.setErrNo(errCode);
            myInfoData.setErrMsg(jsonObject.optString("errmsg"));
            if (errCode == AppConstants.ERROR_CODE_SUCCESS) {
                MyselfData myInfo  = new MyselfData();
                myInfo.setUnreadMsgCount(jsonObject.optInt("message_unread"));
                myInfoData.putData("my_info", myInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return myInfoData;
    }

    /**
     * Parse the sms certify number json.
     * @param smsCertifyJson
     * @return
     */
    public static ServiceData parseToSMSCertify(String smsCertifyJson){
        if (smsCertifyJson == null) {
            return null;
        }

        // Parse the sms certify number json.
        JSONObject jsonObject = null;
        ServiceData smsServiceData = new ServiceData();
        try {
            jsonObject = new JSONObject(smsCertifyJson);
            smsServiceData.setErrNo(jsonObject.optInt("errno"));
            smsServiceData.setErrMsg(jsonObject.optString("errmsg"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return smsServiceData;
    }

    /**
     * Parse to login info.
     * @param loginJson
     * @return
     */
    public static ServiceData parseToLoginData(String loginJson) {
        if (loginJson == null) {
            return null;
        }

        ServiceData loginServiceData = new ServiceData();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(loginJson);
            int errNo = jsonObject.optInt("errno");
            loginServiceData.setErrNo(errNo);
            loginServiceData.setErrMsg(jsonObject.optString("errmsg"));

            if (errNo == 0) {
                LoginData loginData = new LoginData();
                loginData.setToken(jsonObject.optString("token"));
                loginData.setPhoneNumber(jsonObject.optString("phone"));
                loginData.setUid(jsonObject.optString("uid"));
                loginData.setUserName(jsonObject.optString("username"));
                loginServiceData.putData("login", loginData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return loginServiceData;
    }

    /**
     * Parse the recommend json object to the recommend list data.
     *
     * @param recommendJsonObject The recommend json object.
     * @return The recommend list data.
     */
    private static List<RecommendItemData> parseToRecommendList(JSONObject recommendJsonObject){
        if (recommendJsonObject == null) {
            return null;
        }
        List<RecommendItemData> recommendList = new ArrayList<>();
        JSONArray jsonArray = recommendJsonObject.optJSONArray("list");
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonItem = (JSONObject) jsonArray.opt(i);
                JSONArray jsonItemArray = jsonItem.optJSONArray("list");

                RecommendItemData itemData = new RecommendItemData();
                itemData.setCateId(jsonItem.optString("cid"));
                itemData.setCateName(jsonItem.optString("cname"));
                itemData.setItemData(new ArrayList<Product>());
                recommendList.add(itemData);

                List<Product> productList = itemData.getItemData();
                for (int j = 0; j < jsonItemArray.length(); j++) {
                    JSONObject jsonProduct = (JSONObject) jsonItemArray.opt(j);
                    Product product  = new Product();
                    product.setId(jsonProduct.optString("id"));
                    product.setImage(R.drawable.apple);
                    product.setUrl(jsonProduct.optString("pic"));
                    product.setName(jsonProduct.optString("name"));
                    product.setPrice(jsonProduct.optString("price"));
                    productList.add(product);
                }
            }
        }
        return recommendList;
    }

    /**
     * Parse the recommend json object to the recommend banner data.
     *
     * @param recommendJsonObject The recommend json object
     * @return The recommend banner data
     */
    private static List<String> parseToRecommendBanner(JSONObject recommendJsonObject){
        if (recommendJsonObject == null) {
            return null;
        }

        List<String> bannerList = new ArrayList<>();
        try {
            JSONArray jsonArray = recommendJsonObject.getJSONArray("banners");
            for(int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonBanner = (JSONObject) jsonArray.opt(i);
                String bannerURL = jsonBanner.optString("url");
                bannerList.add(bannerURL);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bannerList;
    }

    /**
     * Parse the more product json object to the more product list data.
     *
     * @param moreProductJsonObject The more product json object.
     * @return The more product list data.
     */
    private static List<Product> parseToMoreProductList(JSONObject moreProductJsonObject){
        if (moreProductJsonObject == null) {
            return null;
        }
        List<Product> productList = new ArrayList<>();
        try {
            JSONArray jsonArray = moreProductJsonObject.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonProduct = (JSONObject) jsonArray.opt(i);
                Product product  = new Product();
                product.setId(jsonProduct.optString("id"));
                product.setUrl(jsonProduct.optString("pic"));
                product.setName(jsonProduct.optString("name"));
                product.setPrice(jsonProduct.optString("price"));
                product.setShopName(jsonProduct.optString("shop"));
                productList.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return productList;
    }
    /**
     * Parse the more product json object to the page info data.
     *
     * @param moreProductJsonObject The more product json object.
     * @return The page info data.
     */
    private static PageInfo parseToPageInfoList(JSONObject moreProductJsonObject){
        if (moreProductJsonObject == null) {
            return null;
        }
        PageInfo pageInfo = new PageInfo();
        JSONObject jsonPageInfo = moreProductJsonObject.optJSONObject("pageInfo");
        pageInfo.setLastId(jsonPageInfo.optString("lastid"));
        pageInfo.setPageSize(jsonPageInfo.optInt("pageSize"));
        pageInfo.setHasMore(jsonPageInfo.optInt("hasmore"));

        return pageInfo;
    }

    /**
     * Parse the detail product json object to the detail product data.
     *
     * @param detailProductJsonObject The detail product json object.
     * @return The detail product data.
     */
    private static Product parseToDetailProduct(JSONObject detailProductJsonObject) {
        if (detailProductJsonObject == null) {
            return null;
        }

        JSONObject jsonProduct = detailProductJsonObject.optJSONObject("detail");
        Product detailProduct  = new Product();
        detailProduct.setId(jsonProduct.optString("id"));
        detailProduct.setUrl(jsonProduct.optString("pic"));
        detailProduct.setName(jsonProduct.optString("name"));
        detailProduct.setPrice(jsonProduct.optString("price"));
        detailProduct.setShopName(jsonProduct.optString("seller"));
        detailProduct.setDetailDesc(jsonProduct.optString("desc"));

        return detailProduct;
    }

    /**
     * Parse the cart check json object to the CartCheckData object.
     * @param cartCheckJsonObject
     * @return
     */
    private static CartCheckData parseToCartCheck(JSONObject cartCheckJsonObject) {
        if (cartCheckJsonObject == null) {
            return null;
        }

        CartCheckData cartCheckData  = new CartCheckData();
        cartCheckData.setProductsPrice(cartCheckJsonObject.optString("products_price"));
        cartCheckData.setDeliverPrice(cartCheckJsonObject.optString("deliver_price"));
        cartCheckData.setTotalPay(cartCheckJsonObject.optString("totalpay"));
        cartCheckData.setNeedPay(cartCheckJsonObject.optString("needpay"));
        cartCheckData.setPayMsg(cartCheckJsonObject.optString("paymsg"));

        return cartCheckData;
    }
}
