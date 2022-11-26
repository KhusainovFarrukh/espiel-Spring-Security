package kh.farrukh.espielspringsecurity.lesson

import kh.farrukh.espielspringsecurity.common.exception.custom_exceptions.DuplicateResourceException
import kh.farrukh.espielspringsecurity.common.exception.custom_exceptions.ResourceNotFoundException
import kh.farrukh.espielspringsecurity.common.paging.PagingResponse
import kh.farrukh.espielspringsecurity.common.paging.toPagingResponse
import kh.farrukh.espielspringsecurity.course.CourseRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LessonServiceImpl(
    private val lessonRepository: LessonRepository,
    private val courseRepository: CourseRepository
) : LessonService {

    override fun getLessons(courseId: Long?, page: Int, pageSize: Int): PagingResponse<LessonResponseDTO> {
        val pageable = PageRequest.of(page - 1, pageSize)
        val lessons =
            if (courseId == null) lessonRepository.findAll(pageable)
            else lessonRepository.findAllByCourse_Id(courseId, pageable)

        return lessons.map { lesson -> lesson.toLessonResponseDTO() }
            .toPagingResponse()
    }

    override fun getLessonById(id: Long): LessonResponseDTO {
        return lessonRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Lesson", "id", id) }
            .toLessonResponseDTO()
    }

    override fun createLesson(lessonRequestDTO: LessonRequestDTO): LessonResponseDTO {
        if (lessonRepository.existsByTitle(lessonRequestDTO.title)) {
            throw DuplicateResourceException("Lesson", "title", lessonRequestDTO.title)
        }

        return lessonRepository.save(lessonRequestDTO.toCourse(courseRepository))
            .toLessonResponseDTO()
    }

    override fun updateLesson(id: Long, lessonRequestDTO: LessonRequestDTO): LessonResponseDTO {
        val lesson = lessonRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Lesson", "id", id) }

        if (lessonRequestDTO.title != lesson.title && lessonRepository.existsByTitle(lessonRequestDTO.title)) {
            throw DuplicateResourceException("Course", "title", lessonRequestDTO.title)
        }

        if (lessonRequestDTO.courseId != lesson.course.id) {
            val newCourse = courseRepository.findById(lessonRequestDTO.courseId)
                .orElseThrow { ResourceNotFoundException("Course", "id", lessonRequestDTO.courseId) }
            lesson.course = newCourse
        }

        lesson.title = lessonRequestDTO.title
        lesson.content = lessonRequestDTO.content

        return lessonRepository.save(lesson)
            .toLessonResponseDTO()
    }

    override fun deleteLesson(id: Long) {
        if (!lessonRepository.existsById(id)) throw ResourceNotFoundException("Lesson", "id", id)
        lessonRepository.deleteById(id)
    }
}