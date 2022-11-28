package kh.farrukh.espielspringsecurity.auth

interface AuthService {
    fun register(registerRequestDTO: RegisterRequestDTO): RegisterResponseDTO
    fun login(loginRequestDTO: LoginRequestDTO): TokenResponseDTO
    fun refreshToken(token: String): TokenResponseDTO
}