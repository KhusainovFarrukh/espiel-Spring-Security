package kh.farrukh.espielspringsecurity.auth

import kh.farrukh.espielspringsecurity.keycloak.KeycloakTokenResponseDTO
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants.ComponentModel.SPRING

@Mapper(componentModel = SPRING)
interface AuthMapper {

    fun toTokenResponseDTO(keycloakTokenResponseDTO: KeycloakTokenResponseDTO): TokenResponseDTO
}