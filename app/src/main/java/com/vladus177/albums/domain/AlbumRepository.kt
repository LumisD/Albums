package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.AlbumModel

interface AlbumRepository {

    suspend fun getAlbumList(userId: Long, forceUpdate: Boolean): List<AlbumModel>

    suspend fun insertAll(album: List<AlbumModel>)
}