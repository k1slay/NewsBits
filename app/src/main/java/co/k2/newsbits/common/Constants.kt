package co.k2.newsbits.common

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
object Constants {
    const val ERROR_NO_ARTICLES = "No articles available at the moment"
    const val ARTICLE_CACHE_TABLE_NAME = "cached_articles"
    const val ARTICLE_METADATA_TABLE_NAME = "articles_metadata"
    const val ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val CHROME_PACKAGE = "com.android.chrome"
    const val TIMEZONE_UTC = "UTC"

    object NewsApi {
        const val BASE_URL = "https://newsapi.org"
        private const val VERSION_PATH = "/v2"
        private const val END_POINT = "/top-headlines"
        const val API_PATH = VERSION_PATH + END_POINT
        const val QUERY_KEY_COUNTRY = "country"
        const val QUERY_KEY_API = "apiKey"
        const val API_KEY = "API_KEY_HERE"
    }
}