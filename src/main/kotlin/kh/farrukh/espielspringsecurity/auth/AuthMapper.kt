package kh.farrukh.espielspringsecurity.auth

import kh.farrukh.espielspringsecurity.common.mapstruct.IgnoreMapstruct
import kh.farrukh.espielspringsecurity.keycloak.auth.KeycloakTokenResponseDTO
import kh.farrukh.espielspringsecurity.user.UserRequestDTO
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants.ComponentModel.SPRING

@Mapper(componentModel = SPRING)
abstract class AuthMapper {

    abstract fun toTokenResponseDTO(keycloakTokenResponseDTO: KeycloakTokenResponseDTO): TokenResponseDTO

    abstract fun toUserRequestDTO(registerRequestDTO: RegisterRequestDTO): UserRequestDTO

    @IgnoreMapstruct
    fun toUserRepresentation(registerRequestDTO: RegisterRequestDTO): UserRepresentation {
        val credentialRepresentation = CredentialRepresentation()
        credentialRepresentation.value = registerRequestDTO.password
        credentialRepresentation.type = CredentialRepresentation.PASSWORD
        credentialRepresentation.isTemporary = false

        val userRepresentation = UserRepresentation()
        userRepresentation.username = registerRequestDTO.username
        userRepresentation.isEnabled = true
        userRepresentation.credentials = listOf(credentialRepresentation)
        return userRepresentation
    }
}