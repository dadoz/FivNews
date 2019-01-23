package com.application.fivnews.ui;

import io.reactivex.functions.Action;

/**
 * progress loader
 */
public class ProgressLoader {
    public Action show;
    public Action hide;

    public ProgressLoader(Action show, Action hide) {
        this.show = show;
        this.hide = hide;
    }
}
