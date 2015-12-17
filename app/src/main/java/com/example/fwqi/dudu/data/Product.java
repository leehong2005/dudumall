package com.example.fwqi.dudu.data;

/**
 * Created by leehong on 2015/9/21.
 */
public class Product {
    // The product id.
    private String id = null;
    // The product name.
    private String name = null;
    // The product image.
    private int image = 0;
    // The product image url.
    private String url = null;
    // The product tag.
    private boolean isSale = false;
    // The product price.
    private String price = null;
    // The shop name.
    private String shopName = null;
    // The product detail description.
    private String detailDesc = null;
    // The product purchase count.
    private int purchaseCount = 0;

    /**
     * The constructor.
     */
    public Product() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean getIsSale() {
        return isSale;
    }

    public void setIsSale(boolean isSale) {
        this.isSale = isSale;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
