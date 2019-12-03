package com.dedeandres.cinemaxxii.data.home.repository

import android.util.Log
import com.dedeandres.cinemaxxii.data.home.entity.mapToDomain
import com.dedeandres.cinemaxxii.domain.home.entity.MovieModel
import com.dedeandres.cinemaxxii.domain.home.repository.HomeRepository
import timber.log.Timber
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi
) : HomeRepository {
    override suspend fun searchMovie(apiKey: String, query: String): List<MovieModel> {
        Timber.e("searchMovie")
        val result = homeApi.searchMovie(apiKey, query).await()
        return result.movieBeanDto.map {
            Timber.e(it.toString())
            it.mapToDomain()
        }
    }
}