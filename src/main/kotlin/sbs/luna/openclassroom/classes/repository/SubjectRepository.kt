package sbs.luna.openclassroom.classes.repository

import sbs.luna.openclassroom.classes.entity.Subject
import org.springframework.data.repository.CrudRepository

interface SubjectRepository : CrudRepository<Subject, Long> {
}