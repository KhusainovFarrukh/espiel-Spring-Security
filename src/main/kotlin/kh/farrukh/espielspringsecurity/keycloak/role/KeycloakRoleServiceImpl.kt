package kh.farrukh.espielspringsecurity.keycloak.role

import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.RoleRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class KeycloakRoleServiceImpl(
    keycloak: Keycloak,
    @Value("\${keycloak.realm}") private val realmName: String
) : KeycloakRoleService {

    private val rolesResource = keycloak.realm(realmName).roles()

    override fun createRole(roleRepresentation: RoleRepresentation) {
        rolesResource.create(roleRepresentation)
    }

    override fun getAllRolesList(): List<RoleRepresentation> {
        return rolesResource.list()
    }

    override fun deleteRole(name: String) {
        rolesResource.deleteRole(name)
    }
}