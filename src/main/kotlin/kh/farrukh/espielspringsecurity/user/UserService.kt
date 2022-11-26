package kh.farrukh.espielspringsecurity.user

import kh.farrukh.espielspringsecurity.common.paging.PagingResponse

interface UserService {
    fun getAllUsers(page: Int, pageSize: Int): PagingResponse<UserResponseDTO>
    fun getUserById(id: Long): UserResponseDTO
    fun createUser(userRequestDTO: UserRequestDTO): UserResponseDTO
    fun updateUser(id: Long, userRequestDTO: UserRequestDTO): UserResponseDTO
    fun deleteUser(id: Long)
    fun updatePassword(id: Long, updatePasswordRequestDTO: UpdatePasswordRequestDTO): UserResponseDTO
}