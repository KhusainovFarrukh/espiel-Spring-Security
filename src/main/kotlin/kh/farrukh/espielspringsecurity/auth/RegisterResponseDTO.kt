package kh.farrukh.espielspringsecurity.auth

import kh.farrukh.espielspringsecurity.user.UserResponseDTO

class RegisterResponseDTO(
    val user: UserResponseDTO,
    val token: TokenResponseDTO
)