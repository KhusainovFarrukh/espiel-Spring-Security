package kh.farrukh.espielspringsecurity.common.exception.custom_exceptions

import kh.farrukh.espielspringsecurity.common.exception.ApiException
import kh.farrukh.espielspringsecurity.common.exception.MESSAGE_ID_EXCEPTION_RESOURCE_NOT_FOUND
import org.springframework.http.HttpStatus

class ResourceNotFoundException(
    resourceName: String,
    fieldName: String,
    fieldValue: Any
) : ApiException(
    "$resourceName not found with $fieldName : '$fieldValue'",
    HttpStatus.NOT_FOUND,
    MESSAGE_ID_EXCEPTION_RESOURCE_NOT_FOUND,
    arrayOf(resourceName, fieldName, fieldValue)
)