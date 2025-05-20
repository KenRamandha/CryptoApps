package org.kenramandha.crypto_apps.portfolio.domain

import kotlinx.coroutines.flow.Flow
import org.kenramandha.crypto_apps.core.domain.DataError
import org.kenramandha.crypto_apps.core.domain.EmptyResult
import org.kenramandha.crypto_apps.core.domain.Result

interface PortfolioRepository {

    suspend fun initializeBalance()
    fun allPortfolioCoinsFlow(): Flow<Result<List<PortfolioCoinModel>, DataError.Remote>>
    suspend fun getPortfolioCoin(coinId: String): Result<PortfolioCoinModel?, DataError.Remote>
    suspend fun savePortfolioCoin(portfolioCoin: PortfolioCoinModel): EmptyResult<DataError.Local>
    suspend fun removeCoinFromPortfolio(coinId: String)

    fun calculateTotalPortfolioValue(): Flow<Result<Double, DataError.Remote>> // Total asset values + cash balance
    fun totalBalanceFlow(): Flow<Result<Double, DataError.Remote>> // Total asset values
    fun cashBalanceFlow(): Flow<Double>
    suspend fun updateCashBalance(newBalance: Double) // on buy and sell
}