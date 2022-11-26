package kh.farrukh.espielspringsecurity.course

import kh.farrukh.espielspringsecurity.common.paging.PagingResponse

interface CourseService {
    fun getAllCourses(page: Int, pageSize: Int): PagingResponse<CourseResponseDTO>
    fun getCourseById(id: Long): CourseResponseDTO
    fun createCourse(courseRequestDTO: CourseRequestDTO): CourseResponseDTO
    fun updateCourse(id: Long, courseRequestDTO: CourseRequestDTO): CourseResponseDTO
    fun deleteCourse(id: Long)
}