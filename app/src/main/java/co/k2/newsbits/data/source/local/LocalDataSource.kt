package co.k2.newsbits.data.source.local

import co.k2.newsbits.data.models.Article
import co.k2.newsbits.data.models.ArticleCacheMetaData

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
interface LocalDataSource {
    suspend fun clearCache()
    suspend fun saveToCache(articles: List<Article>)
    suspend fun getCacheMetaData(): ArticleCacheMetaData?
    suspend fun saveCacheMetaData(articleCacheMetaData: ArticleCacheMetaData)
    suspend fun getHeadlines(country: String?): List<Article>
}
