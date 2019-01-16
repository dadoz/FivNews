package com.application.sfy.data;


import com.application.sfy.data.model.News;

import io.reactivex.Observable;

public interface NewsDataSource {
    Observable<News> getNews(String request, String apiKey);
//    void setLyrics(Lyric lyrics, String paramKey);
//    boolean hasLyrics(String paramKey);
}
