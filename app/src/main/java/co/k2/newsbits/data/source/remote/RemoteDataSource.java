package co.k2.newsbits.data.source.remote;

import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;

import co.k2.newsbits.common.Constants;
import co.k2.newsbits.data.NewsListener;
import co.k2.newsbits.data.models.ApiResponse;
import co.k2.newsbits.data.source.DataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void getHeadlines(String country, NewsListener listener) {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put(Constants.NewsApi.QUERY_KEY_COUNTRY, country);
        queryMap.put(Constants.NewsApi.QUERY_KEY_API, Constants.NewsApi.API_KEY);
        newsApiService.query(queryMap).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse == null || !apiResponse.hasArticles()) {
                        listener.onFailure(new Throwable(Constants.ERROR_NO_ARTICLES));
                    } else if (!apiResponse.success()) {
                        listener.onFailure(new Throwable(apiResponse.getErrorMessage()));
                    } else {
                        listener.onSuccess(apiResponse.getArticles());
                    }
                } else {
                    try {
                        listener.onFailure(new Exception(response.errorBody().string()));
                    } catch (IOException e) {
                        listener.onFailure(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }

}
