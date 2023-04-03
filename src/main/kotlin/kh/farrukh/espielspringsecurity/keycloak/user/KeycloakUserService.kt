package kh.farrukh.espielspringsecurity.keycloak.user

import org.keycloak.representations.idm.UserRepresentation

interface KeycloakUserService {

    fun createUser(userRepresentation: UserRepresentation): String
}