package sbs.luna.openclassroom.auth.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService(
    @Value("\${security.jwt.secret-key}") private val secretKey: String,
    @Value("\${security.jwt.expiration-time}") private val jwtExpiration: Long
) {

    fun extractUsername(token: String): String? = extractClaim(token, Claims::getSubject)

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T = claimsResolver(extractAllClaims(token))

    fun generateToken(userDetails: UserDetails): String = generateToken(emptyMap(), userDetails)

    fun generateToken(
        extraClaims: Map<String, Any> = emptyMap(),
        userDetails: UserDetails
    ): String = buildToken(extraClaims, userDetails, jwtExpiration)

    fun getExpirationTime() = jwtExpiration

    private fun buildToken(
        extraClaims: Map<String, Any>,
        userDetails: UserDetails,
        expiration: Long
    ) = Jwts.builder()
        .claims(extraClaims)
        .subject(userDetails.username)
        .issuedAt(Date(System.currentTimeMillis()))
        .expiration(Date(System.currentTimeMillis() + expiration))
        .signWith(getSignInKey(), Jwts.SIG.HS512)
        .compact()

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean =
        !isTokenExpired(token) && (extractUsername(token) == userDetails.username)

    private fun isTokenExpired(token: String): Boolean = extractExpiration(token).before(Date())

    private fun extractExpiration(token: String): Date = extractClaim(token, Claims::getExpiration)

    fun extractAllClaims(token: String): Claims =
        Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).payload

    private fun getSignInKey(): SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
}