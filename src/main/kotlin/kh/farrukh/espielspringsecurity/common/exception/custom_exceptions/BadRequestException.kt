package kh.farrukh.espielspringsecurity.common.exception.custom_exceptions

import kh.farrukh.espielspringsecurity.common.exception.ApiException
import kh.farrukh.espielspringsecurity.common.exception.MESSAGE_ID_EXCEPTION_BAD_REQUEST
import org.springframework.http.HttpStatus

class BadRequestException(
    invalidValue: String
) : ApiException(
    "$invalidValue is invalid",
    HttpStatus.BAD_REQUEST,
    MESSAGE_ID_EXCEPTION_BAD_REQUEST,
    arrayOf(invalidValue)
)