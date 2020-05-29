package com.vladus177.albums.domain

import com.vladus177.albums.domain.requestparams.FavoriteRequestParam
import com.vladus177.currencycheck.common.ResultUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SetFavoriteUseCase @Inject constructor(
    private val repository: UserRepository
) : ResultUseCase<FavoriteRequestParam, Unit>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    override suspend fun executeOnBackground(params: FavoriteRequestParam) {
        return repository.setFavoriteUser(params.userId, params.favorite)
    }
}