package co.k2.newsbits.data.source.local

import co.k2.newsbits.data.models.Article
import co.k2.newsbits.data.models.ArticleCacheMetaData
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val articlesDB: ArticlesDB
) : LocalDataSource {

    override suspend fun clearCache() {
        articlesDB.savedArticlesDao().deleteAll()
    }

    override suspend fun saveToCache(articles: List<Article>) {
        articlesDB.savedArticlesDao().insertAll(articles)
    }

    override suspend fun getHeadlines(country: String?): List<Article> {
        return articlesDB.savedArticlesDao().all ?: emptyList()
    }

    override suspend fun getCacheMetaData(): ArticleCacheMetaData? {
        return articlesDB.articlesMetaDataDao().getMetadata
    }

    override suspend fun saveCacheMetaData(articleCacheMetaData: ArticleCacheMetaData) {
        articlesDB.articlesMetaDataDao().insert(articleCacheMetaData)
    }
}
