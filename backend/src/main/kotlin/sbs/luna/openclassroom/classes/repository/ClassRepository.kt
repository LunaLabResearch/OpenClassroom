package sbs.luna.openclassroom.classes.repository

import sbs.luna.openclassroom.classes.entity.Class
import org.springframework.data.repository.CrudRepository

interface ClassRepository : CrudRepository<Class, Long> {
}