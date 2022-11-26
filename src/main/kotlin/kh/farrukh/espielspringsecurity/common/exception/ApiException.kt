package kh.farrukh.espielspringsecurity.common.exception

import org.springframework.http.HttpStatus

open class ApiException(
    message: String,
    val httpStatus: HttpStatus,
    val messageId: String,
    val messageArgs: Array<Any> = emptyArray()
) : Exception(message)