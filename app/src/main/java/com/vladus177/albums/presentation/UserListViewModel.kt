package com.vladus177.albums.presentation

import androidx.lifecycle.ViewModel
import com.vladus177.albums.common.extension.*
import com.vladus177.albums.domain.GetUserListUseCase
import com.vladus177.albums.domain.SetFavoriteUseCase
import com.vladus177.albums.domain.model.UserModel
import com.vladus177.albums.domain.requestparams.FavoriteRequestParam
import com.vladus177.albums.domain.requestparams.UserListRequestParam
import com.vladus177.albums.ui.mapper.UserViewMapper
import com.vladus177.albums.ui.model.UserView
import javax.inject.Inject

class UserListViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase,
    private val usersListMapper: UserViewMapper
) : ViewModel() {

    val users = LiveResult<List<UserModel>>()

    fun loadUserList(forceUpdate: Boolean) {
        if (forceUpdate) {
            getUserListUseCase.executeRepeating(
                liveData = users,
                params = UserListRequestParam(forceUpdate),
                repeatTime = 1000L,
                repeatQuantity = 5
            )
        } else {
            val job = getUserListUseCase.execute(
                liveData = users,
                params = UserListRequestParam(forceUpdate)
            )
        }
    }

    fun setFavorite(userId: Long, favorite: Boolean) {
        setFavoriteUseCase.executeWithoutResponse(
            params = FavoriteRequestParam(userId, favorite)
        )

        loadUserList(false)
    }

    fun convertUserModelsToUserViews(userModels: List<UserModel>): List<UserView> {
        return userModels?.map { with(usersListMapper) { it.fromDomainToView() } }
    }
}