package co.k2.newsbits.data.source

import co.k2.newsbits.common.ApiResult
import co.k2.newsbits.common.ErrorObj
import co.k2.newsbits.data.models.ApiResponse
import co.k2.newsbits.data.models.Article
import co.k2.newsbits.data.models.ArticleCacheMetaData
import co.k2.newsbits.data.source.local.LocalDataSourceImpl
import co.k2.newsbits.data.source.remote.RemoteDataSourceImpl
import javax.inject.Inject

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSourceImpl,
    private val localDataSource: LocalDataSourceImpl
) {

    suspend fun cachedNews(): List<Article> {
        return localDataSource.getHeadlines(null)
    }

    suspend fun fetchNews(country: String): ApiResult<ApiResponse, ErrorObj> {
        return remoteDataSource.getHeadlines(country)
    }

    suspend fun saveToCache(articles: List<Article>, time: Long, country: String) {
        localDataSource.saveToCache(articles)
        localDataSource.saveCacheMetaData(ArticleCacheMetaData(time, country))
    }

    suspend fun clearCache() {
        return localDataSource.clearCache()
    }

    suspend fun getCacheMetadata(): ArticleCacheMetaData? {
        return localDataSource.getCacheMetaData()
    }

}
