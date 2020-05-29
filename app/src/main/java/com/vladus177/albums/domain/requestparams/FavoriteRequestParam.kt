package com.vladus177.albums.domain.requestparams

data class FavoriteRequestParam(
    val userId: Long,
    val favorite: Boolean
)