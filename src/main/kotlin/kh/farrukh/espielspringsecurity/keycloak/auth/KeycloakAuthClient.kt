package kh.farrukh.espielspringsecurity.keycloak.auth

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "keycloak-auth", url = "\${keycloak.auth-server-url}")
interface KeycloakAuthClient {

    @PostMapping(value = [KEYCLOAK_LOGIN_URL], consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun login(
        //TODO use realm name from application.yaml
        @PathVariable realm: String,
        @RequestBody requestBody: Map<String, Any>
    ): KeycloakTokenResponseDTO
}