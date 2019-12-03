package com.dedeandres.cinemaxxii.data.home.repository

import com.dedeandres.cinemaxxii.data.home.entity.MovieDto
import io.reactivex.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApi {

    @GET("search/movie")
    fun searchMovie(@Query("api_key") apiKey: String, @Query("query") query: String): Deferred<MovieDto>

}