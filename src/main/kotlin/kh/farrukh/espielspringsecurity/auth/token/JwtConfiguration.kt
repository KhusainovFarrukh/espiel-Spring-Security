package kh.farrukh.espielspringsecurity.auth.token

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtConfiguration {
    val secret: String = "test-secret"
    val accessTokenValidityInSeconds: Long = 900
    val refreshTokenValidityInSeconds: Long = 604800
}