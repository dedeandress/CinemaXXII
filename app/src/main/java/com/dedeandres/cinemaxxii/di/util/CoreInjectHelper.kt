package com.dedeandres.cinemaxxii.di.util

import android.content.Context
import com.dedeandres.cinemaxxii.di.CoreComponent
import com.dedeandres.cinemaxxii.di.CoreComponentProvider

object CoreInjectHelper {
    fun provideCoreComponent(applicationContext: Context): CoreComponent? {
        return if (applicationContext is CoreComponentProvider) {
            (applicationContext as CoreComponentProvider).provideCoreComponent()
        } else {
            throw IllegalStateException(
                "The application context you have passed does not implement CoreComponentProvider"
            )
        }
    }
}