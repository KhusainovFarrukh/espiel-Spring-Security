package kh.farrukh.espielspringsecurity.auth

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.ZonedDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
class TokenResponseDTO(
    @JsonProperty("access_token")
    val accessToken: String,
    @JsonProperty("access_token_expiration")
    val accessTokenExpiration: ZonedDateTime,
    @JsonProperty("refresh_token")
    val refreshToken: String? = null,
    @JsonProperty("refresh_token_expiration")
    val refreshTokenExpiration: ZonedDateTime? = null
)