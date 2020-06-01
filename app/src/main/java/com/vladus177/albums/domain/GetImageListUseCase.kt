package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.ImageModel
import com.vladus177.albums.domain.requestparams.ImageListRequestParam
import com.vladus177.albums.common.extension.ResultUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetImageListUseCase @Inject constructor(
    private val repository: ImageRepository
) : ResultUseCase<ImageListRequestParam, List<ImageModel>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: ImageListRequestParam): List<ImageModel>? {
        return repository.getImageList(params.albumId, params.forceUpdate)
    }

}
