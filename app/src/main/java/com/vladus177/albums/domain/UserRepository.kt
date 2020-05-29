package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.UserModel

interface UserRepository {

    suspend fun getUserList(forceUpdate: Boolean): List<UserModel>

    suspend fun setFavoriteUser(userId: Long, favorite: Boolean)

    suspend fun insertAll(users: List<UserModel>)
}
