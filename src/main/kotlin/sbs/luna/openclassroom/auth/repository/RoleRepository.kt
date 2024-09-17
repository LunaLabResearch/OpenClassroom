package sbs.luna.openclassroom.auth.repository

import sbs.luna.openclassroom.auth.entity.Role
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface RoleRepository : CrudRepository<Role, Long> {
    fun findByName(@Param("name") name: String): Role?
}