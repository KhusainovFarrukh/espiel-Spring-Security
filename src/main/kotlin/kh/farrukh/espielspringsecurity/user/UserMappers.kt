package kh.farrukh.espielspringsecurity.user

import kh.farrukh.espielspringsecurity.common.exception.custom_exceptions.BadRequestException

fun AppUser.toUserResponseDTO() = UserResponseDTO(
    id = id ?: 0,
    username = username
)

fun UserRequestDTO.toAppUser() = AppUser(
    id = null,
    username = username,
    password = password ?: throw BadRequestException("Password")
)