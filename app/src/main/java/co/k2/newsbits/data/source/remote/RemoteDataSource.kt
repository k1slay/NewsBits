package co.k2.newsbits.data.source.remote

import co.k2.newsbits.common.ApiResult
import co.k2.newsbits.common.Constants
import co.k2.newsbits.common.ErrorObj
import co.k2.newsbits.common.NetworkUtils
import co.k2.newsbits.data.models.ApiResponse
import co.k2.newsbits.data.source.RemoteDataSource
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
class RemoteDataSourceImpl @Inject constructor(retrofit: Retrofit) : RemoteDataSource {

    private val newsApiService = retrofit.create(NewsApiService::class.java)

    override suspend fun getHeadlines(country: String): ApiResult<ApiResponse, ErrorObj> {
        val queryMap = HashMap<String, String?>()
        queryMap[Constants.NewsApi.QUERY_KEY_COUNTRY] = country
        queryMap[Constants.NewsApi.QUERY_KEY_API] = Constants.NewsApi.API_KEY
        return try {
            NetworkUtils.processResponse(newsApiService.query(queryMap))
        } catch (e: Exception) {
            NetworkUtils.handleException(e)
        }
    }

}
