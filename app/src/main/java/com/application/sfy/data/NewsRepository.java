package com.application.sfy.data;

import android.content.Context;

import com.application.sfy.BuildConfig;
import com.application.sfy.data.local.Local;
import com.application.sfy.data.model.News;
import com.application.sfy.data.remote.Remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by davide-syn on 4/24/18.
 */

public class NewsRepository {

//    private final NewsDataSource localDataSource;
    private final NewsDataSource networkDataSource;

    @Inject
    NewsRepository(Context context, @Local NewsDataSource localDataSource, @Remote NewsDataSource networkDataSource) {
//        this.localDataSource = localDataSource;
        this.networkDataSource = networkDataSource;
    }

    /**
     * get cached or network data
     * @return
     */
    public Observable<List<News>> getNews(String source) {
        return networkDataSource.getNews(source, BuildConfig.API_KEY);
//                .doOnNext(items -> localDataSource.setLyrics(items, trackId));
//        if (localDataSource.hasLyrics(trackId)) {
//            //show data from cache
//            return localDataSource.getLyrics(trackId, BuildConfig.API_KEY);
//        }
//
//        //show data from netwkor and added on cache if some result
//        return networkDataSource.getLyrics(trackId, BuildConfig.API_KEY)
//                .doOnNext(items -> localDataSource.setLyrics(items, trackId));
    }
}
