package com.application.fivnews.data.local;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.application.fivnews.data.NewsDataSource;
import com.application.fivnews.data.model.News;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * In Ram memory storage
 */
@Singleton
public class NewsLocalDataSource implements NewsDataSource {

    private final Context context;

    public NewsLocalDataSource(Context context) {
        this.context = context;
    }
    @Override
    public Observable<List<News>> getNews(String request, String apiKey) {
        try {
            InputStream stream = context.getAssets().open("news_success_response.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            ArrayList obj = new Gson().fromJson(new String(buffer), new TypeToken<ArrayList<News>>() {}.getType());
            return Observable.just(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Observable.just(new ArrayList<>());
    }

//    @Override
//    public boolean hasLyrics(String trackId) {
//    }

    /**
     *
     * @param trackId
     * @param apiKey
     * @return
     */
//    @Override
//    public Observable<News> getLyrics(String trackId, String apiKey) {
//    }

    /**
     *
     * @param lyric
     * @param trackId
     */
//    @Override
//    public void setLyrics(News lyric, String trackId) {
//    }
}
