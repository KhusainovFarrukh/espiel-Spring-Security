package kh.farrukh.espielspringsecurity.keycloak.util

import javax.ws.rs.core.Response

fun Response.handleStatus() {
    if (status < 200 || status >= 300) {
        throw RuntimeException(status.toString())
    }
}