package org.kenramandha.crypto_apps.coins.domain.usecases

import org.kenramandha.crypto_apps.coins.data.maper.toCoinModel
import org.kenramandha.crypto_apps.coins.domain.api.CoinRemoteDataSource
import org.kenramandha.crypto_apps.coins.domain.model.CoinModel
import org.kenramandha.crypto_apps.core.domain.DataError
import org.kenramandha.crypto_apps.core.domain.Result
import org.kenramandha.crypto_apps.core.domain.map

class GetCoinsListUseCase(
    private val client: CoinRemoteDataSource,
) {
    suspend fun execute(): Result<List<CoinModel>, DataError.Remote> {
        return client.getListOfCoins().map { dto ->
            dto.data.coins.map {
                it.toCoinModel()
            }
        }
    }
}