package co.k2.newsbits.data;

import java.util.List;

import co.k2.newsbits.data.models.Article;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public interface NewsListener {

    public void onSuccess(List<Article> articles);

    public void onFailure(Throwable throwable);

}
