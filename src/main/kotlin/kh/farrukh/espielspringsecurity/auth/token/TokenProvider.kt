package kh.farrukh.espielspringsecurity.auth.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import kh.farrukh.espielspringsecurity.auth.TokenResponseDTO
import kh.farrukh.espielspringsecurity.common.exception.custom_exceptions.MissingTokenException
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.CrossOrigin
import java.time.ZonedDateTime
import java.util.*

@Component
@CrossOrigin
class TokenProvider(private val jwtConfiguration: JwtConfiguration): InitializingBean {

    private lateinit var accessTokenAlgorithm: Algorithm
    private lateinit var refreshTokenAlgorithm: Algorithm

        override fun afterPropertiesSet() {
        accessTokenAlgorithm = Algorithm.HMAC256(jwtConfiguration.secret)
        refreshTokenAlgorithm = Algorithm.HMAC256(jwtConfiguration.secret)
    }

    fun generateAccessToken(username: String): TokenResponseDTO {
        val accessExpireDate = ZonedDateTime.now().plusSeconds(jwtConfiguration.accessTokenValidityInSeconds)
        return TokenResponseDTO(
            createToken(username, accessExpireDate, accessTokenAlgorithm),
            accessExpireDate
        )
    }

    fun generateTokens(username: String): TokenResponseDTO {
        val accessExpireDate = ZonedDateTime.now().plusSeconds(jwtConfiguration.accessTokenValidityInSeconds)
        val refreshExpireDate = ZonedDateTime.now().plusSeconds(jwtConfiguration.refreshTokenValidityInSeconds)
        return TokenResponseDTO(
            createAccessToken(username, accessExpireDate),
            accessExpireDate,
            createRefreshToken(username, refreshExpireDate),
            refreshExpireDate
        )
    }

    fun validateToken(authHeader: String?, isRefresh: Boolean): DecodedJWT {
        val algorithm = if (isRefresh) refreshTokenAlgorithm else accessTokenAlgorithm
        return if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring("Bearer ".length)
            val jwtVerifier = JWT.require(algorithm).build()
            jwtVerifier.verify(token)
        } else {
            throw MissingTokenException()
        }
    }

    fun createAccessToken(username: String, expireDate: ZonedDateTime) =
        createToken(username, expireDate, accessTokenAlgorithm)

    fun createRefreshToken(username: String, expireDate: ZonedDateTime) =
        createToken(username, expireDate, refreshTokenAlgorithm)

    private fun createToken(username: String, expireDate: ZonedDateTime, algorithm: Algorithm): String =
        JWT.create()
            .withSubject(username)
            .withExpiresAt(Date.from(expireDate.toInstant()))
            .sign(algorithm)
}