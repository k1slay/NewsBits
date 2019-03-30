package co.k2.newsbits.headlines;

import android.content.Context;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import co.k2.newsbits.data.models.Article;
import co.k2.newsbits.data.source.NewsRepository;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 29/03/19
 */

public class HeadlineViewModel extends ViewModel {

    private final NewsRepository newsRepository;
    private final Navigator navigator;

    HeadlineViewModel(NewsRepository repository, Navigator navigator) {
        this.newsRepository = repository;
        this.navigator = navigator;
    }

    Single<List<Article>> fetchNewArticles(String country) {
        return newsRepository.fetchNews(country)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    Single<List<Article>> fetchCachedArticles() {
        return newsRepository.getCachedNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    Disposable updateCache(List<Article> articles) {
        return newsRepository.clearCache()
                .andThen(newsRepository.saveToCache(articles))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    void openNewsArticle(@NonNull Context context, @NonNull Article article) {
        navigator.navigateToWebPage(context, article.getUrl());
    }

}
