package kh.farrukh.espielspringsecurity.common.config

import org.keycloak.adapters.springboot.KeycloakSpringBootProperties
import org.keycloak.adapters.springsecurity.KeycloakConfiguration
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import org.springframework.security.web.util.matcher.RequestMatcher

@KeycloakConfiguration
class KeycloakSecurityConfig(
    private val keycloakProperties: KeycloakSpringBootProperties,
) : KeycloakWebSecurityConfigurerAdapter() {

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(keycloakAuthenticationProvider())
    }

    override fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy {
        return NullAuthenticatedSessionStrategy()
    }

    override fun configure(http: HttpSecurity) {
        super.configure(http)

        val publicPaths = getPublicPaths()
        http
            .csrf().disable()
            .cors().disable()
            .authorizeHttpRequests()
            .requestMatchers(RequestMatcher { request -> publicPaths.contains(request.servletPath) })
            .permitAll()
            .anyRequest()
            .authenticated()
    }

    //TODO find better way
    //TODO test with/without wildcards (/*, /**)
    private fun getPublicPaths(): List<String> {
        val publicPaths = mutableListOf<String>()
        keycloakProperties.securityConstraints
            .filter { constraint -> constraint.authRoles.size == 0 }
            .forEach { constraint ->
                constraint.securityCollections.forEach { collection ->
                    collection.patterns.forEach { pattern ->
                        publicPaths.add(pattern)
                    }
                }
            }
        return publicPaths
    }
}