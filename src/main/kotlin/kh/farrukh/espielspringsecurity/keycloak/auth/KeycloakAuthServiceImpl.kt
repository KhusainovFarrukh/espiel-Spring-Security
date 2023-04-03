package kh.farrukh.espielspringsecurity.keycloak.auth

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class KeycloakAuthServiceImpl(
    private val keycloakAuthClient: KeycloakAuthClient,
    @Value("\${keycloak.credentials.secret}") private var clientSecret: String,
    @Value("\${keycloak.resource}") private val clientId: String,
    @Value("\${keycloak.realm}") private val realmName: String
) : KeycloakAuthService {

    override fun login(username: String, password: String): KeycloakTokenResponseDTO {
        val requestBody = HashMap<String, Any>()
        //TODO use constants
        requestBody["client_id"] = clientId
        requestBody["username"] = username
        requestBody["password"] = password
        //TODO use ENUM
        requestBody["grant_type"] = "password"
        requestBody["client_secret"] = clientSecret
        return keycloakAuthClient.login(realmName, requestBody)
    }
}