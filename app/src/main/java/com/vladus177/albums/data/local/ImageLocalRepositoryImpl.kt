package com.vladus177.albums.data.local

import com.vladus177.albums.data.mapper.ImageDataMapper
import com.vladus177.albums.data.repository.ImageLocal
import com.vladus177.albums.domain.model.ImageModel
import javax.inject.Inject

class ImageLocalRepositoryImpl @Inject constructor(
    private val imageDao: ImageDao, private val imageMapper: ImageDataMapper
) : ImageLocal {

    override suspend fun getImageList(albumId: Long): List<ImageModel> {
        return imageDao.getAllImages().map { with(imageMapper) { it.fromEntityToDomain() } }
    }

    override suspend fun insertAll(images: List<ImageModel>) {
        imageDao.insertAll(images.map { with(imageMapper) { it.fromDomainToEntity() } })
    }

}