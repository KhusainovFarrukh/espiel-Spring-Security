package kh.farrukh.espielspringsecurity.common.exception

import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.ZonedDateTime
import java.util.*

@ControllerAdvice
class ApiExceptionHandler(private val messageSource: MessageSource) {

    @ExceptionHandler(ApiException::class)
    fun handleApiException(e: ApiException, locale: Locale): ResponseEntity<ErrorResponse> {
        e.printStackTrace()
        return ResponseEntity
            .status(e.httpStatus)
            .body(
                ErrorResponse(
                    messageSource.getMessage(e.messageId, e.messageArgs, locale),
                    e.httpStatus,
                    ZonedDateTime.now()
                )
            )
    }

    @ExceptionHandler(Exception::class)
    fun handleUnknownException(e: Exception, locale: Locale): ResponseEntity<ErrorResponse> {
        e.printStackTrace()
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ErrorResponse(
                    messageSource.getMessage(MESSAGE_ID_EXCEPTION_UNKNOWN, null, locale),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ZonedDateTime.now()
                )
            )
    }
}