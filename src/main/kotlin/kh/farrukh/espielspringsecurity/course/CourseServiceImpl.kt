package kh.farrukh.espielspringsecurity.course

import kh.farrukh.espielspringsecurity.common.exception.custom_exceptions.DuplicateResourceException
import kh.farrukh.espielspringsecurity.common.exception.custom_exceptions.ResourceNotFoundException
import kh.farrukh.espielspringsecurity.common.paging.PagingResponse
import kh.farrukh.espielspringsecurity.common.paging.toPagingResponse
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository
) : CourseService {

    override fun getAllCourses(page: Int, pageSize: Int): PagingResponse<CourseResponseDTO> {
        return courseRepository.findAll(PageRequest.of(page - 1, pageSize))
            .map { course -> course.toCourseResponseDTO() }
            .toPagingResponse()
    }

    override fun getCourseById(id: Long): CourseResponseDTO {
        return courseRepository.findById(id)
            .orElseThrow { throw ResourceNotFoundException("Course", "id", id) }
            .toCourseResponseDTO()
    }

    override fun createCourse(courseRequestDTO: CourseRequestDTO): CourseResponseDTO {
        if (courseRepository.existsByTitle(courseRequestDTO.title)) {
            throw DuplicateResourceException("Course", "title", courseRequestDTO.title)
        }

        return courseRepository.save(courseRequestDTO.toCourse())
            .toCourseResponseDTO()
    }

    override fun updateCourse(id: Long, courseRequestDTO: CourseRequestDTO): CourseResponseDTO {
        val course = courseRepository.findById(id)
            .orElseThrow { throw ResourceNotFoundException("Course", "id", id) }

        if (courseRequestDTO.title != course.title && courseRepository.existsByTitle(courseRequestDTO.title)) {
            throw DuplicateResourceException("Course", "title", courseRequestDTO.title)
        }

        course.title = courseRequestDTO.title
        course.description = courseRequestDTO.description

        return courseRepository.save(course)
            .toCourseResponseDTO()
    }

    override fun deleteCourse(id: Long) {
        if (!courseRepository.existsById(id)) throw ResourceNotFoundException("Course", "id", id)
        courseRepository.deleteById(id)
    }
}