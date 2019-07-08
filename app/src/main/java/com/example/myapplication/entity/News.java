package com.example.myapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class News implements Parcelable {

    private int id;

    private String title;
    private String country;
    private String category;

    private String description;
    private String htmlUrl;
    private String imgUrl;

    private NewsContent newsContent;

    private NewsCategory newsCategory;

    private Date lastUpdated;
    private Source source;

    protected News(Parcel in) {
        id = in.readInt();
        title = in.readString();
        country = in.readString();
        category = in.readString();
        description = in.readString();
        htmlUrl = in.readString();
        imgUrl = in.readString();
        newsContent = in.readParcelable(NewsContent.class.getClassLoader());
        source = in.readParcelable(Source.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(country);
        dest.writeString(category);
        dest.writeString(description);
        dest.writeString(htmlUrl);
        dest.writeString(imgUrl);
        dest.writeParcelable(newsContent, flags);
        dest.writeParcelable(source, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public NewsCategory getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(final NewsCategory newsCategory) {
        this.newsCategory = newsCategory;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }





    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(final String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(final String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public NewsContent getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(final NewsContent newsContent) {
        this.newsContent = newsContent;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(final Source source) {
        this.source = source;
    }
}
