package sbs.luna.openclassroom

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Encoders
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class OpenclassroomApplication

fun main(args: Array<String>) {
    val key = Jwts.SIG.HS512.key().build()
    val secretString = Encoders.BASE64.encode(key.encoded)
    println("Generated secret: $secretString")
    runApplication<OpenclassroomApplication>(*args)
}
