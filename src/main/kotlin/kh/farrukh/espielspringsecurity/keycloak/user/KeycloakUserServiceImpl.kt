package kh.farrukh.espielspringsecurity.keycloak.user

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
        val userResource = keycloak.realm(realmName).users()
        val response = userResource.create(userRepresentation)
        if (response.status < 200 || response.status >= 300) {
            throw RuntimeException(response.status.toString())
        }
        return CreatedResponseUtil.getCreatedId(response)
    }
}