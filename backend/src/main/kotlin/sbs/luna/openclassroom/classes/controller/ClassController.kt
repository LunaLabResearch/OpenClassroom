package sbs.luna.openclassroom.classes.controller

import org.springframework.web.bind.annotation.GetMapping
import sbs.luna.openclassroom.classes.dto.NewClassDTO
import sbs.luna.openclassroom.classes.entity.Class
import sbs.luna.openclassroom.classes.repository.ClassRepository
import sbs.luna.openclassroom.classes.repository.SubjectRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.optionals.getOrNull

@RequestMapping("/classes")
@RestController
class ClassController(
    private val classRepository: ClassRepository,
    private val subjectRepository: SubjectRepository
) {
    @GetMapping("/")
    fun getClasses() = classRepository.findAll().toList()

    @PostMapping("/")
    fun createClass(@RequestBody classToSave: NewClassDTO): Class {
        val subject = classToSave.subjectId?.let { subjectRepository.findById(it) }

        return classRepository.save(
            Class(
                classToSave.name,
                subject?.getOrNull(),
                classToSave.startDate,
                classToSave.endDate,
                classToSave.description
            )
        )
    }
}