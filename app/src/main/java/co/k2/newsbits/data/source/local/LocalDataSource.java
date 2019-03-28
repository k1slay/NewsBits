package co.k2.newsbits.data.source.local;

import java.util.List;

import javax.inject.Inject;

import co.k2.newsbits.data.models.Article;
import co.k2.newsbits.data.source.DataSource;
import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public class LocalDataSource implements DataSource {

    private final ArticlesDB articlesDB;

    @Inject
    public LocalDataSource(ArticlesDB db) {
        this.articlesDB = db;
    }

    @Override
    public Flowable<List<Article>> getHeadlines(String country) {
        return articlesDB.savedBeaconDao().getAll();
    }

    public Completable saveToCache(List<Article> articles) {
        return articlesDB.savedBeaconDao().insertAll(articles);
    }

    public Completable clearCache() {
        return articlesDB.savedBeaconDao().deleteAll();
    }

}
