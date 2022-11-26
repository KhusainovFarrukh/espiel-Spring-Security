package kh.farrukh.espielspringsecurity.lesson

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface LessonRepository : JpaRepository<Lesson, Long> {

    fun existsByTitle(title: String): Boolean

    fun findAllByCourse_Id(courseId: Long?, pageable: Pageable): Page<Lesson>
}