package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.ImageModel

interface ImageRepository {

    suspend fun getImageList(albumId: Long, forceUpdate: Boolean): List<ImageModel>

    suspend fun insertAll(images: List<ImageModel>)

}