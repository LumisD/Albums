package com.vladus177.albums.data

import com.vladus177.albums.data.source.ImageDataSourceFactory
import com.vladus177.albums.domain.ImageRepository
import com.vladus177.albums.domain.model.ImageModel
import javax.inject.Inject

class ImageDataRepository @Inject constructor(
    private val factory: ImageDataSourceFactory

) : ImageRepository {

    override suspend fun getImageList(albumId: Long, forceUpdate: Boolean): List<ImageModel> {
        if (forceUpdate) {
            val images = factory.getRemote().getImageList(albumId)
            images?.let { insertAll(it) }

            return factory.getLocal().getImageList(albumId)
        } else {
            return factory.getLocal().getImageList(albumId)
        }
    }

    override suspend fun insertAll(images: List<ImageModel>) = factory.getLocal().insertAll(images)
}
