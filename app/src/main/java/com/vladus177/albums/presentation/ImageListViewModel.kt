package com.vladus177.albums.presentation

import androidx.lifecycle.ViewModel
import com.vladus177.albums.common.extension.LiveResult
import com.vladus177.albums.domain.GetImageListUseCase
import com.vladus177.albums.domain.model.ImageModel
import com.vladus177.albums.domain.requestparams.ImageListRequestParam
import com.vladus177.albums.ui.mapper.ImageViewMapper
import com.vladus177.albums.ui.model.ImageView
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
    private val getImageListUseCase: GetImageListUseCase,
    private val imageViewMapper: ImageViewMapper
) : ViewModel() {

    val images = LiveResult<List<ImageModel>>()

    fun loadImageList(albumId: Long, forceUpdate: Boolean) {
        if (forceUpdate) {
            getImageListUseCase.executeRepeating(
                liveData = images,
                params = ImageListRequestParam(albumId, forceUpdate),
                repeatTime = 1000L
            )
        } else {
            getImageListUseCase.execute(
                liveData = images,
                params = ImageListRequestParam(albumId, forceUpdate)
            )
        }
    }

    fun convertImageModelsToImageViews(imageModels: List<ImageModel>): List<ImageView> {
        return imageModels?.map { with(imageViewMapper) { it.fromDomainToView() } }
    }
}