package kh.farrukh.espielspringsecurity.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<AppUser, Long> {

    fun existsByUsername(username: String): Boolean
}