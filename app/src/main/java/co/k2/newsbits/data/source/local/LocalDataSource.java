package co.k2.newsbits.data.source.local;

import javax.inject.Inject;

import co.k2.newsbits.data.NewsListener;
import co.k2.newsbits.data.source.DataSource;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public class LocalDataSource implements DataSource {

    @Inject
    public LocalDataSource() {

    }

    @Override
    public void getHeadlines(String country, NewsListener listener) {

    }

}
