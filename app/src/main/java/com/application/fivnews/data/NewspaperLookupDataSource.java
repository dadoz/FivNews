package com.application.fivnews.data;


import com.application.fivnews.data.model.News;
import com.application.fivnews.data.model.NewspaperInfo;

import java.util.List;

import io.reactivex.Observable;

public interface NewspaperLookupDataSource {
    Observable<NewspaperInfo> getNewspaperInfo(String request);
//    void setLyrics(Lyric lyrics, String paramKey);
//    boolean hasLyrics(String paramKey);
}
