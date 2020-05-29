package com.vladus177.albums.data.repository

import com.vladus177.albums.domain.model.ImageModel

interface ImageLocal {

    suspend fun getImageList(albumId: Long): List<ImageModel>

    suspend fun insertAll(images: List<ImageModel>)

}