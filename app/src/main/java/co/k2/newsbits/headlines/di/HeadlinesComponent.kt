package co.k2.newsbits.headlines.di

import co.k2.newsbits.headlines.HeadlinesActivity
import dagger.Subcomponent

@Subcomponent(modules = [HeadlinesModule::class])
interface HeadlinesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HeadlinesComponent
    }

    fun inject(activity: HeadlinesActivity)

}
