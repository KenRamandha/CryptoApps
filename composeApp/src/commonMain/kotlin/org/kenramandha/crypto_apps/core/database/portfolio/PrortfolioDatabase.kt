package org.kenramandha.crypto_apps.core.database.portfolio

import androidx.room.Database
import androidx.room.RoomDatabase
import org.kenramandha.crypto_apps.portfolio.data.local.PortfolioCoinEntity
import org.kenramandha.crypto_apps.portfolio.data.local.PortfolioDao
import org.kenramandha.crypto_apps.portfolio.data.local.UserBalanceDao
import org.kenramandha.crypto_apps.portfolio.data.local.UserBalanceEntity

@Database(entities = [PortfolioCoinEntity::class, UserBalanceEntity::class], version = 2)
abstract class PortfolioDatabase: RoomDatabase() {
    abstract fun portfolioDao(): PortfolioDao
    abstract fun userBalanceDao(): UserBalanceDao
}