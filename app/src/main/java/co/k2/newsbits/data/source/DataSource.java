package co.k2.newsbits.data.source;

import java.util.List;

import co.k2.newsbits.data.models.Article;
import io.reactivex.Single;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public interface DataSource {

    public Single<List<Article>> getHeadlines(String country);

}
