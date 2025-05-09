package org.kenramandha.crypto_apps.coins.domain.usecases

import org.kenramandha.crypto_apps.coins.data.maper.toPriceModel
import org.kenramandha.crypto_apps.coins.domain.api.CoinRemoteDataSource
import org.kenramandha.crypto_apps.coins.domain.model.PriceModel
import org.kenramandha.crypto_apps.core.domain.DataError
import org.kenramandha.crypto_apps.core.domain.Result
import org.kenramandha.crypto_apps.core.domain.map

class GetCoinPriceHistoryUseCase(
    private val client: CoinRemoteDataSource,
) {
    suspend fun execute(coinId: String): Result<List<PriceModel>, DataError.Remote> {
        return client.getCoinPriceHistory(coinId).map {
            dto -> dto.data.history.map{it.toPriceModel()}
        }
    }
}