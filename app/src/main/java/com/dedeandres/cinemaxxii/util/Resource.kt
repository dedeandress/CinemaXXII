package com.dedeandres.cinemaxxii.util

import androidx.lifecycle.MutableLiveData

sealed class ResourceState {
    object LOADING: ResourceState()
    object ERROR: ResourceState()
    object SUCCESS: ResourceState()
}

data class Resource<out T> (val data: T? = null, val state: ResourceState, val errorModel: ErrorModel? = null)

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(
        Resource(
            state = ResourceState.LOADING
        )
)

fun <T> MutableLiveData<Resource<T>>.setError(errorModel: ErrorModel? ) =
    postValue(
        Resource(
            state = ResourceState.ERROR,
            errorModel = errorModel
        )
    )

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T?) =
    postValue(
        Resource(
            state = ResourceState.SUCCESS,
            data = data
        )
    )