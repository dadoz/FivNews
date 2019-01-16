package com.application.sfy.data.local;

import com.application.sfy.data.NewsDataSource;
import com.application.sfy.data.model.News;

import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

/**
 * In Ram memory storage
 */
@Singleton
public class NewsLocalDataSource implements NewsDataSource {
    @Override
    public Observable<News> getNews(String request, String apiKey) {
        return null;
    }

//    @Override
//    public boolean hasLyrics(String trackId) {
//        try (Realm realm = Realm.getDefaultInstance()) {
//            return realm.where(News.class).equalTo("trackId", trackId)
//                    .findFirst() != null;
//        }
//    }

    /**
     *
     * @param trackId
     * @param apiKey
     * @return
     */
//    @Override
//    public Observable<News> getLyrics(String trackId, String apiKey) {
//        return Observable.just(Realm.getDefaultInstance())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(realm -> realm
//                        .where(News.class).equalTo("trackId", trackId)
//                        .findFirst()
//                        .<News>asFlowable()
//                        .toObservable());
//    }

    /**
     *
     * @param lyric
     * @param trackId
     */
//    @Override
//    public void setLyrics(News lyric, String trackId) {
//        Single.create((SingleOnSubscribe<Void>) singleSubscriber -> {
//            try (Realm r = Realm.getDefaultInstance()) {
//                r.executeTransaction(realm -> {
//                    lyric.setTrackId(trackId);
//                    realm.copyToRealm(lyric);
//                });
//            }})
//                .subscribeOn(Schedulers.io())
//                .subscribe();
//
//    }
}
