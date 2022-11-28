package kh.farrukh.espielspringsecurity.auth

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(API_AUTH_PREFIX)
class AuthController(private val authService: AuthService) {

    @PostMapping(API_AUTH_POSTFIX_REGISTER)
    fun register(
        @RequestBody registerRequestDTO: RegisterRequestDTO
    ) = ResponseEntity.ok(authService.register(registerRequestDTO))

    @PostMapping(API_AUTH_POSTFIX_LOGIN)
    fun login(
        @RequestBody loginRequestDTO: LoginRequestDTO
    ) = ResponseEntity.ok(authService.login(loginRequestDTO))

    @GetMapping(API_AUTH_POSTFIX_REFRESH_TOKEN)
    fun refreshToken(
        @RequestHeader(name = HttpHeaders.AUTHORIZATION) token: String
    ) = ResponseEntity.ok(authService.refreshToken(token))
}