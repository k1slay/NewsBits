package co.k2.newsbits.data.source.local;

import java.util.List;

import co.k2.newsbits.data.models.Article;
import co.k2.newsbits.data.source.DataSource;
import io.reactivex.Completable;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public interface LocalDataSource extends DataSource {

    public Completable clearCache();

    public Completable saveToCache(List<Article> articles);

}
