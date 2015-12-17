package com.example.fwqi.dudu.utils;

import android.content.Context;
import android.text.TextUtils;

import com.example.fwqi.dudu.data.CartData;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by leehong on 2015/10/31.
 */
public class FileUtils {
    /**
     * Save the cart date to file.
     *
     * @param context
     */
    public static void saveCartDataToFile(Context context, String fileName){
        String cartJson = JsonUtils.parseToJson(CartData.getInstance());
        saveFile(context, fileName, cartJson);
    }

    /**
     * Get the cart data from the specified file.
     *
     * @param context
     * @param fileName
     * @return
     */
    public static CartData getCartDataFromFile(Context context, String fileName) {
        String cartJson = getFile(context, fileName);
        CartData cartData = JsonUtils.parseToCartData(cartJson);
        return cartData;
    }

    /**
     * Save data to the specified file.
     * @param context
     * @param fileName
     * @param saveContent
     */
    public static void saveFile(Context context, String fileName, String saveContent) {
        if (TextUtils.isEmpty(saveContent)) {
            return;
        }
        try {
            FileOutputStream fos = null;
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(saveContent.getBytes("UTF-8"));
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get data from the specified file.
     * @param context
     * @param fileName
     * @return
     */
    public static String getFile(Context context, String fileName){
        FileInputStream fis = null;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int length = 0;

        try {
            fis = context.openFileInput(fileName);
            while ((length = fis.read(buf)) != -1 ){
                bout.write(buf, 0, length);
            }
            return bout.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
