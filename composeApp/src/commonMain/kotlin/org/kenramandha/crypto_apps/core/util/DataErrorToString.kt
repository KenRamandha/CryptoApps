package org.kenramandha.crypto_apps.core.util

import cryptoapps.composeapp.generated.resources.Res
import cryptoapps.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource
import org.kenramandha.crypto_apps.core.domain.DataError

fun DataError.toUiText(): StringResource {
    return when (this) {
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.INSUFFICIENT_FUNDS -> Res.string.error_insufficient_balance
        DataError.Local.UNKNOWN -> Res.string.error_unknown
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.SERVER -> Res.string.error_server
        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
        DataError.Remote.UNKNOWN -> Res.string.error_unknown
    }
}