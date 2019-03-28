package co.k2.newsbits.data.source.remote;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import co.k2.newsbits.common.Constants;
import co.k2.newsbits.data.models.ApiResponse;
import co.k2.newsbits.data.models.Article;
import co.k2.newsbits.data.source.DataSource;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public class RemoteDataSource implements DataSource {

    private final NewsApiService newsApiService;

    @Inject
    public RemoteDataSource(NewsApiService newsApiService) {
        this.newsApiService = newsApiService;
    }

    @Override
    public Flowable<List<Article>> getHeadlines(String country) {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put(Constants.NewsApi.QUERY_KEY_COUNTRY, country);
        queryMap.put(Constants.NewsApi.QUERY_KEY_API, Constants.NewsApi.API_KEY);

        return newsApiService.query(queryMap).map(new Function<ApiResponse, List<Article>>() {
            @Override
            public List<Article> apply(ApiResponse apiResponse) throws Exception {
                return apiResponse.getArticles();
            }
        });
    }

}
