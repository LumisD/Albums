package com.vladus177.albums.data.remote

import com.vladus177.albums.data.mapper.UserDataMapper
import com.vladus177.albums.data.remote.net.AlbumsRestApi
import com.vladus177.albums.data.repository.UserRemote
import com.vladus177.albums.domain.model.UserModel
import javax.inject.Inject

class UserRemoteRepositoryImpl @Inject constructor(
    private val restApi: AlbumsRestApi,
    private val usserMapper: UserDataMapper
) : UserRemote {

    override suspend fun getUserList(): List<UserModel> = restApi.getAllUsers()
        .map { with(usserMapper) { it.fromDataToDomain() } }
}