package kh.farrukh.espielspringsecurity.user

class UserResponseDTO(
    val id: Long,
    val username: String,
    val email: String?,
    val keycloakId: String
)