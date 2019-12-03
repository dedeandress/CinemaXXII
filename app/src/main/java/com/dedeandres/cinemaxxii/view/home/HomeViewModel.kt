package com.dedeandres.cinemaxxii.view

import androidx.lifecycle.ViewModel
import com.dedeandres.cinemaxxii.domain.home.usecase.SearchMovieUseCase
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    companion object {
        const val TAG = "HomeViewModel"
    }

    init {
        Timber.e("init Home View Model")
    }

    fun search(search: String) {
        Timber.e("ic_search")
        searchMovieUseCase.execute(hashMapOf("query" to search)) {
            onComplete {
                Timber.e(it.toString())
            }

            onError {
                Timber.e(it.toString())
            }

            onCancel {
                Timber.e(it.toString())
            }
        }
    }
}
