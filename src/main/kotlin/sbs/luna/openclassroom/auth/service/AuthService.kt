package sbs.luna.openclassroom.auth.service

import sbs.luna.openclassroom.auth.entity.User
import sbs.luna.openclassroom.auth.repository.UserRepository
import sbs.luna.openclassroom.auth.dto.UserLoginDTO
import sbs.luna.openclassroom.auth.dto.UserSignupDTO
import sbs.luna.openclassroom.auth.repository.RoleRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val roleRepository: RoleRepository
) {
    fun signup(userPayload: UserSignupDTO): User {
        val roles = userPayload.roles.mapNotNull { roleRepository.findByName(it) }.toSet()

        if (roles.isEmpty()) throw IllegalArgumentException("At least one valid role must be provided")

        return userRepository.save(
            User(
                userPayload.email, passwordEncoder.encode(userPayload.password), roles
            )
        )
    }

    fun authenticate(userPayload: UserLoginDTO): User {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(userPayload.email, userPayload.password))

        return userRepository.findByEmail(userPayload.email) ?: throw UsernameNotFoundException("User not found")
    }
}