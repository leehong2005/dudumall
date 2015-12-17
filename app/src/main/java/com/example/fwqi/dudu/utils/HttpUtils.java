package com.example.fwqi.dudu.utils;

import android.util.Log;

import com.example.fwqi.dudu.common.AppConstants;
import com.example.fwqi.dudu.common.Parameter;
import com.example.fwqi.dudu.data.CartData;
import com.example.fwqi.dudu.data.ServiceData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by leehong on 2015/10/10.
 */
public class HttpUtils {
    /**
     * Get recommend data from server.
     *
     * @return Recommend data.
     */
    public static ServiceData getRecommendData(){
        // Get the recommend data in json type from server.
        String recommendJsonData = getDataFromServer(AppConstants.RECOMMEND_URL, null);

        // Parse the recommend json data to recommend data.
        ServiceData recommendData = JsonUtils.parseToRecommendData(recommendJsonData);

        // Return the recommend data.
        return recommendData;
    }

    /**
     * Get the more product data from server.
     * @param cateId The category id.
     * @param offSet The offset of the product will be show.
     * @param pageSize The product count will be show in one page.
     * @return The more products will be show in one page.
     */
    public static ServiceData getMoreProductData(String cateId, int offSet, int pageSize) {
        // Get the product data in json type from server.
        try {
            String params = null;
            params = "cate=" + URLEncoder.encode(cateId, "UTF-8")
                    + "&offset=" + offSet + "&pagesize=" + pageSize;
            String productJson = getDataFromServer(AppConstants.MORE_PRODUCT_URL, params);

            // Parse the product json data to more product data.
            ServiceData productData = JsonUtils.parseToMoreProductData(productJson);

            // Return the product data.
            return productData;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the detail product data from server.
     *
     * @param productID The prodcut id.
     * @return The detail product data.
     */
    public static ServiceData getDetailProductData(String productID) {
        try {
            // Request detail product data from server.
            String params = "id=" + URLEncoder.encode(productID, "UTF-8");
            String detailProductJson = getDataFromServer(AppConstants.DETAIL_PRODUCT_URL, params);

            // Parse the product json data to more product data.
            ServiceData detailProductData = JsonUtils.parseToDetailProductData(detailProductJson);
            return detailProductData;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * Get the cart check data from server.
     * @return The cart check data.
     */
    public static ServiceData getCartCheckData() {
        // Request the cart check data from server.
        Parameter parameter = new Parameter();
        // Cart data parameters.
        String cartDataJson = JsonUtils.parseToJsonForCheck(CartData.getInstance());
        parameter.add("orders", cartDataJson);
        // Common parameters.
        String params = parameter.getParamString(true);

        //String params = JsonUtils.parseToJsonForCheck(CartData.getInstance());
        String cartCheckJson = getDataFromServer(AppConstants.CART_CHECK_URL, params);

        // Parse the cart check json to CartCheckData object.
        ServiceData cartCheckData = JsonUtils.parseToCartCheckData(cartCheckJson);
        return cartCheckData;
    }

    /**
     * Get the SMS certify number.
     * @param phoneNumber The phone number.
     * @return The SMS certify number.
     */
    public static ServiceData getSMSCertifyNumber(String phoneNumber) {
        String params = "mobile=" + phoneNumber;
        String smsCertifyJson = getDataFromServer(AppConstants.SMS_CERTIFY_URL, params);

        ServiceData smsCertifyServiceData = JsonUtils.parseToSMSCertify(smsCertifyJson);
        return smsCertifyServiceData;
    }

    /**
     * Get the login info from server.
     * @param phoneNumber The phone number.
     * @param smsCertify The sms certify number.
     * @return The login info.
     */
    public static ServiceData getLoginInfo(String phoneNumber, String smsCertify){
        String params = "mobile=" + phoneNumber + "&smscode=" + smsCertify;
        String loginJson = getDataFromServer(AppConstants.LOGIN_URL, params);

        ServiceData loginServiceData = JsonUtils.parseToLoginData(loginJson);
        return loginServiceData;
    }

    /**
     * Get myself info from server.
     * @return
     */
    public static ServiceData getMyselfInfo() {
        Parameter parameter = new Parameter();
        String params = parameter.getParamString(true);
        String myInfoJson = getDataFromServer(AppConstants.MY_INFO_URL, params);
        ServiceData myselfInfo = JsonUtils.parseToMyInfoData(myInfoJson);
        return myselfInfo;
    }

    /**
     * Request data from server.
     *
     * @param serverPath
     *          Server path.
     * @param params
     *          Request parameters.
     * @return
     *          Request data by String type.
     */
    private static String getDataFromServer(String serverPath, String params) {
        if (serverPath == null) {
            return null;
        }
        try {
            Log.d("HttpUtils", "getDataFromServer request host url = "
                    + serverPath + ",  params = " + params);
            // 根据地址创建URL对象
            URL url = new URL(serverPath);
            // 根据URL对象打开链接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // 设置请求的方式
            urlConnection.setRequestMethod("POST");
            // 设置请求的超时时间
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            // 设置请求的头
            urlConnection.setRequestProperty("Connection", "keep-alive");
            // 发送POST请求必须设置允许输出
            urlConnection.setDoOutput(true);
            // 发送POST请求必须设置允许输入
            urlConnection.setDoInput(true);

            // 传递的数据
            if (params != null) {
                OutputStream os = urlConnection.getOutputStream();
                os.write(params.getBytes());
                os.flush();
                os.close();
            }

            if (urlConnection.getResponseCode() == 200) {
                // 获取响应的输入流对象
                InputStream is = urlConnection.getInputStream();
                // 创建字节输出流对象
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                // 定义读取的长度
                int len = 0;
                // 定义缓冲区
                byte [] buffer = new byte[1024];
                // 按照缓冲区的大小，循环读取
                while ((len = is.read(buffer)) != -1){
                    // 根据读取的长度写入到os对象中
                    baos.write(buffer, 0, len);
                }
                // 释放资源
                is.close();
                baos.close();
                // 返回字符串
                String result = new String(baos.toByteArray());
                return result;
            } else {
                Log.d("ServerConnection", "网络请求失败！");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
