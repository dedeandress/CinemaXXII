package com.dedeandres.cinemaxxii.di.repository

import com.dedeandres.cinemaxxii.data.home.repository.HomeApi
import com.dedeandres.cinemaxxii.data.home.repository.HomeRepositoryImpl
import com.dedeandres.cinemaxxii.di.util.FeatureScope
import com.dedeandres.cinemaxxii.di.util.services
import com.dedeandres.cinemaxxii.domain.home.repository.HomeRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class HomeModule {

    @FeatureScope
    @Provides
    fun provideApi(retrofit: Retrofit): HomeApi {
        return services(retrofit)
    }

    @FeatureScope
    @Provides
    fun provideRepository(homeApi: HomeApi): HomeRepository {
        return HomeRepositoryImpl(homeApi)
    }
}