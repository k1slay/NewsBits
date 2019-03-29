package co.k2.newsbits.headlines;

import androidx.lifecycle.ViewModel;
import co.k2.newsbits.data.source.NewsRepository;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 29/03/19
 */

public class HeadlineViewModel extends ViewModel {

    private final NewsRepository newsRepository;

    HeadlineViewModel(NewsRepository repository) {
        this.newsRepository = repository;
    }

}
