package com.application.sfy.data;

import com.application.sfy.data.local.Local;
import com.application.sfy.data.local.NewsLocalDataSource;
import com.application.sfy.data.remote.NewsNetworkDataSource;
import com.application.sfy.data.remote.Remote;

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
