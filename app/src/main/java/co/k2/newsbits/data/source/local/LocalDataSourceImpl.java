package co.k2.newsbits.data.source.local;

import java.util.List;

import javax.inject.Inject;

import co.k2.newsbits.data.models.Article;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public class LocalDataSourceImpl implements LocalDataSource {

    private final ArticlesDB articlesDB;

    @Inject
    public LocalDataSourceImpl(ArticlesDB db) {
        this.articlesDB = db;
    }

    @Override
    public Single<List<Article>> getHeadlines(String country) {
        return articlesDB.savedBeaconDao().getAll();
    }

    @Override
    public Completable saveToCache(List<Article> articles) {
        return articlesDB.savedBeaconDao().insertAll(articles);
    }

    @Override
    public Completable clearCache() {
        return articlesDB.savedBeaconDao().deleteAll();
    }

}
