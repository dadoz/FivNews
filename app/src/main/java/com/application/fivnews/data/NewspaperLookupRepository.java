package com.application.fivnews.data;

import android.content.Context;

import com.application.fivnews.BuildConfig;
import com.application.fivnews.data.local.Local;
import com.application.fivnews.data.model.News;
import com.application.fivnews.data.model.NewspaperInfo;
import com.application.fivnews.data.remote.Remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by davide-syn on 4/24/18.
 */

public class NewspaperLookupRepository {

//    private final NewsDataSource localDataSource;
    private final NewspaperLookupDataSource networkDataSource;

    @Inject
    NewspaperLookupRepository(Context context, @Local NewspaperLookupDataSource localDataSource, @Remote NewspaperLookupDataSource networkDataSource) {
//        this.localDataSource = localDataSource;
        this.networkDataSource = networkDataSource;
    }

    /**
     * get cached or network data
     * @return
     */
    public Observable<NewspaperInfo> getNewspaperInfo(String source) {
        return networkDataSource.getNewspaperInfo(source);
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
