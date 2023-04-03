package kh.farrukh.espielspringsecurity.auth

interface AuthService {

    fun login(loginRequestDTO: LoginRequestDTO): TokenResponseDTO

    fun register(registerRequestDTO: RegisterRequestDTO): TokenResponseDTO
}