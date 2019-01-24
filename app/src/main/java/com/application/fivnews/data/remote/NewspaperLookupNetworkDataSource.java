package com.application.fivnews.data.remote;

import com.application.fivnews.data.NewspaperLookupDataSource;
import com.application.fivnews.data.model.NewspaperInfo;
import com.application.fivnews.data.remote.services.RetrofitServiceRx;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by davide-syn on 4/24/18.
 */

@Singleton
public class NewspaperLookupNetworkDataSource extends RetrofitDataSourceBase implements NewspaperLookupDataSource {
//    public Observable<Lyric> getLyrics(Context context, String owner, String repo) {
//        try {
//            InputStream inputStream = context.getAssets().open("sound_track_lyrics_response_200.json");
//            int size = inputStream.available();
//            byte[] buffer = new byte[size];
//            inputStream.read(buffer);
//            inputStream.close();
//            String json = new String(buffer, "UTF-8");
//
//            Lyric res = new GsonBuilder()
//                    .registerTypeAdapter(Lyric.class, new RetrofitServiceRx.LyricsJsonDeserializer())
//                    .create()
//                    .fromJson(json, Lyric.class);
//            return Observable.just(res);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new UnsupportedOperationException("error on get data");
//        }
//    }

    /**
     *
     * @param domain
     * @return
     */
    public Observable<NewspaperInfo> getNewspaperInfo(String domain) {
        return new RetrofitServiceRx().getNewspaperLookupRetrofit()
                .findCompany(domain)
                .compose(handleRxErrorsTransformer());
    }

//    /**
//     * TODO plese refactorize
//     */
//    @Override
//    public void setLyrics(Lyric lyric, String trackId) {
//    }

//    @Override
//    public boolean hasLyrics(String trackId) {
//        return false;
//    }
}
