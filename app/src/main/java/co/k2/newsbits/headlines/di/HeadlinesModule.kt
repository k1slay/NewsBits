package co.k2.newsbits.headlines.di

import androidx.lifecycle.ViewModel
import androidx.room.Room
import co.k2.newsbits.App
import co.k2.newsbits.dagger.ViewModelKey
import co.k2.newsbits.data.source.NewsRepository
import co.k2.newsbits.data.source.local.ArticlesDB
import co.k2.newsbits.headlines.HeadlineViewModel
import co.k2.newsbits.headlines.Navigator
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class HeadlinesModule {

    @Provides
    @IntoMap
    @ViewModelKey(HeadlineViewModel::class)
    fun provideHeadlinesViewModel(repository: NewsRepository): ViewModel {
        return HeadlineViewModel(repository)
    }

    @Provides
    fun provideNavigator(): Navigator {
        return Navigator()
    }

    @Provides
    fun provideArticlesDb(app: App): ArticlesDB {
        return Room.databaseBuilder(app, ArticlesDB::class.java, ArticlesDB.DB_NAME).build()
    }

}