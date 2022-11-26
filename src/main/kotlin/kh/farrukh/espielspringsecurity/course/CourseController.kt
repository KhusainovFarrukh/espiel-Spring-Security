package kh.farrukh.espielspringsecurity.course

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(API_COURSE_BASE_URL)
class CourseController(private val courseService: CourseService) {

    @GetMapping
    fun getAllCourses(
        @RequestParam(
            name = API_COURSE_PARAM_PAGE,
            defaultValue = API_COURSE_DEFAULT_VALUE_PAGE
        ) page: Int,
        @RequestParam(
            name = API_COURSE_PARAM_PAGE_SIZE,
            defaultValue = API_COURSE_DEFAULT_VALUE_PAGE_SIZE
        ) pageSize: Int
    ) = ResponseEntity.ok(courseService.getAllCourses(page, pageSize))

    @GetMapping(API_COURSE_PREFIX_ID)
    fun getCourseById(
        @PathVariable(API_COURSE_PARAM_ID) id: Long
    ) = ResponseEntity.ok(courseService.getCourseById(id))

    @PostMapping
    fun createCourse(
        @RequestBody courseRequestDTO: CourseRequestDTO
    ) = ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseRequestDTO))

    @PutMapping(API_COURSE_PREFIX_ID)
    fun updateCourse(
        @PathVariable(API_COURSE_PARAM_ID) id: Long,
        @RequestBody courseRequestDTO: CourseRequestDTO
    ) = ResponseEntity.ok(courseService.updateCourse(id, courseRequestDTO))

    @DeleteMapping(API_COURSE_PREFIX_ID)
    fun deleteCourse(
        @PathVariable(API_COURSE_PARAM_ID) id: Long
    ): ResponseEntity<Void> {
        courseService.deleteCourse(id)
        return ResponseEntity.noContent().build()
    }
}