package com.application.sfy.tracklist;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TrackPresenterModule {
    @Binds
    abstract TrackContract.TrackPresenterInterface taskPresenter(TrackPresenter presenter);
}
