package co.k2.newsbits.data;

import co.k2.newsbits.common.Constants;
import co.k2.newsbits.data.source.remote.NewsApiService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

@Module
public class DataModule {

    @Provides
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.NewsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public NewsApiService provideNewsApiService(Retrofit retrofit) {
        return retrofit.create(NewsApiService.class);
    }

}
