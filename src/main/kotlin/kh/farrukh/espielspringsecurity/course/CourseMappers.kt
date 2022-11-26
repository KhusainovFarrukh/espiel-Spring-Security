package kh.farrukh.espielspringsecurity.course

fun Course.toCourseResponseDTO() = CourseResponseDTO(
    id = id ?: 0,
    title = title,
    description = description
)

fun CourseRequestDTO.toCourse() = Course(
    id = null,
    title = title,
    description = description
)