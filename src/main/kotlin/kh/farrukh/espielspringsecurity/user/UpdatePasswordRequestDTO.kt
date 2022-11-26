package kh.farrukh.espielspringsecurity.user

import com.fasterxml.jackson.annotation.JsonProperty

class UpdatePasswordRequestDTO(
    val password: String,
    @JsonProperty("new_password")
    val newPassword: String
)