package kh.farrukh.espielspringsecurity.lesson

import kh.farrukh.espielspringsecurity.course.Course
import javax.persistence.*

@Entity
class Lesson(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "lesson_id_generator", sequenceName = "lesson_id_seq")
    val id: Long?,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @ManyToOne(optional = false)
    var course: Course
)