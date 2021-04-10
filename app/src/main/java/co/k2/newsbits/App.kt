package co.k2.newsbits

import android.app.Application
import co.k2.newsbits.dagger.AppComponent
import co.k2.newsbits.dagger.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.builder().application(this).build()
    }

}
