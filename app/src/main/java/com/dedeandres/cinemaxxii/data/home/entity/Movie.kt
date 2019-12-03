package com.dedeandres.cinemaxxii.data.home.entity

import com.dedeandres.cinemaxxii.domain.home.entity.MovieModel
import com.google.gson.annotations.SerializedName

class MovieDto {
    @SerializedName(value = "results")
    var movieBeanDto: ArrayList<MovieBeanDto> = arrayListOf()
}

data class MovieBeanDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("vote_average")
    var voteAverage: Float,
    @SerializedName("poster_path")
    var posterPath: String?
)

fun List<MovieBeanDto>.mapToDomain(): List<MovieModel> = map {
    it.mapToDomain()
}

fun MovieBeanDto.mapToDomain(): MovieModel = MovieModel(
    id = id,
    title = title,
    voteAverage = voteAverage.toString(),
    poster = posterPath ?: ""
)