package com.vladus177.albums.data.repository

import com.vladus177.albums.domain.model.UserModel

interface UserLocal {

    suspend fun getUserList(): List<UserModel>

    suspend fun insertAll(users: List<UserModel>)

    suspend fun setFavoriteUser(userId: Long, favorite: Boolean)
}