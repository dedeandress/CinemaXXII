package com.dedeandres

import androidx.multidex.MultiDexApplication
import com.dedeandres.cinemaxxii.di.CoreComponent
import com.dedeandres.cinemaxxii.di.CoreComponentProvider
import com.dedeandres.cinemaxxii.di.DaggerCoreComponent
import com.dedeandres.cinemaxxii.di.module.NetworkModule

class App : MultiDexApplication(), CoreComponentProvider{

    var coreComponent: CoreComponent? = null

    override fun onCreate() {
        super.onCreate()
        provideCoreComponent()?.inject(this)
    }

    override fun provideCoreComponent(): CoreComponent? {
        if (coreComponent == null) {
            coreComponent = DaggerCoreComponent.builder()
                .networkModule(NetworkModule("https://api.themoviedb.org/3/"))
                .build()
        }

        return coreComponent
    }
}