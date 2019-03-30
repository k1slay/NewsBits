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
    private final Navigator navigator;

    @Inject
    public HeadlineViewModelFactory(NewsRepository repository, Navigator navigator) {
        this.newsRepository = repository;
        this.navigator = navigator;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HeadlineViewModel.class)) {
            return (T) new HeadlineViewModel(newsRepository, navigator);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
