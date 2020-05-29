package com.vladus177.albums.domain.requestparams

data class AlbumListRequestParam(
    val userId: Long,
    val forceUpdate: Boolean
)