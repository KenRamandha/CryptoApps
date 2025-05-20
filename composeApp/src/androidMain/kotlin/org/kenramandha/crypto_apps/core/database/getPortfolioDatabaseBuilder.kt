package org.kenramandha.crypto_apps.core.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import org.kenramandha.crypto_apps.core.database.portfolio.PortfolioDatabase

fun getPortfolioDatabaseBuilder(context: Context): RoomDatabase.Builder<PortfolioDatabase> {
    val dbFile = context.getDatabasePath("portfolio_database.db")
    return Room.databaseBuilder<PortfolioDatabase>(
        context = context,
        name = dbFile.absolutePath
    )
}