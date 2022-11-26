package kh.farrukh.espielspringsecurity.user

import kh.farrukh.espielspringsecurity.common.exception.custom_exceptions.BadRequestException
import kh.farrukh.espielspringsecurity.common.exception.custom_exceptions.DuplicateResourceException
import kh.farrukh.espielspringsecurity.common.exception.custom_exceptions.ResourceNotFoundException
import kh.farrukh.espielspringsecurity.common.paging.PagingResponse
import kh.farrukh.espielspringsecurity.common.paging.toPagingResponse
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun getAllUsers(page: Int, pageSize: Int): PagingResponse<UserResponseDTO> {
        return userRepository.findAll(PageRequest.of(page - 1, pageSize))
            .map { user -> user.toUserResponseDTO() }
            .toPagingResponse()
    }

    override fun getUserById(id: Long): UserResponseDTO {
        return userRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("User", "id", id) }
            .toUserResponseDTO()
    }

    override fun createUser(userRequestDTO: UserRequestDTO): UserResponseDTO {
        if (userRepository.existsByUsername(userRequestDTO.username)) {
            throw DuplicateResourceException("User", "username", userRequestDTO.username)
        }

        return userRepository.save(userRequestDTO.toAppUser())
            .toUserResponseDTO()
    }

    override fun updateUser(id: Long, userRequestDTO: UserRequestDTO): UserResponseDTO {
        val user = userRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("User", "id", id) }

        if (userRequestDTO.username != user.username && userRepository.existsByUsername(userRequestDTO.username)) {
            throw DuplicateResourceException("User", "title", userRequestDTO.username)
        }

        user.username = userRequestDTO.username

        return userRepository.save(user)
            .toUserResponseDTO()
    }

    override fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) throw ResourceNotFoundException("User", "id", id)
        userRepository.deleteById(id)
    }

    override fun updatePassword(id: Long, updatePasswordRequestDTO: UpdatePasswordRequestDTO): UserResponseDTO {
        val user = userRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("User", "id", id) }

        if (user.password != updatePasswordRequestDTO.password)
            throw BadRequestException("Password")

        user.password = updatePasswordRequestDTO.newPassword

        return userRepository.save(user)
            .toUserResponseDTO()
    }
}