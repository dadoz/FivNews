package com.application.sfy.data.remote.services;


import com.application.sfy.data.model.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {
//    @GET("chart.tracks.get")
//    Observable<List<Track>> getTracks(@Query("page") String page, @Query("page_size") String pageSize,
//            @Query("country") String country, @Query("f_has_lyrics") String fHasLyrics, @Query("apikey") String apiKey);
//
    @GET("")
    Observable<News> getNews(@Query("track_id") String newsId, @Query("apikey") String apiKey);
}
