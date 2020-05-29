package com.vladus177.albums.common.extension

import androidx.lifecycle.MutableLiveData
import com.vladus177.albums.common.Resource
import com.vladus177.albums.common.ResourceState

typealias LiveResult<T> = MutableLiveData<Resource<T>>

@JvmName("postSuccessResult")
fun <T> LiveResult<T>.postSuccess(data: T) = postValue(Resource(ResourceState.SUCCESS, data))

@JvmName("postLoadingResult")
fun <T> LiveResult<T>.postLoading() = postValue(Resource(ResourceState.LOADING, value?.data))

@JvmName("postErrorResult")
fun <T> LiveResult<T>.postError(message: String? = null) = postValue(Resource(ResourceState.ERROR, value?.data, message))

@JvmName("postEmptyResult")
fun <T> LiveResult<T>.postEmpty() = postValue(Resource(ResourceState.EMPTY))

@JvmName("postCancelResult")
fun <T> LiveResult<T>.postCancel() = postValue(Resource(ResourceState.CANCEL))