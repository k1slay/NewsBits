package co.k2.newsbits.headlines;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import co.k2.newsbits.data.source.NewsRepository;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 29/03/19
 */

public class HeadlineViewModelFactory implements ViewModelProvider.Factory {

    private final NewsRepository newsRepository;

    @Inject
    public HeadlineViewModelFactory(NewsRepository repository) {
        this.newsRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HeadlineViewModel.class)) {
            return (T) new HeadlineViewModel(newsRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
