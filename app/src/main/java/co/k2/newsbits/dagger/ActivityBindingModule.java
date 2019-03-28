package co.k2.newsbits.dagger;

import co.k2.newsbits.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Copyright (C) 2019 K2 CODEWORKS
 * All rights reserved
 *
 * @author Kislay
 * @since 28/03/19
 */

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();

}
