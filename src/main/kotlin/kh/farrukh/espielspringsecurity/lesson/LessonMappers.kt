package kh.farrukh.espielspringsecurity.lesson

import kh.farrukh.espielspringsecurity.common.exception.custom_exceptions.ResourceNotFoundException
import kh.farrukh.espielspringsecurity.course.CourseRepository
import kh.farrukh.espielspringsecurity.course.toCourseResponseDTO

fun Lesson.toLessonResponseDTO() = LessonResponseDTO(
    id = id ?: 0,
    title = title,
    content = content,
    course = course.toCourseResponseDTO()
)

fun LessonRequestDTO.toCourse(courseRepository: CourseRepository) = Lesson(
    id = null,
    title = title,
    content = content,
    course = courseRepository.findById(courseId)
        .orElseThrow { ResourceNotFoundException("Course", "id", courseId) }
)