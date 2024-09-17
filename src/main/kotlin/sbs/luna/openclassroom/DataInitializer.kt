package sbs.luna.openclassroom

import sbs.luna.openclassroom.auth.entity.Role
import sbs.luna.openclassroom.auth.repository.RoleRepository
import jakarta.transaction.Transactional
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

// TODO: Re-do this with migrations eventually
@Component
class DataInitializer(private val roleRepository: RoleRepository) : ApplicationRunner {

    @Transactional
    override fun run(args: ApplicationArguments) {
        initRoles()
    }

    private fun initRoles() {
        val roles = listOf("ADMIN", "TEACHER", "STUDENT")
        roles.forEach { roleName ->
            if (roleRepository.findByName(roleName) == null) {
                roleRepository.save(Role(name = roleName))
            }
        }
    }
}