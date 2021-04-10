package co.k2.newsbits.data

import co.k2.newsbits.common.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */
@Module
class DataModule {

    @get:Provides
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(Constants.NewsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}
