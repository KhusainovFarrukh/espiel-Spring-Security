package kh.farrukh.espielspringsecurity.common.config

import org.keycloak.OAuth2Constants
import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KeycloakConfig {

    //TODO code refactor (i think we don't need to manually set these values)
    @Bean
    fun keycloak(keycloakProperties: KeycloakSpringBootProperties): Keycloak = KeycloakBuilder.builder()
        .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
        .serverUrl(keycloakProperties.authServerUrl)
        .realm(keycloakProperties.realm)
        .clientId(keycloakProperties.resource)
        .clientSecret(keycloakProperties.credentials["secret"].toString())
        .build()

    @Bean
    fun keycloakConfigResolver(): KeycloakConfigResolver {
        return KeycloakSpringBootConfigResolver()
    }
}