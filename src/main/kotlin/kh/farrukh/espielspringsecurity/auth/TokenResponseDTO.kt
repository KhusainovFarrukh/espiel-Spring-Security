package kh.farrukh.espielspringsecurity.auth

class TokenResponseDTO(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiresIn: Int,
    val refreshTokenExpiresIn: Int
)