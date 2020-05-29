package com.vladus177.albums.domain.requestparams

data class ImageListRequestParam(
    val albumId: Long,
    val forceUpdate: Boolean
)