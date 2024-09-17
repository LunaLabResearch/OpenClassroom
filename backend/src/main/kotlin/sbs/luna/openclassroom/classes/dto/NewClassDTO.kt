package sbs.luna.openclassroom.classes.dto

import sbs.luna.openclassroom.classes.entity.Subject
import java.time.LocalDate

class NewClassDTO(
    val name: String,
    val subjectId: Long? = null,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val description: String? = null
) {}