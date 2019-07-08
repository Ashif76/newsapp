package com.example.myapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Source implements Parcelable {
    private int id;
    private String sourceName;

    private News news;

    private Date lastUpdated;

    protected Source(Parcel in) {
        id = in.readInt();
        sourceName = in.readString();
        news = in.readParcelable(News.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(sourceName);
        dest.writeParcelable(news, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Source> CREATOR = new Creator<Source>() {
        @Override
        public Source createFromParcel(Parcel in) {
            return new Source(in);
        }

        @Override
        public Source[] newArray(int size) {
            return new Source[size];
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

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(final String sourceName) {
        this.sourceName = sourceName;
    }

    public News getNews() {
        return news;
    }

    public void setNews(final News news) {
        this.news = news;
    }
}
