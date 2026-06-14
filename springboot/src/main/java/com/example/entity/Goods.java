package com.example.entity;

import java.math.BigDecimal;

public class Goods {

    /**
     * Primary Key ID
     */
    private Integer id;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Name
     */
    private String name;
    /**
     * Image
     */
    private String img;
    /**
     * Price
     */
    private BigDecimal price;
    /**
     * Brief Description
     */
    private String description;
    /**
     * Details
     */
    private String content;
    /**
     * Stock
     */
    private Integer store;
    /**
     * Category ID
     */
    private Integer categoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private String categoryName;
    /**
     * Listing Status
     */
    private String status;
    /**
     * Page Views
     */
    private Integer views;
    /**
     * Sales Count
     */
    private Integer saleCount;
    /**
     * Creation Time
     */
    private String time;

}
