package com.vladus177.albums.data.remote

import com.vladus177.albums.data.mapper.AlbumDataMapper
import com.vladus177.albums.data.remote.net.AlbumsRestApi
import com.vladus177.albums.data.repository.AlbumRemote
import com.vladus177.albums.domain.model.AlbumModel
import javax.inject.Inject

class AlbumRemoteRepositoryImpl @Inject constructor(
    private val restApi: AlbumsRestApi,
    private val albumMapper: AlbumDataMapper
) :
    AlbumRemote {

    override suspend fun getAlbumList(userId: Long): List<AlbumModel>? {
        val responseList = restApi.getAlbumsByUserId(userId)
        return responseList.map { with(albumMapper) { it.fromDataToDomain() } }
    }
}