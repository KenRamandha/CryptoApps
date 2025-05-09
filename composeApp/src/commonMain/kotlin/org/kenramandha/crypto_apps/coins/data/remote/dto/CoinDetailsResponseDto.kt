

import kotlinx.serialization.Serializable
import org.kenramandha.crypto_apps.coins.data.remote.dto.CoinItemDto

@Serializable
data class CoinDetailsResponseDto(
    val data: CoinResponseDto,
)

@Serializable
data class CoinResponseDto(
    val coin: CoinItemDto
)