package kh.farrukh.espielspringsecurity.common.exception.custom_exceptions

import kh.farrukh.espielspringsecurity.common.exception.ApiException
import kh.farrukh.espielspringsecurity.common.exception.MESSAGE_ID_EXCEPTION_DUPLICATE_RESOURCE
import org.springframework.http.HttpStatus

class DuplicateResourceException(
    resourceName: String,
    fieldName: String,
    fieldValue: Any
) : ApiException(
    "$resourceName is   already exists with $fieldName '${fieldValue}'",
    HttpStatus.NOT_FOUND,
    MESSAGE_ID_EXCEPTION_DUPLICATE_RESOURCE,
    arrayOf(resourceName, fieldName, fieldValue)
)