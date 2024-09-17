package sbs.luna.openclassroom.auth.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, unique = true, length = 100)
    private val email: String,
    @Column(nullable = false)
    private val passwordHash: String,
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    private var roles: Set<Role>,
    @Column(length = 100)
    private val displayName: String? = null,
    @CreationTimestamp
    private val createdAt: Instant? = null,
    @UpdateTimestamp
    private val lastUpdatedAt: Instant? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private val id: Long? = null
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> =
        roles.map { role -> SimpleGrantedAuthority(role.getName()) }

    override fun getPassword(): String {
        return passwordHash
    }

    override fun getUsername(): String {
        return email
    }
}