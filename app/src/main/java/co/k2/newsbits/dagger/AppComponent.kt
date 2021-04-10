package co.k2.newsbits.dagger

import co.k2.newsbits.App
import co.k2.newsbits.headlines.di.HeadlinesComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class, NetworkModule::class, ViewModelBuilderModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
        fun networkModule(networkModule: NetworkModule): Builder

        @BindsInstance
        fun application(application: App): Builder
    }

    fun headlinesComponent(): HeadlinesComponent.Factory

}
