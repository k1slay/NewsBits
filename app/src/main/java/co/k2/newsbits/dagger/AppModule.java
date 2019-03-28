package co.k2.newsbits.dagger;

import android.content.Context;

import co.k2.newsbits.App;
import dagger.Binds;
import dagger.Module;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

@Module
public abstract class AppModule {

    @Binds
    abstract Context bindContext(App application);

}
