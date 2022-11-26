package kh.farrukh.espielspringsecurity.common.exception

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus
import java.time.ZonedDateTime

class ErrorResponse(
    val message: String,
    @JsonProperty("http_status")
    val httpStatus: HttpStatus,
    val timestamp: ZonedDateTime,
    val errors: Map<String, Any> = emptyMap()
)