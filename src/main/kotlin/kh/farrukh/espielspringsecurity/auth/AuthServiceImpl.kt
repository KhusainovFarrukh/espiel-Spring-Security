package kh.farrukh.espielspringsecurity.auth

import kh.farrukh.espielspringsecurity.keycloak.auth.KeycloakAuthService
import kh.farrukh.espielspringsecurity.keycloak.user.KeycloakUserService
import kh.farrukh.espielspringsecurity.user.UserService
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val keycloakAuthService: KeycloakAuthService,
    private val keycloakUserService: KeycloakUserService,
    private val userService: UserService,
    private val authMapper: AuthMapper,
) : AuthService {

    override fun login(loginRequestDTO: LoginRequestDTO): TokenResponseDTO {
        return authMapper.toTokenResponseDTO(
            keycloakAuthService.login(loginRequestDTO.username, loginRequestDTO.password)
        )
    }

    override fun register(registerRequestDTO: RegisterRequestDTO): TokenResponseDTO {
        val keycloakId = keycloakUserService.createUser(authMapper.toUserRepresentation(registerRequestDTO))
        val userRequestDTO = authMapper.toUserRequestDTO(registerRequestDTO)
        userRequestDTO.keycloakId = keycloakId
        userService.createUser(userRequestDTO)
        return authMapper.toTokenResponseDTO(
            keycloakAuthService.login(registerRequestDTO.username, registerRequestDTO.password)
        )
    }
}