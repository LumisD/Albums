package com.vladus177.albums.domain

import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.domain.requestparams.UserListRequestParam
import com.vladus177.currencycheck.common.ResultUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val repository: UserRepository
) : ResultUseCase<UserListRequestParam, List<UserModel>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: UserListRequestParam): List<UserModel>? {
        return repository.getUserList(params.forceUpdate)
    }
}