package kh.farrukh.espielspringsecurity.auth

import kh.farrukh.espielspringsecurity.auth.password_encoder.PasswordEncoder
import kh.farrukh.espielspringsecurity.auth.token.TokenProvider
import kh.farrukh.espielspringsecurity.common.exception.custom_exceptions.BadRequestException
import kh.farrukh.espielspringsecurity.user.UserService
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
) : AuthService {

    override fun register(registerRequestDTO: RegisterRequestDTO): RegisterResponseDTO {
        val user = userService.createUser(registerRequestDTO.toUserRequestDTO(passwordEncoder))
        return RegisterResponseDTO(user, tokenProvider.generateTokens(user.username))
    }

    override fun login(loginRequestDTO: LoginRequestDTO): TokenResponseDTO {
        val user = userService.getByUsername(loginRequestDTO.username)
        if (!passwordEncoder.matches(loginRequestDTO.password, user.password))
            throw BadRequestException("Password")
        return tokenProvider.generateTokens(user.username)
    }

    override fun refreshToken(token: String): TokenResponseDTO {
        try {
            val decodedJWT = tokenProvider.validateToken(token, true)
            return tokenProvider.generateAccessToken(decodedJWT.subject)
        } catch (e: Exception) {
            // TODO: add custom token exceptions
            throw BadRequestException("Token")
        }
    }
}