package com.dedeandres.cinemaxxii.di.module

import androidx.lifecycle.ViewModelProvider
import com.dedeandres.cinemaxxii.util.DaggerViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}