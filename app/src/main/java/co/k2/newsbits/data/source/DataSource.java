package co.k2.newsbits.data.source;

import co.k2.newsbits.data.NewsListener;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

public interface DataSource {

    public void getHeadlines(String country, NewsListener listener);

}
