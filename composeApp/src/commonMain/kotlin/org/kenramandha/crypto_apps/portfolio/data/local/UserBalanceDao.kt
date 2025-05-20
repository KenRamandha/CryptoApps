package org.kenramandha.crypto_apps.portfolio.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface UserBalanceDao {

    @Query("SELECT * FROM cashBalance WHERE id = 1")
    suspend fun getCashBalance(): Double?

    @Upsert
    suspend fun insertCashBalance(cashBalanceEntity: UserBalanceEntity)

    @Query("UPDATE UserBalanceEntity SET cashBalance= :newBalance WHERE id = 1")
    suspend fun updateCashBalance(newBalance: Double)
}