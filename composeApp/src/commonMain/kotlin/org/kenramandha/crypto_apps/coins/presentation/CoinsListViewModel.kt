package org.kenramandha.crypto_apps.coins.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cryptoapps.composeapp.generated.resources.Res
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.kenramandha.crypto_apps.coins.domain.usecases.GetCoinPriceHistoryUseCase
import org.kenramandha.crypto_apps.coins.domain.usecases.GetCoinsListUseCase
import org.kenramandha.crypto_apps.core.domain.Result
import org.kenramandha.crypto_apps.core.util.formatCoinUnit
import org.kenramandha.crypto_apps.core.util.formatFiat
import org.kenramandha.crypto_apps.core.util.formatPercentage
import org.kenramandha.crypto_apps.core.util.toUiText

class CoinsListViewModel(
    private val getCoinsListUseCase: GetCoinsListUseCase,
    private val getCoinPriceHistoryUseCase: GetCoinPriceHistoryUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CoinsState())
    val state = _state.onStart {
        getAllCoins()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CoinsState()
    )

    private suspend fun getAllCoins() {
        when (val coinsResponse = getCoinsListUseCase.execute()) {
            is Result.Success -> {
                _state.update {
                    CoinsState(
                        coins = coinsResponse.data.map { coinItem ->
                            UiCoinListItem(
                                id = coinItem.coin.id,
                                name = coinItem.coin.name,
                                symbol = coinItem.coin.symbol,
                                iconUrl = coinItem.coin.iconUrl,
                                formattedPrice = formatFiat(coinItem.price),
                                formattedChange = formatPercentage(coinItem.change),
                                isPositive = coinItem.change >= 0
                            )
                        }
                    )
                }
            }

            is Result.Error -> {
                _state.update {
                    it.copy(
                        coins = emptyList(),
                        error = coinsResponse.error.toUiText()
                    )
                }
            }
        }
    }

    fun onCoinLongPressed(coinId: String) {
        _state.update {
            it.copy(
                chartState = UiChartState(
                    isLoading = true,
                    sparkLine = emptyList(),
                )
            )
        }
        viewModelScope.launch {
            when(val priceHistory = getCoinPriceHistoryUseCase.execute(coinId)) {
                is Result.Success -> {
                    _state.update { currentState ->
                        currentState.copy(
                            chartState = UiChartState(
                                sparkLine = priceHistory.data.sortedBy { it.timestamp }.map { it.price },
                                isLoading = false,
                                coinName = _state.value.coins.find { it.id == coinId }?.name.orEmpty(),
                            )
                        )
                    }
                }

                is Result.Error -> {
                    _state.update {
                        it.copy(
                            chartState = UiChartState(
                                isLoading = false,
                                sparkLine = emptyList(),
                                coinName = "",
                            )
                        )
                    }
                }
            }
        }
    }

    fun onDismissChart() {
        _state.update {
            it.copy(
                chartState = null
            )
        }
    }
}