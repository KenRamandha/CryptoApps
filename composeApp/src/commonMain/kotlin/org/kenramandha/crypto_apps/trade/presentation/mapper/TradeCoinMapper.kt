package org.kenramandha.crypto_apps.trade.presentation.mapper

import org.kenramandha.crypto_apps.core.domain.coin.Coin
import org.kenramandha.crypto_apps.trade.presentation.common.UiTradeCoinItem

fun UiTradeCoinItem.toCoin() = Coin(
    id = id,
    name = name,
    symbol = symbol,
    iconUrl = iconUrl,
)