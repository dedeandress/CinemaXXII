package com.dedeandres.cinemaxxii.di.module

import androidx.lifecycle.ViewModel
import com.dedeandres.cinemaxxii.di.util.ViewModelKey
import com.dedeandres.cinemaxxii.view.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}