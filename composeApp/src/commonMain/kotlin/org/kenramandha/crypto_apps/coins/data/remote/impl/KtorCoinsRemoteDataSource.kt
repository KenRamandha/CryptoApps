package org.kenramandha.crypto_apps.coins.data.remote.impl

import CoinDetailsResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.kenramandha.crypto_apps.coins.data.remote.dto.CoinPriceHistoryResponseDto
import org.kenramandha.crypto_apps.coins.data.remote.dto.CoinsResponseDto
import org.kenramandha.crypto_apps.coins.domain.api.CoinRemoteDataSource
import org.kenramandha.crypto_apps.core.domain.DataError
import org.kenramandha.crypto_apps.core.domain.Result
import org.kenramandha.crypto_apps.core.network.safeCall

private const val BASE_URL = "https://api.coinranking.com/v2"

class KtorCoinsRemoteDataSource(
    private val httpClient: HttpClient
) : CoinRemoteDataSource {
    override suspend fun getListOfCoins(): Result<CoinsResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL/coins")
        }
    }

    override suspend fun getCoinById(coinId: String): Result<CoinDetailsResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL/coin/$coinId")
        }
    }

    override suspend fun getCoinPriceHistory(coinId: String): Result<CoinPriceHistoryResponseDto, DataError.Remote> {
        return safeCall {
            httpClient.get("$BASE_URL/coin/$coinId/history")
        }
    }

}