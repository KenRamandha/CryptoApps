package org.kenramandha.crypto_apps.coins.data.maper

import org.kenramandha.crypto_apps.coins.data.remote.dto.CoinItemDto
import org.kenramandha.crypto_apps.coins.data.remote.dto.CoinPriceDto
import org.kenramandha.crypto_apps.coins.domain.model.CoinModel
import org.kenramandha.crypto_apps.coins.domain.model.PriceModel
import org.kenramandha.crypto_apps.core.domain.coin.Coin

fun CoinItemDto.toCoinModel() = CoinModel(
    coin = Coin(
        id = uuid,
        symbol = symbol,
        name = name,
        iconUrl = iconUrl
    ),
    price = price,
    change = change,
)

fun CoinPriceDto.toPriceModel() = PriceModel(
    price = price ?: 0.0,
    timestamp = timestamp
)