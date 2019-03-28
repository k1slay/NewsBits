package co.k2.newsbits.data.source;

import co.k2.newsbits.data.NewsListener;
import co.k2.newsbits.data.source.cache.LocalDataSource;
import co.k2.newsbits.data.source.remote.RemoteDataSource;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public class NewsRepository {

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;

    public NewsRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public void getCachedNews(NewsListener listener) {
        localDataSource.getHeadlines(null, listener);
    }

    public void fetchNews(String country, NewsListener listener) {
        remoteDataSource.getHeadlines(country, listener);
    }

}
