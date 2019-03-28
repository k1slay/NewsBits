package co.k2.newsbits.data.source.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import co.k2.newsbits.data.models.Article;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

@Database(entities = {Article.class}, version = 1)
public abstract class ArticlesDB extends RoomDatabase {

    public static final String DB_NAME = "CachedArticles";

    public abstract ArticlesDao savedBeaconDao();

}
