package org.kenramandha.crypto_apps.coins.domain.model

import org.kenramandha.crypto_apps.core.domain.coin.Coin

data class CoinModel (
    val coin : Coin,
    val price : Double,
    val change : Double
)