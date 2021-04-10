package co.k2.newsbits.dagger

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelBuilderModule {

    @Binds
    abstract fun bindVMFactory(glanceViewModelFactory: VmFactory): ViewModelProvider.Factory

}
