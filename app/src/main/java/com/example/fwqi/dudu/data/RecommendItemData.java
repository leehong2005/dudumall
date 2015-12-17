package com.example.fwqi.dudu.data;

import java.util.List;

/**
 * Created by leehong on 2015/9/26.
 */
public class RecommendItemData {
    // the title text of the item.
    private String cateName = null;
    // the category id.
    private String cateId = null;
    // the data of the item.
    private List<Product> itemData = null;

    /**
     * The constructor.
     */
    public RecommendItemData() {

    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public List<Product> getItemData() {
        return itemData;
    }

    public void setItemData(List<Product> itemData) {
        this.itemData = itemData;
    }
}
