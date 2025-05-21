package org.kenramandha.crypto_apps.trade.domain

import kotlinx.coroutines.flow.first
import org.kenramandha.crypto_apps.core.domain.DataError
import org.kenramandha.crypto_apps.core.domain.EmptyResult
import org.kenramandha.crypto_apps.core.domain.coin.Coin
import org.kenramandha.crypto_apps.core.domain.Result
import org.kenramandha.crypto_apps.portfolio.domain.PortfolioRepository

class SellCoinUseCase(
    private val portfolioRepository: PortfolioRepository,
) {
    suspend fun sellCoin(
        coin: Coin,
        amountInFiat: Double,
        price: Double,
    ): EmptyResult<DataError> {
        val sellAllThreshold = 1
        when(val existingCoinResponse = portfolioRepository.getPortfolioCoin(coin.id)) {
            is Result.Success -> {
                val existingCoin = existingCoinResponse.data
                val sellAmountInUnit = amountInFiat / price

                val balance = portfolioRepository.cashBalanceFlow().first()
                if (existingCoin == null || existingCoin.ownedAmountInUnit < sellAmountInUnit) {
                    return Result.Error(DataError.Local.INSUFFICIENT_FUNDS)
                }
                val remainingAmountFiat = existingCoin.ownedAmountInFiat - amountInFiat
                val remainingAmountUnit = existingCoin.ownedAmountInUnit - sellAmountInUnit
                if (remainingAmountFiat < sellAllThreshold) {
                    portfolioRepository.removeCoinFromPortfolio(coin.id)
                } else {
                    portfolioRepository.savePortfolioCoin(
                        existingCoin.copy(
                            ownedAmountInUnit = remainingAmountUnit,
                            ownedAmountInFiat = remainingAmountFiat,
                        )
                    )
                }
                portfolioRepository.updateCashBalance(balance + amountInFiat)
                return Result.Success(Unit)
            }
            is Result.Error -> {
                return existingCoinResponse
            }
        }
    }
}