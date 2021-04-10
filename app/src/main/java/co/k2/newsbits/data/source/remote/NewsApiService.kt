package co.k2.newsbits.data.source.remote

import co.k2.newsbits.common.Constants
import co.k2.newsbits.data.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
interface NewsApiService {

    @GET(Constants.NewsApi.API_PATH)
    suspend fun query(@QueryMap options: Map<String, String?>): Response<ApiResponse>

}
