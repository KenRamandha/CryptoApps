package org.kenramandha.crypto_apps.trade.presentation.sell

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.kenramandha.crypto_apps.trade.presentation.common.TradeScreen
import org.kenramandha.crypto_apps.trade.presentation.common.TradeType
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SellScreen(
    coinId: String,
    navigateToPortfolio: () -> Unit,
) {
    val viewModel = koinViewModel<SellViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    //TODO: handle coinId

    TradeScreen(
        state = state,
        tradeType = TradeType.SELL,
        onAmountChange = viewModel::onAmountChanged,
        onSubmitClicked = viewModel::onSellClicked
    )
}