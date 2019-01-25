package com.application.fivnews.modules.news;

import android.util.SparseArray;

import com.application.fivnews.BasePresenter;
import com.application.fivnews.data.model.News;
import com.application.fivnews.data.model.NewspaperInfo;

import java.util.List;

public interface NewsContract {

    interface NewsView {
        void onRenderData(List<News> item);
        void onError(String error);
        void onRenderNewspaperInfo(NewspaperInfo item);
        void onErrorNewspaperInfo(String error);
        void showStandardLoading();
        void hideStandardLoading();
    }
    interface NewsPresenterInterface extends BasePresenter {
        void unsubscribe();
        void retrieveItems(SparseArray<String> params);
        void retrieveNewspaperInfo(News news);
        void bindView(NewsView view);
        void deleteView();
    }
}