package co.k2.newsbits.data.source.remote;

import java.util.Map;

import co.k2.newsbits.common.Constants;
import co.k2.newsbits.data.models.ApiResponse;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public interface NewsApiService {

    @GET(Constants.NewsApi.API_PATH)
    Flowable<ApiResponse> query(@QueryMap Map<String, String> options);

}
