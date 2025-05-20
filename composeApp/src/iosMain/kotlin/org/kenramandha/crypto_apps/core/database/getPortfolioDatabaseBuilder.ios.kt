package org.kenramandha.crypto_apps.core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import org.kenramandha.crypto_apps.core.database.portfolio.PortfolioDatabase
import platform.Foundation.NSHomeDirectory

fun getPortfolioDatabaseBuilder(): RoomDatabase.Builder<PortfolioDatabase> {
    val dbFile = NSHomeDirectory() + "/portfolio.db"
    return Room.databaseBuilder<PortfolioDatabase>(
        name = dbFile
    )
}