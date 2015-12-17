package com.example.fwqi.dudu.data;

/**
 * Created by leehong on 2015/11/1.
 */
public class CartCheckData {
    private String productsPrice = null;
    private String deliverPrice = null;
    private String totalPay = null;
    private String needPay = null;
    private String payMsg = null;

    public String getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(String productsPrice) {
        this.productsPrice = productsPrice;
    }

    public String getDeliverPrice() {
        return deliverPrice;
    }

    public void setDeliverPrice(String deliverPrice) {
        this.deliverPrice = deliverPrice;
    }

    public String getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(String totalPay) {
        this.totalPay = totalPay;
    }

    public String getNeedPay() {
        return needPay;
    }

    public void setNeedPay(String needPay) {
        this.needPay = needPay;
    }

    public String getPayMsg() {
        return payMsg;
    }

    public void setPayMsg(String payMsg) {
        this.payMsg = payMsg;
    }
}
