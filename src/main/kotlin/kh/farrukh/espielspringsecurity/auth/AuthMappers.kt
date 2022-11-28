package kh.farrukh.espielspringsecurity.auth

import kh.farrukh.espielspringsecurity.auth.password_encoder.PasswordEncoder
import kh.farrukh.espielspringsecurity.user.UserRequestDTO

fun RegisterRequestDTO.toUserRequestDTO(passwordEncoder: PasswordEncoder) = UserRequestDTO(
    username = username,
    password = passwordEncoder.encode(password)
)