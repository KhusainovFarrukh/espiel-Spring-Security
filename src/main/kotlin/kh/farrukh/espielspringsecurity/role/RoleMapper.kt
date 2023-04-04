package kh.farrukh.espielspringsecurity.role

import org.keycloak.representations.idm.RoleRepresentation
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingConstants.ComponentModel.SPRING

@Mapper(componentModel = SPRING)
abstract class RoleMapper {

    abstract fun toRoleRepresentation(roleRequestDTO: RoleRequestDTO): RoleRepresentation

    @Mapping(source = "id", target = "keycloakId")
    abstract fun toRoleResponseDTO(roleRepresentation: RoleRepresentation): RoleResponseDTO
}