package com.dedeandres.cinemaxxii.domain.home.entity

import com.dedeandres.cinemaxxii.view.home.entity.MovieResult

data class MovieModel(val id: Int, val title: String, val voteAverage: String, val poster: String)

fun MovieModel.mapToResult() = MovieResult(
    id = id,
    title = title,
    poster = poster
)