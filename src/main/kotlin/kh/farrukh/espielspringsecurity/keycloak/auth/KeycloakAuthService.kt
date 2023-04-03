package kh.farrukh.espielspringsecurity.keycloak.auth

interface KeycloakAuthService {

    fun login(username: String, password: String): KeycloakTokenResponseDTO
}