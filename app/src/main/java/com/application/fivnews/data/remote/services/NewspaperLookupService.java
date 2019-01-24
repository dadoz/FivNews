package com.application.fivnews.data.remote.services;


import com.application.fivnews.data.model.News;
import com.application.fivnews.data.model.NewspaperInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewspaperLookupService {
    @GET("companies/find")
    Observable<NewspaperInfo> findCompany(@Query("domain") String newsId);
}
