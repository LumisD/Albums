package com.vladus177.albums.presentation

import androidx.lifecycle.ViewModel
import com.vladus177.albums.common.extension.LiveResult
import com.vladus177.albums.domain.GetAlbumListUseCase
import com.vladus177.albums.domain.model.AlbumModel
import com.vladus177.albums.domain.requestparams.AlbumListRequestParam
import com.vladus177.albums.ui.mapper.AlbumViewMapper
import com.vladus177.albums.ui.model.AlbumView
import javax.inject.Inject

class AlbumListViewModel @Inject constructor(
    private val getAlbumListUseCase: GetAlbumListUseCase,
    private val albumMapper: AlbumViewMapper
) : ViewModel() {

    val albums = LiveResult<List<AlbumModel>>()

    fun loadAlbumList(userId: Long, forceUpdate: Boolean) {
        if (forceUpdate) {
            getAlbumListUseCase.executeRepeating(
                liveData = albums,
                params = AlbumListRequestParam(userId, forceUpdate),
                repeatTime = 1000L
            )
        } else {
            getAlbumListUseCase.execute(
                liveData = albums,
                params = AlbumListRequestParam(userId, forceUpdate)
            )
        }
    }

    fun convertAlbumModelsToAlbumViews(albumModels: List<AlbumModel>): List<AlbumView> {
        return albumModels?.map { with(albumMapper) { it.fromDomainToView() } }
    }
}