package kh.farrukh.espielspringsecurity.role

const val API_ROLE_BASE_URL = "/api/roles"
const val API_ROLE_PARAM_ID = "id"
const val API_ROLE_PARAM_NAME = "name"
const val API_ROLE_PREFIX_ID = "/{$API_ROLE_PARAM_ID}"
const val API_ROLE_PREFIX_NAME = "/{$API_ROLE_PARAM_NAME}"
const val API_ROLE_PREFIX_PASSWORD = "$API_ROLE_PREFIX_ID/password"

const val API_ROLE_PARAM_PAGE = "page"
const val API_ROLE_PARAM_PAGE_SIZE = "page_size"

const val API_ROLE_DEFAULT_VALUE_PAGE = "1"
const val API_ROLE_DEFAULT_VALUE_PAGE_SIZE = "25"