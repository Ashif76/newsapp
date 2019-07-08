package com.example.myapplication.network;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapplication.R;
import com.example.myapplication.entity.News;

import java.io.Serializable;

public class NewsDetailsActivity extends AppCompatActivity {

    public static String NEWS_ITEM = "news";
    private TextView newsFrom;
    private TextView newsTitle;
    private TextView newsDescription;
    private ImageView newsImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        newsFrom  = (TextView) findViewById(R.id.news_from);
        newsTitle = (TextView)findViewById(R.id.title);
        newsImage = (ImageView) findViewById(R.id.news_image);
        newsDescription = (TextView)findViewById(R.id.description);
        News news = (News) getIntent().getParcelableExtra(NEWS_ITEM);
        newsFrom.setText(news.getSource().getSourceName());
        newsTitle.setText(news.getTitle());
        newsDescription.setText(news.getNewsContent().getContent());
        Glide.with(this).load(news.getImgUrl())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into( newsImage);

    }

}
