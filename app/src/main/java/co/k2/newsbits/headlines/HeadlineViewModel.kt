package co.k2.newsbits.headlines

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.k2.newsbits.common.isLessThanADayOld
import co.k2.newsbits.data.models.Article
import co.k2.newsbits.data.source.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 29/03/19
 */

class HeadlineViewModel constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val headlines = mutableStateOf<List<Article>?>(null)
    val error = mutableStateOf<Pair<Boolean, String?>>(Pair(false, null))

    fun fetchNewArticles(country: String) = viewModelScope.launch(Dispatchers.IO) {
        error.value = Pair(false, null)
        headlines.value = null
        val result = newsRepository.fetchNews(country)
        result.body?.articles?.let {
            headlines.value = it
            updateCache(it, country)
        } ?: run {
            fetchCachedArticles(country, result.error?.message)
        }
    }

    private fun fetchCachedArticles(
        country: String, errorMessage: String?
    ) = viewModelScope.launch(Dispatchers.IO) {
        val cacheInfo = newsRepository.getCacheMetadata()
        val lastRefresh = cacheInfo?.lastRefreshTime ?: 0L
        if (lastRefresh.isLessThanADayOld && country == cacheInfo?.country) {
            headlines.value = newsRepository.cachedNews()
            error.value = Pair(true, null)
        } else {
            error.value = Pair(true, errorMessage)
        }
    }

    private suspend fun updateCache(
        articles: List<Article>, country: String
    ) = withContext(Dispatchers.IO) {
        newsRepository.clearCache()
        newsRepository.saveToCache(articles, System.currentTimeMillis(), country)
    }

}
