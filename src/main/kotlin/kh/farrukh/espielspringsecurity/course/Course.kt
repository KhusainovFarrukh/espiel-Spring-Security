package kh.farrukh.espielspringsecurity.course

import javax.persistence.*

@Entity
class Course(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_id_generator", sequenceName = "course_id_seq")
    val id: Long?,

    @Column(nullable = false)
    var title: String,

    var description: String?
)
