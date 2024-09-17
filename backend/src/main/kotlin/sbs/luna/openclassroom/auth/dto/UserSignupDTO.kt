package sbs.luna.openclassroom.auth.dto

class UserSignupDTO(val email: String, val password: String, val roles: Set<String>)
