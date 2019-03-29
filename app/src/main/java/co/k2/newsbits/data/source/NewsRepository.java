package co.k2.newsbits.data.source;

import java.util.List;

import javax.inject.Inject;

import co.k2.newsbits.data.models.Article;
import co.k2.newsbits.data.source.local.LocalDataSourceImpl;
import co.k2.newsbits.data.source.remote.RemoteDataSource;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public class NewsRepository {

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSourceImpl localDataSource;

    @Inject
    public NewsRepository(RemoteDataSource remoteDataSource, LocalDataSourceImpl localDataSourceImpl) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSourceImpl;
    }

    public Single<List<Article>> getCachedNews() {
        return localDataSource.getHeadlines(null);
    }

    public Single<List<Article>> fetchNews(String country) {
        return remoteDataSource.getHeadlines(country);
    }

    public Completable saveToCache(List<Article> articles) {
        return localDataSource.saveToCache(articles);
    }

    public Completable clearCache() {
        return localDataSource.clearCache();
    }

}
