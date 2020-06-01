package com.vladus177.albums.common.extension

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class ResultUseCase<Q, T>(
    override val backgroundContext: CoroutineContext,
    override val foregroundContext: CoroutineContext
) : BaseUseCase<Q, LiveResult<T>>(
    backgroundContext, foregroundContext
) {
    protected abstract suspend fun executeOnBackground(params: Q): T?

    override fun execute(liveData: LiveResult<T>, params: Q) {
        CoroutineScope(foregroundContext + newJob()).launch {
            liveData.postLoading()

            runCatching {
                withContext(backgroundContext) { executeOnBackground(params)!! }
            }.onSuccess { response ->
                liveData.postSuccess(response)
            }.onFailure { throwable ->
                when (throwable) {
                    is CancellationException -> liveData.postCancel()
                    is NullPointerException -> liveData.postEmpty()
                    else -> liveData.postError(throwable.message)
                }
            }
        }
    }

    override fun executeRepeating(
        liveData: LiveResult<T>,
        params: Q,
        repeatTime: Long,
        repeatQuantity: Long
    ) {
        CoroutineScope(foregroundContext + newJob()).launch {
            var firstCall = true
            var count = 0L

            while (isActive) {
                if (count >= repeatQuantity) {
                    return@launch
                }
                count += 1
                delay(if (firstCall) 100L else 1000L)
                firstCall = false

                liveData.postLoading()

                runCatching {
                    withContext(backgroundContext) { executeOnBackground(params)!! }
                }.onSuccess { response ->
                    liveData.postSuccess(response)
                    cancel()
                }.onFailure { throwable ->
                    when (throwable) {
                        is CancellationException -> liveData.postCancel()
                        is NullPointerException -> liveData.postEmpty()
                        else -> liveData.postError(throwable.message)
                    }
                }
            }
        }
    }

    override fun executeWithoutResponse(params: Q) {
        CoroutineScope(foregroundContext + newJob()).launch {
            runCatching {
                withContext(backgroundContext) { executeOnBackground(params)!! }
            }
        }
    }

    //for cases when we have to wait a first suspend fun before launch the second (see setFavoriteUser in UserListViewModel)
    override suspend fun executeSuspend(liveData: LiveResult<T>, params: Q) {
        runCatching {
            withContext(backgroundContext) { executeOnBackground(params)!! }
        }.onSuccess { response ->
            liveData.postSuccess(response)
        }.onFailure { throwable ->
            when (throwable) {
                is CancellationException -> liveData.postCancel()
                is NullPointerException -> liveData.postEmpty()
                else -> liveData.postError(throwable.message)
            }
        }
    }

    override suspend fun executeWithoutResponseSuspend(params: Q) {
        runCatching {
            withContext(backgroundContext) { executeOnBackground(params)!! }
        }
    }

}