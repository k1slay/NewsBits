package co.k2.newsbits.data.source.local;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import co.k2.newsbits.data.models.Article;
import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

@Dao
public interface ArticlesDao {

    @Query("SELECT * FROM cached_articles")
    Flowable<List<Article>> getAll();

    @Query("DELETE FROM cached_articles")
    Completable deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<Article> articles);

}
