package org.kenramandha.crypto_apps.coins.domain.usecases

import org.kenramandha.crypto_apps.coins.data.maper.toCoinModel
import org.kenramandha.crypto_apps.coins.domain.api.CoinRemoteDataSource
import org.kenramandha.crypto_apps.coins.domain.model.CoinModel
import org.kenramandha.crypto_apps.core.domain.DataError
import org.kenramandha.crypto_apps.core.domain.Result
import org.kenramandha.crypto_apps.core.domain.map

class GetCoinDetailsUseCase(
    private val client: CoinRemoteDataSource,
) {
    suspend fun execute(coinId: String): Result<CoinModel, DataError.Remote> {
        return client.getCoinById(coinId).map { dto ->
            dto.data.coin.toCoinModel()
        }

    }
}