package kh.farrukh.espielspringsecurity.role

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(API_ROLE_BASE_URL)
class RoleController(private val roleService: RoleService) {

    @GetMapping
    fun getAllRolesList() = ResponseEntity.ok(roleService.getAllRolesList())

    @PostMapping
    fun createUser(
        @RequestBody roleRequestDTO: RoleRequestDTO
    ) = ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleRequestDTO))

    @DeleteMapping(API_ROLE_PREFIX_NAME)
    fun deleteUser(
        @PathVariable(API_ROLE_PARAM_NAME) name: String
    ): ResponseEntity<Void> {
        roleService.deleteRole(name)
        return ResponseEntity.noContent().build()
    }
}