package sbs.luna.openclassroom.auth.entity

import jakarta.persistence.*


@Entity
@Table(name = "roles")
class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private val id: Long? = null,

    @Column(nullable = false, length = 64)
    private val name: String,
) {
    fun getRoleId() = id
    fun getName() = name
}
