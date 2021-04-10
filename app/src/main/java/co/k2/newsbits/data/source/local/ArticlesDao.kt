package co.k2.newsbits.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.k2.newsbits.data.models.Article

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
@Dao
interface ArticlesDao {

    @get:Query("SELECT * FROM cached_articles")
    val all: List<Article>?

    @Query("DELETE FROM cached_articles")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(articles: List<Article>)

}
