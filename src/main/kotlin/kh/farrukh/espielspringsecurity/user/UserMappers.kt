package kh.farrukh.espielspringsecurity.user

import kh.farrukh.espielspringsecurity.common.exception.custom_exceptions.BadRequestException

fun AppUser.toUserResponseDTO() = UserResponseDTO(
    id = id ?: 0,
    username = username,
    email = email,
    keycloakId = keycloakId
)

fun UserRequestDTO.toAppUser() = AppUser(
    id = null,
    username = username,
    email = email,
    keycloakId = keycloakId ?: throw BadRequestException("keycloakId")
)