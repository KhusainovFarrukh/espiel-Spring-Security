package kh.farrukh.espielspringsecurity.lesson

import com.fasterxml.jackson.annotation.JsonProperty

class LessonRequestDTO(
    val title: String,
    val content: String,
    @JsonProperty("course_id")
    val courseId: Long
)