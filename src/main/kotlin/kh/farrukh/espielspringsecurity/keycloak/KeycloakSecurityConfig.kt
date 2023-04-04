package kh.farrukh.espielspringsecurity.keycloak

import org.keycloak.adapters.springsecurity.KeycloakConfiguration
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticatedActionsFilter
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter
import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter
import org.keycloak.adapters.springsecurity.filter.KeycloakSecurityContextRequestFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter
import org.springframework.security.web.util.matcher.RequestMatcher


@KeycloakConfiguration
class KeycloakSecurityConfig(
    @Value("\${spring.security.oauth2.whitelist}") private val whitelistUrls: List<String>
) : KeycloakWebSecurityConfigurerAdapter() {

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
//        val grantedAuthorityMapper = SimpleAuthorityMapper()
//        grantedAuthorityMapper.setPrefix("ROLE_")

        val keycloakAuthenticationProvider = keycloakAuthenticationProvider()
//        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper)
        auth.authenticationProvider(keycloakAuthenticationProvider)
    }

    override fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy {
        return NullAuthenticatedSessionStrategy()
    }

    override fun configure(http: HttpSecurity) {
        super.configure(http)
        //        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter())
        // Require authentication for all requests
        http.csrf().disable().cors().disable().authorizeHttpRequests()
            .requestMatchers(RequestMatcher { whitelistUrls.contains(it.servletPath) })
            .permitAll()
            .anyRequest()
            .authenticated()

        http.csrf().disable()

        //http.oauth2Client();

        // Allow showing pages within a frame
        http.headers().frameOptions().sameOrigin()
    }
}