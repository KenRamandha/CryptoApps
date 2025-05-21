package org.kenramandha.crypto_apps.di

import androidx.room.RoomDatabase
import io.ktor.client.HttpClient
import org.kenramandha.crypto_apps.coins.data.remote.impl.KtorCoinsRemoteDataSource
import org.kenramandha.crypto_apps.coins.domain.api.CoinRemoteDataSource
import org.kenramandha.crypto_apps.coins.domain.usecases.GetCoinDetailsUseCase
import org.kenramandha.crypto_apps.coins.domain.usecases.GetCoinsListUseCase
import org.kenramandha.crypto_apps.coins.domain.usecases.GetCoinPriceHistoryUseCase
import org.kenramandha.crypto_apps.coins.presentation.CoinsListViewModel
import org.kenramandha.crypto_apps.core.database.portfolio.PortfolioDatabase
import org.kenramandha.crypto_apps.core.database.portfolio.getPortfolioDatabase
import org.kenramandha.crypto_apps.core.network.HttpClientFactory
import org.kenramandha.crypto_apps.portfolio.data.PortfolioRepositoryImpl
import org.kenramandha.crypto_apps.portfolio.domain.PortfolioRepository
import org.kenramandha.crypto_apps.portfolio.presentation.PortfolioViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

fun initKoin(config : KoinAppDeclaration? = null) =
    startKoin{
        config?.invoke(this)
        modules(
            sharedModule,
            platformModule
        )
    }

expect val platformModule: Module


val sharedModule = module {
    //core
    single<HttpClient> { HttpClientFactory.create(get())}

    //portfolio
    single {
        getPortfolioDatabase(get<RoomDatabase.Builder<PortfolioDatabase>>())
    }

    singleOf(::PortfolioRepositoryImpl).bind<PortfolioRepository>()
    viewModel { PortfolioViewModel(get()) }
    single { get<PortfolioDatabase>().portfolioDao() }
    single { get<PortfolioDatabase>().userBalanceDao() }
    viewModel { PortfolioViewModel(get()) }

    //coins list
    viewModel { CoinsListViewModel(get(), get())}
    singleOf(::GetCoinsListUseCase)
    singleOf(::KtorCoinsRemoteDataSource).bind<CoinRemoteDataSource>()
    singleOf(::GetCoinDetailsUseCase)
    singleOf(::GetCoinPriceHistoryUseCase)
}