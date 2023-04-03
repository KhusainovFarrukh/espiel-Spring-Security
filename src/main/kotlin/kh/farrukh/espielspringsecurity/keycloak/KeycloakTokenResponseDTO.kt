package kh.farrukh.espielspringsecurity.keycloak

import com.fasterxml.jackson.annotation.JsonProperty

//TODO use representation class from KC
class KeycloakTokenResponseDTO(

    @JsonProperty("access_token")
    val accessToken: String,

    @JsonProperty("refresh_token")
    val refreshToken: String,

    @JsonProperty("refresh_expires_in")
    val refreshTokenExpiresIn: Int,

    @JsonProperty("not-before-policy")
    val notBeforePolicy: Int,

    @JsonProperty("scope")
    val scope: String,

    @JsonProperty("token_type")
    val tokenType: String,

    @JsonProperty("session_state")
    val sessionState: String,

    @JsonProperty("expires_in")
    val accessTokenExpiresIn: Int
)