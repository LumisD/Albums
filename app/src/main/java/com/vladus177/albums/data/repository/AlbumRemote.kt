package com.vladus177.albums.data.repository

import com.vladus177.albums.domain.model.AlbumModel

interface AlbumRemote {

    suspend fun getAlbumList(userId: Long): List<AlbumModel>?

}