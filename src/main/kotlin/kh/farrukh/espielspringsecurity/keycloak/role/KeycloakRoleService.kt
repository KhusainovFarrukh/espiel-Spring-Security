package kh.farrukh.espielspringsecurity.keycloak.role

import org.keycloak.representations.idm.RoleRepresentation

interface KeycloakRoleService {

    fun createRole(roleRepresentation: RoleRepresentation)

    fun getAllRolesList(): List<RoleRepresentation>

    fun deleteRole(name: String)
}