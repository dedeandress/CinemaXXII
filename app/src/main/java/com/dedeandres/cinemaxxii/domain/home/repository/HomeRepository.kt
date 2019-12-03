package com.dedeandres.cinemaxxii.domain.home.repository

import com.dedeandres.cinemaxxii.domain.home.entity.MovieModel

interface HomeRepository {
    suspend fun searchMovie(apiKey: String, query: String): List<MovieModel>
}