package com.example.myapplication.entity;

import java.util.Date;
import java.util.List;

public class NewsCategory {

    private int id;
    private String categoryName;

    private Date lastUpdated;


    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }





    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }
}
