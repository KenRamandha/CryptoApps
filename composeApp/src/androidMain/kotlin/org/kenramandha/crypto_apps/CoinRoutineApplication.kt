package org.kenramandha.crypto_apps

import android.app.Application
import org.kenramandha.crypto_apps.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent


class CoinRoutineApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@CoinRoutineApplication)
        }
    }
}