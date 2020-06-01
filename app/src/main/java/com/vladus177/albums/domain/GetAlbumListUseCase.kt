package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.AlbumModel
import com.vladus177.albums.domain.requestparams.AlbumListRequestParam
import com.vladus177.albums.common.extension.ResultUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetAlbumListUseCase @Inject constructor(
    private val repository: AlbumRepository
) : ResultUseCase<AlbumListRequestParam, List<AlbumModel>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: AlbumListRequestParam): List<AlbumModel>? {
        return repository.getAlbumList(params.userId, params.forceUpdate)
    }
}
