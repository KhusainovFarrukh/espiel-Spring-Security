package kh.farrukh.espielspringsecurity.lesson

import kh.farrukh.espielspringsecurity.course.Course
import javax.persistence.*

@Entity
class Lesson(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_id_generator")
    @SequenceGenerator(name = "lesson_id_generator", sequenceName = "lesson_id_seq")
    val id: Long?,

    @Column(nullable = false, unique = true)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @ManyToOne(optional = false)
    var course: Course
)