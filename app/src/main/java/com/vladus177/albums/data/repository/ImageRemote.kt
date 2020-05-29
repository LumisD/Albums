package com.vladus177.albums.data.repository

import com.vladus177.albums.domain.model.ImageModel

interface ImageRemote {

    suspend fun getImageList(albumId: Long): List<ImageModel>

}