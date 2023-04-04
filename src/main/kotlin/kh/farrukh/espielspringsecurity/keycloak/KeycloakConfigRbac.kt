package kh.farrukh.espielspringsecurity.keycloak

import org.keycloak.adapters.KeycloakConfigResolver
import org.keycloak.adapters.KeycloakDeployment
import org.keycloak.adapters.KeycloakDeploymentBuilder
import org.keycloak.adapters.authorization.KeycloakAdapterPolicyEnforcer
import org.keycloak.adapters.authorization.PolicyEnforcer
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties
import org.keycloak.adapters.springsecurity.KeycloakConfiguration
import org.keycloak.representations.adapters.config.AdapterConfig
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig.UserManagedAccessConfig
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

@KeycloakConfiguration
@EnableConfigurationProperties(KeycloakSpringBootProperties::class)
class KeycloakConfigRbac {

    @Bean
    fun keycloakAdapterPolicyEnforcer(policyEnforcer: PolicyEnforcer): KeycloakAdapterPolicyEnforcer {
        return KeycloakAdapterPolicyEnforcer(policyEnforcer)
    }

    @Bean
    fun policyEnforcer(keycloakDeployment: KeycloakDeployment, adapterConfig: AdapterConfig): PolicyEnforcer {
        return PolicyEnforcer(keycloakDeployment, adapterConfig)
    }

    @Bean
    fun keycloakDeployment(keycloakSpringBootProperties: KeycloakSpringBootProperties): KeycloakDeployment {
//        keycloakSpringBootProperties.policyEnforcerConfig.userManagedAccess = UserManagedAccessConfig()
        return KeycloakDeploymentBuilder.build(keycloakSpringBootProperties)
    }

    @Bean
    fun keycloakConfigResolver(): KeycloakConfigResolver {
        return KeycloakSpringBootConfigResolver()
    }
}