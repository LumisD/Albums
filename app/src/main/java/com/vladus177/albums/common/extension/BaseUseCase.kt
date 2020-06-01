package com.vladus177.albums.common.extension

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<Q, W : MutableLiveData<*>>(
    protected open val backgroundContext: CoroutineContext,
    protected open val foregroundContext: CoroutineContext
) {
    private var parentJob = Job()

    abstract fun execute(liveData: W, params: Q)

    abstract fun executeRepeating(
        liveData: W,
        params: Q,
        repeatTime: Long,
        repeatQuantity: Long = 3
    )

    abstract fun executeWithoutResponse(params: Q)

    abstract suspend fun executeSuspend(liveData: W, params: Q)

    abstract suspend fun executeWithoutResponseSuspend(params: Q)

    protected fun newJob(): Job {
        parentJob = parentJob.run {
            cancelChildren()
            cancel()

            Job()
        }

        return parentJob
    }
}