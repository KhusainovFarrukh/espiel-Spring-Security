package kh.farrukh.espielspringsecurity.user

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(API_USER_BASE_URL)
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(
        @RequestParam(
            name = API_USER_PARAM_PAGE,
            defaultValue = API_USER_DEFAULT_VALUE_PAGE
        ) page: Int,
        @RequestParam(
            name = API_USER_PARAM_PAGE_SIZE,
            defaultValue = API_USER_DEFAULT_VALUE_PAGE_SIZE
        ) pageSize: Int
    ) = ResponseEntity.ok(userService.getAllUsers(page, pageSize))

    @GetMapping(API_USER_POSTFIX_ID)
    fun getUserById(
        @PathVariable(API_USER_PARAM_ID) id: Long
    ) = ResponseEntity.ok(userService.getUserById(id))

    @PostMapping
    fun createUser(
        @RequestBody userRequestDTO: UserRequestDTO
    ) = ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDTO))

    @PutMapping(API_USER_POSTFIX_ID)
    fun updateUser(
        @PathVariable(API_USER_PARAM_ID) id: Long,
        @RequestBody userRequestDTO: UserRequestDTO
    ) = ResponseEntity.ok(userService.updateUser(id, userRequestDTO))

    @DeleteMapping(API_USER_POSTFIX_ID)
    fun deleteUser(
        @PathVariable(API_USER_PARAM_ID) id: Long
    ): ResponseEntity<Void> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping(API_USER_POSTFIX_PASSWORD)
    fun updatePassword(
        @PathVariable(API_USER_PARAM_ID) id: Long,
        @RequestBody updatePasswordRequestDTO: UpdatePasswordRequestDTO
    ) = ResponseEntity.ok(userService.updatePassword(id, updatePasswordRequestDTO))
}