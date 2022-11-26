package kh.farrukh.espielspringsecurity.lesson

import kh.farrukh.espielspringsecurity.course.CourseResponseDTO

class LessonResponseDTO(
    val id: Long,
    val title: String,
    val content: String,
    val course: CourseResponseDTO
)