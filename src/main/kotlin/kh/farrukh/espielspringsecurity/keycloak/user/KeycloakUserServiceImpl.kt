package kh.farrukh.espielspringsecurity.keycloak.user

import kh.farrukh.espielspringsecurity.keycloak.util.handleStatus
import org.keycloak.admin.client.CreatedResponseUtil
import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class KeycloakUserServiceImpl(
    private val keycloak: Keycloak,
    @Value("\${keycloak.realm}") private val realmName: String
) : KeycloakUserService {

    override fun createUser(userRepresentation: UserRepresentation): String {
        val usersResource = keycloak.realm(realmName).users()
        val response = usersResource.create(userRepresentation)
        response.handleStatus()
        return CreatedResponseUtil.getCreatedId(response)
    }
}