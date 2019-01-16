package com.application.sfy.modules.news;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NewsPresenterModule {
    @Binds
    abstract NewsContract.NewsPresenterInterface newsPresenter(NewsPresenter presenter);
}
