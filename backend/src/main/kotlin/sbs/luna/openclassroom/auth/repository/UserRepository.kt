package sbs.luna.openclassroom.auth.repository

import sbs.luna.openclassroom.auth.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(@Param("email") email: String): User?
}