package com.dedeandres.cinemaxxii.di

import com.dedeandres.cinemaxxii.MainActivity
import com.dedeandres.cinemaxxii.di.module.ViewModelModule
import com.dedeandres.cinemaxxii.di.util.FeatureScope
import com.dedeandres.cinemaxxii.view.HomeFragment
import dagger.Component

@Component(
    modules = [
        ViewModelModule::class
    ],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface FeatureComponent {

    @Component.Builder
    interface Builder {
        fun build(): FeatureComponent
        fun coreComponent(component: CoreComponent?): Builder
    }

    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
}