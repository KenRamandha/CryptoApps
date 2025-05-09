package org.kenramandha.crypto_apps.coins.domain.api

import CoinDetailsResponseDto
import org.kenramandha.crypto_apps.coins.data.remote.dto.CoinPriceHistoryResponseDto
import org.kenramandha.crypto_apps.coins.data.remote.dto.CoinsResponseDto
import org.kenramandha.crypto_apps.core.domain.DataError
import org.kenramandha.crypto_apps.core.domain.Result

interface CoinRemoteDataSource {
    suspend fun getListOfCoins(): Result<CoinsResponseDto, DataError.Remote>
    suspend fun getCoinById(coinId: String): Result<CoinDetailsResponseDto, DataError.Remote>
    suspend fun getCoinPriceHistory(coinId: String): Result<CoinPriceHistoryResponseDto, DataError.Remote>
}