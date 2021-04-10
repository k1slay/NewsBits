package co.k2.newsbits.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.k2.newsbits.data.models.ArticleCacheMetaData

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
@Dao
interface ArticlesMetaDataDao {

    @get:Query("SELECT * FROM articles_metadata WHERE id = 1")
    val getMetadata: ArticleCacheMetaData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articleCacheMetaData: ArticleCacheMetaData)

}
