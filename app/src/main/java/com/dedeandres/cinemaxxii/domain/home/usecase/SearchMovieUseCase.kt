package com.dedeandres.cinemaxxii.domain.home.usecase

import com.dedeandres.cinemaxxii.domain.home.entity.mapToResult
import com.dedeandres.cinemaxxii.domain.home.repository.HomeRepository
import com.dedeandres.cinemaxxii.util.ErrorMapper
import com.dedeandres.cinemaxxii.util.UseCase
import com.dedeandres.cinemaxxii.view.home.entity.MovieResult
import timber.log.Timber
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    errorUtil: ErrorMapper,
    private val homeRepository: HomeRepository
): UseCase<List<MovieResult>>(errorUtil) {
    override suspend fun executeOnBackground(data: Map<String, Any?>): List<MovieResult> {
        Timber.e("execute")
        val apiKey = "646921a9835faa94eae58eb47aba4f79"
        val query = data["query"] as String
        return homeRepository.searchMovie(apiKey, query).map {
            it.mapToResult()
        }
    }
}