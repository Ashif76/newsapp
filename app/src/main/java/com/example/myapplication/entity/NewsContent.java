package com.example.myapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class NewsContent implements Parcelable {

    private int id;

    private String content;

    private News news;

    private Date lastUpdated;

    protected NewsContent(Parcel in) {
        id = in.readInt();
        content = in.readString();
        news = in.readParcelable(News.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(content);
        dest.writeParcelable(news, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsContent> CREATOR = new Creator<NewsContent>() {
        @Override
        public NewsContent createFromParcel(Parcel in) {
            return new NewsContent(in);
        }

        @Override
        public NewsContent[] newArray(int size) {
            return new NewsContent[size];
        }
    };

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

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public News getNews() {
        return news;
    }

    public void setNews(final News news) {
        this.news = news;
    }


}
