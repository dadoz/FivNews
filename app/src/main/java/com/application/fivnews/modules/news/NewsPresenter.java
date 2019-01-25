package com.application.fivnews.modules.news;

import android.util.Log;
import android.util.SparseArray;

import com.application.fivnews.data.NewsRepository;
import com.application.fivnews.data.NewspaperLookupRepository;
import com.application.fivnews.data.model.News;
import com.application.fivnews.data.remote.services.NewspaperLookupService;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class NewsPresenter implements NewsContract.NewsPresenterInterface {
    private static final String TAG = "TrackPresenter";
    private static WeakReference<NewsContract.NewsView> newsView;
    private final NewsRepository repository;
    private final NewspaperLookupRepository newspaperLookupRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected ProgressLoader loader;

    @Inject
    NewsPresenter(NewsRepository repository, NewspaperLookupRepository newspaperLookupRepository) {
        this.repository = repository;
        this.newspaperLookupRepository = newspaperLookupRepository;
    }

    @Override
    public void unsubscribe() {
        if (compositeDisposable != null)
            compositeDisposable.dispose();
    }

    /**
     * bind view to presenter
     * @param view
     */
    @Override
    public void bindView(NewsContract.NewsView view) {
        newsView = new WeakReference<>(view);
        loader = new ProgressLoader(
                view::showStandardLoading,
                view::hideStandardLoading);
    }

    /**
     * delete view
     */
    @Override
    public void deleteView() {
        newsView.clear();
    }

    /**
     * retrieve item obs
     * @param params
     */
    @Override
    public void retrieveItems(SparseArray<String> params) {
        Log.e(TAG, params.toString());
        compositeDisposable.add(repository
                .getNews(params.get(0))
//                .compose(retrieveNewspaperInfo())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(composeLoaderTransformer(loader))
                .doOnError(Throwable::printStackTrace)
                .subscribe(
                        items -> newsView.get().onRenderData(items),
                        error -> newsView.get().onError(error.getMessage())));
    }

    /**
     *
     * @return
     */
    private ObservableTransformer<List<News>, List<News>> retrieveNewspaperInfo() {
        return obs -> obs
                .flatMap(newsList -> Observable.fromIterable(newsList)
                .flatMap(news -> Observable.just(news)
                        .map(newsDeep -> new URL(newsDeep.getUrl()))
                        .map(url -> url.getProtocol() + "://" + url.getAuthority())
                        .doOnNext(url1 -> Log.e(getClass().getName(), url1))
                        .flatMap(newspaperLookupRepository::getNewspaperInfo)
                        .doOnNext(newspaperInfo -> news.getSource().setNewspaperName(newspaperInfo.getName()))
                        .doOnNext(newspaperInfo -> news.getSource().setNewspaperLogoUrl(newspaperInfo.getLogo()))
                        .map(x -> news))
                .toList().toObservable());

    }


    /**
     * compose loader transformer
     * @param loader
     * @param <T>
     * @return
     */
    <T extends List<News>>ObservableTransformer<T, T> composeLoaderTransformer(ProgressLoader loader) {
        return upstream -> upstream
                .doOnSubscribe(disposable -> loader.show.run())
                .doOnError(error -> loader.hide.run())
                .doOnNext(res -> loader.hide.run());
    }

    /**
     * progress loader
     */
    class ProgressLoader {
        Action show;
        Action hide;

        ProgressLoader(Action show, Action hide) {
            this.show = show;
            this.hide = hide;
        }
    }

}
