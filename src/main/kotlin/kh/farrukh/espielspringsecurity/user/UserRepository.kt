package kh.farrukh.espielspringsecurity.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<AppUser, Long> {

    fun existsByUsername(username: String): Boolean

    fun findByUsername(username: String): Optional<AppUser>
}