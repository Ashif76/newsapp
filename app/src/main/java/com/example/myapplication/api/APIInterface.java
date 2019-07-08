package com.example.myapplication.api;

import com.example.myapplication.entity.News;
import com.example.myapplication.entity.NewsFeedData;
import com.example.myapplication.model.NewsStubs;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("news")
    Call<NewsFeedData> getNews(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("orderBy") String lastUpdate, @Query("category") String category);

}

