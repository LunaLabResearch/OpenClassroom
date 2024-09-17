package sbs.luna.openclassroom.classes.entity

import jakarta.persistence.*

@Entity
@Table(name = "subjects")
class Subject(
    @Column(nullable = false, length = 100, unique = true)
    val name: String,

    @OneToMany(mappedBy = "subject", cascade = [(CascadeType.DETACH)])
    val classes: List<Class> = listOf(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "subject_id")
    val id: Long? = null
)