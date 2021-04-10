package co.k2.newsbits.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.k2.newsbits.data.models.Article
import co.k2.newsbits.data.models.ArticleCacheMetaData

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
@Database(entities = [Article::class, ArticleCacheMetaData::class], version = 1)
abstract class ArticlesDB : RoomDatabase() {

    abstract fun savedArticlesDao(): ArticlesDao

    abstract fun articlesMetaDataDao(): ArticlesMetaDataDao

    companion object {
        const val DB_NAME = "CachedArticles"
    }

}
