package kh.farrukh.espielspringsecurity.common.exception.custom_exceptions

import kh.farrukh.espielspringsecurity.common.exception.ApiException
import kh.farrukh.espielspringsecurity.common.exception.MESSAGE_ID_EXCEPTION_MISSING_TOKEN
import org.springframework.http.HttpStatus

class MissingTokenException : ApiException(
    "Token is missing",
    HttpStatus.BAD_REQUEST,
    MESSAGE_ID_EXCEPTION_MISSING_TOKEN
)