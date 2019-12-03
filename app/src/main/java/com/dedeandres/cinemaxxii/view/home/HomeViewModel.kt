package com.dedeandres.cinemaxxii.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dedeandres.cinemaxxii.domain.home.usecase.SearchMovieUseCase
import com.dedeandres.cinemaxxii.util.Resource
import com.dedeandres.cinemaxxii.util.setError
import com.dedeandres.cinemaxxii.util.setLoading
import com.dedeandres.cinemaxxii.util.setSuccess
import com.dedeandres.cinemaxxii.view.home.entity.MovieResult
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    val movieLivedata = MutableLiveData<Resource<List<MovieResult>>>()

    companion object {
        const val TAG = "HomeViewModel"
    }

    fun search(search: String) {
        movieLivedata.setLoading()
        searchMovieUseCase.execute(hashMapOf("query" to search)) {
            onComplete {
                movieLivedata.setSuccess(it)
            }

            onError {
                movieLivedata.setError(it)
            }

            onCancel {
                Timber.e(it.toString())
            }
        }
    }
}
