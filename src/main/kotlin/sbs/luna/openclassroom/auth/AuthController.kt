package sbs.luna.openclassroom.auth

import sbs.luna.openclassroom.auth.dto.LoginResponseDTO
import sbs.luna.openclassroom.auth.dto.UserLoginDTO
import sbs.luna.openclassroom.auth.dto.UserSignupDTO
import sbs.luna.openclassroom.auth.entity.User
import sbs.luna.openclassroom.auth.service.AuthService
import sbs.luna.openclassroom.auth.service.JwtService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthController(
    private val jwtService: JwtService,
    private val authService: AuthService,
) {

    @PostMapping("/signup")
    fun signup(@RequestBody userPayload: UserSignupDTO) = ResponseEntity.ok(
        authService.signup(userPayload)
    )

    @PostMapping("/login")
    fun login(@RequestBody userPayload: UserLoginDTO): ResponseEntity<LoginResponseDTO> {
        val authenticatedUser: User = authService.authenticate(userPayload)
        val jwt: String = jwtService.generateToken(authenticatedUser)

        return ResponseEntity.ok(
            LoginResponseDTO(jwt, jwtService.getExpirationTime())
        )
    }
}