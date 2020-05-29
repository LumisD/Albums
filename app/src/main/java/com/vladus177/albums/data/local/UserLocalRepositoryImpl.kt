package com.vladus177.albums.data.local

import com.vladus177.albums.data.mapper.UserDataMapper
import com.vladus177.albums.data.repository.UserLocal
import com.vladus177.albums.domain.model.UserModel
import javax.inject.Inject


class UserLocalRepositoryImpl @Inject constructor(
    private val userDao: UsersDao, private val userMapper: UserDataMapper
) : UserLocal {

    override suspend fun setFavoriteUser(userId: Long, favorite: Boolean) {
        userDao.updateFavorite(userId, favorite)
    }

    override suspend fun insertAll(users: List<UserModel>) {
        userDao.insertAll(users.map { with(userMapper) { it.fromDomainToEntity() } })
    }

    override suspend fun getUserList(): List<UserModel> {
        return userDao.getAllUsers().map { with(userMapper) { it.fromEntityToDomain() } }
    }
}