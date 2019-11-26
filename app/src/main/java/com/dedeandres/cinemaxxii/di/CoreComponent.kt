package com.dedeandres.cinemaxxii.di

import android.app.Application
import com.dedeandres.cinemaxxii.di.module.NetworkModule
import com.google.gson.Gson
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class
    ]
)
@Singleton
interface CoreComponent {

    fun getRetrofit(): Retrofit
    fun getGson(): Gson

    fun inject(app: Application)

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
        fun networkModule(module: NetworkModule): Builder
    }
}