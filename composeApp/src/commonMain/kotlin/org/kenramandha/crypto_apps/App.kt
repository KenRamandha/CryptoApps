package org.kenramandha.crypto_apps

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kenramandha.crypto_apps.coins.presentation.CoinsListScreen
import org.kenramandha.crypto_apps.core.navigation.Buy
import org.kenramandha.crypto_apps.core.navigation.Coins
import org.kenramandha.crypto_apps.core.navigation.Portfolio
import org.kenramandha.crypto_apps.core.navigation.Sell
import org.kenramandha.crypto_apps.portfolio.presentation.PortfolioScreen
import org.kenramandha.crypto_apps.theme.CoinRoutineTheme
import org.kenramandha.crypto_apps.trade.presentation.buy.BuyScreen
import org.kenramandha.crypto_apps.trade.presentation.sell.SellScreen

@Composable
@Preview
fun App() {
    val navController: NavHostController = rememberNavController()
    CoinRoutineTheme {
        NavHost(
            navController = navController,
            startDestination = Portfolio,
            modifier = Modifier.fillMaxSize()
        ) {
            composable<Portfolio> {
                PortfolioScreen(
                    onCoinItemClicked = { coinId ->
                        navController.navigate(Sell(coinId))
                    },
                    onDiscoverCoinsClicked = {
                        navController.navigate(Coins)
                    }
                )
            }

            composable<Coins> {
                CoinsListScreen { coinId ->
                    navController.navigate(Buy(coinId))
                }
            }

            composable<Buy> { navBackStackEntry ->
                val coinId: String = navBackStackEntry.toRoute<Buy>().coinId
                BuyScreen(
                    coinId = coinId,
                    navigateToPortfolio = {
                        navController.navigate(Portfolio) {
                            popUpTo(Portfolio) { inclusive = true }
                        }
                    }
                )
            }
            composable<Sell> { navBackStackEntry ->
                val coinId: String = navBackStackEntry.toRoute<Sell>().coinId
                SellScreen(
                    coinId = coinId,
                    navigateToPortfolio = {
                        navController.navigate(Portfolio) {
                            popUpTo(Portfolio) { inclusive = true }
                        }
                    }
                )
            }

        }
    }
}