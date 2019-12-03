package com.dedeandres.cinemaxxii.util

import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

typealias CompletionBlock<T> = UseCase.Request<T>.() -> Unit

abstract class UseCase<T>(val errorUtil: ErrorMapper) {

    private var parentJob: Job = Job()
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main

    protected abstract suspend fun executeOnBackground(data: Map<String, Any?>): T

    fun execute(data: Map<String, Any?>, block: CompletionBlock<T>) {
        val response = Request<T>().apply { block() }
        unsubscribe()
        parentJob = Job()

        CoroutineScope(foregroundContext + parentJob).launch {
            try {
                val result = withContext(backgroundContext) {
                    executeOnBackground(data)
                }
                response(result)
            } catch (cancellationException: CancellationException) {
                response(cancellationException)
            } catch (e: Exception) {
                Timber.e("${e.printStackTrace()}")
            }
        }
    }


    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }



    class Request<T> {
        private var onComplete: ((T) -> Unit)? = null
        private var onError: ((ErrorModel) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null

        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }

        fun onError(block: (ErrorModel) -> Unit) {

            onError = block

        }

        fun onCancel(block: (CancellationException) -> Unit) {
            onCancel = block
        }


        operator fun invoke(result: T) {
            onComplete?.let {
                it.invoke(result)
            }
        }

        operator fun invoke(error: ErrorModel) {
            onError?.let {
                it.invoke(error)

            }
        }

        operator fun invoke(error: CancellationException) {
            onCancel?.let {
                it.invoke(error)
            }
        }
    }
}