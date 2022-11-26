package kh.farrukh.espielspringsecurity.user

import javax.persistence.*

@Entity
class AppUser(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq")
    val id: Long?,

    @Column(nullable = false, unique = true)
    var username: String,

    @Column(nullable = false)
    var password: String
)