package com.vladus177.albums.data.repository

import com.vladus177.albums.domain.model.AlbumModel

interface AlbumLocal {

    suspend fun getAlbumList(userId: Long): List<AlbumModel>

    suspend fun insertAll(images: List<AlbumModel>)

}