package kh.farrukh.espielspringsecurity.role

interface RoleService {

    //TODO make it return response
    fun createRole(roleRequestDTO: RoleRequestDTO)

    //TODO add pagination
    fun getAllRolesList(): List<RoleResponseDTO>

    fun deleteRole(name: String)
}