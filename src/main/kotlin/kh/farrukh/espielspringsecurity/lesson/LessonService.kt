package kh.farrukh.espielspringsecurity.lesson

import kh.farrukh.espielspringsecurity.common.paging.PagingResponse

interface LessonService {
    fun getLessons(courseId: Long?, page: Int, pageSize: Int): PagingResponse<LessonResponseDTO>
    fun getLessonById(id: Long): LessonResponseDTO
    fun createLesson(lessonRequestDTO: LessonRequestDTO): LessonResponseDTO
    fun updateLesson(id: Long, lessonRequestDTO: LessonRequestDTO): LessonResponseDTO
    fun deleteLesson(id: Long)
}