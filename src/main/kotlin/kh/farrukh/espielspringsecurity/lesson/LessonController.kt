package kh.farrukh.espielspringsecurity.lesson

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(API_LESSON_BASE_URL)
class LessonController(private val lessonService: LessonService) {

    @GetMapping
    fun getLessons(
        @RequestParam(
            name = API_LESSON_PARAM_COURSE_ID,
            required = false
        ) courseId: Long?,
        @RequestParam(
            name = API_LESSON_PARAM_PAGE,
            defaultValue = API_LESSON_DEFAULT_VALUE_PAGE
        ) page: Int,
        @RequestParam(
            name = API_LESSON_PARAM_PAGE_SIZE,
            defaultValue = API_LESSON_DEFAULT_VALUE_PAGE_SIZE
        ) pageSize: Int
    ) = ResponseEntity.ok(lessonService.getLessons(courseId, page, pageSize))

    @GetMapping(API_LESSON_PREFIX_ID)
    fun getLessonById(
        @PathVariable(API_LESSON_PARAM_ID) id: Long
    ) = ResponseEntity.ok(lessonService.getLessonById(id))

    @PostMapping
    fun createLesson(
        @RequestBody lessonRequestDTO: LessonRequestDTO
    ) = ResponseEntity.status(HttpStatus.CREATED).body(lessonService.createLesson(lessonRequestDTO))

    @PutMapping(API_LESSON_PREFIX_ID)
    fun updateLesson(
        @PathVariable(API_LESSON_PARAM_ID) id: Long,
        @RequestBody lessonRequestDTO: LessonRequestDTO
    ) = ResponseEntity.ok(lessonService.updateLesson(id, lessonRequestDTO))

    @DeleteMapping(API_LESSON_PREFIX_ID)
    fun deleteLesson(
        @PathVariable(API_LESSON_PARAM_ID) id: Long
    ): ResponseEntity<Void> {
        lessonService.deleteLesson(id)
        return ResponseEntity.noContent().build()
    }
}