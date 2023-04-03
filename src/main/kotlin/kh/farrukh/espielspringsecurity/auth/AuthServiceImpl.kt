package kh.farrukh.espielspringsecurity.auth

import kh.farrukh.espielspringsecurity.keycloak.KeycloakAuthClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val keycloakAuthClient: KeycloakAuthClient,
    private val authMapper: AuthMapper,
    @Value("\${keycloak.credentials.secret}") private var clientSecret: String,
    @Value("\${keycloak.resource}") private val clientId: String,
    @Value("\${keycloak.realm}") private val realm: String
) : AuthService {

    override fun login(loginRequestDTO: LoginRequestDTO): TokenResponseDTO {
        val requestBody = HashMap<String, Any>()
        requestBody["client_id"] = clientId
        requestBody["username"] = loginRequestDTO.username
        requestBody["password"] = loginRequestDTO.password
        //TODO use ENUM
        requestBody["grant_type"] = "password"
        requestBody["client_secret"] = clientSecret
        return authMapper.toTokenResponseDTO(keycloakAuthClient.login(realm, requestBody))
    }

    override fun register(registerRequestDTO: RegisterRequestDTO): TokenResponseDTO {
        TODO("Not yet implemented")
    }
}