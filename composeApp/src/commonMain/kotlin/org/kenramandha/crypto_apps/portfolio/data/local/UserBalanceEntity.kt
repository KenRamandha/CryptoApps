package org.kenramandha.crypto_apps.portfolio.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserBalanceEntity (
    @PrimaryKey val id: Int = 1,
    val cashBalance: Double,
)