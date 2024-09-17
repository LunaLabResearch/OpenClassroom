package sbs.luna.openclassroom.classes.controller

import sbs.luna.openclassroom.classes.dto.NewSubjectDTO
import sbs.luna.openclassroom.classes.entity.Subject
import sbs.luna.openclassroom.classes.repository.SubjectRepository
import org.springframework.web.bind.annotation.*

@RequestMapping("/subjects")
@RestController
class SubjectController(private val subjectRepository: SubjectRepository) {

    @GetMapping("/")
    fun listSubjects() = subjectRepository.findAll().toList()

    @PostMapping("/")
    fun createSubject(@RequestBody subject: NewSubjectDTO): Subject = subjectRepository.save(
        Subject(subject.name)
    )

    @DeleteMapping("/{subjectId}")
    fun deleteSubject(@PathVariable subjectId: Long) = subjectRepository.deleteById(subjectId)
}