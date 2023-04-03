package kh.farrukh.espielspringsecurity.common.config

import org.keycloak.OAuth2Constants
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KeycloakConfig(
    @Value("\${keycloak.credentials.secret}") private var clientSecret: String,
    @Value("\${keycloak.resource}") private val clientId: String,
    @Value("\${keycloak.realm}") private val realmName: String,
    @Value("\${keycloak.auth-server-url}") private val serverUrl: String
) {

    //TODO code refactor (i think we don't need to manually set these values)
    @Bean
    fun keycloak(): Keycloak = KeycloakBuilder.builder()
        .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
        .serverUrl(serverUrl)
        .realm(realmName)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .build()
}