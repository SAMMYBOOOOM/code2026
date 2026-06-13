package com.example.entity;

public class Category {
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

    /**
     * Primary Key ID
     */
    private Integer id;
    /**
     * Name
     */
    private String name;

}