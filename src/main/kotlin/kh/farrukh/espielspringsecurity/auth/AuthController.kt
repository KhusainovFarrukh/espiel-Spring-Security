package kh.farrukh.espielspringsecurity.auth

import kh.farrukh.espielspringsecurity.course.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(private val authService: AuthService) {

    @PostMapping(API_LOGIN_URL)
    fun login(
        @RequestBody loginRequestDTO: LoginRequestDTO
    ) = ResponseEntity.ok(authService.login(loginRequestDTO))

    @PostMapping(API_REGISTER_URL)
    fun register(
        @RequestBody registerRequestDTO: RegisterRequestDTO
    ) = ResponseEntity.ok(authService.register(registerRequestDTO))
}
