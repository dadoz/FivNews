package com.application.fivnews.data;

import com.application.fivnews.data.local.Local;
import com.application.fivnews.data.local.NewsLocalDataSource;
import com.application.fivnews.data.model.NewspaperInfo;
import com.application.fivnews.data.remote.NewsNetworkDataSource;
import com.application.fivnews.data.remote.NewspaperLookupNetworkDataSource;
import com.application.fivnews.data.remote.Remote;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsRepositoryModule {

    @Provides
    @Singleton
    @Local
    NewsDataSource provideNewsLocalDataSource() {
        return new NewsLocalDataSource();
    }

    @Provides
    @Singleton
    @Remote
    NewsDataSource provideNewsRemoteDataSource() {
        return new NewsNetworkDataSource();
    }

    @Provides
    @Singleton
    @Local
    NewspaperLookupDataSource provideNewspaperLocalDataSource() {
        return new NewspaperLookupNetworkDataSource(); //TODO local still not build
    }

    @Provides
    @Singleton
    @Remote
    NewspaperLookupDataSource provideNewspaperRemoteDataSource() {
        return new NewspaperLookupNetworkDataSource();
    }
}
