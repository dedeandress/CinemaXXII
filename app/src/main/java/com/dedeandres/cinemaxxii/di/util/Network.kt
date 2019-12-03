package com.dedeandres.cinemaxxii.di.util

import retrofit2.Retrofit

inline fun <reified T> services(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}