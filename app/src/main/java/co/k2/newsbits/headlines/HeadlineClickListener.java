package co.k2.newsbits.headlines;

import co.k2.newsbits.data.models.Article;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 31/03/19
 */

public interface HeadlineClickListener {

    public void onClick(int position, Article article);

}
