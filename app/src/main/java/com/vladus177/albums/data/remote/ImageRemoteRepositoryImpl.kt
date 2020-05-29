package com.vladus177.albums.data.remote

import com.vladus177.albums.data.mapper.ImageDataMapper
import com.vladus177.albums.data.remote.net.AlbumsRestApi
import com.vladus177.albums.data.repository.ImageRemote
import com.vladus177.albums.domain.model.ImageModel
import javax.inject.Inject

class ImageRemoteRepositoryImpl @Inject constructor(
    private val restApi: AlbumsRestApi,
    private val imageMapper: ImageDataMapper
) :
    ImageRemote {

    override suspend fun getImageList(albumId: Long): List<ImageModel> =
        restApi.getImagesByAlbumId(albumId).map { with(imageMapper) { it.fromDataToDomain() } }
}