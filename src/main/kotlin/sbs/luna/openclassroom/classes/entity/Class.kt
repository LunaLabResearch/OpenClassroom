package sbs.luna.openclassroom.classes.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "classes")
class Class(
    @Column(nullable = false, length = 100)
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    val subject: Subject? = null,

    @Column(name = "start_date")
    val startDate: LocalDate? = null,

    @Column(name = "end_date")
    val endDate: LocalDate? = null,

    @Column(length = 500)
    val description: String? = null,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)