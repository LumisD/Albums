package com.vladus177.albums.data.repository

import com.vladus177.albums.domain.model.UserModel

interface UserRemote {

    suspend fun getUserList(): List<UserModel>
}