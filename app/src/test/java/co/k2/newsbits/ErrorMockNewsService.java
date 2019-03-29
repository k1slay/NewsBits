package co.k2.newsbits;

import com.google.gson.Gson;

import java.util.Map;

import co.k2.newsbits.data.models.ApiResponse;
import co.k2.newsbits.data.source.remote.NewsApiService;
import io.reactivex.Single;
import retrofit2.mock.BehaviorDelegate;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 30/03/19
 */

public class ErrorMockNewsService implements NewsApiService {

    BehaviorDelegate<NewsApiService> delegate;

    public ErrorMockNewsService(BehaviorDelegate<NewsApiService> delegate) {
        this.delegate = delegate;
    }

    private static final String RESPONSE_ERROR = "{\"status\":\"error\",\"code\":\"apiKeyInvalid\",\"message\":\"Your API key is invalid or incorrect. Check your key, or go to https://newsapi.org to create a free API key.\"}";

    @Override
    public Single<ApiResponse> query(Map<String, String> options) {
        Gson gson = new Gson();
        ApiResponse apiResponse = gson.fromJson(RESPONSE_ERROR, ApiResponse.class);
        return Single.just(apiResponse);
    }
}
