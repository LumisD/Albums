package com.vladus177.albums.data

import com.vladus177.albums.data.source.AlbumDataSourceFactory
import com.vladus177.albums.domain.AlbumRepository
import com.vladus177.albums.domain.model.AlbumModel
import javax.inject.Inject

class AlbumDataRepository @Inject constructor(
    private val factory: AlbumDataSourceFactory
) : AlbumRepository {

    override suspend fun getAlbumList(userId: Long, forceUpdate: Boolean): List<AlbumModel> {
        if (forceUpdate) {
            val users = factory.getRemote().getAlbumList(userId)
            users?.let { insertAll(it) }

            return factory.getLocal().getAlbumList(userId)
        } else {
            return factory.getLocal().getAlbumList(userId)
        }
    }

    override suspend fun insertAll(album: List<AlbumModel>) = factory.getLocal().insertAll(album)
}