package com.vladus177.albums.data


import com.vladus177.albums.data.source.UserDataSourceFactory
import com.vladus177.albums.domain.UserRepository

import javax.inject.Inject
import javax.inject.Singleton
import com.vladus177.albums.domain.model.UserModel


@Singleton
class UserDataRepository @Inject constructor(
    private val factory: UserDataSourceFactory
) : UserRepository {


    override suspend fun setFavoriteUser(userId: Long, favorite: Boolean) =
        factory.getLocal().setFavoriteUser(userId, favorite)


    override suspend fun getUserList(forceUpdate: Boolean): List<UserModel> {
        if (forceUpdate) {
            val users = factory.getRemote().getUserList()
            users?.let { insertAll(it) }

            return factory.getLocal().getUserList()
        } else {
            return factory.getLocal().getUserList()
        }
    }

    override suspend fun insertAll(users: List<UserModel>) = factory.getLocal().insertAll(users)
}