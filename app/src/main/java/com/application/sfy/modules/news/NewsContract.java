package com.application.sfy.modules.news;

import android.util.SparseArray;

import com.application.sfy.BasePresenter;
import com.application.sfy.data.model.News;

public interface NewsContract {

    interface NewsView {
        void onRenderData(News item);
        void onError(String error);
        void showStandardLoading();
        void hideStandardLoading();
    }
    interface NewsPresenterInterface extends BasePresenter {
        void unsubscribe();
        void retrieveItems(SparseArray<String> params);
        void bindView(NewsView view);
        void deleteView();
    }
}