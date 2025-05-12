package org.kenramandha.crypto_apps.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.dsl.module

actual val platformModule = module {

    //Core
    single<HttpClientEngine> {Android.create()}
}