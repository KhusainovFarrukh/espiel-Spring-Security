package kh.farrukh.espielspringsecurity.keycloak.auth

//TODO use in @RequestParam
const val KEYCLOAK_REALM_PARAM = "{realm}"
const val KEYCLOAK_LOGIN_URL = "/realms/$KEYCLOAK_REALM_PARAM/protocol/openid-connect/token"
