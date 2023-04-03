package kh.farrukh.espielspringsecurity.user

class UserRequestDTO(
    val username: String,
    val email: String?,
    //TODO make it val
    var keycloakId: String?
)