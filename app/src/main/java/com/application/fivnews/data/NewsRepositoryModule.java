package com.application.fivnews.data;

import com.application.fivnews.data.local.Local;
import com.application.fivnews.data.local.NewsLocalDataSource;
import com.application.fivnews.data.remote.NewsNetworkDataSource;
import com.application.fivnews.data.remote.Remote;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsRepositoryModule {

    @Provides
    @Singleton
    @Local
    NewsDataSource provideLyricsLocalDataSource() {
        return new NewsLocalDataSource();
    }

    @Provides
    @Singleton
    @Remote
    NewsDataSource provideLyricsRemoteDataSource() {
        return new NewsNetworkDataSource();
    }
}
