package kh.farrukh.espielspringsecurity.role

import kh.farrukh.espielspringsecurity.keycloak.role.KeycloakRoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(
    private val keycloakRoleService: KeycloakRoleService,
    private val roleMapper: RoleMapper
) : RoleService {

    override fun createRole(roleRequestDTO: RoleRequestDTO) {
        keycloakRoleService.createRole(roleMapper.toRoleRepresentation(roleRequestDTO))
    }

    override fun getAllRolesList(): List<RoleResponseDTO> {
        return keycloakRoleService.getAllRolesList().map { roleMapper.toRoleResponseDTO(it) }
    }

    override fun deleteRole(name: String) {
        keycloakRoleService.deleteRole(name)
    }
}