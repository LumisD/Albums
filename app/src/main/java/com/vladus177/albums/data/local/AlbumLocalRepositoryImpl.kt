package com.vladus177.albums.data.local

import com.vladus177.albums.data.mapper.AlbumDataMapper
import com.vladus177.albums.data.repository.AlbumLocal
import com.vladus177.albums.domain.model.AlbumModel
import javax.inject.Inject

class AlbumLocalRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao, private val albumMapper: AlbumDataMapper
) : AlbumLocal {
    override suspend fun getAlbumList(userId: Long): List<AlbumModel> {
        return albumDao.getAlbumsByUserId(userId)
            .map { with(albumMapper) { it.fromEntityToDomain() } }
    }

    override suspend fun insertAll(images: List<AlbumModel>) {
        albumDao.insertAll(images.map { with(albumMapper) { it.fromDomainToEntity() } })
    }

}